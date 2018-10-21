package com.example.jzhang.wagstackoverflowapi.api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

class ApiServiceGenerator {

    private static final String BASE_URL = "https://api.stackexchange.com/";

    private static final OkHttpClient.Builder okHttpClientBuilder;

    private static final OkHttpClient okHttpClient;

    private static final Retrofit.Builder retrofitBuilder;

    private static final Retrofit retrofit;

    static {
        okHttpClientBuilder = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor());

        okHttpClient = okHttpClientBuilder.build();

        retrofitBuilder = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL);

        retrofit = retrofitBuilder.build();
    }

    static ApiServiceGenerator apiServiceGenerator;

    private ApiServiceGenerator() {
    }

    static ApiServiceGenerator getInstance() {
        if (apiServiceGenerator == null) {
            apiServiceGenerator = new ApiServiceGenerator();
        }

        return apiServiceGenerator;
    }

    public ApiClient.UserService getService(Class<ApiClient.UserService> userServiceClass) {
        return retrofit.create(userServiceClass);
    }
}
