package com.zavazapp.zzhorizontalscroll;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * RecyclerView adapter. To customize, extend it and override its methods.
 */
public class ScrollAdapter extends RecyclerView.Adapter<ScrollAdapter.ViewHolder> {

    private List<ScrollItemModel> data;
    private OnScrollItemClickListener clickListener;
    private Context context;
    private Bundle bundle;
    private int scrollItemLayout;
    private int itemsPerScreen;

    /**
     * @param context
     * @param data
     * @param bundle
     * @param clickListener
     */
    public ScrollAdapter(Context context, List<ScrollItemModel> data, Bundle bundle, int scrollItemLayout, int itemsPerScreen, OnScrollItemClickListener clickListener) {
        this.context = context;
        this.data = data;
        this.bundle = bundle;
        this.scrollItemLayout = scrollItemLayout;
        this.clickListener = clickListener;
        this.itemsPerScreen = itemsPerScreen;

    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewGroup layout = (ViewGroup) LayoutInflater.from(context).inflate(scrollItemLayout, parent, false);
        return new ViewHolder(layout, clickListener, bundle);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int x = Utils.getScreenDimen(context).x;
        //image size
        //default size is screen width / 6
        int imageSize = bundle.getInt("image_size");
        holder.itemView.getLayoutParams().width = x / itemsPerScreen;
        holder.itemView.getLayoutParams().height = x / itemsPerScreen;

        //badge text
        if (data.get(position).getBadge().equals("")){
            holder.scrollBadge.setVisibility(View.INVISIBLE);
        }else {
            holder.scrollBadge.setText(data.get(position).getBadge());
            holder.scrollBadge.setVisibility(View.VISIBLE);
        }

        //image size
        int imageX = imageSize > 0 ? imageSize : x / 6;
        int imageY = imageSize > 0 ? imageSize : x / 6;

        if (data.get(position).getImageRes() == 0){
            if (!data.get(position).getImageUrl().isEmpty()){
                Picasso.get().load(data.get(position).getImageUrl()).resize(imageX, imageY).into(holder.imageView);
            }
        }else {
            Picasso.get().load(data.get(position).getImageRes()).resize(imageX, imageY).into(holder.imageView);
        }
    }


    @Override
    public int getItemCount() {
        return data.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ViewGroup itemView;
        ImageView imageView;
        TextView scrollBadge;
        OnScrollItemClickListener clickListener;
        public ViewHolder(@NonNull ViewGroup itemView,  OnScrollItemClickListener clickListener, Bundle bundle) {
            super(itemView);
            this.itemView = itemView;
            imageView = itemView.findViewById(R.id.weeksImageScroll);
            scrollBadge = itemView.findViewById(R.id.scrollBadge);
            scrollBadge.setTextSize(bundle.getFloat("text_size"));
            scrollBadge.setTextColor(bundle.getInt("text_color"));
            this.clickListener = clickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getBindingAdapterPosition());
        }
    }
}
