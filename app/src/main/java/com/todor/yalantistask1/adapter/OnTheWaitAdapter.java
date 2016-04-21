package com.todor.yalantistask1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.todor.yalantistask1.R;
import com.todor.yalantistask1.model.Task;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class OnTheWaitAdapter extends ArrayAdapter<Task> {

    private Context mContext;
    private List<Task> mTasks;

    public OnTheWaitAdapter(Context context, List<Task> tasks) {
        super(context, 0, tasks);
        mTasks = tasks;
        mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolderItem viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.task_item, parent, false);
            viewHolder = new ViewHolderItem(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolderItem) convertView.getTag();
        }
        Task task = mTasks.get(position);
//        Picasso.with(mContext).load(task.getImgUrl()).error(R.drawable.image1).into(holder.imageCategory);
        viewHolder.likeValue.setText(task.getLikeValue());
        viewHolder.header.setText(task.getHeader());
        viewHolder.address.setText(task.getAddress());
        viewHolder.date.setText(task.getDate());
        viewHolder.expectedTime.setText(task.getExpiredTime());

        return convertView;
    }

    @Override
    public int getCount() {
        return mTasks.size();
    }

    @Override
    public Task getItem(int position) {
        return mTasks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class ViewHolderItem {
        @Bind(R.id.item) protected LinearLayout item;
        @Bind(R.id.image_category) protected ImageView imageCategory;
        @Bind(R.id.like_value) protected TextView likeValue;
        @Bind(R.id.header) protected TextView header;
        @Bind(R.id.address) protected TextView address;
        @Bind(R.id.date) protected TextView date;
        @Bind(R.id.expected_time) protected TextView expectedTime;

        public ViewHolderItem(View itemView) {
            ButterKnife.bind(this, itemView);
        }
    }
}
