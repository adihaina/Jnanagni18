package com.example.adi.jnanagni;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener  {
    private static final String Sps="mypef";
    public static Set<String> smalls;
    public static Set<String> full;
    public static SharedPreferences sf;
    public static SharedPreferences.Editor edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        setTheme(R.style.AppTheme_NoActionBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);







        sf=MainActivity.this.getSharedPreferences(Sps,Context.MODE_PRIVATE);
       edit=sf.edit();


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        display_layout(R.id.nav_send);

    }

    @Override
    public void onBackPressed() {
       // DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
       // if (drawer.isDrawerOpen(GravityCompat.START)) {
       //     drawer.closeDrawer(GravityCompat.START);
       // } else {
        //    super.onBackPressed();
      //  }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        return display_layout(id);
    }



    public boolean display_layout(int id){
        Fragment fragment=null;
        String s=null;

        switch(id) {
            case R.id.nav_camera:

                if(SharedPreferenceManager.getInstance(this).isloggedin()) {
                    fragment = new User_Profile();
                    s = "User Profile";

                }
                else{
                    fragment =new login_screen();
                    s = "Login";

                }
                break;

            case R.id.nav_gallery:
                fragment=new about();
                s="About";
                break;
            case R.id.nav_slideshow:
                fragment=new events();
                s="Events";
                break;
            case R.id.nav_manage:
                Intent i=new Intent(this,Register.class);
                startActivity(i);
                break;
            case R.id.nav_send:
                fragment=new Home_screen();
                s="Home";
                break;
            case R.id.nav_contact:
                fragment=new contact();
                s="Contact US";
                break;



        }
        if(fragment!=null){
            FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_main,fragment);
            if(!s.equals(null)){
            setTitle(s);}
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;



    }





}
