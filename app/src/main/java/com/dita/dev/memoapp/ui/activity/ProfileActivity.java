package com.dita.dev.memoapp.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.dita.dev.memoapp.R;
import com.dita.dev.memoapp.settings.PrefSettings;
import com.dita.dev.memoapp.utility.ImagePickUp;
import com.dita.dev.memoapp.utility.ImageUtils;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {

    private static final int IMAGE_PICKER_SELECT = 1;
    //Random activity request code
    private static final int ACTIVITY_REQUEST = 53642;
    @Bind(R.id.profile_name)
    EditText userName;
    @Bind(R.id.profile_email)
    EditText userEmail;
    @Bind(R.id.profile_desc)
    EditText userDesc;
    CircleImageView profileImage;
    //Output uri of selected image
    private android.net.Uri outputFileUri;
    private android.net.Uri newFileUri;

    private boolean isCamera;

    public static Bitmap getBitmapFromCameraData(Uri uri, Context context) {
        Uri selectedImage = uri;
        String[] filePathColumn = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(selectedImage, filePathColumn, null, null, null);
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        String picturePath = cursor.getString(columnIndex);
        cursor.close();
        return BitmapFactory.decodeFile(picturePath);
    }

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

        profileImage = (CircleImageView) findViewById(R.id.profile_image);
        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (newFileUri == null)
                    newFileUri = Uri.fromFile(ImageUtils.getOutputMediaFile());
                Intent intent = ImagePickUp.createGalleryAndCameraIntent(getApplicationContext(), newFileUri);
                startActivityForResult(intent, ACTIVITY_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ACTIVITY_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                if (data == null) {
                    isCamera = true;
                    System.out.println("Camera");
                } else {
                    System.out.println("data is not null");
                    final String action = data.getAction();
                    isCamera = action != null && action.equals("inline-data");
                }
                if (!isCamera) {
                    System.out.println("Gallery");
                    outputFileUri = data.getData();
                    System.out.println(outputFileUri);
                    try {
                        Bitmap selectedImage = MediaStore.Images.Media.getBitmap(this.getContentResolver(), outputFileUri);
                        profileImage.setImageBitmap(selectedImage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    outputFileUri = newFileUri;
                    System.out.println("please work");
                    System.out.println(outputFileUri.getPath());
                    int width = profileImage.getWidth();
                    int height = profileImage.getHeight();

                    BitmapFactory.Options factoryOptions = new BitmapFactory.Options();
                    factoryOptions.inJustDecodeBounds = true;
                    BitmapFactory.decodeFile(outputFileUri.getPath(), factoryOptions);

                    int imageHeight = factoryOptions.outHeight;
                    int imageWidth = factoryOptions.outWidth;
                    int scaleFactor = Math.min(imageWidth / width, imageHeight / height);

                    factoryOptions.inJustDecodeBounds = false;
                    factoryOptions.inSampleSize = scaleFactor;

                    Bitmap bitmap = BitmapFactory.decodeFile(outputFileUri.getPath(), factoryOptions);
                    profileImage.setImageBitmap(bitmap);
                }

            }
        }
    }

    public String getRealPathFromUri(Context context, Uri uri) {
        Cursor cursor = null;
        try {
            String[] projections = {MediaStore.Images.Media.DATA};
            cursor = context.getContentResolver().query(uri, projections, null, null, null);
            int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(columnIndex);
        } finally {
            if (cursor != null) {
                cursor.close();
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
