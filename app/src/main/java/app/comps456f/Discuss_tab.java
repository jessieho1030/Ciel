package app.comps456f;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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

public class Discuss_tab extends AppCompatActivity implements TabLayout.OnTabSelectedListener{

    TabLayout tab;
    ViewPager viewPager;
    ViewPagerAdaptor viewPagerAdaptor;

    private DatabaseReference mDatabase;
    //mDatabase = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.discuss);

        // Get category from discuss.class
        Intent intent = getIntent();
        //String category = intent.getStringExtra(Discuss.CATEGORY);
        String category = intent.getStringExtra("category");
        intent.putExtra("category",category);

        // Create tab
        tab = (TabLayout)findViewById(R.id.tab);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPagerAdaptor = new ViewPagerAdaptor(getSupportFragmentManager());
        viewPagerAdaptor.addFragments(tab_unread.newInstance());
        viewPagerAdaptor.addFragments(tab_read.newInstance());
        viewPagerAdaptor.addFragments(tab_all.newInstance());
        viewPager.setOffscreenPageLimit(1);
        /*
        viewPagerAdaptor.addFragments(new tab_read(), "Read");
        viewPagerAdaptor.addFragments(new tab_read(), "Unread");
        viewPagerAdaptor.addFragments(new tab_read(), "All");*/
        viewPager.setAdapter(viewPagerAdaptor);

/*
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                viewPager.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });*/
        tab.setupWithViewPager(viewPager);
        tab.addOnTabSelectedListener(this);
        init();
    }


    public void init(){
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Discuss_tab.this, CreatePost.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }



}
