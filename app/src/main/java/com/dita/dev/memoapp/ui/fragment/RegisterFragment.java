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
import android.widget.Spinner;
import android.widget.TextView;

import com.dita.dev.memoapp.R;
import com.dita.dev.memoapp.bus.SignUpEvent;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends BaseFragment {

    @Bind(R.id.signup_username)
    EditText usernameEditText;
    @Bind(R.id.signup_pass)
    EditText passEditText;
    @Bind(R.id.signup_pass1)
    EditText pass1EditText;
    @Bind(R.id.user_type)
    Spinner userTypeSpinner;

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
        //passEditText.setOnEditorActionListener(this);
        pass1EditText.setOnEditorActionListener(this);
        return view;
    }

    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if (i == EditorInfo.IME_ACTION_DONE) {
            InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getView().getWindowToken(), 0);
            login();
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
        username = usernameEditText.getText().toString();
        passwd = passEditText.getText().toString().trim();
        passwd1 = pass1EditText.getText().toString().trim();
        userType = userTypeSpinner.getSelectedItem().toString().toLowerCase();
        if (passwd.equals(passwd1)) {
            signUpEvent = new SignUpEvent(username, passwd, userType);
            EventBus.getDefault().post(signUpEvent);
        } else {
            pass1EditText.setError("Password not similar");
        }


    }

    private void login() {

        userType = usernameEditText.getText().toString().trim();
        passwd = passEditText.getText().toString().trim();
        signUpEvent = new SignUpEvent(userType, username, passwd);
        EventBus.getDefault().post(signUpEvent);

    }
}
