package app.comps456f;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.andreabaccega.widget.FormEditText;
import com.dd.processbutton.iml.ActionProcessButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by hoyin on 10/1/2018.
 */

public class LoginPage extends AppCompatActivity implements View.OnClickListener{
    private View view;
    private ActionProcessButton btnLogin, btnRegister;
    public FormEditText useremail,userpassword;
    private MyProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        init();
    }

    public void init(){

        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser() !=null){
        finish();
            startActivity(new Intent(getApplicationContext(),MainPage.class));
        }
        progressDialog = new MyProgressDialog(this,"Logining...","Please wait few seconds");
        useremail = (FormEditText)findViewById(R.id.useremail);
        userpassword = (FormEditText)findViewById(R.id.password);
       // btnRegister = (ActionProcessButton)findViewById(R.id.btnRegister);
        btnLogin = (ActionProcessButton)findViewById(R.id.btnLogin);
//        btnRegister.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
                Intent intent=new Intent();
               /* if(view== view.findViewById(R.id.btnRegister)){
                    intent.setClass(this,RegisterPage.class);
                    startActivity(intent);
                }*/
                if(view== view.findViewById(R.id.btnLogin)){
                    userLogin();

                }
    }

    public void userLogin(){
        String email = useremail.getText().toString();
        String password = userpassword.getText().toString();
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dimiss();
                if (task.isSuccessful()) {
                    finish();
                    startActivity(new Intent(getApplicationContext(),MainPage.class));
                    Toast.makeText(LoginPage.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
