package com.dita.dev.memoapp.ui.activity;


import android.content.ContentValues;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import com.dita.dev.memoapp.R;
import com.dita.dev.memoapp.data.database.MemosColumns;

import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ComposeActivity extends AppCompatActivity {
    @Bind(R.id.edtxtTo)
    EditText Username;
    @Bind(R.id.edtxSubject)
    EditText Subject;
    @Bind(R.id.edtxtCompose)
    EditText Message;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);

    }

    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setupActionBar();
        ButterKnife.bind(this);




    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // API 5+ solution
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void saveDraft(MenuItem item) {
        Date date = new Date();
        ContentValues values = new ContentValues();
        values.put(MemosColumns.USERNAME, Username.getText().toString().trim());
        values.put(MemosColumns.SUBJECT, Subject.getText().toString().trim());
        values.put(MemosColumns.MESSAGE, Message.getText().toString().trim());
        values.put(MemosColumns.STATUS, "draft");
        /*values.put(MemosColumns.DATE,Integer.valueOf(date.toString()));*/
        getContentResolver().insert(MemoContract.Memos.CONTENT_URI, values);

    }
}
