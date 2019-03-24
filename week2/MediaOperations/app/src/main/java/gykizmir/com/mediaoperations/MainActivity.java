package gykizmir.com.mediaoperations;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button fotoBtn, sesBtn, haritaBtn, webBtn, smsBtn, aramaBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fotoBtn = (Button) findViewById(R.id.fotoCek);
        sesBtn = (Button) findViewById(R.id.sesKaydet);
        haritaBtn = (Button) findViewById(R.id.haritayaGit);
        webBtn = (Button) findViewById(R.id.webSayfasinaGit);
        smsBtn = (Button) findViewById(R.id.smsGonder);
        aramaBtn = (Button) findViewById(R.id.aramaYap);

        fotoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeActivity(v);
            }
        });
        sesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeActivity(v);
            }
        });
        webBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeActivity(v);
            }
        });
        haritaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeActivity(v);
            }
        });

        aramaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeActivity(v);
            }
        });
        smsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeActivity(v);
            }
        });
    }

    public void changeActivity(View view){
        if(view == fotoBtn) {
            Intent intent = new Intent(MainActivity.this, FotografActivity.class);
            startActivity(intent);
        } else if(view == sesBtn){
            Intent intent = new Intent(MainActivity.this, SesActivity.class);
            startActivity(intent);
        } else if (view == webBtn){
            Intent intent = new Intent(MainActivity.this, WebActivity.class);
            startActivity(intent);
        } else if(view == haritaBtn){
            Intent intent = new Intent(MainActivity.this, HaritaActivity.class);
            startActivity(intent);
        } else if(view == aramaBtn){
            Intent intent = new Intent(MainActivity.this, AramaActivity.class);
            startActivity(intent);
        } else if(view == smsBtn){
            Intent intent = new Intent(MainActivity.this, SmsActivity.class);
            startActivity(intent);
        }

    }
}
