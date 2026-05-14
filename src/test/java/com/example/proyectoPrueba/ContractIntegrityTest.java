package com.example.proyectoPrueba;

import com.atlassian.oai.validator.mockmvc.OpenApiValidationMatchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class ContractIntegrityTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(roles = "ADMIN") // Le decimos al Portero que simulemos ser ADMIN
    void getProductsShouldMatchOpenApiContract() throws Exception {
        // Usamos la ruta exacta donde pusiste tu archivo maestro
        String openApiPath = "src/main/resources/static/openapi.yaml";

        // 1. Hacemos una petición GET a nuestra propia API simulada
        mockMvc.perform(get("/api/productos"))
                // 2. Esperamos que nos devuelva un 200 OK
                .andExpect(status().isOk())
                // 3. LA MAGIA: Verificamos que la respuesta cumple estrictamente con el YAML
                .andExpect(OpenApiValidationMatchers.openApi().isValid(openApiPath));
    }
}