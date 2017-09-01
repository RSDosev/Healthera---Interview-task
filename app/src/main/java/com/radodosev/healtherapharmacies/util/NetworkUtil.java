package com.radodosev.healtherapharmacies.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.radodosev.healtherapharmacies.HealtheraPharmaciesApplication;

public final class NetworkUtil {

    private NetworkUtil() {
        // no instances allowed
    }

    public static boolean isNetworkConnected() {
        final ConnectivityManager connectivityManager =
                (ConnectivityManager) HealtheraPharmaciesApplication.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

}