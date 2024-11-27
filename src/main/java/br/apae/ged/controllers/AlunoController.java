package br.apae.ged.controllers;

import br.apae.ged.dto.aluno.AlunoRequestDTO;
import br.apae.ged.dto.aluno.AlunoResponseDTO;
import br.apae.ged.services.AlunoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/alunos")
@RequiredArgsConstructor
@Tag(name = "ALUNOS")
public class AlunoController {
    private final AlunoService alunoService;

    @Operation(summary = "Salvar novos alunos")
    @PostMapping
    public ResponseEntity<AlunoResponseDTO> salvar(@RequestBody AlunoRequestDTO alunoRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(alunoService.salvarAluno(alunoRequest));
    }

    @GetMapping
    @Operation(summary = "Lista todos os alunos")
    public ResponseEntity buscarTodos(@RequestParam(required = false) String filter, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<AlunoDTO> alunos = alunoService.listarAlunos(filter, pageable);
        return ResponseEntity.ok(alunos);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um aluno pelo ID")
    public ResponseEntity buscarPorId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(alunoService.buscarAlunoPorId(id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove um aluno pelo ID")
    @Transactional
    public ResponseEntity deletar(@PathVariable("id") Long id) {
        alunoService.deletarAluno(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um aluno pelo ID")
    @Transactional
    public ResponseEntity<AlunoResponseDTO> atualizar(@PathVariable("id") Long id, @RequestBody AlunoRequestDTO alunoRequest) {
        return ResponseEntity.ok().body(alunoService.atualizarAluno(id, alunoRequest));
    }
}
