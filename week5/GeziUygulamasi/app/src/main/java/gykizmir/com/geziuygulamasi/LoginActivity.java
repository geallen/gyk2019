package gykizmir.com.geziuygulamasi;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private Button registerBtn, loginBtn;
    private EditText userMailLoginVal, userPwLoginVal;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();

        registerBtn = (Button) findViewById(R.id.user_register_btn);
        loginBtn = (Button) findViewById(R.id.user_login_btn);

        userMailLoginVal = (EditText) findViewById(R.id.user_mail_login_et);
        userPwLoginVal = (EditText) findViewById(R.id.user_pw_login_et);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailAdr = userMailLoginVal.getText().toString().trim();
                String password = userPwLoginVal.getText().toString().trim();
                if(!emailAdr.isEmpty() && !password.isEmpty()){
                    goToLogin(emailAdr, password);
                } else {
                    Toast.makeText(getApplicationContext(), "Mail veya sifre bos olamaz!", Toast.LENGTH_LONG).show();
                }
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void goToLogin(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else{
                    Log.w("Fail", "signInWithEmail:failure", task.getException());
                    Toast.makeText(getApplicationContext(), "Boyle bir kullanici yok", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(),RegisterActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
