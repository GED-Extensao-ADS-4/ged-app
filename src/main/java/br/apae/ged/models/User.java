package br.apae.ged.models;

import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity(name = "tb_users")
public class User extends EntityID {

    private String username;
    private String nome;
    private String password;
    private String email;
    private Boolean isAtivo;
}