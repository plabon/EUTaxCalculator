package com.jukti.taxcalculatordemo.utils;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by Pl@b0n on 01,October,2018
 */
public class Utility {
    public static boolean isNetworkAvailable(Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

}
