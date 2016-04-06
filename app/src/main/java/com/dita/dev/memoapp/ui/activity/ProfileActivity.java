package com.dita.dev.memoapp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.dita.dev.memoapp.R;
import com.dita.dev.memoapp.settings.PrefSettings;
import com.dita.dev.memoapp.utility.ImagePickUp;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

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
    @Bind(R.id.profile_image)
    CircleImageView profileImage;

    Bitmap bitmap;

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

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == ImagePickUp.REQUEST_CODE) {
                if (data != null) {
                    if (bitmap != null) {
                        bitmap.recycle();
                    }

                    try {
                        InputStream stream = getContentResolver().openInputStream(data.getData());
                        bitmap = BitmapFactory.decodeStream(stream);
                        stream.close();
                        profileImage.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
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
    public void editProfile(View view) {
        startActivity(new Intent(this, EditProfileActivity.class));
    }

    @OnClick(R.id.profile_image)
    public void editProfileImage(View view) {
        ImagePickUp.openMediaSelector(ProfileActivity.this);
        //Intent intent = new Intent();
        //intent.setType("image/*");
        //intent.setAction(Intent.ACTION_GET_CONTENT);
        //intent.addCategory(Intent.CATEGORY_OPENABLE);
        //startActivityForResult(intent, ImagePickUp.IMAGE_FILE_CODE);
    }

    public void Init(Context context) {
        userName.setText(PrefSettings.getPrefUserName(context));
        userEmail.setText(PrefSettings.getPrefUserEmail(context));
        userDesc.setText(PrefSettings.getPrefUserDescription(context));
    }
}
