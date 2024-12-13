package br.apae.ged.dto.aluno;

import br.apae.ged.models.Alunos;
import br.apae.ged.models.Endereco;

import java.time.LocalDate;

public record AlunoRequestDTO(
        String nome,
        LocalDate dataNascimento,
        String sexo,
        String cpf,
        String telefone,
        String cpfResponsavel,
        String deficiencia,
        LocalDate dataEntrada,
        Boolean isAtivo,
        String observacoes,
        String estado,
        String cidade,
        String bairro,
        String rua,
        int numero,
        String complemento,
        String cep
) {

    public static Alunos alunoFromEntity(AlunoRequestDTO request){
        return new Alunos(
                request.nome(),
                request.dataNascimento(),
                request.sexo(),
                request.cpf(),
                request.telefone(),
                request.cpfResponsavel(),
                request.deficiencia(),
                LocalDate.now(),
                request.observacoes()
        );
    }

    public static Endereco enderecoFromEntity(AlunoRequestDTO requestDTO){
        return new Endereco(
                requestDTO.estado(),
                requestDTO.cidade(),
                requestDTO.bairro(),
                requestDTO.rua(),
                requestDTO.numero(),
                requestDTO.complemento(),
                requestDTO.cep()
        );
    }
}
