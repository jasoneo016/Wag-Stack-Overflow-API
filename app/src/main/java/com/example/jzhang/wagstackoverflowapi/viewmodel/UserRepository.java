package com.example.jzhang.wagstackoverflowapi.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.example.jzhang.wagstackoverflowapi.api.ApiClient;
import com.example.jzhang.wagstackoverflowapi.model.User;
import com.example.jzhang.wagstackoverflowapi.model.UserResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class UserRepository {

    private ApiClient apiClient = ApiClient.getInstance();
    private MutableLiveData<List<User>> usersLiveData = new MutableLiveData<>();

    LiveData<List<User>> getUsersLiveData() {
        return usersLiveData;
    }

    public void updateUsers() {
        apiClient.userService().getUsers().enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.body() == null) {
                    return;
                }

                UserResponse userResponse = response.body();
                usersLiveData.postValue(userResponse.getUsers());
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                call.cancel();
            }
        });
    }
}
