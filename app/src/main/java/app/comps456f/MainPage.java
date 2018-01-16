package app.comps456f;

import android.content.Intent;
import android.icu.text.RelativeDateTimeFormatter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

public class MainPage extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private String job_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // set main page layout
        init();
    }

    public void init(){
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close) {

        };
        mDrawerLayout.addDrawerListener(mToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        NavigationView nvDrawer = (NavigationView)findViewById(R.id.nav_view);
       // NavigationView nvDrawer2 = (NavigationView)findViewById(R.id.tech_view);
        setDrawerContent(nvDrawer);
       // if(job_title=="Student"){

         /*   nvDrawer.setVisibility(View.VISIBLE);
            nvDrawer2.setVisibility(View.GONE);
        }
        else if(job_title=="Teacher"){
            setDrawerContent(nvDrawer2);
            nvDrawer2.setVisibility(View.VISIBLE);
            nvDrawer.setVisibility(View.GONE);
        }*/
    }











    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        //return super.onOptionsItemSelected(item);

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mToggle.syncState();
    }

    public void selectDrawer(MenuItem item){
        Fragment mFragment = null;
        Class fClass;
        switch (item.getItemId()){
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
                fClass = LoginPage.class;
                break;
            case R.id.tech_result:
                fClass = TeacherResult.class;
                break;
            case R.id.tech_discuss:
                fClass = Discuss.class;
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
        FragmentManager fManager = getSupportFragmentManager();
        fManager.beginTransaction().replace(R.id.fContent,mFragment).commit();
        item.setChecked(true);
        mDrawerLayout.closeDrawers();
    }
    private void setDrawerContent (NavigationView nv){
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectDrawer(item);
                return true;
            }
        });
    }



   /* @Override
    public boolean onNavigationItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.nav_tutor:
                Intent tutorIntent = new Intent(MainPage.this, TutorPage.class);
                startActivity(tutorIntent);
                break;
            case R.id.nav_quiz:
                Intent quizIntent = new Intent(MainPage.this, QuizPage.class);
                startActivity(quizIntent);
                break;
            case R.id.nav_result:
                Intent resultIntent = new Intent(MainPage.this, ResultPage.class);
                startActivity(resultIntent);
                break;
            case R.id.nav_logout:
                Intent logoutIntent = new Intent(MainPage.this, LoginPage.class);
                startActivity(logoutIntent);
                break;

        }
        return true;
    }*/
  // methods call when click on menu items
   /* public void tutorClicked (MenuItem menuItem){
        Intent tutorIntent = new Intent(MainPage.this, TutorPage.class);
        startActivity(tutorIntent);
    }
    public void quizClicked (MenuItem menuItem){
        Intent quizIntent = new Intent(MainPage.this, QuizPage.class);
        startActivity(quizIntent);
    }
    public void resultClicked (MenuItem menuItem){
        Intent resultIntent = new Intent(MainPage.this, ResultPage.class);
        startActivity(resultIntent);
    }
    public void logoutClicked (MenuItem menuItem){
        Intent logoutIntent = new Intent(MainPage.this, LoginPage.class);
        startActivity(logoutIntent);
    }*/
}
