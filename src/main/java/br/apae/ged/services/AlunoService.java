package br.apae.ged.services;

import br.apae.ged.exceptions.AlunoNaoEncontradoException;
import br.apae.ged.models.Alunos;
import br.apae.ged.repositories.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;


    public Alunos salvarAluno(Alunos aluno) {

        if (alunoRepository.findByCpf(aluno.getCpf()) != null) {
            throw new RuntimeException("CPF já cadastrado");
        }

        if (alunoRepository.findByRg(aluno.getRg()) != null) {
            throw new RuntimeException("RG já cadastrado");
        }

        return alunoRepository.save(aluno);
    }

    public List<Alunos> listarAlunos() {
        return alunoRepository.findAll();
    }

    public Alunos buscarAlunoPorId(Long id) {
        return alunoRepository.findById(id)
                .orElseThrow(() -> new AlunoNaoEncontradoException(id));
    }

    // UPDATE
    public Alunos atualizarAluno(Long id, Alunos alunoAtualizado) {

        Alunos aluno = alunoRepository.findById(id).orElseThrow(() -> new AlunoNaoEncontradoException(id));

        if (alunoRepository.findByCpf(alunoAtualizado.getCpf()) != null){
            throw new RuntimeException("CPF já cadastrado na base de dados!");
        }

        if (alunoRepository.findByRg(alunoAtualizado.getRg()) != null){
            throw new RuntimeException("RG já cadastrado na base de dados!");
        }

        aluno.setNome(alunoAtualizado.getNome());
        aluno.setSobrenome(alunoAtualizado.getSobrenome());
        aluno.setDataNascimento(alunoAtualizado.getDataNascimento());
        aluno.setCpf(alunoAtualizado.getCpf());
        aluno.setRg(alunoAtualizado.getRg());
        aluno.setIsAtivo(alunoAtualizado.getIsAtivo());
        aluno.setEndereco(alunoAtualizado.getEndereco());
        aluno.setResponsavelLegal(alunoAtualizado.getResponsavelLegal());

        return alunoRepository.save(aluno);
    }

    public void deletarAluno(Long id) {
        if (!alunoRepository.existsById(id)) {
            throw new AlunoNaoEncontradoException(id);
        }
        alunoRepository.deleteById(id);
    }
}