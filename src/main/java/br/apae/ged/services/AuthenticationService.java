package br.apae.ged.services;

import br.apae.ged.exceptions.ActiveUserException;
import br.apae.ged.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements UserDetailsService {

    private final UserRepository repository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var find = repository.findByUsername(username);

        if (!find.getIsAtivo()){
            throw new ActiveUserException("Seu usuário foi desativado. Por favor entre em contato com algum administrador");
        }

        return find;
    }
}
