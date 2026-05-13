# ProductosApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**addProductToInventory**](ProductosApi.md#addProductToInventory) | **POST** /products/{inventory_id} | Crear producto en inventario |
| [**deleteProduct**](ProductosApi.md#deleteProduct) | **DELETE** /products/{inventory_id}/{product_id} | Eliminar producto |
| [**getAllProducts**](ProductosApi.md#getAllProducts) | **GET** /products | Listar productos |
| [**getProductByIdAndInventory**](ProductosApi.md#getProductByIdAndInventory) | **GET** /products/{inventory_id}/{product_id} | Obtener producto |
| [**updateProduct**](ProductosApi.md#updateProduct) | **PUT** /products/{inventory_id}/{product_id} | Actualizar producto |


<a id="addProductToInventory"></a>
# **addProductToInventory**
> addProductToInventory(inventoryId, productDto)

Crear producto en inventario

### Example
```java
// Import classes:
import com.example.cliente.invoker.ApiClient;
import com.example.cliente.invoker.ApiException;
import com.example.cliente.invoker.Configuration;
import com.example.cliente.invoker.auth.*;
import com.example.cliente.invoker.models.*;
import com.example.cliente.api.ProductosApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    ProductosApi apiInstance = new ProductosApi(defaultClient);
    Integer inventoryId = 56; // Integer | 
    ProductDto productDto = new ProductDto(); // ProductDto | 
    try {
      apiInstance.addProductToInventory(inventoryId, productDto);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProductosApi#addProductToInventory");
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
| **productDto** | [**ProductDto**](ProductDto.md)|  | |

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
| **201** | Producto creado |  -  |
| **400** | Petición incorrecta |  -  |
| **404** | Recurso no encontrado |  -  |

<a id="deleteProduct"></a>
# **deleteProduct**
> deleteProduct(inventoryId, productId)

Eliminar producto

### Example
```java
// Import classes:
import com.example.cliente.invoker.ApiClient;
import com.example.cliente.invoker.ApiException;
import com.example.cliente.invoker.Configuration;
import com.example.cliente.invoker.auth.*;
import com.example.cliente.invoker.models.*;
import com.example.cliente.api.ProductosApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    ProductosApi apiInstance = new ProductosApi(defaultClient);
    Integer inventoryId = 56; // Integer | 
    Integer productId = 56; // Integer | 
    try {
      apiInstance.deleteProduct(inventoryId, productId);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProductosApi#deleteProduct");
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
| **productId** | **Integer**|  | |

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
| **200** | Producto eliminado |  -  |
| **404** | Recurso no encontrado |  -  |

<a id="getAllProducts"></a>
# **getAllProducts**
> List&lt;ProductDto&gt; getAllProducts()

Listar productos

### Example
```java
// Import classes:
import com.example.cliente.invoker.ApiClient;
import com.example.cliente.invoker.ApiException;
import com.example.cliente.invoker.Configuration;
import com.example.cliente.invoker.auth.*;
import com.example.cliente.invoker.models.*;
import com.example.cliente.api.ProductosApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    ProductosApi apiInstance = new ProductosApi(defaultClient);
    try {
      List<ProductDto> result = apiInstance.getAllProducts();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProductosApi#getAllProducts");
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

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Lista de productos |  -  |

<a id="getProductByIdAndInventory"></a>
# **getProductByIdAndInventory**
> ProductDto getProductByIdAndInventory(inventoryId, productId)

Obtener producto

### Example
```java
// Import classes:
import com.example.cliente.invoker.ApiClient;
import com.example.cliente.invoker.ApiException;
import com.example.cliente.invoker.Configuration;
import com.example.cliente.invoker.auth.*;
import com.example.cliente.invoker.models.*;
import com.example.cliente.api.ProductosApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    ProductosApi apiInstance = new ProductosApi(defaultClient);
    Integer inventoryId = 56; // Integer | 
    Integer productId = 56; // Integer | 
    try {
      ProductDto result = apiInstance.getProductByIdAndInventory(inventoryId, productId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProductosApi#getProductByIdAndInventory");
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
| **productId** | **Integer**|  | |

### Return type

[**ProductDto**](ProductDto.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Producto encontrado |  -  |
| **404** | Recurso no encontrado |  -  |

<a id="updateProduct"></a>
# **updateProduct**
> updateProduct(inventoryId, productId, product)

Actualizar producto

### Example
```java
// Import classes:
import com.example.cliente.invoker.ApiClient;
import com.example.cliente.invoker.ApiException;
import com.example.cliente.invoker.Configuration;
import com.example.cliente.invoker.auth.*;
import com.example.cliente.invoker.models.*;
import com.example.cliente.api.ProductosApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    ProductosApi apiInstance = new ProductosApi(defaultClient);
    Integer inventoryId = 56; // Integer | 
    Integer productId = 56; // Integer | 
    Product product = new Product(); // Product | 
    try {
      apiInstance.updateProduct(inventoryId, productId, product);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProductosApi#updateProduct");
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
| **productId** | **Integer**|  | |
| **product** | [**Product**](Product.md)|  | |

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
| **200** | Producto actualizado |  -  |
| **404** | Recurso no encontrado |  -  |

