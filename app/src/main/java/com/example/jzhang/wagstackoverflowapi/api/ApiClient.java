package com.example.jzhang.wagstackoverflowapi.api;

import com.example.jzhang.wagstackoverflowapi.model.UserResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public class ApiClient {

    private static ApiClient apiClient;

    private ApiServiceGenerator serviceGenerator = ApiServiceGenerator.getInstance();

    private ApiClient() {
    }

    public static ApiClient getInstance() {
        if (apiClient == null) {
            apiClient = new ApiClient();
        }

        return apiClient;
    }

    public UserService userService() {
        return serviceGenerator.apiServiceGenerator.getService(UserService.class);
    }

    public interface UserService {

        @GET("2.2/users?site=stackoverflow")
        Call<UserResponse> getUsers();
    }
}