package br.apae.ged.dto;

import br.apae.ged.models.Document;
import br.apae.ged.models.enums.TipoArquivo;
import br.apae.ged.models.enums.TipoDocumento;
import org.springframework.web.multipart.MultipartFile;

public record DocumentRequestDTO(

        String nome,
        TipoDocumento tipoDocumento,
        TipoArquivo tipoArquivo,
        String path,
        String uploadedBy,
        Document prevVersion,
        MultipartFile file
) {
}