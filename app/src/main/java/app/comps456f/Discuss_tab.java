package app.comps456f;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import android.view.View;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
/*
 * Created by meiyuk on 21/3/2018.
 */

public class Discuss_tab extends AppCompatActivity{

    TabLayout tab;
    ViewPager viewPager;
    ViewPagerAdaptor viewPagerAdaptor;
    //private int commentId;
    private String author;
    private String text;
    private long timecreate;
    private DatabaseReference mDatabase;
    //mDatabase = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.discuss);

        tab = (TabLayout)findViewById(R.id.tab);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPagerAdaptor = new ViewPagerAdaptor(getSupportFragmentManager());
        viewPagerAdaptor.addFragments(new tab_read(), "Read");
        viewPagerAdaptor.addFragments(new tab_read(), "Unread");
        viewPagerAdaptor.addFragments(new tab_read(), "All");
        viewPager.setAdapter(viewPagerAdaptor);
        tab.setupWithViewPager(viewPager);

    }



    private void getComment(){



    }

    private void writeComment(String author, String text, long timecreate){
        this.author = author;
        this.text = text;
        this.timecreate = timecreate;

    }




}
