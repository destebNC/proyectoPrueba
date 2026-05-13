# AutenticacinApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**login**](AutenticacinApi.md#login) | **POST** /auth/login | Login de usuario |


<a id="login"></a>
# **login**
> TokenResponse login(loginRequest)

Login de usuario

Devuelve un JWT válido para acceder a la API

### Example
```java
// Import classes:
import com.example.cliente.invoker.ApiClient;
import com.example.cliente.invoker.ApiException;
import com.example.cliente.invoker.Configuration;
import com.example.cliente.invoker.auth.*;
import com.example.cliente.invoker.models.*;
import com.example.cliente.api.AutenticacinApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    AutenticacinApi apiInstance = new AutenticacinApi(defaultClient);
    LoginRequest loginRequest = new LoginRequest(); // LoginRequest | 
    try {
      TokenResponse result = apiInstance.login(loginRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AutenticacinApi#login");
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
| **loginRequest** | [**LoginRequest**](LoginRequest.md)|  | |

### Return type

[**TokenResponse**](TokenResponse.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Token generado correctamente |  -  |
| **401** | Credenciales incorrectas |  -  |

