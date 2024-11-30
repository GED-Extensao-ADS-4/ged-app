package br.apae.ged.dto;

import java.time.LocalDate;

public class AlunoRequestDTO(
        String nome,
        String sobrenome,
        LocalDate dataNascimento,
        String sexo,
        String cep,
        String telefone,
        String cpfResponsavel,
        String deficiencia,
        LocalDate dataEntrada,
        String observacoes,
        String estado,
        String cidade,
        String bairro,
        String rua,
        int numero,
        String complemento,
        String cep

) {
    public  class  Aluno alunoFromEntity(AlunoRequestDTO request)

    {
        return new Aluno(
                request.nome(),
                request.sobrenome(),
                request.dataNascimento(),
                request.sexo(),
                request.cpf,
                request.telefone(),
                request.cpfResponsavel,
                request.deficiencia,
                LocalDate.now(),
                request.observacoes();
                );

    public static  Endereco enderecoFromEntity(AlunoRequestDTO requestDTO){
        return new Endereco(
                requestDTO.estado(),
                requestDTO.cidade(),
                requestDTO.bairro(),
                requestDTO.rua(),
                requestDTO.numero(),
                requestDTO.complemento(),
                requestDTO.cep()
        )
    }

    }
}
