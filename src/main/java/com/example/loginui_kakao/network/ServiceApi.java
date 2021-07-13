package com.example.loginui_kakao.network;

import com.example.loginui_kakao.data.Categories;
import com.example.loginui_kakao.data.CommentResponse;
import com.example.loginui_kakao.data.JoinData;
import com.example.loginui_kakao.data.JoinResponse;
import com.example.loginui_kakao.data.KakaoData;
import com.example.loginui_kakao.data.KakaoResponse;
import com.example.loginui_kakao.data.LikeResponse;
import com.example.loginui_kakao.data.LoginData;
import com.example.loginui_kakao.data.LoginResponse;
import com.example.loginui_kakao.data.NewCommentData;
import com.example.loginui_kakao.data.PostData;
import com.example.loginui_kakao.data.PostResponse;
import com.example.loginui_kakao.data.UserInfoResponse;
import com.example.loginui_kakao.data.postId;
import com.kakao.usermgmt.response.UserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ServiceApi {
    @POST("/users/login")
    Call<LoginResponse> userLogin(@Body LoginData data);

    @POST("/users/join")
    Call<JoinResponse> userJoin(@Body JoinData data);

    @POST("/users/kakao")
    Call<KakaoResponse> userKakao(@Body KakaoData data);

    @GET("/posts/all/{category}")
    Call<Categories> category(@Path("category") int category);

    @POST("/posts")
    Call<PostResponse> userPost(@Body PostData data, @Header("token") String token);

    @POST("/posts/like")
    Call<LikeResponse> postLike(@Body postId postId, @Header("token") String token);

    @GET("/posts/comments/{postId}")
    Call<CommentResponse> getComment(@Path("postId") int postId);

    @GET("/search")
    Call<Categories> getSearch(@Query("query") String data);

    @POST("/posts/comment")
    Call<PostResponse> postComment(@Body NewCommentData data, @Header("token") String token);

    @DELETE("/posts/comment/{commentId}")
    Call<PostResponse> deleteComment(@Path("commentId") int commentId, @Header("token") String token);

    @DELETE("/posts/{postId}")
    Call<PostResponse> deletePost(@Path("postId") int postId, @Header("token") String token);

    @PUT("/posts/{postId}")
    Call<PostResponse> editPost(@Path("postId") int postId, @Body PostData data, @Header("token") String token);

    @GET("/posts/check/{postId}")
    Call<PostResponse> checkUser(@Path("postId") int postId, @Header("token") String token);

    @GET("/users/me")
    Call<UserInfoResponse> getUserInfo(@Header("token") String token);
}
