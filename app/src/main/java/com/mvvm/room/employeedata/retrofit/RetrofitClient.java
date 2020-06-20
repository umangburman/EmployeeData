package com.mvvm.room.employeedata.retrofit;

import com.mvvm.room.employeedata.BuildConfig;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private final String MainServer = "http://dummy.restapiexample.com/api/v1/";

    private Retrofit retrofit = null;

    private HttpLoggingInterceptor logging;

    private Level level;

    private OkHttpClient.Builder okHttpClient;

    public ApiInterface getRetrofitClient() {

        if (retrofit == null) {

            // Setting the Logging Level
            if (BuildConfig.BUILD_TYPE.contentEquals("debug")) {
                level = Level.BODY;
            }
            else {
                level = Level.NONE;
            }

            // Instance of Logging Interceptor
            logging = new HttpLoggingInterceptor();
            logging.setLevel(level);

            // Attaching a Logging to HTTPClient
            okHttpClient = new OkHttpClient.Builder();
            okHttpClient.addInterceptor(logging);

            retrofit = new Retrofit.Builder()
                    .baseUrl(MainServer)
                    .client(okHttpClient.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit.create(ApiInterface.class);
    }
}
