package com.example.to7fademo.api;

import com.example.to7fademo.Models.Check.CheckModel;
import com.example.to7fademo.Models.ConfirmationModel.ConfirmationModel;
import com.example.to7fademo.Models.GetProducts.GetProductsModel;
import com.example.to7fademo.Models.GetSection.Datum;
import com.example.to7fademo.Models.GetSection.GetSectionModel;
import com.example.to7fademo.Models.Login.LoginModel;
import com.example.to7fademo.Models.Register.RegisterModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {


    @FormUrlEncoded
    @POST("auth/registered")
    Call<RegisterModel> register(@Field("name") String name, @Field("email") String email, @Field("phone") String phone, @Field("password") String password, @Field("password_confirmation") String password_confirmation);

    @FormUrlEncoded
    @POST("auth/login")
    Call<LoginModel> login(@Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST("auth/check-email")
    Call<CheckModel> check(@Field("email") String email);

    @FormUrlEncoded
    @POST("auth/code-confirmation")
    Call<ConfirmationModel> confirm(@Field("code") String code, @Field("email") String email);

    @GET("get-sections")
    Call <GetSectionModel> getdata();

    @GET("get-products")
    Call <GetProductsModel> getProducts(@Query ("type")String type,
                                        @Query ("page")int page);
}




