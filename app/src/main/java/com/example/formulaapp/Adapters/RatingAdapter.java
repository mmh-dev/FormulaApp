package com.example.formulaapp.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.formulaapp.Models.User;
import com.example.formulaapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RatingAdapter extends RecyclerView.Adapter<RatingAdapter.Holder> {


    List<User> userList;
    Context context;
    RecycleOnClickListener listener;

    public void setOnItemClickListener (RecycleOnClickListener listener){
        this.listener = listener;
    }

    public RatingAdapter(List<User> userList, Context context) {
        this.userList = userList;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rating_menu_item, parent, false);
        return new Holder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.ranking.setText(String.valueOf(userList.get(position).getRanking()));
        holder.userNameRating.setText(userList.get(position).getUsername());
        holder.userStatusRating.setText(userList.get(position).getStatus());
        if (userList.get(position).getImageUrl().equals("default")){
            holder.userPhotoRating.setImageResource(R.drawable.person_icon);
        }
        else {
            Picasso.get().load(userList.get(position).getImageUrl()).into(holder.userPhotoRating);
        }
        holder.pointsRating.setText(String.valueOf(userList.get(position).getAllPoints()));
        if (userList.get(position).getRanking() == 1){
            holder.cupRating.setImageResource(R.drawable.ic_cup_gold);
            holder.cupRating.setVisibility(View.VISIBLE);
            holder.ranking.setTextColor(context.getResources().getColor(R.color.colorYellow));
        }
        else if (userList.get(position).getRanking() == 2){
            holder.cupRating.setImageResource(R.drawable.ic_cup_silver);
            holder.cupRating.setVisibility(View.VISIBLE);
            holder.ranking.setTextColor(context.getResources().getColor(R.color.colorYellow));
        }
        else if (userList.get(position).getRanking() == 3){
            holder.cupRating.setImageResource(R.drawable.ic_cup_bronze);
            holder.cupRating.setVisibility(View.VISIBLE);
            holder.ranking.setTextColor(context.getResources().getColor(R.color.colorYellow));
        }
        else{
            holder.cupRating.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        TextView ranking, userNameRating, userStatusRating, pointsRating;
        CircleImageView userPhotoRating;
        ImageView cupRating;
        public Holder(@NonNull View itemView, RecycleOnClickListener listener) {
            super(itemView);

            ranking = itemView.findViewById(R.id.ranking);
            userNameRating = itemView.findViewById(R.id.userName_rating);
            userStatusRating = itemView.findViewById(R.id.userStatus_rating);
            pointsRating = itemView.findViewById(R.id.points_rating);
            userPhotoRating = itemView.findViewById(R.id.userPhoto_rating);
            cupRating = itemView.findViewById(R.id.cup_rating);

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
