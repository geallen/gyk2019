package gykizmir.com.mediaoperations;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SmsActivity extends AppCompatActivity {

    private EditText msjIcerik, telNoTxt;
    private Button msjGonderBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
        msjIcerik = (EditText) findViewById(R.id.msjIcerik);
        telNoTxt = (EditText) findViewById(R.id.telNoMsj);
        msjGonderBtn = (Button) findViewById(R.id.mesajGonderBtn);

        msjGonderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mesajGonder(telNoTxt.getText().toString(), msjIcerik.getText().toString());
            }
        });

    }

    public void mesajGonder(String telNo, String icerik){
        Uri uri = Uri.parse("sms:" + telNo);
        Intent i = new Intent(Intent.ACTION_SENDTO, uri);
        i.putExtra("sms_body" , icerik);

        if (i.resolveActivity(getPackageManager()) != null){
            startActivity(i);
        }
    }

}






