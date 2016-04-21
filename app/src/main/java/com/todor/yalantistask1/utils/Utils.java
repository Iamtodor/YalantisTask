package com.todor.yalantistask1.utils;

import android.content.Context;

import com.todor.yalantistask1.R;
import com.todor.yalantistask1.model.Task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class Utils {

    public static List<String> getImageFromNetwork(Context context) {
        return Arrays.asList(context.getResources().getStringArray(R.array.images_from_network));
    }

    public static int[] getImageFromDrawable() {
        return new int[]{R.drawable.image1, R.drawable.image2, R.drawable.image3,
                R.drawable.image3, R.drawable.image4, R.drawable.image5, R.drawable.image6,
                R.drawable.image7, R.drawable.image8};
    }

    public static List<Task> getTasks() {
        List<Task> mTasks = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Task task = new Task();
            task.setAddress("address " + i);
            task.setDate("date " + i);
            task.setExpiredTime("exp date " + i);
            task.setHeader("header " + i);
            task.setImgUrl("img " + i);
            task.setLikeValue(String.valueOf(i));
            mTasks.add(task);
        }
        return mTasks;
    }

}
