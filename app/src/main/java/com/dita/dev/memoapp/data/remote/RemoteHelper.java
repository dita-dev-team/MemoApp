package com.dita.dev.memoapp.data.remote;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.RxJavaCallAdapterFactory;



/**
 * Created by Wamatu on 9/30/15.
 */
public class RemoteHelper {
    MemoApi memoApi;

    public RemoteHelper() {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client
                .addNetworkInterceptor(new StethoInterceptor())
                .addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();

                // Customize the request
                Request request = original.newBuilder()
                        .header("Accept", "application/json")
                        .header("Authorization", MemoApi.BASIC_AUTH)
                        .build();

                Response response = chain.proceed(request);

                // Do anything with response here

                return response;
            }
                }/*new CurlLoggingInterceptor()*/);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MemoApi.BASE_URL)
                        //.baseUrl(MemoApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client.build())
                .callFactory(new Call.Factory() {
                    @Override
                    public Call newCall(Request request) {
                        return null;
                    }
                })
                .build();



        memoApi = retrofit.create(MemoApi.class);
    }

    public MemoApi getMemoApi(){
        return memoApi;
    }
}
