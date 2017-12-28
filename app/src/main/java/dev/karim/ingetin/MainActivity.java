package dev.karim.ingetin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import dev.karim.ingetin.Fragment.ArtikelFragment;
import dev.karim.ingetin.Fragment.PengaturanFragment;
import dev.karim.ingetin.Fragment.ProfilFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    // toolbar titles respected to selected nav menu item
    private String[] activityTitles;

    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        activityTitles = getResources().getStringArray(R.array.nav_item_activity_titles);

        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(activityTitles[0]);

        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.containerView,new TabFragment()).commit();

//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(MenuItem menuItem) {
//                drawer.closeDrawers();
//
//                if (menuItem.getItemId() == R.id.nav_beranda) {
//                    FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
//                    fragmentTransaction.replace(R.id.containerView,new TabFragment()).commit();
//                    getSupportActionBar().setTitle(activityTitles[0]);
//                }
//                if (menuItem.getItemId() == R.id.nav_profil) {
//                    FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
//                    fragmentTransaction.replace(R.id.containerView,new TabFragment()).commit();
//                    getSupportActionBar().setTitle(activityTitles[1]);
//                }
//                if (menuItem.getItemId() == R.id.nav_tugas) {
//                    FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
//                    fragmentTransaction.replace(R.id.containerView,new TabFragment()).commit();
//                    getSupportActionBar().setTitle(activityTitles[1]);
//                }
//                if (menuItem.getItemId() == R.id.nav_organisasi) {
//                    FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
//                    fragmentTransaction.replace(R.id.containerView,new TabFragment()).commit();
//                    getSupportActionBar().setTitle(activityTitles[1]);
//                }
//                if (menuItem.getItemId() == R.id.nav_pengaturan) {
//                    FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
//                    fragmentTransaction.replace(R.id.containerView,new TabFragment()).commit();
//                    getSupportActionBar().setTitle(activityTitles[1]);
//                }
//                if (menuItem.getItemId() == R.id.nav_tentang) {
//                    Intent profile = new Intent (MainActivity.this, DemoMainActivity.class);
//                    startActivity(profile);
//                }
//
//
//
//                return false;
//            }
//
//        });
//
//        /**
//         * Setup Drawer Toggle of the Toolbar
//         */
//
//        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this,drawer, toolbar,R.string.app_name,
//                R.string.app_name);
//
//        drawer.setDrawerListener(mDrawerToggle);
//
//        mDrawerToggle.syncState();
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


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_beranda) {
            FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.containerView,new TabFragment()).commit();
            getSupportActionBar().setTitle(activityTitles[0]);
        } else if (id == R.id.nav_profil) {
            FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.containerView,new ProfilFragment()).commit();
            getSupportActionBar().setTitle(activityTitles[1]);
        } else if (id == R.id.nav_artikel) {
            FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.containerView,new ArtikelFragment()).commit();
            getSupportActionBar().setTitle(activityTitles[2]);
        } else if (id == R.id.nav_pengaturan) {
            FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.containerView,new PengaturanFragment()).commit();
            getSupportActionBar().setTitle(activityTitles[3]);
        } else if (id == R.id.nav_tentang) {
            Intent profile = new Intent (MainActivity.this, TentangKamiActivity.class);
            startActivity(profile);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
