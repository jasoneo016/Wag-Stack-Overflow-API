package com.example.jzhang.wagstackoverflowapi.master;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.jzhang.wagstackoverflowapi.R;
import com.example.jzhang.wagstackoverflowapi.model.User;
import com.example.jzhang.wagstackoverflowapi.viewmodel.UserViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private UserViewModel userViewModel;
    private RecyclerView recyclerUsers;
    private UserAdapter usersAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_master);

        assignGlobalFields();
        setUpRecyclerView();
        setUpObservers();
        userViewModel.updateUsers();

    }

    private void assignGlobalFields() {
        recyclerUsers = findViewById(R.id.recycler_users);
        usersAdapter = new UserAdapter();
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
    }

    private void setUpRecyclerView() {
        recyclerUsers.setAdapter(usersAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerUsers.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerUsers.getContext(),
                linearLayoutManager.getOrientation());
        recyclerUsers.addItemDecoration(dividerItemDecoration);
    }

    private void setUpObservers() {
        userViewModel.getUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> users) {
                usersAdapter.updateData(users);
            }
        });

    }

}
