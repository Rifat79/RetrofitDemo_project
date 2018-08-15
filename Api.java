package com.example.mdibrahim.retroexp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Md.Ibrahim on 8/14/2018.
 */


public interface Api {

    String BASE_URL = "https://api.github.com/";

    @GET("users")
    Call<List<Hero>> getHeroes();


}
