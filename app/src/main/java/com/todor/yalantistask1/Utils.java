package com.todor.yalantistask1;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

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

    public static boolean isOnline(Context context) {
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        return (networkInfo != null && networkInfo.isConnected());
    }

}
