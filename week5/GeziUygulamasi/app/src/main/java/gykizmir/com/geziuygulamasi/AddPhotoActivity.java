package gykizmir.com.geziuygulamasi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class AddPhotoActivity extends AppCompatActivity {

    private ImageView selectedPhoto;
    private Button selectPhotoBtn, savePhotoBtn;
    private static final int IMAGE_REQUEST = 111;

    private ProgressDialog progressDialog;
    private FirebaseStorage firebaseStorage;
    private Uri filePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_photo);

        firebaseStorage = FirebaseStorage.getInstance();

        showPhoto();
        selectedPhoto = (ImageView) findViewById(R.id.selected_photo_id);
        selectPhotoBtn = (Button) findViewById(R.id.select_photo);
        savePhotoBtn = (Button) findViewById(R.id.save_photo);

        selectPhotoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectPhoto();
            }
        });


        savePhotoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePhotoToFirebase();
            }
        });
    }

    private void selectPhoto(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Resim Seciniz"), IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null){
            filePath = data.getData();

            try {

                Picasso.with(AddPhotoActivity.this).load(filePath).fit().centerCrop().into(selectedPhoto);



            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }


    private void savePhotoToFirebase(){
        if(filePath != null){
            showProgressDialog();
            StorageReference storageReference = firebaseStorage.getReference();
            storageReference.child("userprofilephoto").putFile(filePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    progressDialog.dismiss();
                    Toast.makeText(AddPhotoActivity.this, "Fotografiniz basariyla yuklendi", Toast.LENGTH_LONG).show();
                    onBackPressed();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressDialog.dismiss();
                    Toast.makeText(AddPhotoActivity.this, "Fotografiniz yuklenemedi", Toast.LENGTH_LONG).show();
                }
            });


        }
    }


    private void showPhoto(){
        showProgressDialog();
        StorageReference storageReference = firebaseStorage.getReference();

        storageReference.child("userprofilephoto").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                progressDialog.dismiss();
                Picasso.with(AddPhotoActivity.this).load(uri).fit().centerCrop().into(selectedPhoto);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                Toast.makeText(AddPhotoActivity.this, "Fotograf Yuklenemedi", Toast.LENGTH_LONG).show();
            }
        });

    }


    private void showProgressDialog(){
        progressDialog = new ProgressDialog(AddPhotoActivity.this);
        progressDialog.setMessage("Yukleniyor ...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

}
