package app.comps456f;

import android.content.Intent;
import android.content.SearchRecentSuggestionsProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.andreabaccega.widget.FormEditText;
import com.dd.processbutton.iml.ActionProcessButton;

import java.util.ArrayList;

public class Notification extends AppCompatActivity {
    private FormEditText subject, notificationTitle, Ncontent;
    private ActionProcessButton btnNotification;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        Intent intent = getIntent();
        String[] notification_list = intent.getStringArrayExtra(TeacherPage.list_notification);
        subject = (FormEditText)findViewById(R.id.subject);
        for (int i = 0; i<notification_list.length; i++){
            subject.setText(notification_list[i] + "  ");
        }
        notificationTitle = (FormEditText)findViewById(R.id.notificationTitle);
        Ncontent = (FormEditText)findViewById(R.id.Ncontent);
        btnNotification = (ActionProcessButton)findViewById(R.id.btnNotification);


    }
}
