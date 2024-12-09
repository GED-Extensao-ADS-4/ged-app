package br.apae.ged.strategy.alunos;

import br.apae.ged.dto.aluno.AlunoRequestDTO;
import br.apae.ged.exceptions.ValidationException;
import br.apae.ged.repositories.AlunoRepository;
import br.apae.ged.strategy.NewAlunoValidationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AlunoCpfValidation implements NewAlunoValidationStrategy {

    private final AlunoRepository alunoRepository;

    @Override
    public void validate(AlunoRequestDTO request) {
        if (alunoRepository.existsByCpf(request.cpf())){
            throw new ValidationException("CPF já cadastrado");
        }
    }
}
