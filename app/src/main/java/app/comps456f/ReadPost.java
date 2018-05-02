package app.comps456f;

import android.content.Intent;
import android.icu.text.RelativeDateTimeFormatter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.andreabaccega.widget.FormEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.TimeZone;

import app.comps456f.R;

public class ReadPost extends AppCompatActivity {
    private String pid;
    private Post post;
    private String name, comment_text, comment_time;
    private String subject, time, text;
    private ListView listView;
    //private ArrayList<String> rcm_time = new ArrayList<String>();
    //private ArrayList<String> rcm_name = new ArrayList<String>();
    //private ArrayList<String> rcm_text = new ArrayList<String>();
    List<Comment> comments = new ArrayList<Comment>();
    private ListViewAdapter adapter;
    private String category;
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private String reader = user.getUid();
    public TextView post_name,post_text, post_subject, post_time;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.readpost);
        Intent intent = getIntent();
        pid = intent.getStringExtra("pid");
        category = intent.getStringExtra("category");


        System.out.print("hiiiiiiiiiiiiiiiii");
        // read by
        mDatabase.child("comment").child(category).child(pid).child("readby").child(reader).setValue("true");
        getPostData(pid);
        getComment(pid);
        Button submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FormEditText comment = (FormEditText) findViewById(R.id.comment);
                comment_text = comment.getText().toString();
                // Apr 24, 2018 6:21:40 PM
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                df.setTimeZone(TimeZone.getTimeZone("GMT+8"));
                Calendar c = Calendar.getInstance();
                comment_time = df.format(c.getTime());
                writeComment();

                Toast.makeText(ReadPost.this, "Comment successful", Toast.LENGTH_SHORT).show();
                finish();
                startActivity(getIntent());
            }
        });


    }

    public void getPostData(String pid) {
        mDatabase.child("comment").child(category).child(pid).addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                name = dataSnapshot.child("name").getValue(String.class);
                subject = dataSnapshot.child("subject").getValue(String.class);
                time = dataSnapshot.child("time").getValue(String.class);
                text = dataSnapshot.child("text").getValue(String.class);

                post_name = (TextView) findViewById(R.id.stud_name);
                post_subject = (TextView) findViewById(R.id.title);
                post_text = (TextView) findViewById(R.id.content);
                post_time = (TextView) findViewById(R.id.date);


                post_time.setText(time);
                post_subject.setText(subject);
                post_text.setText(text);
                post_name.setText(name);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    public void getComment(String pid){

        listView = (ListView) findViewById(R.id.comment_list_view);
        mDatabase.child("comment").child(category).child(pid).child("recomment").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                comments.clear();
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    Comment comment = ds.getValue(Comment.class);
                    comments.add(comment);
                    if(comment!= null)
                        Log.d("comment not null","blabla");
                }
                if(comments != null){
                    Collections.reverse(comments);
                    adapter = new ListViewAdapter(ReadPost.this, comments);
                    //adapter = new ListViewAdapter(ReadPost.this, comments);
                    adapter.notifyDataSetChanged();

                    listView.setAdapter(adapter);
                   // adapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        /*
        mDatabase.child("comment").child(category).child(pid).child("recomment").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                String string = dataSnapshot.getValue(String.class);
                Log.d("string", string);
                //recomment.add(string);
                //adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {


            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/

    }
    public void writeComment(){
        mDatabase.child("user/"+ reader +"/name").addListenerForSingleValueEvent (new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                name = dataSnapshot.getValue(String.class);
                Comment comment = new Comment(name, comment_text, comment_time);
                mDatabase.child("comment").child(category).child(pid).child("recomment").push().setValue(comment);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }

        });


    }


}
