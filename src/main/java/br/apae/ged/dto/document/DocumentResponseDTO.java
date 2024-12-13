package br.apae.ged.dto.document;

import br.apae.ged.dto.aluno.AlunoResponseDTO;
import br.apae.ged.dto.user.UserResponse;
import br.apae.ged.models.Document;
import br.apae.ged.models.enums.TipoArquivo;
import br.apae.ged.models.enums.TipoDocumento;

import java.time.LocalDateTime;

public record DocumentResponseDTO(
        Long id,
        String nome,
        TipoDocumento tipoDocumento,
        TipoArquivo tipoArquivo,
        String path,
        LocalDateTime dataUpload,
        LocalDateTime dataDownload,
        UserResponse downloadedBy,
        UserResponse uploadedBy,
        AlunoResponseDTO aluno,
        Boolean isLast
) {

    public static DocumentResponseDTO fromEntity(Document document){
        return new DocumentResponseDTO(
                document.getId(),
                document.getTitulo(),
                document.getTipoDocumento(),
                document.getTipoArquivo(),
                document.getPath(),
                document.getDataUpload(),
                document.getDataDownload(),
                UserResponse.fromEntity(document.getDownloadedBy()),
                UserResponse.fromEntity(document.getUploadedBy()),
                AlunoResponseDTO.fromEntity(document.getAluno()),
                document.getIsLast()
        );
    }
}
