package com.example.jzhang.wagstackoverflowapi.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.jzhang.wagstackoverflowapi.model.User;
import java.util.List;

public class UserViewModel extends ViewModel {

    UserRepository userRepository = new UserRepository();

    public LiveData<List<User>> getUsers() {
        return userRepository.getUsersLiveData();
    }

    public void updateUsers() {
        userRepository.updateUsers();
    }
}
