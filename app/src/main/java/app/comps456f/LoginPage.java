package app.comps456f;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dd.processbutton.iml.ActionProcessButton;

/**
 * Created by hoyin on 10/1/2018.
 */

public class LoginPage extends AppCompatActivity implements View.OnClickListener{
    private View view;
    private ActionProcessButton btnLogin, btnRegister;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login);
        init();
    }

    public void init(){
        btnRegister = (ActionProcessButton)findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
                Intent intent=new Intent();
                if(view== view.findViewById(R.id.btnRegister)){
                    intent.setClass(this,RegisterPage.class);
                    startActivity(intent);
                }
    }
}
