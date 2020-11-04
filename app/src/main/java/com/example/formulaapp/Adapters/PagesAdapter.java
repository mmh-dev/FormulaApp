package com.example.formulaapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.formulaapp.Models.MenuBullet;
import com.example.formulaapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PagesAdapter extends RecyclerView.Adapter<PagesAdapter.Holder> {

    List<String> pagesList;
    private final int fragment;
    private final Context context;
    RecycleOnClickListener listener;

    public void setOnItemClickListener (RecycleOnClickListener listener){
        this.listener = listener;
    }

    public PagesAdapter(List<String> pagesList, int fragment, Context context) {
        this.pagesList = pagesList;
        this.fragment = fragment;
        this.context = context;
    }

    /** fragment = 1 -> manager, EditPages
     fragment = 2 -> user, SecondMenu
     fragment = 3 -> user, SavedPages
     */
    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        switch (fragment){
            case 1:
                view = LayoutInflater.from(context).inflate(R.layout.edit_pages_item, parent, false);
                break;
            case 2:
                view = LayoutInflater.from(context).inflate(R.layout.second_menu_item, parent, false);
                break;
            case 3:
                view = LayoutInflater.from(context).inflate(R.layout.saved_pages_item, parent, false);
                break;
        }
        assert view != null;
        return new Holder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull final Holder holder, int position) {
        holder.header.setText(pagesList.get(position));
        holder.item_position.setText(String.valueOf(position+1));
    }

    @Override
    public int getItemCount() {
        return pagesList.size();
    }

    public static class Holder extends RecyclerView.ViewHolder {
        TextView header, item_position;

        public Holder(@NonNull View itemView, RecycleOnClickListener listener ) {
            super(itemView);
            header = itemView.findViewById(R.id.header);
            item_position = itemView.findViewById(R.id.item_position);

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
