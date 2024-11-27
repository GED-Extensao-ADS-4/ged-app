package br.apae.ged.services;

import br.apae.ged.dto.DocumentRequestDTO;
import br.apae.ged.dto.DocumentResponseDTO;
import br.apae.ged.dto.DocumentResponseStatusDTO;
import br.apae.ged.exceptions.NotFoundException;
import br.apae.ged.models.Document;
import br.apae.ged.repositories.DocumentRepository;
import br.apae.ged.repositories.specifications.DocumentSpecification;
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
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DocumentService {

    private final DocumentRepository documentRepository;

    public DocumentResponseStatusDTO save(DocumentRequestDTO request) throws IOException {

        if (request.prevVersion() != null) {
            var last = documentRepository.findById(request.prevVersion().getId()).orElseThrow(() -> new RuntimeException("Documento não existe."));
            last.setIsLast(false);
            documentRepository.save(last);
        }
        String path = MultipartFileConverter.convertToFile(request.file(),
                request.nome().concat("-" + UUID.randomUUID()).concat(".").concat(request.tipoArquivo().toString().toLowerCase().trim())).getPath();

        Document document = Document.builder()
                .nome(request.nome())
                .tipoDocumento(request.tipoDocumento())
                .tipoArquivo(request.tipoArquivo())
                .path(path)
                .dataUpload(LocalDateTime.now())
                .uploadedBy("upado")
                .isLast(true)
                .prevVersion(request.prevVersion() == null ? null : documentRepository.findById(request.prevVersion().getId()).orElseThrow(() -> new RuntimeException("Documento não existe.")))
                .build();
        documentRepository.save(document);

        return new DocumentResponseStatusDTO(201, "Documento upado com sucesso.");
    }

    public List<DocumentResponseDTO> list(String nome, String downloadBy, String uploadedBy) {
        var spec = Specification
                .where(DocumentSpecification.hasNome(nome))
                .and(DocumentSpecification.downloadedBy(downloadBy))
                .and(DocumentSpecification.uploadedBy(uploadedBy));


        return documentRepository.findAll(spec)
                .stream()
                .map(DocumentResponseDTO::fromEntity)
                .toList();
    }

    public List<DocumentResponseDTO> byID(Long id) {
        List<DocumentResponseDTO> resp = new ArrayList<>();
        getDocumentsRescursively(documentRepository.findById(id).orElseThrow(()
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
        doc.setDownloadedBy("baixado");

        documentRepository.save(doc);

        return resource;
    }

    private void getDocumentsRescursively(Document document, List<DocumentResponseDTO> resp){
        if(resp.size() == 5) return;
        resp.add(DocumentResponseDTO.fromEntity(document));
        if (document.getPrevVersion() == null) return;
        var prev = document.getPrevVersion();
        getDocumentsRescursively(prev,resp);
    }
}





