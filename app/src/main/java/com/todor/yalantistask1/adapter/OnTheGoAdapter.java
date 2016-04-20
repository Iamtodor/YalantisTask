package com.todor.yalantistask1.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.todor.yalantistask1.R;
import com.todor.yalantistask1.interfaces.OnItemClickListener;
import com.todor.yalantistask1.model.Task;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class OnTheGoAdapter extends RecyclerView.Adapter<OnTheGoAdapter.ViewHolderProductItem> {

    private Context mContext;
    private List<Task> mTasks;
    private OnItemClickListener onItemClickListener;

    public OnTheGoAdapter(Context mContext, List<Task> tasks, OnItemClickListener onItemClickListener) {
        this.mContext = mContext;
        this.mTasks = tasks;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolderProductItem onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolderProductItem(LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolderProductItem holder, int position) {
        Task task = mTasks.get(position);
//        Picasso.with(mContext).load(task.getImgUrl()).error(R.drawable.image1).into(holder.imageCategory);
        holder.likeValue.setText(task.getLikeValue());
        holder.header.setText(task.getHeader());
        holder.address.setText(task.getAddress());
        holder.date.setText(task.getDate());
        holder.expectedTime.setText(task.getExpiredTime());
        holder.bind(onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return mTasks.size();
    }

    static class ViewHolderProductItem extends RecyclerView.ViewHolder {

        @Bind(R.id.item) protected LinearLayout item;
        @Bind(R.id.image_category) protected ImageView imageCategory;
        @Bind(R.id.like_value) protected TextView likeValue;
        @Bind(R.id.header) protected TextView header;
        @Bind(R.id.address) protected TextView address;
        @Bind(R.id.date) protected TextView date;
        @Bind(R.id.expected_time) protected TextView expectedTime;

        public ViewHolderProductItem(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(final OnItemClickListener onImageClickListener) {
            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onImageClickListener.onImageClick(getAdapterPosition());
                }
            });
        }

    }
}

