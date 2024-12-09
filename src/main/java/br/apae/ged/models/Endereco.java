package br.apae.ged.models;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity(name = "tb_endereco")
public class Endereco extends EntityID{

    private String estado;
    private String cidade;
    private String bairro;
    private String rua;
    private int numero;
    private String complemento;
    private String cep;

    @ManyToOne
    @JoinColumn(name = "aluno_id", referencedColumnName = "id")
    private Alunos aluno;

    public Endereco(String estado,
                    String cidade,
                    String bairro,
                    String rua,
                    int numero,
                    String complemento,
                    String cep) {
        this.estado = estado;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
        this.cep = cep;
    }
}
