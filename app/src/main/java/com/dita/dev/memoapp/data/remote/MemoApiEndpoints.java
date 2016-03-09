package com.dita.dev.memoapp.data.remote;

import com.dita.dev.memoapp.data.model.MemoResponse;
import com.dita.dev.memoapp.data.model.Message;

import okhttp3.Credentials;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;


public interface MemoApiEndpoints {

    /**

     Groups
         url - groups/{name}
         GET - {name}
         POST - (name-R,fullname,password-R,image)
         PUT - {name} (name-R,fullname,password-R,image)
         DELETE {name}

         url - groups/authenticate
         POST (username-R,password-R)

     Individuals
         url - individuals/{id-no}
         GET - {id-no}
     POST - (id-no-R,name-R,password-R,image)
         PUT - {id-no} (id-no-R,name,password-R,image)
         DELETE {id-no}

         url -individuals/authenticate
         POST (username-R,password-R)
     */

    String BASE_URL = "http://dita.dev.ngrok.io/api/v1/";
    String BASE_URL_LOCAL = "192.168.7.74:5050/api/v1/";
    String BASIC_AUTH = Credentials.basic("mobile", "mobile");

    /**
     * User API
     */
    String USER_CREATE = "users/";
    String USER_DELETE = "users/{username}";
    String USER_UPDATE = "users/{username}";
    String USER_AUTH = "users/authenticate/";


    /**
     * Retrofit Callbacks
     */

    @Multipart
    @POST(USER_AUTH)
    Call<MemoResponse> authUser(@Part("username") String username, @Part("password") String password);

    @Multipart
    @POST(USER_CREATE)
    Call<MemoResponse> userCreate(@Part("username") String username, @Part("password") String passwd, @Part("type") String userType, @Part("email") String email);

}
