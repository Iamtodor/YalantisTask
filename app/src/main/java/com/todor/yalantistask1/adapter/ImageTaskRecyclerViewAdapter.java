package com.todor.yalantistask1.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.todor.yalantistask1.R;
import com.todor.yalantistask1.interfaces.OnItemClickListener;

import java.util.List;

public class ImageTaskRecyclerViewAdapter extends RecyclerView.Adapter<ImageTaskRecyclerViewAdapter.ViewHolder> {

    private Context mContext;
    private List<String> mImagesUrl;
    private OnItemClickListener mOnImageClickListener;
    private int[] mImageIds;

    public ImageTaskRecyclerViewAdapter(List<String> imagesUrl, Context mContext, OnItemClickListener onImageClickListener) {
        this.mImagesUrl = imagesUrl;
        this.mContext = mContext;
        this.mOnImageClickListener = onImageClickListener;
    }

    public ImageTaskRecyclerViewAdapter(int[] imagesUrl, Context mContext, OnItemClickListener onImageClickListener) {
        this.mImageIds = imagesUrl;
        this.mContext = mContext;
        this.mOnImageClickListener = onImageClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (mImagesUrl != null && mImagesUrl.size() > 0) {
            holder.bind(mOnImageClickListener);
            Picasso.with(mContext)
                    .load(mImagesUrl.get(position))
                    .into(holder.image);
        } else if (mImageIds != null && mImageIds.length > 0) {
            holder.bind(mOnImageClickListener);
            Picasso.with(mContext)
                    .load(mImageIds[position])
                    .into(holder.image);
        }
    }

    @Override
    public int getItemCount() {
        return mImagesUrl != null ? mImagesUrl.size() : mImageIds.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView image; //[Comment] Wrong visibility modifier

        public ViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView;
        }

        public void bind(final OnItemClickListener onImageClickListener) {
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onImageClickListener.onImageClick(getAdapterPosition());
                }
            });
        }

    }
}
