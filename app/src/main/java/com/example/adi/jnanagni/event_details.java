package com.example.adi.jnanagni;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class event_details extends AppCompatActivity {
    private long mLastClickTime=0;
    Set<String> cd;
    String event_string;
    String table_name;
    int uid;

    HashMap<String,ArrayList> myHashMap=new HashMap<>();
    List<String> eventDetails=new ArrayList<>();
    TextView eventDesc,eventJudgementalCriteria,eventCoordinators,eventTask,eventPrerequisites;
    TextView ttDesc,ttJudge,ttCoor,ttTask,ttSpec;
    Button btnRegForEvent;
    String event;
    ProgressDialog pd;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        pd=new ProgressDialog(this);

        Bundle rec =getIntent().getExtras();
        if(rec!=null){
             event=rec.getString("event").toLowerCase().replace("_","-");
        }
        this.setTitle(event.toUpperCase().replace("_","-"));
        loadDatatoMap();

        eventDetails=myHashMap.get(event);

        Drawable d=getResources().getDrawable(R.drawable.bggg);
        getSupportActionBar().setBackgroundDrawable(d);

        ttDesc=(TextView)findViewById(R.id.text_desc);
        ttTask=(TextView)findViewById(R.id.text_task);
        ttCoor=(TextView)findViewById(R.id.text_coor);
        ttJudge=(TextView)findViewById(R.id.text_judge);
        ttSpec=(TextView)findViewById(R.id.text_spec);
        eventDesc=(TextView)findViewById(R.id.text_event_desc);

        ImageView  eventImage = (ImageView) findViewById(R.id.image_event_info);
        eventTask=(TextView)findViewById(R.id.text_event_task);
        eventPrerequisites=(TextView)findViewById(R.id.text_event_prerequisite);
        eventJudgementalCriteria=(TextView)findViewById(R.id.text_event_judgemental_criteria);
        eventCoordinators=(TextView)findViewById(R.id.text_event_coordinators);
        btnRegForEvent=(Button)findViewById(R.id.btn_reg_event);

        uid=MainActivity.sf.getInt("uid",0);







        if(event.equals("celebrity-night")||event.equals("bowling")||event.equals("cube")||event.equals("dart")||event.equals("mini-militia")||event.equals("throw-ball"))
        {
            btnRegForEvent.setVisibility(View.GONE);
        }



        nullCheck();
        eventDesc.setText(eventDetails.get(0));
        eventTask.setText(eventDetails.get(1));
        try {
            Class res = R.drawable.class;
            Field field = res.getField(event.toLowerCase().replace("-","_"));
            int drawableId = field.getInt(null);
            eventImage.setImageResource(drawableId);
        }
        catch (Exception e) {

        }
        eventPrerequisites.setText(eventDetails.get(2));
        eventJudgementalCriteria.setText(eventDetails.get(3));
        eventCoordinators.setText(eventDetails.get(4));


    }



    private void loadDatatoMap() {
        myHashMap.put("hydroriser",createList(R.string.desc_hydroriser,R.string.task_hydroriser,R.string.spcf_hydroriser,R.string.judg_hydroriser,R.string.coor_hydroriser));
        myHashMap.put("cipher",createList(R.string.desc_cipher,R.string.task_cipher,R.string.spcf_cipher,R.string.judg_cipher,R.string.coor_cipher));
        myHashMap.put("electroguisal",createList(R.string.desc_electroguisial,R.string.task_electroguisial,R.string.spcf_electroguisial,R.string.judg_electroguisial,R.string.coor_electroguisial));
        myHashMap.put("annihilator",createList(R.string.desc_annihilator,R.string.task_annihilator,R.string.spcf_annihilator,R.string.judg_annihilator,R.string.coor_annihilator));
        myHashMap.put("codeathon",createList(R.string.desc_apptitude,R.string.task_apptitude,R.string.spcf_apptitude,R.string.judg_apptitude,R.string.coor_apptitude));
        myHashMap.put("exgesis",createList(R.string.desc_exgesis,R.string.task_exgesis,R.string.spcf_exgesis,R.string.judg_exgesis,R.string.coor_exgesis));
        myHashMap.put("concatenation",createList(R.string.desc_concatenation,R.string.task_concatenation,R.string.spcf_concatenation,R.string.judg_concatenation,R.string.coor_concatenation));
        myHashMap.put("electricio",createList(R.string.desc_electrocio,R.string.task_electrocio,R.string.spcf_electrocio,R.string.judg_electrocio,R.string.coor_electrocio));
        myHashMap.put("tinkerer",createList(R.string.desc_tinkerer,R.string.task_tinkerer,R.string.spcf_tinkerer,R.string.judg_tinkerer,R.string.coor_tinkerer));
        myHashMap.put("nopc",createList(R.string.desc_nopc,R.string.task_nopc,R.string.spcf_nopc,R.string.judg_nopc,R.string.coor_nopc));
        myHashMap.put("inclino",createList(R.string.desc_inclino,R.string.task_inclino,R.string.spcf_inclino,R.string.judg_inclino,R.string.coor_inclino));
        myHashMap.put("cuandigo",createList(R.string.desc_cuandigo,R.string.task_cuandigo,R.string.spcf_cuandigo,R.string.judg_cuandigo,R.string.coor_cuandigo));
        myHashMap.put("ameliorator",createList(R.string.desc_ameliorator,R.string.task_ameliorator,R.string.spcf_ameliorator,R.string.judg_ameliorator,R.string.coor_ameliorator));
        myHashMap.put("abhivyakti",createList(R.string.desc_abhivyakti,R.string.task_abhivyakti,R.string.spcf_abhivyakti,R.string.judg_abhivyakti,R.string.coor_abhivyakti));
        myHashMap.put("third-vision",createList(R.string.desc_third_vision,R.string.task_third_vision,R.string.spcf_third_vision,R.string.judg_third_vision,R.string.coor_third_vision));
        myHashMap.put("treasure-hunt",createList(R.string.desc_mist,R.string.task_mist,R.string.spcf_mist,R.string.judg_mist,R.string.coor_mist));
        myHashMap.put("qcognito",createList(R.string.desc_qcognito,R.string.task_qcognito,R.string.spcf_qcognito,R.string.judg_qcognito,R.string.coor_qcognito));
        myHashMap.put("freedoscrawl",createList(R.string.desc_freedscrawl,R.string.task_freedscrawl,R.string.spcf_freedscrawl,R.string.judg_freedscrawl,R.string.coor_freedscrawl));
        myHashMap.put("kalakriti",createList(R.string.desc_bursh_n_paint,R.string.task_bursh_n_paint,R.string.spcf_bursh_n_paint,R.string.judg_bursh_n_paint,R.string.coor_bursh_n_paint));
        myHashMap.put("craftsvilla",createList(R.string.desc_craftvilla,R.string.task_craftvilla,R.string.spcf_craftvilla,R.string.judg_craftvilla,R.string.coor_craftvilla));
        myHashMap.put("enthuse",createList(R.string.desc_enthuse,R.string.task_enthuse,R.string.spcf_enthuse,R.string.judg_enthuse,R.string.coor_enthuse));
        myHashMap.put("cricket-keeda",createList(R.string.desc_cricket_keeda,R.string.task_cricket_keeda,R.string.spcf_cricket_keeda,R.string.judg_cricket_keeda,R.string.coor_cricket_keeda));
        myHashMap.put("fancy-footwork",createList(R.string.desc_anukriti,R.string.task_anukriti,R.string.spcf_anukriti,R.string.judg_anukriti,R.string.coor_anukriti));
        myHashMap.put("sargam",createList(R.string.desc_sargam,R.string.task_sargam,R.string.spcf_sargam,R.string.judg_sargam,R.string.coor_sargam));
        myHashMap.put("kritika",createList(R.string.desc_kritika,R.string.task_kritika,R.string.spcf_kritika,R.string.judg_kritika,R.string.coor_kritika));
        myHashMap.put("lol",createList(R.string.desc_lol,R.string.task_lol,R.string.spcf_lol,R.string.judg_lol,R.string.coor_lol));
        myHashMap.put("nautankishala",createList(R.string.desc_nautankishala,R.string.task_nautankishala,R.string.spcf_nautankishala,R.string.judg_nautankishala,R.string.coor_nautankishala));
        myHashMap.put("carrom",createList(R.string.desc_carrom,R.string.task_carrom,R.string.spcf_carrom,R.string.judg_carrom,R.string.coor_carrom));
        myHashMap.put("table-tennis",createList(R.string.desc_table_tennis,R.string.task_table_tennis,R.string.spcf_table_tennis,R.string.judg_table_tennis,R.string.coor_table_tennis));
        myHashMap.put("chess",createList(R.string.desc_chess,R.string.task_chess,R.string.spcf_chess,R.string.judg_chess,R.string.coor_chess));
        myHashMap.put("badminton",createList(R.string.desc_badminton,R.string.task_badminton,R.string.spcf_badminton,R.string.judg_badminton,R.string.coor_badminton));
        myHashMap.put("nfs",createList(R.string.desc_nfs,R.string.task_nfs,R.string.spcf_nfs,R.string.judg_nfs,R.string.coor_nfs));
        myHashMap.put("counter-strike",createList(R.string.desc_counter_strike,R.string.task_counter_strike,R.string.spcf_counter_strike,R.string.judg_counter_strike,R.string.coor_counter_strike));
        myHashMap.put("fifa",createList(R.string.desc_fifa,R.string.task_fifa,R.string.spcf_fifa,R.string.judg_fifa,R.string.coor_fifa));
        myHashMap.put("cube",createList(R.string.desc_rubik_cube,R.string.task_rubik_cube,R.string.spcf_rubik_cube,R.string.judg_rubik_cube,R.string.coor_rubik_cube));
        myHashMap.put("mini-militia",createList(R.string.desc_minimilitia,R.string.task_minimilitia,R.string.spcf_minimilitia,R.string.judg_minimilitia,R.string.coor_minimilitia));
        myHashMap.put("bowling",createList(R.string.desc_bowling,R.string.task_bowling,R.string.spcf_bowling,R.string.judg_bowling,R.string.coor_bowling));
        myHashMap.put("dart",createList(R.string.desc_dart,R.string.task_dart,R.string.spcf_dart,R.string.judg_dart,R.string.coor_dart));
        myHashMap.put("throw-ball",createList(R.string.desc_throwball,R.string.task_throwball,R.string.spcf_throwball,R.string.judg_throwball,R.string.coor_throwball));
       // myHashMap.put("samagam",createList(R.string.desc_samagam,R.string.task_samagam,R.string.spcf_samagam,R.string.judg_samagam,R.string.coor_samagam));
        myHashMap.put("celebrity-night",createList(R.string.desc_celebrity_visit,R.string.task_celebrity_visit,R.string.spcf_celebrity_visit,R.string.judg_celebrity_visit,R.string.coor_celebrity_visit));
        myHashMap.put("startup-fair",createList(R.string.desc_startup_fair,R.string.task_startup_fair,R.string.spcf_startup_fair,R.string.judg_startup_fair,R.string.coor_startup_fair));
        //myHashMap.put("rock syndrome",createList(R.string.desc_rock_syndrome,R.string.task_rock_syndrome,R.string.spcf_rock_syndrome,R.string.judg_rock_syndrome,R.string.coor_rock_syndrome));
        myHashMap.put("weber",createList(R.string.desc_weber,R.string.task_weber,R.string.spcf_weber,R.string.judg_weber,R.string.coor_weber));
        myHashMap.put("speedomer",createList(R.string.desc_speedomer,R.string.task_speedomer,R.string.spcf_speedomer,R.string.judg_speedomer,R.string.coor_speedomer));
        myHashMap.put("gyan-zara-hat-ke",createList(R.string.desc_gyan,R.string.task_gyan,R.string.spcf_gyan,R.string.judg_gyan,R.string.coor_gyan));
        myHashMap.put("clash-on",createList(R.string.desc_clash_on,R.string.task_clash_on,R.string.spcf_clash_on,R.string.judg_clash_on,R.string.coor_clash_on));

    }

    private ArrayList<String> createList(int desc,int task,int spcf,int judg,int coor) {
        ArrayList<String> mList=new ArrayList<>();
        mList.add(getString(desc));
        mList.add(getString(task));
        mList.add(getString(spcf));
        mList.add(getString(judg));
        mList.add(getString(coor));
        return mList;
    }

    private void nullCheck(){
        if(eventDetails.get(0).equals("")){
            ttDesc.setVisibility(View.GONE);
            eventDesc.setVisibility(View.GONE);}
        if (eventDetails.get(1).equals(""))
        {
            ttTask.setVisibility(View.GONE);
            eventTask.setVisibility(View.GONE);
        }
        if (eventDetails.get(2).equals("")){
            ttSpec.setVisibility(View.GONE);
            eventPrerequisites.setVisibility(View.GONE);
        }
        if (eventDetails.get(3).equals("")){
            ttJudge.setVisibility(View.GONE);
            eventJudgementalCriteria.setVisibility(View.GONE);
        }
        if (eventDetails.get(4).equals("")){
            ttCoor.setVisibility(View.GONE);
            eventCoordinators.setVisibility(View.GONE);
        }
    }


    public void register_for_event(View view){

        if (SystemClock.elapsedRealtime() - mLastClickTime < 4000){
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();


        if (!isNetworkEnabled()) {
            // Toast.makeText(getApplicationContext(),"We are not uploading data",Toast.LENGTH_SHORT).show();

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            AlertDialog alert = builder.setMessage("Please check your device internet connection.").
                    setTitle("Internet Unavailable").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            }).create();
            alert.show();}

        else{

        if (!SharedPreferenceManager.getInstance(this).isloggedin())
        {
            Toast.makeText(this,"You must log in first",Toast.LENGTH_SHORT).show();
        }


        else {
            table_name=event;
            if(table_name.equals("counter-strike"))
                    table_name="cs";
            else if(table_name.equals("table-tennis"))
                    table_name="tt";
            else if(table_name.equals("fancy-footwork"))
                table_name="fancy";
            else if(table_name.equals("treasure-hunt"))
                table_name="mist";
            else if(table_name.equals("cricket-keeda"))
                table_name="cricketkeeda";
            else if(table_name.equals("third-vision"))
                table_name="thirdvision";
            else if(table_name.equals("annihilator"))
                table_name="annhilator";
            else if(table_name.equals("clash-on"))
                table_name="clashon";
            else if(table_name.equals("gyan-zara-hat-ke"))
                table_name="gyanzarahatke";
            else if(table_name.equals("startup-fair"))
                table_name="startupfair";




            pd.setMessage("Registering...");
            pd.show();

            StringRequest stringRequest=new StringRequest(Request.Method.POST,
                    Constants.eventwa,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject obj=new JSONObject(response);
                                if(obj.getBoolean("error")) {
                                    if(obj.getString("message").equals("QQ")){


                                        AlertDialog.Builder builder = new AlertDialog.Builder(event_details.this);
                                        AlertDialog alert = builder.setMessage("You are registered for this event.\nDo you want to unregister ?").
                                                setTitle("Un-Register").setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                del_event();
                                                dialogInterface.cancel();
                                            }
                                        }).setNegativeButton("NO",new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                dialogInterface.cancel();
                                            }}).create();
                                        alert.show();


                                }
                                    else{
                                    Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();
                                }}
                                else{
                                    Toast.makeText(getApplicationContext(),obj.getString("message"),Toast.LENGTH_SHORT).show();


                                } }catch (JSONException e) {
                                e.printStackTrace();
                            }
                            pd.dismiss();

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(),"Can't connect to server right now.",Toast.LENGTH_SHORT).show();
                    pd.dismiss();

                }
            }
            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params=new HashMap<>();
                    params.put("table_name",table_name);
                    params.put("uid",String.valueOf(uid));
                    return params;
                }
            };


            stringRequest.setShouldCache(false);
            RequestHandler.getmInstance(getApplicationContext()).addToRequestQueue(stringRequest);

        }

        }}
    public void del_event(){
        pd.setMessage("Un-registering...");
        pd.show();

        StringRequest stringRequest=new StringRequest(Request.Method.POST,
                Constants.delevent,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj=new JSONObject(response);
                            if(obj.getBoolean("error")) {
                                    Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                                }
                            else{
                                Toast.makeText(getApplicationContext(),"Sucessfully Unregistered from this event.",Toast.LENGTH_SHORT).show();


                            } }catch (JSONException e) {
                            e.printStackTrace();
                        }
                        pd.dismiss();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Can't connect to server right now.",Toast.LENGTH_SHORT).show();
                pd.dismiss();

            }
        }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put("table_name",table_name);
                params.put("uid",String.valueOf(uid));
                return params;
            }
        };


        stringRequest.setShouldCache(false);
        RequestHandler.getmInstance(getApplicationContext()).addToRequestQueue(stringRequest);


    }

    public boolean isNetworkEnabled(){
        ConnectivityManager connectivityManager = (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            return true;
        }
        else
            return false;
    }

}
