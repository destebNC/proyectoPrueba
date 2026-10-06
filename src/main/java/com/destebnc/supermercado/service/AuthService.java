package com.destebnc.supermercado.service;

import com.destebnc.supermercado.dto.LoginRequestDto;
import com.destebnc.supermercado.dto.RegisterRequestDto;
import com.destebnc.supermercado.exception.ConflictException;
import com.destebnc.supermercado.exception.InvalidCredentialsException;
import com.destebnc.supermercado.model.User;
import com.destebnc.supermercado.repository.UserRepository;
import com.destebnc.supermercado.security.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    public static final String ROLE_USER = "USER";
    public static final String ROLE_ADMIN = "ADMIN";

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
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
        // Mismo error si el usuario no existe o la contraseña falla: no revelamos que usuarios existen
        User user = userRepository.findByUsername(dto.username())
                .filter(u -> passwordEncoder.matches(dto.password(), u.getPassword()))
                .orElseThrow(InvalidCredentialsException::new);
        return jwtUtil.generateToken(user.getUsername(), user.getRole());
    }
}
