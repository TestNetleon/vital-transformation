package org.vitaltransformation.http;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("index.php?c=users&f=login")
    Call<BaseResponse> requestLogin(@Field("email") String email,
                                    @Field("password") String password);

   @FormUrlEncoded
    @POST("index.php?c=users&f=register")
    Call<BaseResponse> requestRegister(@Field("user_name") String userName,
                                    @Field("user_email") String userEmail,
                                    @Field("mobile") String mobile,
                                    @Field("password") String password);

    /*@GET("enteredReport")
    Call<BaseResponse> getDashboardData();*/

    /*@Multipart
    @POST("irsReply")
    Call<BaseResponse> replyIssue(@Part("ticket_id") RequestBody id,
                                  @Part("reply") RequestBody reply,
                                  @Part MultipartBody.Part file);*/

}