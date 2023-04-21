package com.example.bloom3;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.shape.Shapeable;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
    ArrayList<Plantlist> plantlistArrayList;
    private ItemClickListener mItemClickListener,infoClickListener;
    public MyAdapter(ArrayList<Plantlist> plantlistArrayList) {
        //we are passing the recycler the list of users
        this.plantlistArrayList = plantlistArrayList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ShapeableImageView titleImage;
        TextView tvHeading;
        public final Button buttonSensors;
        public final ImageButton infoButton;
        public MyViewHolder(final View view){
            super(view);
        titleImage = view.findViewById(R.id.title_image);
        tvHeading = view.findViewById(R.id.tvHeading);

        buttonSensors = view.findViewById(R.id.buttonSensors);
        infoButton = view.findViewById(R.id.infoButton);
        }
    }

    public interface ItemClickListener {
        void onItemClick(int position);
        void onInfoClick(int position);
    }

    public void addItemClickListener(ItemClickListener listener) {
        mItemClickListener = listener;
    }
    public void addInfoClickListener(ItemClickListener listener) {
        infoClickListener = listener;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Plantlist plantlist = plantlistArrayList.get(position);
        holder.tvHeading.setText(plantlist.heading);
        holder.titleImage.setImageResource(plantlist.titleImage);
        holder.buttonSensors.setOnClickListener(view -> {
            if(mItemClickListener !=null){
                mItemClickListener.onItemClick(holder.getAdapterPosition());
            }
        });
        holder.infoButton.setOnClickListener(view -> {
            if(infoClickListener !=null){
                 infoClickListener.onInfoClick(holder.getAdapterPosition());
             }
        });
    }

    @Override
    public int getItemCount() {
        return plantlistArrayList.size();
    }
}


