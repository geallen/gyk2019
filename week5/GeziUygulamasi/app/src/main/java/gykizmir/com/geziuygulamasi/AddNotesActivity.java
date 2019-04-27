package gykizmir.com.geziuygulamasi;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddNotesActivity extends AppCompatActivity {

    private EditText noteDetailTxt;
    private Button addNoteBtn, goToNotesBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notes);

        noteDetailTxt = (EditText) findViewById(R.id.note_detail_tw);
        addNoteBtn = (Button) findViewById(R.id.yeni_note_btn);
        goToNotesBtn = (Button) findViewById(R.id.gotonotes_btn);


        addNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNote();
            }
        });

        goToNotesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void addNote(){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference().child("GezdigimYerler");

        String noteId = databaseReference.push().getKey();

        String userNoteDetail = noteDetailTxt.getText().toString();

        if(userNoteDetail.length() >0 ){

            databaseReference.child(noteId).child("sehirAdi").setValue(userNoteDetail);
            showDialog("Islem Basarili", "Notunuz kaydedildi");
        } else{
            showDialog("Hata", "Not alani bos gecilemez");
        }
    }

   private void showDialog(String title, String msg){
       AlertDialog.Builder builder = new AlertDialog.Builder(AddNotesActivity.this);
       builder.setTitle(title);
       builder.setMessage(msg);
       builder.setNegativeButton("TAMAM", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialog, int which) {

           }
       });

       builder.show();


   }
}
