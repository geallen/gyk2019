package gykizmir.com.zaruygulamasi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ImageView zar1,zar2;
    TextView kullaciSkor,cpuSkor,kullaciZar,cpuZar;
    Button bt;
    Random gen;
    int ToplamKullanici=0;
    int toplamCpu=0;
    int [] zarlar = {R.drawable.die1,R.drawable.die2,R.drawable.die3,R.drawable.die4,
    R.drawable.die5,R.drawable.die6};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        zar1 = (ImageView) findViewById(R.id.zar1ImageView);
        zar2 = (ImageView) findViewById(R.id.zar2ImageView);
        kullaciSkor= (TextView) findViewById(R.id.kullaniciSkor);
        cpuSkor= (TextView) findViewById(R.id.cpuSkor);
        kullaciZar= (TextView) findViewById(R.id.kullaniciZar);
        cpuZar = (TextView) findViewById(R.id.cpuZar);

        bt= (Button) findViewById(R.id.button2);
        gen = new Random();
        cpuSkor.setText("0");
        kullaciSkor.setText("0");

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = gen.nextInt(6);
                int j = gen.nextInt(6);
                zar1.setImageResource(zarlar[i]);
                zar2.setImageResource(zarlar[j]);
                kullaciZar.setText(Integer.toString(i+1));
                cpuZar.setText(Integer.toString(j+1));
                if (i>j){
                    ToplamKullanici++;
                } else if (i<j){
                    toplamCpu++;
                }
                else {
                    Toast.makeText(MainActivity.this,"Berabere",Toast.LENGTH_LONG).show();
                }
                cpuSkor.setText(Integer.toString(toplamCpu));
                kullaciSkor.setText(Integer.toString(ToplamKullanici));
            }
        });




    }
}
