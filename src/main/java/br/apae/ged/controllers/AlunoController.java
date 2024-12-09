package br.apae.ged.controllers;

import br.apae.ged.dto.aluno.AlunoRequestDTO;
import br.apae.ged.models.Alunos;
import br.apae.ged.services.AlunoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
@RequiredArgsConstructor
public class AlunoController {

    private final AlunoService alunoService;

    @PostMapping("/create")
    public ResponseEntity<Alunos> post(@RequestBody AlunoRequestDTO request){
        return ResponseEntity.status(201).body(alunoService.create(request));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Alunos>> findAll(@RequestParam(required = false) String nome,
                                               @RequestParam(required = false) String cpf,
                                               @RequestParam(required = false) String cpfResponsavel,
                                               @RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "10") int size){
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(alunoService.findAll(cpf, cpfResponsavel, nome, pageable).getContent());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Alunos> update(@PathVariable("id") Long id, @RequestBody AlunoRequestDTO aluno){
        return ResponseEntity.ok(alunoService.update(id, aluno));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Alunos> byID(@PathVariable("id")Long id){
        return ResponseEntity.ok(alunoService.byID(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> desativarAluno(@PathVariable("id")Long id){
        alunoService.changeStatusAluno(id);
        return ResponseEntity.ok().build();
    }
}
