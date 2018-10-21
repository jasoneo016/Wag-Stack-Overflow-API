package com.example.jzhang.wagstackoverflowapi.master;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jzhang.wagstackoverflowapi.R;
import com.example.jzhang.wagstackoverflowapi.model.User;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
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

        private final TextView goldText;
        private final TextView silverText;
        private final TextView bronzeText;

        UserViewHolder(@NonNull View itemView) {
            super(itemView);

            userImage = itemView.findViewById(R.id.image_user);
            textTitle = itemView.findViewById(R.id.text_title);

            goldText = itemView.findViewById(R.id.gold_badge_text);
            silverText = itemView.findViewById(R.id.silver_badge_text);
            bronzeText = itemView.findViewById(R.id.bronze_badge_text);
        }

        public void setUp(final User user) {
            textTitle.setText(user.getDisplayName());
            loadImage(user);

            goldText.setText(String.valueOf(user.getBadgeCounts().getGold()));
            silverText.setText(String.valueOf(user.getBadgeCounts().getSilver()));
            bronzeText.setText(String.valueOf(user.getBadgeCounts().getBronze()));
        }

        public void loadImage(final User user) {
            Picasso.get()
                    .load(Uri.parse(user.getProfileImage()))
                    .networkPolicy(NetworkPolicy.OFFLINE)
                    .into(userImage, new Callback() {
                        @Override
                        public void onSuccess() {
                        }

                        @Override
                        public void onError(Exception e) {
                            Picasso.get()
                                    .load(user.getProfileImage())
                                    .placeholder(R.drawable.loading)
                                    .into(userImage);
                        }
                    });
        }
    }
}