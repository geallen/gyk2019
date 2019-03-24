package gykizmir.com.example01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button girisBtn;
    private EditText isimTxt;
    private EditText sifreTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        girisBtn = (Button) findViewById(R.id.giris);
        isimTxt = (EditText) findViewById(R.id.isim);
        sifreTxt = (EditText) findViewById(R.id.sifre);

        girisBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
                startActivity(intent);
            }
        });


    }
}
