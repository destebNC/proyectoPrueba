package com.example.proyectoPrueba;
import org.springframework.web.client.RestTemplate;

public class TestClient {

    public static void main(String[] args) {

        RestTemplate restTemplate = new RestTemplate();

        String url = "http://localhost:8080/inv";

        String response = restTemplate.getForObject(url, String.class);

        System.out.println("Respuesta: " + response);
    }
}
