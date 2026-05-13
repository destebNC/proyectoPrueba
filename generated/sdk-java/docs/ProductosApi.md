# ProductosApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**agregarProducto**](ProductosApi.md#agregarProducto) | **POST** /agregar | Crear nuevo producto |
| [**borrarProducto**](ProductosApi.md#borrarProducto) | **DELETE** /delete/{product_id} | Eliminar producto |
| [**buscarProductoPorId**](ProductosApi.md#buscarProductoPorId) | **GET** /productos/{product_id} | Buscar por ID |
| [**listarProductos**](ProductosApi.md#listarProductos) | **GET** /productos | Listar inventario |


<a id="agregarProducto"></a>
# **agregarProducto**
> String agregarProducto(product)

Crear nuevo producto

Registra un nuevo producto en la base de datos de inventario.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ProductosApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    ProductosApi apiInstance = new ProductosApi(defaultClient);
    Product product = new Product(); // Product | Objeto producto que se va a guardar
    try {
      String result = apiInstance.agregarProducto(product);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProductosApi#agregarProducto");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **product** | [**Product**](Product.md)| Objeto producto que se va a guardar | |

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json, application/problem+json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Producto creado con éxito |  -  |
| **400** | Error de validación en la petición. |  -  |
| **500** | Error interno del servidor. |  -  |

<a id="borrarProducto"></a>
# **borrarProducto**
> String borrarProducto(productId)

Eliminar producto

Elimina un producto de forma permanente mediante su ID.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ProductosApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    ProductosApi apiInstance = new ProductosApi(defaultClient);
    Integer productId = 56; // Integer | ID del producto a borrar
    try {
      String result = apiInstance.borrarProducto(productId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProductosApi#borrarProducto");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **productId** | **Integer**| ID del producto a borrar | |

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json, application/problem+json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Producto eliminado |  -  |
| **404** | El recurso no existe. |  -  |

<a id="buscarProductoPorId"></a>
# **buscarProductoPorId**
> ProductDto buscarProductoPorId(productId)

Buscar por ID

Obtiene los detalles de un producto específico mediante su identificador.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ProductosApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    ProductosApi apiInstance = new ProductosApi(defaultClient);
    Integer productId = 56; // Integer | Identificador único del producto
    try {
      ProductDto result = apiInstance.buscarProductoPorId(productId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProductosApi#buscarProductoPorId");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **productId** | **Integer**| Identificador único del producto | |

### Return type

[**ProductDto**](ProductDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json, application/problem+json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Producto encontrado |  -  |
| **404** | El recurso no existe. |  -  |

<a id="listarProductos"></a>
# **listarProductos**
> List&lt;ProductDto&gt; listarProductos()

Listar inventario

Retorna todos los productos registrados actualmente.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ProductosApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    ProductosApi apiInstance = new ProductosApi(defaultClient);
    try {
      List<ProductDto> result = apiInstance.listarProductos();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProductosApi#listarProductos");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**List&lt;ProductDto&gt;**](ProductDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Lista de productos obtenida |  -  |

