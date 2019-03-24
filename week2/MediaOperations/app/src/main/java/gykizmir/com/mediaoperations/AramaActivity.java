package gykizmir.com.mediaoperations;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AramaActivity extends AppCompatActivity {

    private EditText telNoTxt;
    private Button araBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arama);

        telNoTxt = (EditText) findViewById(R.id.telNo);
        araBtn = (Button) findViewById(R.id.araBtn);

        araBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aramaYap(telNoTxt.getText().toString());
            }
        });
    }

    public void aramaYap(String telNo){
        Uri uri = Uri.parse("tel:" + telNo);
        Intent i = new Intent(Intent.ACTION_DIAL, uri);
        if(i.resolveActivity(getPackageManager()) != null){
            startActivity(i);
        }
    }

}
