package com.destebnc.supermercado;

import com.atlassian.oai.validator.mockmvc.OpenApiValidationMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

/**
 * Comprueba que las peticiones y respuestas reales cumplen el contrato
 * src/main/resources/static/openapi.yaml (el mismo que se usa para generar los SDKs).
 */
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class ContractIntegrityTest {

    private static final String SPEC = "src/main/resources/static/openapi.yaml";

    @Autowired
    private MockMvc mockMvc;

    private String admin;

    @BeforeEach
    void login() throws Exception {
        admin = TestTokens.admin(mockMvc);
    }

    private static ResultMatcher matchesContract() {
        return OpenApiValidationMatchers.openApi().isValid(SPEC);
    }

    @Test
    void login_matchesContract() throws Exception {
        mockMvc.perform(post("/auth/login").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"admin\",\"password\":\"admin123\"}"))
                .andExpect(matchesContract());
    }

    @Test
    void listProducts_matchesContract() throws Exception {
        mockMvc.perform(get("/api/products").header("Authorization", admin))
                .andExpect(matchesContract());
    }

    @Test
    void inventoryProducts_matchesContract() throws Exception {
        mockMvc.perform(get("/api/inventories/1/products").param("size", "2").header("Authorization", admin))
                .andExpect(matchesContract());
    }

    @Test
    void inventoryCrud_matchesContract() throws Exception {
        mockMvc.perform(get("/api/inventories").header("Authorization", admin))
                .andExpect(matchesContract());
        mockMvc.perform(get("/api/inventories/1").header("Authorization", admin))
                .andExpect(matchesContract());
        mockMvc.perform(post("/api/inventories").header("Authorization", admin).contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Contrato\",\"products\":[{\"name\":\"Agua\",\"price\":0.5}]}"))
                .andExpect(matchesContract());
    }

    @Test
    void errorResponses_matchContract() throws Exception {
        mockMvc.perform(get("/api/products/99999").header("Authorization", admin))
                .andExpect(matchesContract());
        // Peticion valida segun el contrato pero rechazada por @NotBlank: la respuesta 400 debe cumplir el contrato
        mockMvc.perform(post("/api/products").header("Authorization", admin).contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"  \",\"price\":1}"))
                .andExpect(matchesContract());
    }
}
