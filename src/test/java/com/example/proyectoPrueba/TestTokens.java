package com.example.proyectoPrueba;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/** Obtiene tokens reales haciendo login con los usuarios demo que crea DataInitializer. */
final class TestTokens {

    private static final ObjectMapper JSON = new ObjectMapper();

    private TestTokens() {
    }

    static String admin(MockMvc mockMvc) throws Exception {
        return login(mockMvc, "admin", "admin123");
    }

    static String user(MockMvc mockMvc) throws Exception {
        return login(mockMvc, "user", "user123");
    }

    static String login(MockMvc mockMvc, String username, String password) throws Exception {
        String body = mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"" + username + "\",\"password\":\"" + password + "\"}"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        return "Bearer " + JSON.readTree(body).get("token").asText();
    }
}
