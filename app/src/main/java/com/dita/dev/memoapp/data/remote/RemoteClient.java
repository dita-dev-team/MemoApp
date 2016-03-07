package com.dita.dev.memoapp.data.remote;

import com.dita.dev.memoapp.data.model.MemoResponse;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RemoteClient {
    MemoApiEndpoints endpoints;

    public RemoteClient() {
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
                                .header("Authorization", MemoApiEndpoints.BASIC_AUTH)
                                .build();

                        Response response = chain.proceed(request);

                        // Do anything with response here

                        return response;
                    }
                }/*new CurlLoggingInterceptor()*/);
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(MemoResponse.class, new MemoDeserializer());
        mapper.registerModule(module);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MemoApiEndpoints.BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create(mapper))
                .client(client.build())
                .build();

        endpoints = retrofit.create(MemoApiEndpoints.class);
    }

    public MemoApiEndpoints getEndpoints() {
        return endpoints;
    }
}
