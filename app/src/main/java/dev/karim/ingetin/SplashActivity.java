package dev.karim.ingetin;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import dev.karim.ingetin.Others.EditProfilActivity;

public class SplashActivity extends AppCompatActivity {

    private static boolean splashLoaded = false;
    //private RealmHelper realmHelper = new RealmHelper(SplashActivity.this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!splashLoaded) {
            setContentView(R.layout.activity_splash);
            int secondsDelayed = 1;
            new Handler().postDelayed(new Runnable() {
                public void run() {

                    try {
                        RealmHelper realmHelper = new RealmHelper(SplashActivity.this);
                        if (realmHelper.findAllProfil().isEmpty()) {
                            startActivity(new Intent(SplashActivity.this, EditProfilActivity.class));
                            finish();
                        } else {
                            startActivity(new Intent(SplashActivity.this, MainActivity.class));
                            finish();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }, secondsDelayed * 3000);

            splashLoaded = true;
        }
        else {
            Intent goToMainActivity = new Intent(SplashActivity.this, MainActivity.class);
            goToMainActivity.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(goToMainActivity);
            finish();
        }
    }
}
