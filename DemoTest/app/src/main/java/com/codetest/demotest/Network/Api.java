package com.codetest.demotest.Network;
import android.text.Editable;


import java.util.List;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface Api {



String BASE_URL_NEW = "https://animechan.vercel.app/";

/** 1  Api
 *
 *
 **/

// Api call
    @Headers({"Content-Type:application/json;charset=UTF-8"})
    @GET("/api/random")
    Call<String> DataAPI();

}

