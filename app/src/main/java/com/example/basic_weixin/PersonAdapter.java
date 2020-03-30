package com.example.basic_weixin;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> {
    private List<Person> mPersonList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView personImg;
        TextView personDescription;
        public ViewHolder(View view) {
            super(view);
            personImg = view.findViewById(R.id.person_img);
            personDescription = view.findViewById(R.id.person_description);
        }
    }

    public PersonAdapter(List<Person> personList) {
        this.mPersonList = personList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.person_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Person person = mPersonList.get(i);
        viewHolder.personImg.setImageResource(person.getImgID());
        viewHolder.personDescription.setText(person.getDescription());
    }

    @Override
    public int getItemCount() {
        return mPersonList.size();
    }
}
