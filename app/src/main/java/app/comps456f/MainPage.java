package app.comps456f;

import android.content.Intent;
import android.content.res.Configuration;
import android.icu.text.RelativeDateTimeFormatter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
//TODO: Specify teacher account
public class MainPage extends AppCompatActivity{
    public DrawerLayout mDrawerLayout;
    public ActionBarDrawerToggle mToggle;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close) {
            public void onDrawerClosed(View view) {
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
            public void onDrawerOpened(View drawerView) {
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        init();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        mDrawerLayout.addDrawerListener(mToggle);
        // set main page layout

    }

    public void init(){
        firebaseAuth = FirebaseAuth.getInstance();



        if(firebaseAuth.getCurrentUser() ==null){
            finish();
            startActivity(new Intent(getApplicationContext(),LoginPage.class));
        }
        else if(firebaseAuth.getCurrentUser()!=null) {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            String email = user.getEmail();
            if (!email.equals("ha@gmail.com")) {
                Toast.makeText(MainPage.this, "Welcome2 " + user.getEmail(), Toast.LENGTH_SHORT).show();
                NavigationView nvDrawer = (NavigationView)findViewById(R.id.nav_view);
                nvDrawer.setVisibility(View.VISIBLE);
                nvDrawer.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        selectDrawer(item);
                        mDrawerLayout.closeDrawer(GravityCompat.START);
                        return true;
                    }
                });
            } else {
                Toast.makeText(MainPage.this, "Welcome1 " + user.getEmail(), Toast.LENGTH_SHORT).show();
                NavigationView navigationView = (NavigationView)findViewById(R.id.tech_view);
                navigationView.setVisibility(View.VISIBLE);
                navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                        selectDrawer(item);
                        mDrawerLayout.closeDrawer(GravityCompat.END);
                        return true;
                    }
                });
            }

        }

    }


    public void selectDrawer(MenuItem item){
        Fragment mFragment = null;
        Class fClass;
        switch (item.getItemId()){
            case R.id.tech_result:
                fClass = TeacherResult.class;
                break;
            case R.id.tech_discuss:
                fClass = Discuss.class;
                break;
            case R.id.tech_logout:
                fClass = Logout.class;
                break;
            case R.id.nav_profile:
                fClass = Profile.class;
                break;
            case R.id.nav_tutor:
                fClass = Tutorial.class;
                break;
            case R.id.nav_quiz:
                fClass = Quiz.class;
                break;
            case R.id.nav_result:
                fClass = Result.class;
                break;
            case R.id.nav_discuss:
                fClass = Discuss.class;
                break;
            case R.id.nav_logout:
                fClass = Logout.class;
                break;

            default:
                fClass = MainPage.class;
        }
        try{
            mFragment = (Fragment) fClass.newInstance();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        if (mFragment !=null){
        FragmentManager fManager = getSupportFragmentManager();
        fManager.beginTransaction().replace(R.id.fContent,mFragment).commit();
        item.setChecked(true);
        setTitle(item.getTitle());
        }
    }
    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else if (mDrawerLayout.isDrawerOpen(GravityCompat.END)) {  /*Closes the Appropriate Drawer*/
            mDrawerLayout.closeDrawer(GravityCompat.END);
        } else {
            super.onBackPressed();
            System.exit(0);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mToggle.syncState();
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass configurations
        mToggle.onConfigurationChanged(newConfig);
    }


}
