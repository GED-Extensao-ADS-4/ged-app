package br.apae.ged.services;

import br.apae.ged.configs.TokenService;
import br.apae.ged.dto.user.UserLoginDTO;
import br.apae.ged.dto.user.UserLoginResponseDTO;
import br.apae.ged.dto.user.UserRequestDTO;
import br.apae.ged.dto.user.UserResponse;
import br.apae.ged.exceptions.NotFoundException;
import br.apae.ged.models.Roles;
import br.apae.ged.models.User;
import br.apae.ged.repositories.UserRepository;
import br.apae.ged.strategy.NewUserValidationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final List<NewUserValidationStrategy> userValidationStrategies;

    public UserResponse register(UserRequestDTO entity){

        userValidationStrategies.forEach(validation -> validation.validate(entity));

        User user = UserRequestDTO.toEntity(entity);
        user.setRoles(Collections.singletonList(roleService.retrieve("ROLE_USER")));
        user.setIsAtivo(true);
        var save = userRepository.save(user);
        return UserResponse.fromEntity(save);
    }

    public UserLoginResponseDTO login(UserLoginDTO user){
        var usernamePassword = new UsernamePasswordAuthenticationToken(user.username(), user.password());
        var auth = authenticationManager.authenticate(usernamePassword);
        return new UserLoginResponseDTO(tokenService.generateToken((User) auth.getPrincipal()), LocalDateTime.now().plusMinutes(120));
    }

    public void changeStatusUser(Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("Usuário não encontrado"));

        if (!user.getIsAtivo()){
            user.setIsAtivo(true);
            userRepository.save(user);
            return;
        }

        user.setIsAtivo(false);

        userRepository.save(user);
    }

    public void setAdminRole(Long userID){
        User user = userRepository.findById(userID).orElseThrow(() ->  new NotFoundException("Usuário não encontrado"));

        List<Roles> roles = new ArrayList<>();

        roles.add(roleService.retrieve("ROLE_USER"));
        roles.add(roleService.retrieve("ROLE_ADMIN"));

        user.setRoles(roles);

        userRepository.save(user);
    }

    public void removeAdminRole(Long userID){
        User user = userRepository.findById(userID).orElseThrow(() ->  new NotFoundException("Usuário não encontrado"));

        List<Roles> roles = new ArrayList<>();

        roles.add(roleService.retrieve("ROLE_USER"));
        user.setRoles(roles);
        userRepository.save(user);
    }
}
