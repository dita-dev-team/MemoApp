package com.dita.dev.memoapp.ui.fragment;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.dita.dev.memoapp.R;
import com.dita.dev.memoapp.bus.RegisterStatus;
import com.dita.dev.memoapp.bus.SignInEvent;
import com.dita.dev.memoapp.bus.SignInStatus;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends BaseFragment {
    @Bind(R.id.login_username)
    EditText login;
    @Bind(R.id.login_pass)
    EditText pass;

    String username;
    String passwd;
    SignInEvent signInEvent;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);
        pass.setOnEditorActionListener(this);
        return view;
    }

    @OnClick(R.id.sign_in_button)
    public void login(View view) {
        init();
        login();
    }

    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        init();
        if (i == EditorInfo.IME_ACTION_DONE) {
            InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getView().getWindowToken(), 0);
            login();
            return true;
        }
        return false;
    }

    private void init() {
        username = login.getText().toString().trim();
        passwd = pass.getText().toString().trim();
        signInEvent = new SignInEvent(username, passwd);
    }

    private void login() {
        //EventBus.getDefault().post(new SignInStatus());
        EventBus.getDefault().post(signInEvent);

    }
}
