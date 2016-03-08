package com.dita.dev.memoapp.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.dita.dev.memoapp.R;
import com.dita.dev.memoapp.bus.RegisterEvent;
import com.dita.dev.memoapp.bus.SignInStatus;
import com.dita.dev.memoapp.bus.RegisterStatus;
import com.dita.dev.memoapp.bus.SignInEvent;
import com.dita.dev.memoapp.data.DataManager;
import com.dita.dev.memoapp.ui.fragment.LoginFragment;
import com.dita.dev.memoapp.ui.fragment.RegisterFragment;
import com.dita.dev.memoapp.ui.fragment.WelcomeActivityFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;


public class WelcomeActivity extends AppCompatActivity {
    static final String TAG = WelcomeActivity.class.getName();
    @Bind(R.id.sync_result)
    SwipeRefreshLayout refreshLayout;
    private DataManager dataManager;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        EventBus.getDefault().register(this);
        ButterKnife.bind(this);

        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment);

        if (fragment == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment, new WelcomeActivityFragment()).commit();
        }

        refreshLayout.setColorSchemeColors(R.color.black);

        dataManager = new DataManager();
    }

    // Create a new user
    @Subscribe
    public void onEvent(RegisterEvent event) {
        Log.d("Signup: ", event.toString());
        dataManager.createUser(event.username, event.passwd, event.userType, "default@gmail.com");
        //refreshLayout.setRefreshing(true);
    }

    // Sign in a user
    @Subscribe
    public void onEvent(SignInEvent event) {
        //refreshLayout.setRefreshing(true);
    }

    @Subscribe
    public void onEvent(RegisterStatus event) {
        Log.d(TAG, event.message);
    }

    /*@Subscribe
    public void onEvent(SignInStatus loginSucces) {
        PrefSettings.markLoggedIn(getApplicationContext(), true);
        startActivity(new Intent(getApplicationContext(), BaseActivity.class));
        *//*new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(false);
                startActivity(new Intent(getApplicationContext(), BaseActivity.class));
            }
        }, 5000);*//*

    }
*/
    @Subscribe
    public void onEvent(SignInStatus loginFail) {
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

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
