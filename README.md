# API de Gestion de Inventarios - SDK Multi-lenguaje

Este proyecto contiene una API REST para la gestion de inventarios y productos, junto con SDKs generados automaticamente para multiples lenguajes de programacion.

## SDKs Disponibles

### Lenguajes Soportados

| Lenguaje | Ubicacion | Estado |
|----------|-----------|--------|
| Java | target/generated-sources/ | Generado automaticamente |
| TypeScript | clients/typescript/ | Listo para generar |
| C# | clients/csharp/ | Listo para generar |
| PHP | clients/php/ | Listo para generar |

### Postman Collection
- Ubicacion: examples/postman/
- Archivo: inventory-api.postman_collection.json
- Auto-generacion: Scripts disponibles para Windows y Linux/Mac

---

## Como generar el SDK en Java

Para generar el SDK Java a partir del archivo OpenAPI:

1. Asegurarse de tener Maven instalado.
2. Ejecutar el siguiente comando en la raiz del proyecto:
```bash
mvn clean compile
```

El SDK generado se encuentra en la carpeta: `target/generated-sources`
o dentro de: `target/classes` (dependiendo de la configuracion del generador)

---

## Como usar los SDKs en otros lenguajes

### TypeScript

```bash
cd clients/typescript
npm install
npm run generate
npm run build
```

### C#

```bash
cd clients/csharp
# Instalar OpenAPI Generator CLI
openapi-generator-cli generate \
  -i ../../../src/main/resources/openAPI.yaml \
  -g csharp-netcore \
  -o . \
  -c config.json
```

### PHP

```bash
cd clients/php
composer install
composer run generate
```

---

## Como usar el SDK Java

1. Importar las dependencias generadas en el proyecto.
2. Crear una instancia del cliente API.
3. Usar los metodos generados para consumir los endpoints.

Ejemplo:

```java
import com.example.generated.api.AuthApi;
import com.example.generated.api.ProductsApi;
import com.example.generated.model.LoginRequest;
import com.example.generated.model.TokenResponse;

public class TestClient {

    public static void main(String[] args) {

        // Crear cliente
        ApiClient client = new ApiClient();
        client.setBasePath("http://localhost:8080");

        // Login
        AuthApi authApi = new AuthApi(client);
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("admin");
        loginRequest.setPassword("1234");

        TokenResponse token = authApi.login(loginRequest);
        System.out.println("Token: " + token.getJwt());

        // Configurar token para futuras llamadas
        client.setAccessToken(token.getJwt());

        // Usar otras APIs
        ProductsApi productsApi = new ProductsApi(client);
        var products = productsApi.getAllProducts();
        System.out.println(products);
    }
}
```

### Ejemplo de ejecucion

Para ejecutar el cliente:

1. Iniciar la aplicacion Spring Boot.
2. Ejecutar la clase TestClient desde IntelliJ o terminal.
3. Ver la salida en consola.

---

## Importar coleccion Postman

1. Abrir Postman
2. Click en "Import"
3. Seleccionar examples/postman/inventory-api.postman_collection.json
4. Configurar variable base_url como http://localhost:8080

---

## Que se ha aprendido

* Uso de OpenAPI para describir una API REST.
* Generacion automatica de SDKs en multiples lenguajes.
* Consumo de APIs mediante clientes tipados en diferentes tecnologias.
* Diferencia entre DTOs y modelos de dominio.
* Manejo de respuestas HTTP y errores en un cliente generado.
* Importancia de documentar correctamente una API para automatizar su uso.
* Seguridad basica en Spring con JWT.
* Creacion de colecciones Postman para testing de APIs.

---

## Estructura del proyecto

```
proyectoPrueba/
├── src/main/
│   ├── java/                    # Codigo fuente Java
│   ├── resources/
│   │   ├── application.properties
│   │   └── openAPI.yaml         # Especificacion OpenAPI
├── clients/                     # SDKs para otros lenguajes
│   ├── typescript/
│   ├── csharp/
│   └── php/
├── examples/
│   └── postman/                 # Coleccion Postman
├── target/
│   └── generated-sources/       # SDK Java generado
└── README.md
```

---

## Tecnologias utilizadas

- Spring Boot 3.3.5 - Framework principal
- OpenAPI Generator - Generacion de SDKs
- JWT - Autenticacion
- MySQL - Base de datos
- Maven - Gestion de dependencias
- Postman - Testing de APIs

