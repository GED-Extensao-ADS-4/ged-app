package br.apae.ged.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity(name = "tb_users")
@Table(indexes = {
        @Index(name = "username_idx", columnList = "username"),
        @Index(name = "email_idx", columnList = "email")
})
public class User extends EntityID implements UserDetails{

    private String username;
    private String nome;
    private String password;
    private String email;
    private Boolean isAtivo;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "roles_id"))
    private List<Roles> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public User(String nome, String email, String username, String password){
        this.nome = nome;
        this.email = email;
        this.username = username;
        this.password = password;
    }
}