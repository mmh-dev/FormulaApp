package com.example.formulaapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.formulaapp.Models.MenuBullet;
import com.example.formulaapp.R;

import java.util.List;

public class MainMenuAdapter extends RecyclerView.Adapter<MainMenuAdapter.Holder> {

    List<MenuBullet> menuBulletList;
    Context context;
    RecycleOnClickListener listener;

    public void setOnItemClickListener (RecycleOnClickListener listener){
        this.listener = listener;
    }

    public MainMenuAdapter(List<MenuBullet> menuBulletList, Context context) {
        this.menuBulletList = menuBulletList;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.main_menu_item, parent, false);
        return new Holder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.header.setText(menuBulletList.get(position).getHeader());
        holder.desc.setText(menuBulletList.get(position).getDesc());
        holder.catImage.setImageResource(menuBulletList.get(position).getCatImage());

    }

    @Override
    public int getItemCount() {
        return menuBulletList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        TextView header, desc;
        ImageView catImage;
        public Holder(@NonNull View itemView, RecycleOnClickListener listener) {
            super(itemView);

            header = itemView.findViewById(R.id.header);
            desc = itemView.findViewById(R.id.main_desc);
            catImage = itemView.findViewById(R.id.catImage);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    listener.onItemClick(position);
                }
            });
        }
    }

    public interface RecycleOnClickListener{
        void onItemClick (int position);
    }
}
