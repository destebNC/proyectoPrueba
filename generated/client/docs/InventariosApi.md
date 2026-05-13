# InventariosApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**createInventory**](InventariosApi.md#createInventory) | **POST** /inv | Crear inventario |
| [**deleteInventory**](InventariosApi.md#deleteInventory) | **DELETE** /inv/{inventory_id} | Eliminar inventario |
| [**getAllInventories**](InventariosApi.md#getAllInventories) | **GET** /inv | Listar inventarios |
| [**getInventoryById**](InventariosApi.md#getInventoryById) | **GET** /inv/{inventory_id} | Obtener inventario por ID |
| [**updateInventory**](InventariosApi.md#updateInventory) | **PUT** /inv/{inventory_id} | Actualizar inventario |


<a id="createInventory"></a>
# **createInventory**
> createInventory(inventory)

Crear inventario

Crea un nuevo inventario en el sistema.

### Example
```java
// Import classes:
import com.example.cliente.invoker.ApiClient;
import com.example.cliente.invoker.ApiException;
import com.example.cliente.invoker.Configuration;
import com.example.cliente.invoker.auth.*;
import com.example.cliente.invoker.models.*;
import com.example.cliente.api.InventariosApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    InventariosApi apiInstance = new InventariosApi(defaultClient);
    Inventory inventory = new Inventory(); // Inventory | 
    try {
      apiInstance.createInventory(inventory);
    } catch (ApiException e) {
      System.err.println("Exception when calling InventariosApi#createInventory");
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
| **inventory** | [**Inventory**](Inventory.md)|  | |

### Return type

null (empty response body)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | Inventario creado correctamente |  -  |
| **400** | Petición incorrecta |  -  |
| **500** | Error interno del servidor |  -  |

<a id="deleteInventory"></a>
# **deleteInventory**
> deleteInventory(inventoryId)

Eliminar inventario

### Example
```java
// Import classes:
import com.example.cliente.invoker.ApiClient;
import com.example.cliente.invoker.ApiException;
import com.example.cliente.invoker.Configuration;
import com.example.cliente.invoker.auth.*;
import com.example.cliente.invoker.models.*;
import com.example.cliente.api.InventariosApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    InventariosApi apiInstance = new InventariosApi(defaultClient);
    Integer inventoryId = 56; // Integer | 
    try {
      apiInstance.deleteInventory(inventoryId);
    } catch (ApiException e) {
      System.err.println("Exception when calling InventariosApi#deleteInventory");
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
| **inventoryId** | **Integer**|  | |

### Return type

null (empty response body)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Inventario eliminado |  -  |
| **404** | Recurso no encontrado |  -  |

<a id="getAllInventories"></a>
# **getAllInventories**
> List&lt;InventoryDto&gt; getAllInventories()

Listar inventarios

Devuelve todos los inventarios con sus productos.

### Example
```java
// Import classes:
import com.example.cliente.invoker.ApiClient;
import com.example.cliente.invoker.ApiException;
import com.example.cliente.invoker.Configuration;
import com.example.cliente.invoker.auth.*;
import com.example.cliente.invoker.models.*;
import com.example.cliente.api.InventariosApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    InventariosApi apiInstance = new InventariosApi(defaultClient);
    try {
      List<InventoryDto> result = apiInstance.getAllInventories();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling InventariosApi#getAllInventories");
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

[**List&lt;InventoryDto&gt;**](InventoryDto.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Lista de inventarios |  -  |
| **500** | Error interno del servidor |  -  |

<a id="getInventoryById"></a>
# **getInventoryById**
> InventoryDto getInventoryById(inventoryId)

Obtener inventario por ID

### Example
```java
// Import classes:
import com.example.cliente.invoker.ApiClient;
import com.example.cliente.invoker.ApiException;
import com.example.cliente.invoker.Configuration;
import com.example.cliente.invoker.auth.*;
import com.example.cliente.invoker.models.*;
import com.example.cliente.api.InventariosApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    InventariosApi apiInstance = new InventariosApi(defaultClient);
    Integer inventoryId = 56; // Integer | 
    try {
      InventoryDto result = apiInstance.getInventoryById(inventoryId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling InventariosApi#getInventoryById");
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
| **inventoryId** | **Integer**|  | |

### Return type

[**InventoryDto**](InventoryDto.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Inventario encontrado |  -  |
| **404** | Recurso no encontrado |  -  |

<a id="updateInventory"></a>
# **updateInventory**
> updateInventory(inventoryId, inventory)

Actualizar inventario

### Example
```java
// Import classes:
import com.example.cliente.invoker.ApiClient;
import com.example.cliente.invoker.ApiException;
import com.example.cliente.invoker.Configuration;
import com.example.cliente.invoker.auth.*;
import com.example.cliente.invoker.models.*;
import com.example.cliente.api.InventariosApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    InventariosApi apiInstance = new InventariosApi(defaultClient);
    Integer inventoryId = 56; // Integer | 
    Inventory inventory = new Inventory(); // Inventory | 
    try {
      apiInstance.updateInventory(inventoryId, inventory);
    } catch (ApiException e) {
      System.err.println("Exception when calling InventariosApi#updateInventory");
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
| **inventoryId** | **Integer**|  | |
| **inventory** | [**Inventory**](Inventory.md)|  | |

### Return type

null (empty response body)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Inventario actualizado |  -  |
| **400** | Petición incorrecta |  -  |
| **404** | Recurso no encontrado |  -  |

