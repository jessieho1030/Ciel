package app.comps456f;

import android.content.Intent;
import android.content.SearchRecentSuggestionsProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.andreabaccega.widget.FormEditText;
import com.dd.processbutton.iml.ActionProcessButton;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

//TODO: Do Connection with firebase function by using node.js

public class Notification extends AppCompatActivity {
    private FormEditText subject, notificationTitle, Ncontent;
    private ActionProcessButton btnNotification;
    private FirebaseAuth fireauth;
    private final String from_user_id = fireauth.getInstance().getCurrentUser().getUid();
    private DatabaseReference noDatabase;
    private String user_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        Intent intent = getIntent();
        final String[] notification_list = intent.getStringArrayExtra(TeacherPage.list_notification);
        user_id = intent.getStringExtra("user_id");

        noDatabase = FirebaseDatabase.getInstance().getReference().child("notifications");

        if(fireauth.getInstance().getCurrentUser() != null) {


            Log.v("Hello","Userid from : " + from_user_id);

            notificationTitle = (FormEditText)findViewById(R.id.notificationTitle);
            Ncontent = (FormEditText)findViewById(R.id.Ncontent);
            subject = (FormEditText)findViewById(R.id.subject);
            for (int i = 0; i<notification_list.length; i++){
                subject.setText(notification_list[i] + "  ");
            }

            btnNotification = (ActionProcessButton)findViewById(R.id.btnNotification);
            btnNotification.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String title = notificationTitle.getText().toString();
                    String message = Ncontent.getText().toString();
                  //  String to = subject.getText().toString();
                    if(!TextUtils.isEmpty(message) && !TextUtils.isEmpty(title)){
                        HashMap<String,String> noData = new HashMap<String, String>();
                        noData.put("from", from_user_id);
                        noData.put("title", title);
                        noData.put("message", message);
                       // noData.put("to", to);

                        noDatabase.child(user_id).push().setValue(noData).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(Notification.this,"The notification is sent successfully", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }

                }
            });
        }
        else{
            Toast.makeText(Notification.this,"Cannot get the fking current user",Toast.LENGTH_SHORT).show();
        }


    }
}
