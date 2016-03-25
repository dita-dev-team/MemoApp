package com.dita.dev.memoapp.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.dita.dev.memoapp.R;
import com.dita.dev.memoapp.settings.PrefSettings;

import butterknife.Bind;
import butterknife.ButterKnife;

public class EditProfileActivity extends AppCompatActivity {

    @Bind(R.id.user_name)
    EditText userName;
    @Bind(R.id.user_email)
    EditText userEmail;
    @Bind(R.id.user_description)
    EditText userDesc;


    Context context;
    String username, email, desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        ButterKnife.bind(this);
        context = this;
        Init(this);
    }

    public void Init(Context context) {
        userName.setText(PrefSettings.getPrefUserName(context));
        userEmail.setText(PrefSettings.getPrefUserEmail(context));
        userDesc.setText(PrefSettings.getPrefUserDescription(context));
    }

    public void SaveProfile(View view) {
        username = userName.getText().toString().trim();
        email = userEmail.getText().toString().trim();
        desc = userDesc.getText().toString().trim();
        //Context context = getApplicationContext();

        PrefSettings.setPrefUserName(context, username);
        PrefSettings.setPrefUserEmail(context, email);
        PrefSettings.setPrefUserDescription(context, desc);
        onBackPressed();
    }
}
