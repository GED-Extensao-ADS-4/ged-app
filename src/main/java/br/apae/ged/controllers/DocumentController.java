package br.apae.ged.controllers;

import br.apae.ged.dto.DocumentRequestDTO;
import br.apae.ged.dto.DocumentResponseDTO;
import br.apae.ged.dto.DocumentResponseStatusDTO;
import br.apae.ged.services.DocumentService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/documentos")
public class DocumentController {

    private final DocumentService service;

    @PostMapping(value = "/create",consumes = "multipart/form-data")
    public ResponseEntity<DocumentResponseStatusDTO>post(@ModelAttribute DocumentRequestDTO document) throws IOException {
        return ResponseEntity.status(201).body(service.save(document));
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<DocumentResponseDTO>> byId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.byID(id));
    }

    @GetMapping("/list/{alunoID}")
    public ResponseEntity<List<DocumentResponseDTO>> list(@PathVariable("alunoID") Long id,
                                                          @RequestParam(required = false) String nome,
                                                          @RequestParam(required = false) LocalDate start,
                                                          @RequestParam(required = false) LocalDate end,
                                                          @RequestParam(required = false) String downloadBy,
                                                          @RequestParam(required = false) String uploadedBy){
        return ResponseEntity.ok(service.list(nome, downloadBy, uploadedBy, start, end));
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable("id")Long id) throws MalformedURLException {
        Resource resource = service.downloadFile(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment;filename=\""+resource.getFilename() + "\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
