package com.example.mvvmdaggerapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mvvmdaggerapi.model.Owner;
import com.example.mvvmdaggerapi.model.Repos;

import java.util.List;

public class ReposAdapter extends RecyclerView.Adapter<ReposAdapter.HolderItem> {
    private Context context;
    private List<Repos> list;

    public ReposAdapter(Context context, List<Repos> list) {
        this.context  = context;
        this.list = list;

    }

    @NonNull
    @Override
    public HolderItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_adapter,parent,false);

        return new HolderItem(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderItem holder, int position) {
        holder.onBind(holder,position);
    }



    @Override
    public int getItemCount() {
        return list.size();
    }
    public void filterList(List<Repos> list){
        this.list = list;
        notifyDataSetChanged();
    }

    public class HolderItem extends RecyclerView.ViewHolder {
        private ImageView imgAvatar;
        private TextView tvName,tvFullName;
        public HolderItem(@NonNull View itemView) {
            super(itemView);
            imgAvatar = itemView.findViewById(R.id.img_avatar);
            tvName = itemView.findViewById(R.id.tv_name);
            tvFullName = itemView.findViewById(R.id.tv_fullname);
        }
        public void onBind(HolderItem holder, int position) {
            Repos repos = list.get(position);
            Owner owner = repos.getOwner();
            holder.tvName.setText(repos.getName());
            holder.tvFullName.setText(repos.getFullName());
            Glide.with(context).load(owner.getAvatarUrl()).into(holder.imgAvatar);
        }

    }
}
