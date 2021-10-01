package com.example.videogameexplorer;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

//public interface ApiInterface {
//        @Headers({ "Content-Type: application/json",
//                "token: Token aa17288ae98d4b18a1fc1b34bfb44e01",
//        })
//        @GET("games?dates=2019-10-10,2020-10-10&key=aa17288ae98d4b18a1fc1b34bfb44e01&ordering=-added")
//        Call<Games> getGames();
//}
public interface ApiInterface {
        @Headers({ "Content-Type: application/json",
                "token: Token aa17288ae98d4b18a1fc1b34bfb44e01",
        })
        @GET("games")
        Call<Games> getGames(
                @Query("key") String key,
                @Query("dates") String dates,
                @Query("ordering") String ordering
        );
}