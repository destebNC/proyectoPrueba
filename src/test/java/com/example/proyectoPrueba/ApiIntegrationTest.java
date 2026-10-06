package com.example.proyectoPrueba;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/** Pruebas de extremo a extremo sobre H2 con tokens JWT reales. Cada test se revierte al terminar. */
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class ApiIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private String admin;
    private String user;

    @BeforeEach
    void login() throws Exception {
        admin = TestTokens.admin(mockMvc);
        user = TestTokens.user(mockMvc);
    }

    // ---------- Autenticacion ----------

    @Test
    void register_createsUserWithRoleUser_evenIfAdminRequested() throws Exception {
        String body = mockMvc.perform(post("/auth/register").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"nuevo\",\"password\":\"secreto1\",\"role\":\"ADMIN\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.token").isNotEmpty())
                .andReturn().getResponse().getContentAsString();
        String token = "Bearer " + objectMapper.readTree(body).get("token").asText();

        mockMvc.perform(get("/api/inv").header("Authorization", token))
                .andExpect(status().isForbidden());
    }

    @Test
    void register_duplicateUsername_returns409() throws Exception {
        mockMvc.perform(post("/auth/register").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"admin\",\"password\":\"otraclave\"}"))
                .andExpect(status().isConflict());
    }

    @Test
    void register_invalidData_returns400() throws Exception {
        mockMvc.perform(post("/auth/register").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"ab\",\"password\":\"123\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors", hasSize(2)));
    }

    @Test
    void login_wrongPassword_returns401() throws Exception {
        mockMvc.perform(post("/auth/login").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"admin\",\"password\":\"mala\"}"))
                .andExpect(status().isUnauthorized());
    }

    // ---------- Seguridad por roles ----------

    @Test
    void withoutToken_returns401() throws Exception {
        mockMvc.perform(get("/api/productos")).andExpect(status().isUnauthorized());
    }

    @Test
    void invalidToken_returns401() throws Exception {
        mockMvc.perform(get("/api/productos").header("Authorization", "Bearer no.es.valido"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void userCannotAccessInventories_butCanAccessProducts() throws Exception {
        mockMvc.perform(get("/api/inv").header("Authorization", user)).andExpect(status().isForbidden());
        mockMvc.perform(get("/api/productos").header("Authorization", user)).andExpect(status().isOk());
    }

    @Test
    void docsArePublic() throws Exception {
        mockMvc.perform(get("/openapi.yaml")).andExpect(status().isOk());
    }

    // ---------- Inventarios ----------

    @Test
    void inventoryLifecycle() throws Exception {
        int id = createInventory("Almacen Test", "[{\"name\":\"Agua\",\"price\":0.5,\"weight\":1.5}]");

        mockMvc.perform(get("/api/inv/" + id).header("Authorization", admin))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Almacen Test"))
                .andExpect(jsonPath("$.products", hasSize(1)))
                .andExpect(jsonPath("$.products[0].inventoryId").value(id));

        mockMvc.perform(put("/api/inv/" + id).header("Authorization", admin).contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Renombrado\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Renombrado"));

        mockMvc.perform(delete("/api/inv/" + id).header("Authorization", admin))
                .andExpect(status().isNoContent());
        mockMvc.perform(get("/api/inv/" + id).header("Authorization", admin))
                .andExpect(status().isNotFound());
    }

    @Test
    void createInventory_withoutName_returns400() throws Exception {
        mockMvc.perform(post("/api/inv").header("Authorization", admin).contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"\"}"))
                .andExpect(status().isBadRequest());
    }

    // ---------- Productos ----------

    @Test
    void productsByInventory_onlyReturnsThatInventory() throws Exception {
        int a = createInventory("A", "[{\"name\":\"A1\",\"price\":1},{\"name\":\"A2\",\"price\":2}]");
        createInventory("B", "[{\"name\":\"B1\",\"price\":3}]");

        mockMvc.perform(get("/api/productos/inv/" + a + "/paginated").param("size", "1").header("Authorization", user))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalElements").value(2))
                .andExpect(jsonPath("$.content", hasSize(1)))
                .andExpect(jsonPath("$.content[0].name").value("A1"));
    }

    @Test
    void productLifecycleInInventory() throws Exception {
        int inv = createInventory("Con productos", "[]");

        String body = mockMvc.perform(post("/api/productos/inv/" + inv).header("Authorization", user)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Cafe\",\"price\":3.2,\"weight\":0.25}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.inventoryId").value(inv))
                .andReturn().getResponse().getContentAsString();
        int productId = objectMapper.readTree(body).get("id").asInt();

        mockMvc.perform(put("/api/productos/inv/" + inv + "/" + productId).header("Authorization", user)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Cafe molido\",\"price\":3.5,\"weight\":0.25}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Cafe molido"));

        mockMvc.perform(get("/api/productos/" + productId).header("Authorization", user))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(3.5));

        mockMvc.perform(delete("/api/productos/inv/" + inv + "/" + productId).header("Authorization", user))
                .andExpect(status().isNoContent());
        mockMvc.perform(get("/api/productos/" + productId).header("Authorization", user))
                .andExpect(status().isNotFound());
    }

    @Test
    void productFromAnotherInventory_returns404() throws Exception {
        int a = createInventory("A", "[{\"name\":\"A1\",\"price\":1}]");
        int b = createInventory("B", "[]");
        int productId = objectMapper.readTree(mockMvc.perform(get("/api/inv/" + a).header("Authorization", admin))
                .andReturn().getResponse().getContentAsString()).at("/products/0/id").asInt();

        mockMvc.perform(delete("/api/productos/inv/" + b + "/" + productId).header("Authorization", admin))
                .andExpect(status().isNotFound());
    }

    @Test
    void invalidProduct_returns400WithFieldErrors() throws Exception {
        mockMvc.perform(post("/api/productos").header("Authorization", user).contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"\",\"price\":-1}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors", hasItem(containsString("price"))));
    }

    @Test
    void badPathOrPagingParams_return400() throws Exception {
        mockMvc.perform(get("/api/productos/abc").header("Authorization", user))
                .andExpect(status().isBadRequest());
        mockMvc.perform(get("/api/productos/paginated").param("page", "-1").header("Authorization", user))
                .andExpect(status().isBadRequest());
    }

    private int createInventory(String name, String productsJson) throws Exception {
        String body = mockMvc.perform(post("/api/inv").header("Authorization", admin)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"" + name + "\",\"products\":" + productsJson + "}"))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();
        return objectMapper.readTree(body).get("id").asInt();
    }
}
