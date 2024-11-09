package br.apae.ged.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;


    public Aluno salvarAluno(Aluno aluno) {

        if (alunoRepository.findByCpf(aluno.getCpf()).isPresent()) {
            throw new RuntimeException("CPF já cadastrado");
        }


        if (alunoRepository.findByRg(aluno.getRg()).isPresent()) {
            throw new RuntimeException("RG já cadastrado");
        }

        return alunoRepository.save(aluno);
    }

    public List<Aluno> listarAlunos() {
        return alunoRepository.findAll();
    }

    public Aluno buscarAlunoPorId(Long id) {
        return alunoRepository.findById(id)
                .orElseThrow(() -> new AlunoNaoEncontradoException(id));
    }

    // UPDATE
    public Aluno atualizarAluno(Long id, Aluno alunoAtualizado) {
        return alunoRepository.findById(id).map(aluno -> {
            if (alunoRepository.findByCpf(alunoAtualizado.getCpf())
                    .filter(existingAluno -> !existingAluno.getId().equals(id)).isPresent()) {
                throw new RuntimeException("CPF já cadastrado em outro aluno");
            }

            if (alunoRepository.findByRg(alunoAtualizado.getRg())
                    .filter(existingAluno -> !existingAluno.getId().equals(id)).isPresent()) {
                throw new RuntimeException("RG já cadastrado em outro aluno");
            }
            aluno.setNome(alunoAtualizado.getNome());
            aluno.setSobrenome(alunoAtualizado.getSobrenome());
            aluno.setDataNascimento(alunoAtualizado.getDataNascimento());
            aluno.setCpf(alunoAtualizado.getCpf());
            aluno.setRg(alunoAtualizado.getRg());
            aluno.setStatus(alunoAtualizado.getStatus());
            aluno.setEndereco(alunoAtualizado.getEndereco());
            aluno.setResponsavelLegal(alunoAtualizado.getResponsavelLegal());

            return alunoRepository.save(aluno);
        }).orElseThrow(() -> new AlunoNaoEncontradoException(id));
    }

    public void deletarAluno(Long id) {
        if (!alunoRepository.existsById(id)) {
            throw new AlunoNaoEncontradoException(id);
        }
        alunoRepository.deleteById(id);
    }
}