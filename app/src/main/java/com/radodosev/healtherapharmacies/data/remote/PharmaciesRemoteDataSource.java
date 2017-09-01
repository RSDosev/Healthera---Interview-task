package com.radodosev.healtherapharmacies.data.remote;

import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.radodosev.healtherapharmacies.BuildConfig;
import com.radodosev.healtherapharmacies.data.PharmaciesDataSource;
import com.radodosev.healtherapharmacies.data.model.PharmaciesResponseModel;
import com.radodosev.healtherapharmacies.data.model.PharmacyService;
import com.radodosev.healtherapharmacies.data.model.PharmacyServicesResponseModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Rado on 8/24/2017.
 */

public class PharmaciesRemoteDataSource implements PharmaciesDataSource {
    private static PharmaciesRemoteDataSource instance;

    private PharmaciesRemoteDataSource() {
    }

    public static PharmaciesRemoteDataSource get() {
        if (instance == null) {
            synchronized (PharmaciesRemoteDataSource.class) {
                if (instance == null) {
                    instance = new PharmaciesRemoteDataSource();
                }
            }
        }
        return instance;
    }

    @Override
    public Observable<PharmaciesResponseModel> getPharmaciesNearby(String latitude, String longitude) {
        return Observable.fromCallable(() -> {
            final String url = BuildConfig.API_BASE_URL
                    + String.format("locations?lat=%s&long=%s&radius=50000.0&unit=km", latitude, longitude);

            final PharmaciesResponseModel response = fetchData(url, PharmaciesResponseModel.class);
            return response == null ? new PharmaciesResponseModel() : response;
        })
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<List<PharmacyService>> getPharmacyServices(String pharmacyId) {
        return Observable.fromCallable(() -> {
            final String url = BuildConfig.API_BASE_URL
                    + String.format("pharmacies/%s/services", pharmacyId);

            final PharmacyServicesResponseModel response = fetchData(url, PharmacyServicesResponseModel.class);
            return response == null ? new ArrayList<PharmacyService>() : Arrays.asList(response.getData());
        }).subscribeOn(Schedulers.io());
    }

    @Nullable
    private <T> T fetchData(String urlAsString, Class<T> responseModelClass) {
        try {
            final URL url = new URL(urlAsString);

            final HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
            httpConnection.setRequestMethod("GET");
            httpConnection.setRequestProperty("app-version", "1.5");
            httpConnection.setRequestProperty("app-platform", "ios");
            httpConnection.setRequestProperty("client-id", BuildConfig.API_CLIENT_ID);
            httpConnection.setRequestProperty("Content-Type", "application/json");
            httpConnection.setRequestProperty("Accept", "application/json");
            httpConnection.setRequestProperty("Token", BuildConfig.API_TOKEN);
            httpConnection.setRequestProperty("user_id", BuildConfig.API_USER_ID);
            httpConnection.setUseCaches(false);
            httpConnection.setAllowUserInteraction(false);
            httpConnection.setConnectTimeout(BuildConfig.API_CONNECT_TIMEOUT * 1000);
            httpConnection.setReadTimeout(BuildConfig.API_READ_TIMEOUT * 1000);

            httpConnection.connect();

            if (httpConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                final BufferedReader br = new BufferedReader(new
                        InputStreamReader(httpConnection.getInputStream()));
                final StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                br.close();
                return new Gson().fromJson(sb.toString(), responseModelClass);
            } else {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
