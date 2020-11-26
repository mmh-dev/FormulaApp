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
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainMenuAdapter extends RecyclerView.Adapter<MainMenuAdapter.Holder> {

    private final int fragment;
    /**
     * fragment == 0 -> Main menu
     * fragment == 1 -> Edit questions categories menu (manager)
     * fragment == 2 -> Manage users menu (manager)
     */

    List<MenuBullet> menuBulletList;
    Context context;
    RecycleOnClickListener listener;

    public MainMenuAdapter(List<MenuBullet> menuBulletList, Context context, int fragment) {
        this.menuBulletList = menuBulletList;
        this.context = context;
        this.fragment = fragment;
    }

    public void setOnItemClickListener(RecycleOnClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.main_menu_item, parent, false);
        return new Holder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.header.setText(menuBulletList.get(position).getHeader()); // for userList -> username
        holder.desc.setText(menuBulletList.get(position).getDesc());  // for userList -> status
        holder.catImage.setImageResource(menuBulletList.get(position).getCatImage());
        if (fragment == 2) {
            MenuBullet menuBullet = menuBulletList.get(position);
            if (menuBullet.getUser_photo().equals("default")) {
                holder.user_photo.setImageResource(R.drawable.person_icon);
            } else {
                Picasso.get().load(menuBullet.getUser_photo()).into(holder.user_photo); // for userList
            }
        }
        holder.questions_quantity.setText(String.valueOf(menuBulletList.get(position).getQuestions()));

    }

    @Override
    public int getItemCount() {
        return menuBulletList.size();
    }

    public interface RecycleOnClickListener {
        void onItemClick(int position);

        void onDeleteClick(int position);
    }

    public class Holder extends RecyclerView.ViewHolder {

        TextView header, desc, questions_quantity;
        CircleImageView user_photo;
        ImageView catImage, circle_red, delete_user;

        public Holder(@NonNull View itemView, RecycleOnClickListener listener) {
            super(itemView);

            header = itemView.findViewById(R.id.header);
            desc = itemView.findViewById(R.id.main_desc);
            circle_red = itemView.findViewById(R.id.circle_red);
            questions_quantity = itemView.findViewById(R.id.questions_quantity);
            user_photo = itemView.findViewById(R.id.user_photo);
            catImage = itemView.findViewById(R.id.catImage);
            delete_user = itemView.findViewById(R.id.delete_user);

            if (fragment == 1) {
                questions_quantity.setVisibility(View.VISIBLE);
                circle_red.setVisibility(View.VISIBLE);
            }

            if (fragment == 2) {
                user_photo.setVisibility(View.VISIBLE);
                catImage.setVisibility(View.GONE);
                delete_user.setVisibility(View.VISIBLE);
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    listener.onItemClick(position);
                }
            });

            if (fragment == 2) {
                delete_user.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listener.onDeleteClick(getAdapterPosition());
                    }
                });
            }
        }
    }
}
