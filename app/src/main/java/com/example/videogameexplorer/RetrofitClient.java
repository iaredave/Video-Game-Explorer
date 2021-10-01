package com.example.videogameexplorer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String BASE_URL = "https://api.rawg.io/api/";
    private static Retrofit retrofit = null;

    public static ApiInterface getRetrofitClient() {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
//        Gson gson = new GsonBuilder().serialize
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit.create(ApiInterface.class);
    }
}
