package com.example.jzhang.wagstackoverflowapi.master;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jzhang.wagstackoverflowapi.R;
import com.example.jzhang.wagstackoverflowapi.model.User;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private final List<User> userList = new ArrayList<>();

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        return new UserViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_cell, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int i) {
        holder.setUp(userList.get(i));
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public void updateData(List<User> users) {
        userList.clear();
        userList.addAll(users);
        notifyDataSetChanged();
    }

    class UserViewHolder extends RecyclerView.ViewHolder {

        private final ImageView userImage;
        private final TextView textTitle;

        UserViewHolder(@NonNull View itemView) {
            super(itemView);

            userImage = itemView.findViewById(R.id.image_user);
            textTitle = itemView.findViewById(R.id.text_title);
        }

        public void setUp(User user) {
            textTitle.setText(user.getDisplayName());
            Picasso.get().load(user.getProfileImage()).into(userImage);
        }
    }
}