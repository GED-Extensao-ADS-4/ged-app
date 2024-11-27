package br.apae.ged.dto;

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
        String downloadedBy,
        String uploadedBy,
        Boolean isLast
) {

    public static DocumentResponseDTO fromEntity(Document document){
        return new DocumentResponseDTO(
                document.getId(),
                document.getNome(),
                document.getTipoDocumento(),
                document.getTipoArquivo(),
                document.getPath(),
                document.getDataUpload(),
                document.getDataDownload(),
                document.getDownloadedBy(),
                document.getUploadedBy(),
                document.getIsLast()
        );
    }

    private static String removeUUID(String nome){
        int index = nome.indexOf("-");

        return nome.substring(0, index);
    }
}
