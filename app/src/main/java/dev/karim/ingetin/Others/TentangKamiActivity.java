package dev.karim.ingetin.Others;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import dev.karim.ingetin.R;

public class TentangKamiActivity extends AppCompatActivity {

    CardView cv_github;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tentang_kami);

        cv_github = (CardView)findViewById(R.id.cv_github);

        cv_github.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://github.com/setakarim/ingetin"));
                startActivity(browserIntent);
            }
        });
    }
}
