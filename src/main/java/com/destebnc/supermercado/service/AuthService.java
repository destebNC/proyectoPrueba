package com.destebnc.supermercado.service;

import com.destebnc.supermercado.dto.LoginRequestDto;
import com.destebnc.supermercado.dto.RegisterRequestDto;
import com.destebnc.supermercado.exception.ConflictException;
import com.destebnc.supermercado.exception.InvalidCredentialsException;
import com.destebnc.supermercado.model.User;
import com.destebnc.supermercado.repository.UserRepository;
import com.destebnc.supermercado.security.JwtUtil;
import com.destebnc.supermercado.exception.TooManyAttemptsException;

import java.util.Optional;

import jakarta.validation.constraints.Size;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    public static final String ROLE_USER = "USER";
    public static final String ROLE_ADMIN = "ADMIN";
    // Hash de una contraseña cualquiera, para gastar el mismo tiempo cuando el usuario no existe
    private static final String DUMMY_HASH = new BCryptPasswordEncoder().encode("contraseña-que-noexiste");

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final LoginAttemptService loginAttemptService;

    public AuthService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, JwtUtil jwtUtil, LoginAttemptService loginAttemptService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.loginAttemptService = loginAttemptService;
    }

    /** Registra un usuario con rol USER y devuelve su token. Los ADMIN se crean al arrancar (DataInitializer). */
    @Transactional
    public String register(RegisterRequestDto dto) {
        if (userRepository.findByUsername(dto.username()).isPresent()) {
            throw new ConflictException("El usuario '" + dto.username() + "' ya existe");
        }
        User user = userRepository.save(
                new User(dto.username(), passwordEncoder.encode(dto.password()), ROLE_USER));
        return jwtUtil.generateToken(user.getUsername(), user.getRole());
    }




    @Transactional(readOnly = true)
    public String login(LoginRequestDto dto) {
        if (loginAttemptService.isBlocked(dto.username())){
            throw new TooManyAttemptsException(); // nueva excepcion -> 429 too many requests
        }
        Optional<User> found = userRepository.findByUsername(dto.username());
        String hash = found.map(User::getPassword).orElse(DUMMY_HASH);
        boolean ok = passwordEncoder.matches(dto.password(), hash) && found.isPresent();
        if (!ok){
            loginAttemptService.loginFailed(dto.username());
            throw new InvalidCredentialsException();
        }
        loginAttemptService.loginSucceeded(dto.username());
        return jwtUtil.generateToken(found.get().getUsername(), found.get().getRole());
    }
}
