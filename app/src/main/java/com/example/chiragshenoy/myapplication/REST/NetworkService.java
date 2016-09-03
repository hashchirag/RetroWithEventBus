package com.example.chiragshenoy.myapplication.REST;

import com.example.chiragshenoy.myapplication.Models.Chapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by chiragshenoy on 03/09/16.
 */

public interface NetworkService {
    @GET("/v2/api/tutor_corner/subject_chapter_list/")
    Call<List<Chapter>> listChapters(@Query("pk") String pk);
}
