package com.example.basic_weixin;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.ViewHolder> {
    private List<Friend> mFriendList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView friendName;
        TextView friendSex;
        TextView friendAge;
        public ViewHolder(View view) {
            super(view);
            friendName = view.findViewById(R.id.friend_name);
            friendSex = view.findViewById(R.id.friend_sex);
            friendAge = view.findViewById(R.id.friend_age);
        }
    }

    public FriendAdapter(List<Friend> mFriendList) {
        this.mFriendList = mFriendList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.friend_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Friend friend = mFriendList.get(i);
        viewHolder.friendName.setText(friend.getName());
        viewHolder.friendSex.setText(friend.getSex());
        viewHolder.friendAge.setText(String.valueOf(friend.getAge()));
    }

    @Override
    public int getItemCount() {
        return mFriendList.size();
    }
}
