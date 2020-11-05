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
import com.example.formulaapp.Models.Question;
import com.example.formulaapp.R;


import java.util.List;

public class MainMenuAdapter extends RecyclerView.Adapter<MainMenuAdapter.Holder> {

    List<MenuBullet> menuBulletList;
    Context context;
    RecycleOnClickListener listener;
    private final int fragment;

    public void setOnItemClickListener (RecycleOnClickListener listener){
        this.listener = listener;
    }

    public MainMenuAdapter(List<MenuBullet> menuBulletList, Context context, int fragment) {
        this.menuBulletList = menuBulletList;
        this.context = context;
        this.fragment = fragment;
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
        holder.questions_quantity.setText(String.valueOf(menuBulletList.get(position).getQuestions()));

    }

    @Override
    public int getItemCount() {
        return menuBulletList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        TextView header, desc, questions_quantity;
        ImageView catImage, circle_red;
        public Holder(@NonNull View itemView, RecycleOnClickListener listener) {
            super(itemView);

            header = itemView.findViewById(R.id.header);
            desc = itemView.findViewById(R.id.main_desc);
            circle_red = itemView.findViewById(R.id.circle_red);
            questions_quantity = itemView.findViewById(R.id.questions_quantity);
            if (fragment == 1){
                questions_quantity.setVisibility(View.VISIBLE);
                circle_red.setVisibility(View.VISIBLE);
            }
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
