package com.dita.dev.memoapp;

import com.dita.dev.memoapp.data.model.MemoResponse;
import com.dita.dev.memoapp.data.model.UserData;
import com.dita.dev.memoapp.data.remote.MemoDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import org.junit.Before;
import org.junit.Test;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class MemoApiTests {
    private ObjectMapper mapper;
    private SimpleModule module;

    @Before
    public void setUp() throws Exception {
        mapper = new ObjectMapper();
        module = new SimpleModule();
        module.addDeserializer(MemoResponse.class, new MemoDeserializer());
        mapper.registerModule(module);
    }

    @Test
    public void testMemoDeserializerGet() throws Exception {
        MemoResponse memoResponse;
        OkHttpClient client = new OkHttpClient();
        String url = "http://dita.dev.ngrok.io/api/v1/userss";

        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();

        memoResponse = mapper.readValue(response.body().string(), MemoResponse.class);
        assertEquals("404", memoResponse.error.error.code.toString());

        url = "http://dita.dev.ngrok.io/api/v1/users";

        request = new Request.Builder().url(url).build();
        response = client.newCall(request).execute();
        memoResponse = mapper.readValue(response.body().string(), MemoResponse.class);
        UserData data = (UserData) memoResponse.data.data;
        assertEquals(3, data.count.intValue());
    }

    @Test
    public void testMemoDeserializerPost() throws Exception {
        MemoResponse memoResponse;
        OkHttpClient client = new OkHttpClient();
        String url = "http://dita.dev.ngrok.io/api/v1/users";

        RequestBody body = new FormBody.Builder()
                .add("username", "default")
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        memoResponse = mapper.readValue(response.body().string(), MemoResponse.class);
        assertNotNull(memoResponse.message);

        body = new FormBody.Builder()
                .add("username", "default")
                .add("password", "default")
                .add("type", "individual")
                .add("email", "default@gmail.com")
                .build();

        request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        response = client.newCall(request).execute();
        memoResponse = mapper.readValue(response.body().string(), MemoResponse.class);
        assertNotNull(memoResponse);
    }
}