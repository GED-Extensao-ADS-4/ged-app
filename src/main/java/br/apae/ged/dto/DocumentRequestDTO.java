package br.apae.ged.dto;

import br.apae.ged.models.Document;
import org.springframework.web.multipart.MultipartFile;

public record DocumentRequestDTO(

        String nome,
        String tipoDocumento,
        String tipoArquivo,
        String path,
        String uploadedBy,
        Document prevVersion,
        MultipartFile file
) {
}