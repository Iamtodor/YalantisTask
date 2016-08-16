package com.todor.yalantistask.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.todor.yalantistask.R;
import com.todor.yalantistask.interfaces.OnItemClickListener;
import com.todor.yalantistask.model.Item;
import com.todor.yalantistask.utils.Utils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WorkAdapter extends RecyclerView.Adapter<WorkAdapter.ViewHolderProductItem> {

    private Context mContext;
    private List<Item> mTasks;
    private OnItemClickListener onItemClickListener;

    public WorkAdapter(Context mContext, List<Item> tasks, OnItemClickListener onItemClickListener) {
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
        Item task = mTasks.get(position);
//        Picasso.with(mContext).load(task.getImgUrl()).error(R.drawable.image1).into(holder.imageCategory);
        holder.likeCounter.setText(String.valueOf(task.getLikesCounter()));
        holder.header.setText(task.getCategory().getName());
        String address = task.getUser().getAddress().getCity().getName() + ", " +
                task.getUser().getAddress().getStreet().getStreetType().getShortName() + task.getUser().getAddress().getStreet().getName()
                + ", " + task.getUser().getAddress().getHouse().getName();

        holder.address.setText(address);

        holder.createdDate.setText(Utils.getStringDataFromMillis(task.getCreatedDate()));
        holder.deadLine.setText(Utils.getStringDataFromMillis(task.getDeadline()));

        holder.bind(onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return mTasks.size();
    }

    public void updateData(List<Item> items) {
        mTasks.clear();
        mTasks.addAll(items);
        notifyDataSetChanged();
    }

    static class ViewHolderProductItem extends RecyclerView.ViewHolder {

        @Bind(R.id.item) protected LinearLayout item;
        @Bind(R.id.image_category) protected ImageView imageCategory;
        @Bind(R.id.like_value) protected TextView likeCounter;
        @Bind(R.id.header) protected TextView header;
        @Bind(R.id.address) protected TextView address;
        @Bind(R.id.date) protected TextView createdDate;
        @Bind(R.id.expected_time) protected TextView deadLine;

        public ViewHolderProductItem(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(final OnItemClickListener onImageClickListener) {
            item.setOnClickListener(v -> onImageClickListener.onItemClick(getAdapterPosition()));
        }

    }
}

