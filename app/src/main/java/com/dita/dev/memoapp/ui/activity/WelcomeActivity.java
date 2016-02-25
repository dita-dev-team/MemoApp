package com.dita.dev.memoapp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.dita.dev.memoapp.R;
import com.dita.dev.memoapp.bus.LoginFail;
import com.dita.dev.memoapp.bus.LoginSucces;
import com.dita.dev.memoapp.bus.SignInEvent;
import com.dita.dev.memoapp.bus.SignUpEvent;
import com.dita.dev.memoapp.data.DataManager;
import com.dita.dev.memoapp.data.model.Message;
import com.dita.dev.memoapp.settings.PrefSettings;
import com.dita.dev.memoapp.ui.fragment.LoginFragment;
import com.dita.dev.memoapp.ui.fragment.RegisterFragment;
import com.dita.dev.memoapp.ui.fragment.WelcomeActivityFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;


public class WelcomeActivity extends AppCompatActivity {
    @Bind(R.id.sync_result)
    SwipeRefreshLayout refreshLayout;
    private DataManager dataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        EventBus.getDefault().register(this);
        ButterKnife.bind(this);
        Fragment welcome = new WelcomeActivityFragment();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment, welcome).commit();
        refreshLayout.setColorSchemeColors(R.color.black);

        dataManager = new DataManager();
    }

    //CREATING A NEW USER
    @Subscribe
    public void onEvent(SignUpEvent event) {
        Log.d("Signup: ", event.toString());
        dataManager.createUser(event.name, event.passwd, event.userType);
        //refreshLayout.setRefreshing(true);
    }

    @Subscribe
    public void onEvent(SignInEvent event) {
        dataManager.individualAuth(event.id, event.passwd);
        Toast.makeText(WelcomeActivity.this, event.toString(), Toast.LENGTH_SHORT).show();
        refreshLayout.setRefreshing(true);
    }

    @Subscribe
    public void onEvent(LoginSucces loginSucces) {
        PrefSettings.markLoggedIn(getApplicationContext(), true);
        startActivity(new Intent(getApplicationContext(), BaseActivity.class));
        /*new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(false);
                startActivity(new Intent(getApplicationContext(), BaseActivity.class));
            }
        }, 5000);*/

    }

    @Subscribe
    public void onEvent(LoginFail loginFail) {
        refreshLayout.setRefreshing(false);
    }

    public void register(View view) {
        // startActivity(new Intent(this, SignupActivity.class));
        Fragment register = new RegisterFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment, register).addToBackStack(null).commit();
    }

    public void login(View view) {
        // startActivity(new Intent(this, LoginActivity.class));

        Fragment login = new LoginFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment, login).addToBackStack(null).commit();
        //dataManager.RxtryAuth();
        //dataManager.individualAuth();
    }

    public void trylog(Message message){
        try {
            if (message.message!=null){
                Log.i("Message: ", message.toString());
            }
        }finally {
            Log.i("Message: ", message.Error);
        }
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
