package com.dita.dev.memoapp.ui.activity;

import android.content.Intent;
import android.media.audiofx.BassBoost;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.dita.dev.memoapp.R;
import com.dita.dev.memoapp.settings.PrefSettings;
import com.dita.dev.memoapp.ui.fragment.ConnectionsFragment;
import com.dita.dev.memoapp.ui.fragment.DocumentFragment;
import com.dita.dev.memoapp.ui.fragment.MemosFragment;

import de.hdodenhof.circleimageview.CircleImageView;


public class BaseActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_base);

        if (!PrefSettings.isLoggedIn(getApplicationContext())) {
            Intent intent = new Intent(this, WelcomeActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);

        CircleImageView profile = (CircleImageView) headerView.findViewById(R.id.profile_image);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.base, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fragmentManager = getSupportFragmentManager();

        if (id == R.id.nav_memos) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction().replace(R.id.base_content, new MemosFragment()).addToBackStack(null);
            transaction.commit();
            Toast.makeText(BaseActivity.this, "Memo pressed", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_connections) {
            Fragment fragment = new ConnectionsFragment();
            FragmentTransaction transaction = fragmentManager.beginTransaction().replace(R.id.base_content, fragment).addToBackStack(null);
            transaction.commit();

            Toast.makeText(BaseActivity.this, "Connections pressed", Toast.LENGTH_SHORT).show();
            //startActivity(new Intent(this, ConnectionsActivity.class));
        } else if (id == R.id.nav_docs) {
            Fragment fragment = new DocumentFragment();
            FragmentTransaction transaction = fragmentManager.beginTransaction().replace(R.id.base_content, fragment).addToBackStack(null);
            transaction.commit();

            Toast.makeText(BaseActivity.this, "Documents pressed", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_settings) {
            {
                Intent settings = new Intent(this, SettingsActivity.class);
                startActivity(settings);
                finish();
            }
        } else if (id == R.id.nav_help) {
            Toast.makeText(BaseActivity.this, "Help pressed", Toast.LENGTH_SHORT).show();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void openProfile(View view) {
        startActivity(new Intent(this, ProfileActivity.class));
    }

    public void launchSettingsActivity(MenuItem item) {

        Intent launchSettings = new Intent(this, SettingsActivity.class);
        startActivity(launchSettings);
        finish();
}
}
