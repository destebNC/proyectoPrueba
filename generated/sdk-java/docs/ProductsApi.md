# ProductsApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**apiProductosGet**](ProductsApi.md#apiProductosGet) | **GET** /api/productos | List all products |
| [**apiProductosInvInventoryIdPaginatedGet**](ProductsApi.md#apiProductosInvInventoryIdPaginatedGet) | **GET** /api/productos/inv/{inventory_id}/paginated | List products from inventory (Paginated) |
| [**apiProductosInvInventoryIdPost**](ProductsApi.md#apiProductosInvInventoryIdPost) | **POST** /api/productos/inv/{inventory_id} | Add product to specific inventory |
| [**apiProductosInvInventoryIdProductIdDelete**](ProductsApi.md#apiProductosInvInventoryIdProductIdDelete) | **DELETE** /api/productos/inv/{inventory_id}/{product_id} | Delete product from inventory |
| [**apiProductosInvInventoryIdProductIdPut**](ProductsApi.md#apiProductosInvInventoryIdProductIdPut) | **PUT** /api/productos/inv/{inventory_id}/{product_id} | Update product in inventory |
| [**apiProductosPaginatedGet**](ProductsApi.md#apiProductosPaginatedGet) | **GET** /api/productos/paginated | List all products (Paginated) |
| [**apiProductosPost**](ProductsApi.md#apiProductosPost) | **POST** /api/productos | Add standalone product |
| [**apiProductosProductIdGet**](ProductsApi.md#apiProductosProductIdGet) | **GET** /api/productos/{product_id} | Get product by ID |


<a id="apiProductosGet"></a>
# **apiProductosGet**
> List&lt;ProductDto&gt; apiProductosGet()

List all products

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ProductsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    ProductsApi apiInstance = new ProductsApi(defaultClient);
    try {
      List<ProductDto> result = apiInstance.apiProductosGet();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProductsApi#apiProductosGet");
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
 - **Accept**: application/json, application/problem+json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |
| **400** | Invalid request |  -  |
| **401** | Unauthorized |  -  |

<a id="apiProductosInvInventoryIdPaginatedGet"></a>
# **apiProductosInvInventoryIdPaginatedGet**
> apiProductosInvInventoryIdPaginatedGet(inventoryId, page, size)

List products from inventory (Paginated)

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ProductsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    ProductsApi apiInstance = new ProductsApi(defaultClient);
    Integer inventoryId = 56; // Integer | 
    Integer page = 0; // Integer | 
    Integer size = 10; // Integer | 
    try {
      apiInstance.apiProductosInvInventoryIdPaginatedGet(inventoryId, page, size);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProductsApi#apiProductosInvInventoryIdPaginatedGet");
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
| **page** | **Integer**|  | [optional] [default to 0] |
| **size** | **Integer**|  | [optional] [default to 10] |

### Return type

null (empty response body)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/problem+json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |
| **400** | Invalid request |  -  |
| **401** | Unauthorized |  -  |
| **404** | Resource not found |  -  |

<a id="apiProductosInvInventoryIdPost"></a>
# **apiProductosInvInventoryIdPost**
> apiProductosInvInventoryIdPost(inventoryId, product)

Add product to specific inventory

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ProductsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    ProductsApi apiInstance = new ProductsApi(defaultClient);
    Integer inventoryId = 56; // Integer | 
    Product product = new Product(); // Product | 
    try {
      apiInstance.apiProductosInvInventoryIdPost(inventoryId, product);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProductsApi#apiProductosInvInventoryIdPost");
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
| **product** | [**Product**](Product.md)|  | |

### Return type

null (empty response body)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/problem+json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |
| **400** | Invalid request |  -  |
| **401** | Unauthorized |  -  |
| **404** | Resource not found |  -  |

<a id="apiProductosInvInventoryIdProductIdDelete"></a>
# **apiProductosInvInventoryIdProductIdDelete**
> apiProductosInvInventoryIdProductIdDelete(inventoryId, productId)

Delete product from inventory

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ProductsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    ProductsApi apiInstance = new ProductsApi(defaultClient);
    Integer inventoryId = 56; // Integer | 
    Integer productId = 56; // Integer | 
    try {
      apiInstance.apiProductosInvInventoryIdProductIdDelete(inventoryId, productId);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProductsApi#apiProductosInvInventoryIdProductIdDelete");
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
 - **Accept**: application/problem+json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |
| **401** | Unauthorized |  -  |
| **404** | Resource not found |  -  |

<a id="apiProductosInvInventoryIdProductIdPut"></a>
# **apiProductosInvInventoryIdProductIdPut**
> apiProductosInvInventoryIdProductIdPut(inventoryId, productId, product)

Update product in inventory

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ProductsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    ProductsApi apiInstance = new ProductsApi(defaultClient);
    Integer inventoryId = 56; // Integer | 
    Integer productId = 56; // Integer | 
    Product product = new Product(); // Product | 
    try {
      apiInstance.apiProductosInvInventoryIdProductIdPut(inventoryId, productId, product);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProductsApi#apiProductosInvInventoryIdProductIdPut");
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
 - **Accept**: application/problem+json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |
| **400** | Invalid request |  -  |
| **401** | Unauthorized |  -  |
| **404** | Resource not found |  -  |

<a id="apiProductosPaginatedGet"></a>
# **apiProductosPaginatedGet**
> apiProductosPaginatedGet(page, size)

List all products (Paginated)

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ProductsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    ProductsApi apiInstance = new ProductsApi(defaultClient);
    Integer page = 0; // Integer | 
    Integer size = 10; // Integer | 
    try {
      apiInstance.apiProductosPaginatedGet(page, size);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProductsApi#apiProductosPaginatedGet");
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
| **page** | **Integer**|  | [optional] [default to 0] |
| **size** | **Integer**|  | [optional] [default to 10] |

### Return type

null (empty response body)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/problem+json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |
| **400** | Invalid request |  -  |
| **401** | Unauthorized |  -  |

<a id="apiProductosPost"></a>
# **apiProductosPost**
> apiProductosPost(product)

Add standalone product

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ProductsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    ProductsApi apiInstance = new ProductsApi(defaultClient);
    Product product = new Product(); // Product | 
    try {
      apiInstance.apiProductosPost(product);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProductsApi#apiProductosPost");
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
| **product** | [**Product**](Product.md)|  | |

### Return type

null (empty response body)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/problem+json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |
| **400** | Invalid request |  -  |
| **401** | Unauthorized |  -  |

<a id="apiProductosProductIdGet"></a>
# **apiProductosProductIdGet**
> apiProductosProductIdGet(productId)

Get product by ID

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ProductsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    ProductsApi apiInstance = new ProductsApi(defaultClient);
    Integer productId = 56; // Integer | 
    try {
      apiInstance.apiProductosProductIdGet(productId);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProductsApi#apiProductosProductIdGet");
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
| **productId** | **Integer**|  | |

### Return type

null (empty response body)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/problem+json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |
| **400** | Invalid request |  -  |
| **404** | Resource not found |  -  |

