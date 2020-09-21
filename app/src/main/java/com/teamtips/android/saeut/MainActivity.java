package com.teamtips.android.saeut;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.teamtips.android.saeut.func.dashboard.CreatePostActivity;
import com.teamtips.android.saeut.func.home.HomeFragment;
import com.teamtips.android.saeut.func.login.ui.generalLogin.SplashActivity;

import java.security.MessageDigest;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView navigation;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, SplashActivity.class);
        startActivity(intent);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.container_main, new HomeFragment()).commit();

        navigation = findViewById(R.id.navigation);
        BottomNavItemSelectedListener listener = new BottomNavItemSelectedListener(fragmentManager, toolbar);
        navigation.setOnNavigationItemSelectedListener(listener);
        bindNavigationDrawer();
        initTitle();
    }

    private void initTitle() {
        toolbar.post(() -> toolbar.setTitle(navigation.getMenu().getItem(0).getTitle()));
    }

    private void bindNavigationDrawer() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle =
                new ActionBarDrawerToggle(
                        this,
                        drawer,
                        toolbar,
                        R.string.navigation_drawer_open,
                        R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.nav_view);
        SideNavItemSelectedListener listener = new SideNavItemSelectedListener(drawer, navigation, getApplicationContext());
        navigationView.setNavigationItemSelectedListener(listener);
    }

    public void onFabClicked(View view) {
        Intent intent = new Intent(MainActivity.this, CreatePostActivity.class);
        startActivity(intent);
    }

    private void getAppKeyHash() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
//                Log.e("Hash key", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (Exception e) {
            Log.e("name not found", e.toString());
        }
    }
}
