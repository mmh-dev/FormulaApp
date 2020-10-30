//package com.example.formulaapp.Adapters;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.formulaapp.Models.MenuBullet;
//import com.example.formulaapp.R;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.squareup.picasso.Picasso;
//
//import java.util.List;
//
//public class PagesAdapter extends RecyclerView.Adapter<PagesAdapter.Holder> {
//
//    List<MenuBullet> menuBulletList;
//    private int fragment = 0;
//    String fragmentName;
//    private Context context;
//    FirebaseUser firebaseUser;
//
//
//    public PagesAdapter(List<MenuBullet> menuBulletList, String fragmentName, Context context) {
//        this.menuBulletList = menuBulletList;
//        this.fragmentName = fragmentName;
//        this.context = context;
//    }
//    /** fragment = 1 -> manager, EditPages
//     fragment = 2 -> user, MainMenu
//     fragment = 3 -> user, SecondMenu
//     fragment = 4 -> user, SavedPages
//     */
//    @NonNull
//    @Override
//    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = null;
//        switch (viewType){
//            case 1:
//                view = LayoutInflater.from(context).inflate(R.layout.edit_pages_item, parent, false);
//                break;
//            case 2:
//                view = LayoutInflater.from(context).inflate(R.layout.main_menu_item, parent, false);
//                break;
//            case 3:
//                view = LayoutInflater.from(context).inflate(R.layout.second_menu_item, parent, false);
//                break;
//            case 4:
//                view = LayoutInflater.from(context).inflate(R.layout.saved_pages_item, parent, false);
//                break;
//        }
//        return new Holder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull final Holder holder, int position) {
//         chat = chatList.get(position);
//        holder.show_message.setText(chat.getMessage());
//        if (imageUrl.equals("default")){
//            holder.profile_image_chat.setImageResource(R.drawable.person_icon);
//        }
//        else {
//            Picasso.get().load(imageUrl).into(holder.profile_image_chat);
//        }
//        if (position == chatList.size()-1){
//            if (chat.getIsSeen().equals("seen")){
//                holder.delivery_status.setText("Seen");
//            }
//            else {
//                holder.delivery_status.setText("Delivered");
//            }
//        }
//        else {
//            holder.delivery_status.setVisibility(View.GONE);
//        }
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return menuBulletList.size();
//    }
//
//    public static class Holder extends RecyclerView.ViewHolder {
//        TextView header, desc;
//
//        public Holder(@NonNull View itemView) {
//            super(itemView);
//            header = itemView.findViewById(R.id.show_message);
//            profile_image_chat = itemView.findViewById(R.id.user_avatar);
//            delivery_status = itemView.findViewById(R.id.delivery_status);
//        }
//    }
//
//    /** fragment = 1 -> manager, EditPages
//        fragment = 2 -> user, MainMenu
//        fragment = 3 -> user, SecondMenu
//        fragment = 4 -> user, SavedPages
//     */
//    @Override
//    public int getItemViewType(int position) {
//        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
//        switch (fragmentName){
//            case "":
//                if (firebaseUser != null) {
//                    if (firebaseUser.getEmail().equals("formula.osh.manager@gmail.com")){
//                        fragment = 1;
//                    }
//                }
//                break;
//            case "MainMenu":
//                fragment = 2;
//                break;
//            case "SecondMenu":
//                fragment = 3;
//                break;
//            case "SavedPages":
//                fragment = 4;
//                break;
//        }
//        return fragment;
//    }
//}
