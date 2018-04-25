package app.comps456f;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentPagerAdapter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import com.andreabaccega.widget.FormEditText;
import com.dd.processbutton.iml.ActionProcessButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.util.Log;
import android.view.View;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class CreatePost extends AppCompatActivity {

    //private int commentId;
    private String category;
    private String subject;
    private String text;
    private String time;
    private String name;

    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private String author = user.getUid();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_post);
        final Spinner spinner = (Spinner) findViewById(R.id.categoryspinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.categorylist, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        Button submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText content = (EditText) findViewById(R.id.content);
                text = content.getText().toString();
                FormEditText title = (FormEditText) findViewById(R.id.title);
                subject = title.getText().toString();
                category = spinner.getSelectedItem().toString();
                // Apr 24, 2018 6:21:40 PM
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                df.setTimeZone(TimeZone.getTimeZone("GMT+8"));
                Calendar c = Calendar.getInstance();
                time = df.format(c.getTime());
                writeComment();
                startActivity(new Intent(CreatePost.this, Discuss_tab.class));
            }
        });
    }




    public void writeComment(){
        //DatabaseReference ref = mDatabase.child("user").child(author).child("name");


        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                 name = dataSnapshot.child("user").child("author").child("name").getValue(String.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
            }
        };
        mDatabase.addValueEventListener(postListener);

        //Post post = new Post(author, category, subject, text, time);
        Post post = new Post(author, subject, text, time, name);
        mDatabase.child("comment").child(category).push().setValue(post);
    }


}
