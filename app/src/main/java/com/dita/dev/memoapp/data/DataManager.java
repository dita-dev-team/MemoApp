package com.dita.dev.memoapp.data;

import android.util.Log;

import com.dita.dev.memoapp.bus.RegisterStatus;
import com.dita.dev.memoapp.data.model.MemoResponse;
import com.dita.dev.memoapp.data.remote.RemoteClient;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import de.greenrobot.event.EventBus;
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
        Call<MemoResponse> userCreate = remoteClient.getEndpoints().userCreate(username, passwd, userType, email);
        userCreate.enqueue(new Callback<MemoResponse>() {
            @Override
            public void onResponse(Call<MemoResponse> call, Response<MemoResponse> response) {
                Log.d(TAG, new Integer(response.code()).toString());
                if (response.isSuccess()) {
                    Log.i(TAG, "Success " + response.body());
                } else {
                    try {
                        MemoResponse memoResponse = new ObjectMapper().readValue(response.errorBody().string(), MemoResponse.class);
                        RegisterStatus status = new RegisterStatus();
                        status.message = memoResponse.message;
                        EventBus.getDefault().post(status);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<MemoResponse> call, Throwable t) {
                Log.e(TAG, Log.getStackTraceString(t));
            }
        });
    }


}
