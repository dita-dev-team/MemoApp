package com.dita.dev.memoapp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.dita.dev.memoapp.R;
import com.dita.dev.memoapp.utility.ImagePickUp;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageView name = (ImageView) findViewById(R.id.profile_name_button);
        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText name = (EditText) findViewById(R.id.profile_name);
                name.setFocusableInTouchMode(true);
                name.requestFocus();
            }
        });

        ImageView email = (ImageView) findViewById(R.id.profile_email_button);
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText email = (EditText) findViewById(R.id.profile_email);
                email.setFocusableInTouchMode(true);
                email.requestFocus();
            }
        });

        ImageView description = (ImageView) findViewById(R.id.profile_desc_button);
        description.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText desc = (EditText) findViewById(R.id.profile_desc);
                desc.setFocusableInTouchMode(true);
                desc.requestFocus();
            }
        });

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
}
