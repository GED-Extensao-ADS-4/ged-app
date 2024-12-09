package br.apae.ged.strategy;


import br.apae.ged.dto.aluno.AlunoRequestDTO;

public interface NewAlunoValidationStrategy{
    void validate(AlunoRequestDTO request);
}
