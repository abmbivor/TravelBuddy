package com.example.sesipuser.travelbuudy.api;

import com.example.sesipuser.travelbuudy.api.responses.ForumModel;
import com.example.sesipuser.travelbuudy.api.responses.GalleryModel;
import com.example.sesipuser.travelbuudy.api.responses.HomeModel;
import com.example.sesipuser.travelbuudy.api.responses.HotelModel;
import com.example.sesipuser.travelbuudy.api.responses.RegisterModel;
import com.example.sesipuser.travelbuudy.api.responses.ReviewModel;
import com.example.sesipuser.travelbuudy.api.responses.SpotDescriptionModel;
import com.example.sesipuser.travelbuudy.api.responses.SubmitReviewModel;
import com.example.sesipuser.travelbuudy.api.responses.TestModel;
import com.example.sesipuser.travelbuudy.api.responses.ThreadCommentModel;
import com.example.sesipuser.travelbuudy.api.responses.ThreadDetailsModel;
import com.example.sesipuser.travelbuudy.api.responses.ThreadModel;
import com.example.sesipuser.travelbuudy.api.responses.TouristSpotModel;
import com.example.sesipuser.travelbuudy.api.responses.TransportModel;
import com.example.sesipuser.travelbuudy.api.responses.PostForumModel;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by SESIP User on 11-Nov-15.
 */
public interface ApiEndPoints {

    @FormUrlEncoded
    @POST("/api/login")
    public void checkLogin(@Field("username") String username, @Field("password") String password, Callback<TestModel> callback);

    @FormUrlEncoded
    @POST("/api/register")
    public void register(@Field("username") String username, @Field("email") String email, @Field("password") String password,Callback<RegisterModel> callback2);

    @FormUrlEncoded
    @POST("/api/home")
    public void home(@Field("emnei") String emnei ,Callback<HomeModel> callback3);

    @GET("/api/transport")
    public void getTransport(@Query("source") String source,
                             @Query("destination") String destination,
                             Callback<ArrayList<TransportModel>> callback);

    @GET("/api/hotel")
    public  void getHotel(@Query("destination") String destination, Callback<ArrayList<HotelModel>> callback);

    @GET("/api/touristSpot")
    public void getTouristSpot(@Query("destination") String destination, Callback<ArrayList<TouristSpotModel>> callback);

    @GET("/api/spotDescription")
    public void getsSpotDescription(@Query("spotname") String spotname, Callback<SpotDescriptionModel> callback);

    @GET("/api/image")
    public void getImage(@Query("spotname") String spotname, Callback<ArrayList<GalleryModel>> callback);

    @GET("/api/review_spot")
    public void getSpot(@Query("destination") String destination,Callback<HomeModel> callback3);

    @FormUrlEncoded
    @POST("/api/submitReview")
    public void submitReview(@Field("spot") String spot, @Field("review") String review,@Field("username") String username,Callback<SubmitReviewModel> cb);

    @GET("/api/spotReview")
    public void getReview(@Query("spotname") String spotname, Callback<ArrayList<ReviewModel>> cb);

    @FormUrlEncoded
    @POST("/api/postThread")
    public void postThread(@Field("text")String text,@Field("username") String username,Callback<PostForumModel> cb);

    @GET("/api/threadList")
    public void getThreadList(Callback<ArrayList<ForumModel>> cb);

    @FormUrlEncoded
    @POST("/api/thread")
    public void getThread(@Field("thread") String threadNo,Callback<ThreadModel> cb);

    @FormUrlEncoded
    @POST("/api/threadDetails")
    public void getThreadComments(@Field("thread") String threadNo,Callback<ArrayList<ThreadDetailsModel>> cb);

    @FormUrlEncoded
    @POST("/api/postComment")
    public void postComment(@Field("thread") String threadNo,@Field("text") String text,@Field("username") String username,Callback<ThreadCommentModel> cb);

}
