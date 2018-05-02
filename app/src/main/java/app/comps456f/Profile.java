package app.comps456f;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.andreabaccega.widget.FormEditText;
import com.dd.processbutton.iml.ActionProcessButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
//TODO: Update Profile Java
/**
 * Created by hoyin on 21/3/2018.
 */
public class Profile extends Fragment{
    private View view;
    private FormEditText newPass, rePass;
    private FirebaseAuth auth;
    private ActionProcessButton submit;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        init();

        return view;

    }

    public void init(){
        newPass = (FormEditText)view.findViewById(R.id.newPass_txt);
        rePass = (FormEditText)view.findViewById(R.id.rePass_txt);
        submit = (ActionProcessButton)view.findViewById(R.id.btnSubmit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                change(view);
                Intent intent = new Intent(getActivity(), MainPage.class);
                startActivity(intent);
            }
        });
        auth = FirebaseAuth.getInstance();

    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }


    public void change(View v){
        FirebaseUser user = auth.getCurrentUser();
        String password = rePass.getText().toString();
        if(user != null){

                user.updatePassword(password).addOnCompleteListener(new OnCompleteListener<Void>() {

                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){

                        Toast.makeText(getContext(),"Your password has been changed successfully.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getActivity(),MainPage.class);
                        startActivity(intent);

                    }
                    else{

                        Toast.makeText(getContext(),"Password mismatch",Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }


}
