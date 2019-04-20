package gykizmir.com.geziuygulamasi;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

public class RegisterActivity extends AppCompatActivity {

    private EditText userMailRegisterEt,userPwRegisterEt, confirmUserPwRegisterEt;
    private Button registerBtn;
    private String userEmail, userPw;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();

        userMailRegisterEt = (EditText) findViewById(R.id.user_mail_register_et);
        userPwRegisterEt = (EditText) findViewById(R.id.user_pw_register_et);
        confirmUserPwRegisterEt = (EditText) findViewById(R.id.confirm_user_pw_register_et);
        registerBtn = (Button) findViewById(R.id.register_btn);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUserRegisterInfo();
            }
        });
    }

    private void getUserRegisterInfo(){
        userEmail = userMailRegisterEt.getText().toString().trim();
        userPw = userPwRegisterEt.getText().toString().trim();
        String confirmUserPw = confirmUserPwRegisterEt.getText().toString().trim();

        if(!userEmail.isEmpty() && !userPw.isEmpty() && !confirmUserPw.isEmpty()){
            if(userPw.equals(confirmUserPw)){
                register();
            }
        } else{
            Toast.makeText(getApplicationContext(),"Tum alanlari doldurunuz", Toast.LENGTH_LONG).show();
        }
    }

    private void register(){

        mAuth.createUserWithEmailAndPassword(userEmail, userPw).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Kayit islemi basarili", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        }).addOnFailureListener(this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                if(e instanceof FirebaseAuthException){
                    if(((FirebaseAuthException) e).getErrorCode().equals("ERROR_WEAK_PASSWORD")){
                        Toast.makeText(getApplicationContext(), "Kolay sifre", Toast.LENGTH_LONG).show();
                    } else if(((FirebaseAuthException) e).getErrorCode().equals("ERROR_INVALID_EMAIL")){
                        Toast.makeText(getApplicationContext(), "Gecersiz Mail", Toast.LENGTH_LONG).show();
                    } else if(((FirebaseAuthException) e).getErrorCode().equals("ERROR_EMAIL_ALREADY_IN_USE")){
                        Toast.makeText(getApplicationContext(), "Mail zaten kayitli", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}
