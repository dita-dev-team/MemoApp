package com.dita.dev.memoapp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.dita.dev.memoapp.R;
import com.dita.dev.memoapp.settings.PrefSettings;
import com.dita.dev.memoapp.utility.ImagePickUp;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {

    @Bind(R.id.profile_name)
    EditText userName;
    @Bind(R.id.profile_email)
    EditText userEmail;
    @Bind(R.id.profile_desc)
    EditText userDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);

        Init(this);

        CircleImageView profileImage = (CircleImageView) findViewById(R.id.profile_image);
        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ImagePickUp.openMediaSelector(ProfileActivity.this);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == ImagePickUp.IMAGE_FILE_CODE) {
                if (data != null) {
                    System.out.println("is not null");
                } else {
                    System.out.println("is null");
                }
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Init(this);
    }

    @OnClick(R.id.profile_edit_button)
    public void EditProfile(View view) {
        startActivity(new Intent(this, EditProfileActivity.class));
    }

    public void Init(Context context) {
        userName.setText(PrefSettings.getPrefUserName(context));
        userEmail.setText(PrefSettings.getPrefUserEmail(context));
        userDesc.setText(PrefSettings.getPrefUserDescription(context));
    }
}
