package app.comps456f;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.andreabaccega.widget.FormEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by meiyuk on 1/5/2018.
 */

public class ReadNewPost extends AppCompatActivity{
    public TextView name, subject, text, time;
    private String category, post_text, post_time;
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private String author = user.getUid();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.readpost);
        Intent intent = getIntent();
        category = intent.getStringExtra("category");

        mDatabase.child("user/"+ author +"/name").addListenerForSingleValueEvent (new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                name = (TextView)findViewById(R.id.stud_name);
                String post_name = dataSnapshot.getValue(String.class);
                Log.d("name", post_name);
                name.setText(post_name);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });



        subject = (TextView)findViewById(R.id.title);
        text = (TextView)findViewById(R.id.content);
        time = (TextView)findViewById(R.id.date);

        subject.setText(intent.getStringExtra("subject"));
        text.setText(intent.getStringExtra("text"));
        time.setText(intent.getStringExtra("time"));

        Button submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FormEditText comment = (FormEditText) findViewById(R.id.comment);
                post_text = comment.getText().toString();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                df.setTimeZone(TimeZone.getTimeZone("GMT+8"));
                Calendar c = Calendar.getInstance();
                post_time = df.format(c.getTime());
                writeComment();
                Toast.makeText(ReadNewPost.this, "Post successful", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ReadNewPost.this, ReadNewPost.class);
                //intent.putExtra("subject", subject);
                //intent.putExtra("text", text);
                //intent.putExtra("time", time);
                //intent.putExtra("category", category);
                //startActivity(intent);
                //startActivity(new Intent(CreatePost.this, ReadNewPost.class));
            };

        });
    }


    public void writeComment(){
        mDatabase.child("user/"+ author +"/name").addListenerForSingleValueEvent (new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                name = (TextView)findViewById(R.id.stud_name);
                String post_name = dataSnapshot.getValue(String.class);
                Log.d("name", post_name);
                name.setText(post_name);
                //Post post = new Post(author, category, subject, text, time);
                Comment comment = new Comment(post_name, post_text, post_time);
                //get pid
                //mDatabase.child("comment").child(category).child(pid).child("recomment").push().setValue(comment);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }

        });


    }

}
