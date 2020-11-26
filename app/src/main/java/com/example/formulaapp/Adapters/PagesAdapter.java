package com.example.formulaapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.formulaapp.R;

import java.util.List;

public class PagesAdapter extends RecyclerView.Adapter<PagesAdapter.Holder> {

    private final int fragment;
    private final Context context;
    List<String> pagesList;
    RecycleOnClickListener listener;

    public PagesAdapter(List<String> pagesList, int fragment, Context context) {
        this.pagesList = pagesList;
        this.fragment = fragment;
        this.context = context;
    }

    public void setOnItemClickListener(RecycleOnClickListener listener) {
        this.listener = listener;
    }

    /**
     * fragment = 1 -> manager, EditPages
     * fragment = 2 -> user, SecondMenu
     */
    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        switch (fragment) {
            case 1:
                view = LayoutInflater.from(context).inflate(R.layout.edit_pages_item, parent, false);
                break;
            case 2:
                view = LayoutInflater.from(context).inflate(R.layout.second_menu_item, parent, false);
                break;
        }
        return new Holder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull final Holder holder, int position) {
        holder.header1.setText(pagesList.get(position));
    }

    @Override
    public int getItemCount() {
        return pagesList.size();
    }

    public interface RecycleOnClickListener {
        void onItemClick(int position);

        void onDeleteClick(int position);
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView header1;
        ImageView delete_page;

        public Holder(@NonNull View itemView, RecycleOnClickListener listener) {
            super(itemView);
            header1 = itemView.findViewById(R.id.header1);
            delete_page = itemView.findViewById(R.id.delete_page);

            if (fragment == 1) {
                delete_page.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listener.onDeleteClick(getAdapterPosition());
                    }
                });
            }


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    listener.onItemClick(position);
                }
            });
        }
    }
}
