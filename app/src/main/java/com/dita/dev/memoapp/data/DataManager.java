package com.dita.dev.memoapp.data;

import android.util.Log;

import com.dita.dev.memoapp.bus.RegisterStatus;
import com.dita.dev.memoapp.bus.SignInEvent;
import com.dita.dev.memoapp.bus.SignInStatus;
import com.dita.dev.memoapp.data.model.MemoResponse;
import com.dita.dev.memoapp.data.model.Token;
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
        final RegisterStatus status = new RegisterStatus();
        Call<MemoResponse> userCreate = remoteClient.getEndpoints().userCreate(username, passwd, userType, email);
        userCreate.enqueue(new Callback<MemoResponse>() {
            @Override
            public void onResponse(Call<MemoResponse> call, Response<MemoResponse> response) {
                Log.d(TAG, new Integer(response.code()).toString());
                if (response.isSuccess()) {
                    status.success = true;
                    Log.i(TAG, "Success " + response.body());
                } else {
                    try {
                        MemoResponse memoResponse = new ObjectMapper().readValue(response.errorBody().string(), MemoResponse.class);

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

    public void authUser(String username, String passwd) {
        final SignInStatus signInStatus = new SignInStatus();
        Call<MemoResponse> authUser = remoteClient.getEndpoints().authUser(username, passwd);
        authUser.enqueue(new Callback<MemoResponse>() {
            @Override
            public void onResponse(Call<MemoResponse> call, Response<MemoResponse> response) {
                Log.d(TAG, new Integer(response.code()).toString());
                if (response.isSuccess()) {

                    signInStatus.success = true;
                    signInStatus.token = ((Token) response.body().data.data).token;
                    EventBus.getDefault().post(signInStatus);
                    Log.i(TAG, "Success " + response.body());
                } else {
                    try {
                        MemoResponse memoResponse = new ObjectMapper().readValue(response.errorBody().string(), MemoResponse.class);
                        signInStatus.message = memoResponse.message;
                        EventBus.getDefault().post(signInStatus);
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
