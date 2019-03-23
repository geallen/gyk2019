package gykizmir.com.mediaoperations;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

public class FotografActivity extends AppCompatActivity {

    private Button fotoBtn, videoBtn;
    private static  final int VIDEO_CODE = 101;
    private static  final int IIMAGE_CODE = 102;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fotograf);

        fotoBtn = (Button) findViewById(R.id.resimCek);
        videoBtn = (Button) findViewById(R.id.videoCek);

        fotoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeNewPhoto();
            }
        });

        videoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeNewVideo();
            }
        });
    }
    private void  takeNewPhoto(){
            Intent photoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(photoIntent, IIMAGE_CODE);
     }

    private void  takeNewVideo(){
        Intent videoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        startActivityForResult(videoIntent, VIDEO_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode != RESULT_OK) return;

        switch (requestCode){
            case IIMAGE_CODE:
                Bundle extras = data.getExtras();
                ((ImageView) findViewById(R.id.img)).setImageBitmap((Bitmap) extras.get("data"));
                break;
            case VIDEO_CODE:

                VideoView videoView = (VideoView) findViewById(R.id.videoView);
                videoView.setVideoURI(data.getData());
                videoView.setMediaController(new MediaController(this));

                videoView.requestFocus();
                videoView.start();
                break;
            default:
                break;
        }
    }
}
