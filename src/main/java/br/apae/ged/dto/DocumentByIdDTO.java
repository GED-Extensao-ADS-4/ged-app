package br.apae.ged.dto;

import br.apae.ged.models.Document;
import br.apae.ged.models.enums.TipoArquivo;
import br.apae.ged.models.enums.TipoDocumento;

import java.time.LocalDateTime;

public record DocumentByIdDTO(

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

    public static DocumentByIdDTO fromEntity(Document document){
        return new DocumentByIdDTO(
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
}