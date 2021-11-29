package com.zavazapp.zzhorizontalscroll;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.net.URI;
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

        //badge text
        if (data.get(position).getBadge() == null) {
            holder.scrollBadge.setVisibility(View.GONE);
        } else {
            if (data.get(position).getBadge().equals("")) {
                holder.scrollBadge.setVisibility(View.GONE);
            } else {
                holder.scrollBadge.setText(data.get(position).getBadge());
                holder.scrollBadge.setVisibility(View.VISIBLE);
            }
        }

        //image view size
        int maxImageSize = bundle.getInt("max_image_size");
        //default items per screen = 5
        int imageSize = bundle.getInt("image_size");
        holder.itemView.getLayoutParams().width = x / itemsPerScreen;
//        holder.itemView.getLayoutParams().height = x / itemsPerScreen;

        //holder.itemView.getLayoutParams().width = Math.min(Math.min(imageSize, maxImageSize), x / itemsPerScreen);
        holder.itemView.getLayoutParams().height = Math.min(Math.min(imageSize, maxImageSize), x / itemsPerScreen);


        //image size

        imageSize = Math.min(imageSize, maxImageSize);
        int imageX = imageSize > 0 ? imageSize : x / itemsPerScreen;
        int imageY = imageSize > 0 ? imageSize : x / itemsPerScreen;

        if (data.get(position).getImageRes() != 0) {
            Picasso.get().load(data.get(position).getImageRes()).resize(imageX, imageY).into(holder.imageView);
        }
        if (data.get(position).getImageUrl() != null && !data.get(position).getImageUrl().isEmpty()) {
            setImageView(data.get(position).getImageUrl(), holder, position, imageX, imageY);
        }
        if (data.get(position).getUri() != null) {
            setImageView(data.get(position).getUri(), holder, position, imageX, imageY);
        }

    }

    private void setImageView(String imageUrl, @NonNull ViewHolder holder, int position, int imageX, int imageY) {
        int transformCode = bundle.getInt("transform_code");
        int curveSize = bundle.getInt("curve_size");
        Picasso.get()
                .load(imageUrl)
                .transform(
                        transformCode == 1 ?
                                new CircleTransform() :
                                (transformCode == 2 ? new RadiusRectangleTransform(curveSize) :
                                        new NoTransform()
                                ))
                .resize(imageX, imageY)
                .into(holder.imageView);
    }

    private void setImageView(Uri imageURI, @NonNull ViewHolder holder, int position, int imageX, int imageY) {
        int transformCode = bundle.getInt("transform_code");
        int curveSize = bundle.getInt("curve_size");
        Picasso.get()
                .load(new File(imageURI.getPath()))
                .transform(
                        transformCode == 1 ?
                                new CircleTransform() :
                                (transformCode == 2 ? new RadiusRectangleTransform(curveSize) :
                                        new NoTransform()
                                ))
                .resize(imageX, imageY)
                .into(holder.imageView);
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
        Bundle bundle;

        public ViewHolder(@NonNull ViewGroup itemView, OnScrollItemClickListener clickListener, Bundle bundle) {
            super(itemView);
            this.itemView = itemView;
            this.bundle = bundle;
            imageView = itemView.findViewById(R.id.scrollItemView);
            scrollBadge = itemView.findViewById(R.id.scrollBadge);
            scrollBadge.setTextSize(bundle.getFloat("text_size"));
            scrollBadge.setTextColor(bundle.getInt("text_color"));
            this.clickListener = clickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (clickListener != null) {
                clickListener.onItemClick(getBindingAdapterPosition(), bundle.getInt("recycler_id"));
            }
        }
    }
}
