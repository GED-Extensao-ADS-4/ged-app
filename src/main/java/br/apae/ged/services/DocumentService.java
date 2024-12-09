package br.apae.ged.services;

import br.apae.ged.dto.document.DocumentRequestDTO;
import br.apae.ged.dto.document.DocumentResponseDTO;
import br.apae.ged.dto.document.DocumentUploadResponseDTO;
import br.apae.ged.exceptions.NotFoundException;
import br.apae.ged.exceptions.ValidationException;
import br.apae.ged.models.Alunos;
import br.apae.ged.models.Document;
import br.apae.ged.repositories.AlunoRepository;
import br.apae.ged.repositories.DocumentRepository;
import br.apae.ged.repositories.specifications.DocumentSpecification;
import br.apae.ged.utils.AuthenticationUtil;
import br.apae.ged.utils.MultipartFileConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DocumentService {

    private final DocumentRepository documentRepository;
    private final AlunoRepository alunoRepository;

    public DocumentUploadResponseDTO save(DocumentRequestDTO document, Long alunoID) throws IOException {

        if (document.prevVersion() != null) {

            if (!Objects.equals(document.prevVersion().getAluno().getId(), alunoID)){
                throw new ValidationException("O aluno vinculado a este novo documento não é o mesmo da versão anterior");
            }

            var last = documentRepository.findById(document.prevVersion().getId());
            if (last.isPresent()) {
                var updated = last.get();
                updated.setIsLast(false);
                documentRepository.save(updated);
            }
        }

        Alunos aluno = alunoRepository.findById(alunoID).orElseThrow(() -> new NotFoundException("Aluno não encontrado"));

        String path = MultipartFileConverter.convertToFile(document.file(),
                document.nome().concat("-" + UUID.randomUUID()).concat(".").concat(document.tipoArquivo().toString())).getPath();

        Document doc = Document.builder()
                .titulo(document.nome())
                .tipoDocumento(document.tipoDocumento())
                .tipoArquivo(document.tipoArquivo())
                .uploadedBy(AuthenticationUtil.retriveAuthenticatedUser())
                .path(path)
                .isLast(true)
                .aluno(aluno)
                .dataUpload(LocalDateTime.now())
                .prevVersion(document.prevVersion() == null ? null : documentRepository.findById(document.prevVersion().getId())
                        .orElseThrow(() -> new NotFoundException("Documento atribuído como versão anterior não existe")))
                .build();

        documentRepository.save(doc);

        return new DocumentUploadResponseDTO(201, "Upload de documento efetuado com sucesso!");
    }

    public List<DocumentResponseDTO> list(Long alunoID, String titulo, String alunoNome) {

        var spec = Specification
                .where(DocumentSpecification.isLast())
                .and(DocumentSpecification.dateDesc())
                .and(DocumentSpecification.byAlunoId(alunoID)
                        .and(DocumentSpecification.byTitulo(titulo))
                        .and(DocumentSpecification.byAlunoNome(alunoNome)));

        return documentRepository.findAll(spec)
                .stream()
                .map(DocumentResponseDTO::fromEntity)
                .toList();
    }

    public List<DocumentResponseDTO> byID(Long id) {
        List<DocumentResponseDTO> resp = new ArrayList<>();
        getDocumentsRecursively(documentRepository.findById(id).orElseThrow(()
                -> new NotFoundException("documento não encontrado")), resp);

        return resp;
    }


    public Resource downloadFile(Long id) throws MalformedURLException {

        Document doc = documentRepository.findById(id).orElseThrow(() -> new NotFoundException("Documento não existe"));

        Path filePath = Paths.get(doc.getPath()).normalize();

        Resource resource = new UrlResource(filePath.toUri());

        if (!resource.exists() || !resource.isReadable()){
            throw new NotFoundException("Não foi possível baixar o arquivo");
        }

        doc.setDataDownload(LocalDateTime.now());
        doc.setDownloadedBy(AuthenticationUtil.retriveAuthenticatedUser());

        documentRepository.save(doc);

        return resource;
    }

    private void getDocumentsRecursively(Document document, List<DocumentResponseDTO> resp){
        if (resp.size() == 5) return;
        resp.add(DocumentResponseDTO.fromEntity(document));
        if (document.getPrevVersion() == null) return;
        var prev = document.getPrevVersion();
        getDocumentsRecursively(prev, resp);
    }
}