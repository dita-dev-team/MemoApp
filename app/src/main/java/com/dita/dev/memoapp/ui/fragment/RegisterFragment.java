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
import com.dita.dev.memoapp.bus.SignUpEvent;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends BaseFragment {
    @Bind(R.id.signup_username)
    EditText signup_username;
    @Bind(R.id.signup_pass)
    EditText signup_pass;
    @Bind(R.id.signup_pass1)
    EditText signup_pass1;
    SignUpEvent signUpEvent;
    private String passwd1;
    private String passwd;
    private String username;
    private String userType;


    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        ButterKnife.bind(this, view);
        //signup_pass.setOnEditorActionListener(this);
        signup_pass1.setOnEditorActionListener(this);
        return view;
    }

    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if (i == EditorInfo.IME_ACTION_DONE) {
            InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getView().getWindowToken(), 0);
            //login();
            return true;
        }
        /*
        switch (i){
            case (EditorInfo.IME_ACTION_DONE):
                InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getView().getWindowToken(), 0);
                login();
                return true;
            case (EditorInfo.IME_ACTION_NEXT):

                return true;
        }
        */
        return false;
    }

    @OnClick(R.id.register_button)
    public void register(View view) {

    }

    /*private void login() {
        username = signup_username.getText().toString().trim();
        passwd = signup_pass.getText().toString().trim();
        passwd1 = signup_pass1.getText().toString().trim();
        signUpEvent = new SignUpEvent(name, id, passwd);
        EventBus.getDefault().post(signUpEvent);
        /*
        if (passwd == passwd1){
            signUpEvent = new SignUpEvent(name,id,passwd);
            EventBus.getDefault().post(signUpEvent);
        }
        else {
            signup_pass1.setError("Password not similar");
        }
        *
    }*/
}
