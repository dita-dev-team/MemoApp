package com.dita.dev.memoapp.data;

import android.util.Log;

import com.dita.dev.memoapp.bus.LoginFail;
import com.dita.dev.memoapp.bus.LoginSucces;
import com.dita.dev.memoapp.data.model.Message;
import com.dita.dev.memoapp.data.remote.RemoteHelper;

import de.greenrobot.event.EventBus;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class DataManager {
    RemoteHelper remoteHelper;

    public DataManager() {
        remoteHelper = new RemoteHelper();
    }

    public void individualAuth(String id, String passwd) {
        Call<Message> messageCall = remoteHelper.getMemoApi().individualAuth(/*MemoApi.BASIC_AUTH,*/id, passwd);
        messageCall.enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Response<Message> response) {
                if (response.isSuccess()) {
                    // request successful (status code 200, 201)
                    Log.i("Retrofit Success: ", String.valueOf(response.code()));
                    EventBus.getDefault().post(new LoginSucces());
                } else {
                    //request not successful (like 400,401,403 etc)
                    //Handle errors
                    Log.i("Retrofit Error: ", String.valueOf(response.code()));
                    EventBus.getDefault().post(new LoginFail());
                    //Log.i("Retrofit: ", result.message);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.i("Retrofit: ", t.getMessage());
            }
        });
    }

    public void createUser(String username, String passwd, String userType) {
        Call<Message> messageCall = remoteHelper.getMemoApi().userCreate(username, passwd, userType);
        messageCall.enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Response<Message> response) {
                if (response.isSuccess()) {
                    // request successful (status code 200, 201)
                    Log.i("Retrofit Success: ", String.valueOf(response.code()));
                    EventBus.getDefault().post(new LoginSucces());
                } else {
                    //request not successful (like 400,401,403 etc)
                    //Handle errors
                    Log.i("Retrofit Error: ", String.valueOf(response.code()));
                    //EventBus.getDefault().post(new LoginFail());
                    //Log.i("Retrofit: ", result.message);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.i("Retrofit Error: ", String.valueOf(t.getMessage()));
                EventBus.getDefault().post(new LoginFail());
            }
        });
    }


}
