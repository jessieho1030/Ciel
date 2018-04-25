package app.comps456f;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.icu.text.RelativeDateTimeFormatter;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.Random;

import app.comps456f.BroadCast.NotificationReceiver;

import static android.app.Notification.DEFAULT_ALL;



//TODO: Specify teacher account
public class MainPage extends AppCompatActivity{
    private static final String TAG = "MainPage";
    public DrawerLayout mDrawerLayout;
    public ActionBarDrawerToggle mToggle;
    BroadcastReceiver registrationBoradcast;
    private FirebaseAuth firebaseAuth;
    private Button getToken;
    @Override
    protected void onPause(){
        LocalBroadcastManager.getInstance(this).unregisterReceiver(registrationBoradcast);
        super.onPause();
    }
    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(registrationBoradcast, new IntentFilter("registrationComplete"));
        LocalBroadcastManager.getInstance(this).registerReceiver(registrationBoradcast, new IntentFilter(FirebaseMessage.PUSH_NOT));
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registerNotification();
        /*getToken = (Button)findViewById(R.id.getToken);
        getToken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String token = FirebaseInstanceId.getInstance().getToken();
                Log.d(TAG,"Token : " + token);
                Toast.makeText(MainPage.this,token,Toast.LENGTH_SHORT).show();
            }
        });*/

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

    private void registerNotification() {
        registrationBoradcast = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if(intent.getAction().equals(FirebaseMessage.PUSH_NOT)){
                    String message  = intent.getStringExtra("message");
                    showNotification("Ciel",message);
                }
            }


        };
    }

    private void showNotification(String title, String message) {
        Intent intent = new Intent(getApplicationContext(), MainPage.class);
        PendingIntent contentIntent = PendingIntent.getActivity(getBaseContext(),0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        android.support.v4.app.NotificationCompat.Builder builder = new NotificationCompat.Builder(getBaseContext());
                builder.setAutoCancel(true)
                        .setDefaults(DEFAULT_ALL)
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(title)
                        .setContentText(message)
                        .setContentIntent(contentIntent);
        NotificationManager notificationManager = (NotificationManager)getBaseContext().getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(new Random().nextInt(),builder.build());
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
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

                registerAlarm(); //for daily notification
                Toast.makeText(MainPage.this, "Welcome " + user.getEmail(), Toast.LENGTH_SHORT).show();
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
                Toast.makeText(MainPage.this, "Welcome " + user.getEmail(), Toast.LENGTH_SHORT).show();
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
        else{
            Toast.makeText(this,"User email/User password is not match. Please try again.",Toast.LENGTH_SHORT).show();
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void registerAlarm() {
        Calendar calender = Calendar.getInstance();
        calender.set(java.util.Calendar.HOUR_OF_DAY, 10);
        calender.set(java.util.Calendar.MINUTE, 0);
        calender.set(java.util.Calendar.SECOND,0);

        Intent intent = new Intent(MainPage.this, NotificationReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(MainPage.this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager am = (AlarmManager)this.getSystemService(this.ALARM_SERVICE);
        am.setRepeating(AlarmManager.RTC_WAKEUP,calender.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);

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