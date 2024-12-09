package br.apae.ged.strategy.users;

import br.apae.ged.dto.user.UserRequestDTO;
import br.apae.ged.exceptions.ValidationException;
import br.apae.ged.repositories.UserRepository;
import br.apae.ged.strategy.NewUserValidationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserEmailValidation implements NewUserValidationStrategy {

    private final UserRepository userRepository;

    @Override
    public void validate(UserRequestDTO request) {
        if (userRepository.existsByEmail(request.email())){
            throw new ValidationException("Email já está em uso!");
        }
    }
}
