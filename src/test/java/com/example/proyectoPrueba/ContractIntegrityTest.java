package com.example.proyectoPrueba;

import com.atlassian.oai.validator.mockmvc.OpenApiValidationMatchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
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
    @WithMockUser(roles = "ADMIN")
    void getProductsShouldMatchOpenApiContract() throws Exception {
        String openApiPath = "src/main/resources/static/openapi.yaml";

        mockMvc.perform(get("/api/productos"))
                .andExpect(status().isOk())
                .andExpect(OpenApiValidationMatchers.openApi().isValid(openApiPath));
    }
}