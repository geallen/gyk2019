package gykizmir.com.mediaoperations;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HaritaActivity extends AppCompatActivity {

    private Button haritaBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_harita);

        haritaBtn = (Button) findViewById(R.id.haritaGoster);

        haritaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                haritaGoster();
            }
        });
        //geo: 48.7412356, 12.1203698
    }

    public void haritaGoster(){

        Uri uri = Uri.parse("geo: 0,0?q= Uckuyular Metro");
        Intent i = new Intent(Intent.ACTION_VIEW, uri);

        if(i.resolveActivity(getPackageManager()) != null){
            startActivity(i);
        }
    }
}
