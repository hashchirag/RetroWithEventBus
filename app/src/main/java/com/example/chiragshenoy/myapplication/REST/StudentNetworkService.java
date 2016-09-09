package com.example.chiragshenoy.myapplication.REST;

import com.example.chiragshenoy.myapplication.Models.BaseModel;
import com.example.chiragshenoy.myapplication.Models.Chapter;
import com.example.chiragshenoy.myapplication.Models.Tutor;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by chiragshenoy on 03/09/16.
 */

public interface StudentNetworkService {
    @GET("/v2/api/tutor_corner/subject_chapter_list/")
    Call<List<Chapter>> listChapters(@Query("pk") String pk);

    @GET("/v2/api/tutor_corner/get_tutor_status/")
    Call<Tutor> getTutorStatus(@Query("uid") String pk);

    @FormUrlEncoded
    @POST("/api/users/registerStudentWithCode/")
    Call<BaseModel> getPromoResponse(@Field("userid") String userid, @Field("code") String code);


    //This is using generic object type.
    @POST("/api/session/test/")
    Call<BaseModel> getGenericPromoRequest(@Body RequestBody requestModel);

}
