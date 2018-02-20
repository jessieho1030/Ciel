package app.comps456f;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.andreabaccega.widget.FormEditText;
import com.dd.processbutton.iml.ActionProcessButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Transaction;

/**
 * Created by hoyin on 10/1/2018.
 */

public class RegisterPage extends AppCompatActivity implements View.OnClickListener{
    private FormEditText emailText, passwordText, repasswordText, nameText;
    private ActionProcessButton btnSubmit;
    private FirebaseAuth firebaseAuth;
    private MyProgressDialog progressDialog;
    private Handler handler;
    private DatabaseReference databaseReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        progressDialog = new MyProgressDialog(this,"Registering User...","Wait few seconds");
        handler = new Handler();
        firebaseAuth = FirebaseAuth.getInstance();
        btnSubmit = (ActionProcessButton)findViewById(R.id.btnSubmit);
        emailText = (FormEditText)findViewById(R.id.emailText);
        passwordText = (FormEditText)findViewById(R.id.passwordText);
        repasswordText = (FormEditText)findViewById(R.id.repasswordText);
        nameText = (FormEditText)findViewById(R.id.nameText);
        btnSubmit.setOnClickListener(this);
    }

   /* public boolean checkIntegrity() {
        boolean Valid = true;

        if (!repasswordText.getText().toString().equals(passwordText.getText().toString())) {
            repasswordText.setError("Confirm password did not match");
            Valid = false;
        }
        return Valid;
    }*/


    private void registerUser(){
        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();
        /*FormEditText[] allFields = {emailText,passwordText,repasswordText};
        boolean allValid = true;
        if (!checkIntegrity())
            allValid = false;
        for (FormEditText field : allFields) {
            allValid = field.testValidity() && allValid;
        }*/
        progressDialog.show();
            firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    progressDialog.dimiss();
                    if (task.isSuccessful()) {
                        Toast.makeText(RegisterPage.this, "Register Successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(RegisterPage.this, "Could not register...please try again", Toast.LENGTH_SHORT).show();
                    }
                }

        });

    }

    @Override
    public void onClick(View view) {
        if (view == btnSubmit){
            registerUser();
            Intent intent = new Intent();
            intent.setClass(this,LoginPage.class);
            startActivity(intent);
    }
    }
}

