package com.destebnc.supermercado.config;

import com.destebnc.supermercado.model.Inventory;
import com.destebnc.supermercado.model.Product;
import com.destebnc.supermercado.model.User;
import com.destebnc.supermercado.repository.InventoryRepository;
import com.destebnc.supermercado.repository.UserRepository;
import com.destebnc.supermercado.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Al arrancar crea los usuarios demo (si no existen) y, si la BD esta vacia,
 * un par de inventarios de ejemplo. Se desactiva con SEED_ENABLED=false.
 */
@Component
@ConditionalOnProperty(name = "app.seed.enabled", havingValue = "true", matchIfMissing = true)
public class DataInitializer implements ApplicationRunner {

    private static final Logger log = LoggerFactory.getLogger(DataInitializer.class);

    private final UserRepository userRepository;
    private final InventoryRepository inventoryRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Value("${app.seed.admin-username}")
    private String adminUsername;
    @Value("${app.seed.admin-password}")
    private String adminPassword;
    @Value("${app.seed.user-username}")
    private String userUsername;
    @Value("${app.seed.user-password}")
    private String userPassword;

    public DataInitializer(UserRepository userRepository, InventoryRepository inventoryRepository,
                           BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.inventoryRepository = inventoryRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        createUserIfMissing(adminUsername, adminPassword, AuthService.ROLE_ADMIN);
        createUserIfMissing(userUsername, userPassword, AuthService.ROLE_USER);

        if (inventoryRepository.count() == 0) {
            Inventory madrid = new Inventory("Almacen Madrid");
            madrid.addProduct(new Product("Leche entera 1L", 0.95, 1.03));
            madrid.addProduct(new Product("Pan de molde", 1.45, 0.46));
            madrid.addProduct(new Product("Aceite de oliva 1L", 8.99, 0.92));

            Inventory valencia = new Inventory("Almacen Valencia");
            valencia.addProduct(new Product("Naranjas 2kg", 2.80, 2.0));
            valencia.addProduct(new Product("Arroz redondo 1kg", 1.30, 1.0));

            inventoryRepository.save(madrid);
            inventoryRepository.save(valencia);
            log.info("Datos de ejemplo creados: 2 inventarios con 5 productos");
        }
    }

    private void createUserIfMissing(String username, String password, String role) {
        if (userRepository.findByUsername(username).isEmpty()) {
            userRepository.save(new User(username, passwordEncoder.encode(password), role));
            log.info("Usuario demo creado: {} (rol {})", username, role);
        }
    }
}
