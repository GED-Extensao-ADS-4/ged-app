package br.apae.ged.strategy;


import br.apae.ged.dto.user.UserRequestDTO;

public interface NewUserValidationStrategy {

    void validate(UserRequestDTO request);
}
