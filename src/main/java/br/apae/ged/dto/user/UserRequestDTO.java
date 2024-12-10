package br.apae.ged.dto.user;

import br.apae.ged.models.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public record UserRequestDTO(String nome,
                             String email,
                             String username,
                             String password) {

    public static User toEntity(UserRequestDTO request){
        return new User(
                request.nome(),
                request.email(),
                new BCryptPasswordEncoder().encode(request.password())
        );
    }
}
