package br.apae.ged.controllers;

import br.apae.ged.dto.document.DocumentRequestDTO;
import br.apae.ged.dto.document.DocumentResponseDTO;
import br.apae.ged.dto.document.DocumentUploadResponseDTO;
import br.apae.ged.services.DocumentService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/documentos")
public class DocumentController {

    private final DocumentService service;

    @PostMapping(value = "/create/{alunoID}", consumes = "multipart/form-data")
    public ResponseEntity<DocumentUploadResponseDTO> post(@ModelAttribute DocumentRequestDTO document, @PathVariable("alunoID")Long id) throws IOException {
        return ResponseEntity.status(201).body(service.save(document, id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<DocumentResponseDTO>> byID(@PathVariable("id") Long id){
        return ResponseEntity.ok(service.byID(id));
    }

    @GetMapping("/list")
    public ResponseEntity<List<DocumentResponseDTO>> list(@RequestParam(required = false)Long id,
                                                          @RequestParam(required = false)String nome,
                                                          @RequestParam(required = false)String aluno){
        return ResponseEntity.ok(service.list(id, nome, aluno));
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable("id")Long id) throws MalformedURLException {
        Resource resource = service.downloadFile(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
