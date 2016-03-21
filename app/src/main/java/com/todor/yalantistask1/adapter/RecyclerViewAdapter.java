package com.todor.yalantistask1.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.todor.yalantistask1.R;
import com.todor.yalantistask1.interfaces.OnImageClickListener;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context context;
    private List<String> imagesUrl; //[Comment] Wrong names mImagesUrl, mImagesIds
    private OnImageClickListener onImageClickListener;
    private int[] imageIds;

    public RecyclerViewAdapter(List<String> imagesUrl, Context context, OnImageClickListener onImageClickListener) {
        this.imagesUrl = imagesUrl;
        this.context = context;
        this.onImageClickListener = onImageClickListener;
    }

    public RecyclerViewAdapter(int[] imagesUrl, Context context, OnImageClickListener onImageClickListener) {
        this.imageIds = imagesUrl;
        this.context = context;
        this.onImageClickListener = onImageClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (imagesUrl != null && imagesUrl.size() > 0) {
            holder.bind(onImageClickListener);
            Picasso.with(context)
                    .load(imagesUrl.get(position))
                    .into(holder.image);
        } else if (imageIds != null && imageIds.length > 0) {
            holder.bind(onImageClickListener);
            Picasso.with(context)
                    .load(imageIds[position])
                    .into(holder.image);
        }
    }

    @Override
    public int getItemCount() {
        if (imagesUrl != null)
            return imagesUrl.size(); //[Comment] Looks not good. It  return imagesUrl != null ? imagesUrl.size() : imageIds.length;
        else
            return imageIds.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image; //[Comment] Wrong visibility modifier

        public ViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView;
        }

        public void bind(final OnImageClickListener onImageClickListener) {
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onImageClickListener.onImageClick(getAdapterPosition());
                }
            });
        }

    }
}
