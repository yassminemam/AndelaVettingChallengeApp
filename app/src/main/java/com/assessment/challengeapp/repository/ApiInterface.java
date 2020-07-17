package com.assessment.challengeapp.repository;

import com.assessment.challengeapp.pojo.PhotosSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("services/rest")
    Call<PhotosSearchResponse> getPhotosSearchAnswers(@Query("method") String method,
                                          @Query("api_key") String api_key,
                                          @Query("text") String searchText,
                                          @Query("format") String format,
                                          @Query("nojsoncallback") String nojsoncallback,
                                          @Query("per_page") String per_page);
}