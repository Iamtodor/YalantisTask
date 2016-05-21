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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.RealmResults;

public class WorkAdapter extends RecyclerView.Adapter<WorkAdapter.ViewHolderProductItem> {

    private Context mContext;
    private RealmResults<Item> mTasks;
    private OnItemClickListener onItemClickListener;

    public WorkAdapter(Context mContext, RealmResults<Item> tasks, OnItemClickListener onItemClickListener) {
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
        holder.likeValue.setText(String.valueOf(task.getLikesCounter()));
        holder.header.setText(task.getCategory().getName());
        String address = task.getUser().getAddress().getCity().getRuName();
        holder.address.setText(address);

        holder.date.setText(getStringDataFromMillis(task.getCreatedDate()));
        holder.expectedTime.setText(getStringDataFromMillis(task.getDeadline()));

        holder.bind(onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return mTasks.size();
    }

    private String getStringDataFromMillis(long millis) {
        Date date = new Date(millis);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM dd, yyyy", new Locale("uk_UA"));
        return simpleDateFormat.format(date);
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
                    onImageClickListener.onItemClick(getAdapterPosition());
                }
            });
        }

    }
}

