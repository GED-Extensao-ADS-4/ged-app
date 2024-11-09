package br.apae.ged.entitys;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Alunos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String sobrenome;

    @Column(nullable = false)
    private LocalDate dataNascimento;

    @Column(nullable = false, unique = true, length = 11)
    private String cpf;

    @Column(nullable = false, unique = true, length = 9)
    private String rg;

    @Column(nullable = false)
    private boolean status;

    @Column(nullable = false)
    private String endereco;

    @Column(nullable = false)
    private String responsavelLegal;

}
