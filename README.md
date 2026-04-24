# API de Gestión de Inventarios - SDK

## Descripción

Este proyecto contiene una API REST para la gestión de inventarios y productos, junto con un SDK generado a partir de una especificación OpenAPI.

El SDK permite consumir la API desde Java de forma tipada y estructurada.

---

## Cómo generar el SDK

Para generar el SDK a partir del archivo OpenAPI:

1. Asegurarse de tener Maven instalado.
2. Ejecutar el siguiente comando en la raíz del proyecto:
mvn clean install

El SDK generado se encuentra en la carpeta: target/generated-sources
o dentro de: target/classes (dependiendo de la configuración del generador)


---

## Cómo usar el SDK

1. Importar las dependencias generadas en el proyecto.
2. Crear una instancia del cliente API.
3. Usar los métodos generados para consumir los endpoints.

Ejemplo:

```java
import com.example.client.ApiClient;
import com.example.client.api.InventariosApi;
import com.example.client.model.InventoryDto;

public class TestClient {

    public static void main(String[] args) {

        ApiClient client = new ApiClient();
        client.setBasePath("http://localhost:8080");

        InventariosApi api = new InventariosApi(client);

        var inventories = api.getAllInventories();

        System.out.println(inventories);
    }
}

```

### Ejemplo de ejecución

Para ejecutar el cliente:

1. Iniciar la aplicación Spring Boot.
2. Ejecutar la clase TestClient desde IntelliJ o terminal.
3. Ver la salida en consola.

---

## Qué se ha aprendido:

* Uso de OpenAPI para describir una API REST.
* Generación automática de un SDK a partir de una especificación.
* Consumo de APIs mediante clientes tipados en Java.
* Diferencia entre DTOs y modelos de dominio.
* Manejo de respuestas HTTP y errores en un cliente generado.
* Importancia de documentar correctamente una API para automatizar su uso.
