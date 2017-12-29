package dev.karim.ingetin;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import dev.karim.ingetin.Others.ArtikelFragment;
import dev.karim.ingetin.Others.PengaturanFragment;
import dev.karim.ingetin.Others.ProfilFragment;
import dev.karim.ingetin.Others.TentangKamiActivity;

/**
 * Created by Karim on 12/29/2017.
 */

public class MainActivityOld extends AppCompatActivity
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
            Intent profile = new Intent (MainActivityOld.this, TentangKamiActivity.class);
            startActivity(profile);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
