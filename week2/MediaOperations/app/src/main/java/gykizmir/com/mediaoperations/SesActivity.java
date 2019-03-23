package gykizmir.com.mediaoperations;

import android.content.Context;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class SesActivity extends AppCompatActivity {


    private Button recordVoiceButton;
    private Button stopVoiceButton;
    private Button playVoiceButton;
    private MediaRecorder recorder;
    private MediaPlayer player;

    //    filePath değişkeni ise dosyanın kayıt edileceği yeri belirtir.
//    Biz burada harici SD kart üzerinde record.3gp adında bir dosyaya kayıt edilmesini belirtiyoruz.
    private final String filepath = Environment.getExternalStorageDirectory().getPath() + "/record.3gp";

    private static final int REQUEST_AUDIO_PERMISSION_CODE = 200;

// 2 tane izin lazim.

    //    <uses-permission android:name="android.permission.RECORD_AUDIO" />
//    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ses);

        recordVoiceButton = (Button) findViewById(R.id.record_voice_button);
        recordVoiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                control(v);
            }
        });
        stopVoiceButton = (Button) findViewById(R.id.stop_voice_button);
        stopVoiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                control(v);
            }
        });
        playVoiceButton = (Button) findViewById(R.id.play_voice_button);
        playVoiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                control(v);
            }
        });


    }

    public void control(View view) {
        // ses kaydetme islemi ise
        if (view == recordVoiceButton) {
            if (checkPermissions()) {
                //iznler varsa kkaydet
                startRecording();
            } else {
                // izin yoksa izinleri iste, sonra kaydet
                requestPermissions();
                startRecording();
            }
        } else if (view == stopVoiceButton) {
            stopRecording();
        } else if (view == playVoiceButton) {
            startPlaying();
        }

    }
//
//    MediaRecorder sınıfı Android'de ses kayıt işlemlerini gerçekleştiren sınıftır.
//    MediaPlayer ise bir ses kaynağını oynatmak için kullanılır.


    private void startRecording() {

        recorder = new MediaRecorder();
        //MIC değeri kaydın mikrofon üzerinden gelen ses ile yapılacağını gösterir.
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
//        setOutputFormat ise kaydın hangi sıkıştırma formatıyla çevrileceğini belirtir.
//        Biz burada ufak boyutlu bir dosya istediğimizden 3GP adı verilen sıkıştırma formatını tercih ettik.
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        // kaydedilecegi konumu belirtir
        recorder.setOutputFile(filepath);


        try {
            // prepare eder, hata almazsa baslatir yoksa catch e duseriz
            recorder.prepare();
            recorder.start();
            Toast.makeText(getApplicationContext(), "Kayıt Yapılıyor", Toast.LENGTH_LONG).show();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void stopRecording() {
        if (recorder != null) {
            Toast.makeText(getApplicationContext(), "Kayıt Durduruldu", Toast.LENGTH_LONG).show();
//            Kayıt durduğunda ses dosyası diske yazılır ve mikrofondan ses alma durdurulur
            recorder.stop();
            recorder.reset();
//            release metodundan sonra MediaRecorder bellekten silinir ve bir daha kullanılamaz hale gelir.
            recorder.release();
            recorder = null;
        }
    }


    private void startPlaying() {
        player = new MediaPlayer();
        player.setVolume(1.0f, 1.0f);
        try {
            player.setDataSource(filepath);
            player.prepare();
            player.start();
            Toast.makeText(getApplicationContext(), "Kayıt Çalıyor", Toast.LENGTH_LONG).show();
            // dineleme bittiginde setoncompletionlistener a duseriz.
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                @Override
                public void onCompletion(MediaPlayer arg0) {
                    player.stop();
                    player.release();
                    player = null;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean checkPermissions() {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);
        int result1 = ContextCompat.checkSelfPermission(getApplicationContext(), RECORD_AUDIO);
        return result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED;
    }

    //izinleri iste
    private void requestPermissions() {
        ActivityCompat.requestPermissions(SesActivity.this, new String[]{RECORD_AUDIO, WRITE_EXTERNAL_STORAGE},
                REQUEST_AUDIO_PERMISSION_CODE);
    }

    // izin isteme sonuc dondu
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_AUDIO_PERMISSION_CODE:
                if (grantResults.length > 0) {
                    boolean permissionToRecord = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean permissionToStore = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    if (permissionToRecord && permissionToStore) {
                        Toast.makeText(getApplicationContext(), "Permission Granted", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Permission Denied", Toast.LENGTH_LONG).show();
                    }
                }
                break;
        }
    }

}
