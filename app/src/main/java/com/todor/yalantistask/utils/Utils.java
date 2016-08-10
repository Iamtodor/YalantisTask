package com.todor.yalantistask.utils;

import android.content.Context;

import com.todor.yalantistask.R;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public final class Utils {

    public static List<String> getImageFromNetwork(Context context) {
        return Arrays.asList(context.getResources().getStringArray(R.array.images_from_network));
    }

    public static int[] getImageFromDrawable() {
        return new int[]{R.drawable.image1, R.drawable.image2, R.drawable.image3,
                R.drawable.image3, R.drawable.image4, R.drawable.image5, R.drawable.image6,
                R.drawable.image7, R.drawable.image8};
    }

    public static String getStringDataFromMillis(long millis) {
        Date date = new Date(millis);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM dd, yyyy", new Locale("uk_UA"));
        return simpleDateFormat.format(date);
    }

}
