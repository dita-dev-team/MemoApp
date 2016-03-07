package com.dita.dev.memoapp.data;

import android.util.Log;

import com.dita.dev.memoapp.data.model.Message;
import com.dita.dev.memoapp.data.remote.RemoteClient;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DataManager {
    static final String TAG = DataManager.class.getName();
    RemoteClient remoteClient;

    public DataManager() {
        remoteClient = new RemoteClient();
    }


    public void createUser(String username, String passwd, String userType, String email) {
        Call<Message> userCreate = remoteClient.getEndpoints().userCreate(username, passwd, userType, email);
        userCreate.enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                if (response.isSuccess()) {
                    Log.i(TAG, "Success " + response.body());
                } else {
                    try {
                        Log.i(TAG, response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {
                Log.e(TAG, Log.getStackTraceString(t));
            }
        });
    }


}
