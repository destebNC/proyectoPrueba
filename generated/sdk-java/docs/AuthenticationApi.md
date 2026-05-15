# AuthenticationApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**authLoginPost**](AuthenticationApi.md#authLoginPost) | **POST** /auth/login | User login |
| [**authRegisterPost**](AuthenticationApi.md#authRegisterPost) | **POST** /auth/register | User registration |


<a id="authLoginPost"></a>
# **authLoginPost**
> authLoginPost(loginRequest)

User login

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AuthenticationApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    AuthenticationApi apiInstance = new AuthenticationApi(defaultClient);
    LoginRequest loginRequest = new LoginRequest(); // LoginRequest | 
    try {
      apiInstance.authLoginPost(loginRequest);
    } catch (ApiException e) {
      System.err.println("Exception when calling AuthenticationApi#authLoginPost");
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

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/problem+json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |
| **400** | Invalid request |  -  |
| **401** | Unauthorized |  -  |

<a id="authRegisterPost"></a>
# **authRegisterPost**
> authRegisterPost(registerRequest)

User registration

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AuthenticationApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    AuthenticationApi apiInstance = new AuthenticationApi(defaultClient);
    RegisterRequest registerRequest = new RegisterRequest(); // RegisterRequest | 
    try {
      apiInstance.authRegisterPost(registerRequest);
    } catch (ApiException e) {
      System.err.println("Exception when calling AuthenticationApi#authRegisterPost");
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
| **registerRequest** | [**RegisterRequest**](RegisterRequest.md)|  | |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/problem+json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |
| **400** | Invalid request |  -  |
| **409** | Conflict |  -  |

