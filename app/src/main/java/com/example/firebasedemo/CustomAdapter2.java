package com.example.firebasedemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAdapter2 extends RecyclerView.Adapter<CustomAdapter2.MyViewHolder> {

    private Context context;
    private List<Upload> uploadList;

    public CustomAdapter2(Context context, List<Upload> uploadList) {
        this.context = context;
        this.uploadList = uploadList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater1 = LayoutInflater.from(context);
        View view1 = layoutInflater1.inflate(R.layout.card_view, parent, false);
        return new MyViewHolder(view1);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Upload upload = uploadList.get(position);
        holder.tView.setText(upload.getImageName());
        Picasso.with(context)
                .load(upload.getImageUri())
                .placeholder(R.mipmap.ic_launcher_round)
                .fit()
                .centerCrop()
                .into(holder.iView);
    }

    @Override
    public int getItemCount() {
        return uploadList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tView;
        ImageView iView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tView = itemView.findViewById(R.id.cardTextViewId);
            iView = itemView.findViewById(R.id.cardImageViewId);
        }
    }
}
