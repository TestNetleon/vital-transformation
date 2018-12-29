package org.vitaltransformation.http;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

public class ApiClient {

    // private static final String BASE_URL = "http://192.168.1.11/cake/api/";
    private static final String BASE_URL = "https://vitaltransformation.org/apis/";

    private static Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(getHttpClient())
            .build();

    private static OkHttpClient getHttpClient() {
        HttpLoggingInterceptor loggingInterceptorceptor = new HttpLoggingInterceptor();
        loggingInterceptorceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient().newBuilder()
                .connectTimeout(30000, TimeUnit.MILLISECONDS)
                .readTimeout(30000, TimeUnit.MILLISECONDS)
                .writeTimeout(30000, TimeUnit.MILLISECONDS)
                .addInterceptor(loggingInterceptorceptor)
                .build();
    }

    private static ApiInterface service;

    public static ApiInterface getService() {
        if (service == null)
            service = retrofit.create(ApiInterface.class);
        return service;
    }
}
