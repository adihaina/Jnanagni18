package com.example.adi.jnanagni;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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


/**
 * Created by adi on 2/25/2018.
 */

public class login_screen extends Fragment implements View.OnClickListener{
   // Animation animation,animation1;
    ImageView jnana2,img;
    private long mLastClickTime=0;
    TextInputLayout email_layout,password_layout;
    EditText email,password;
    Button login;
    TextView reg;
    private ProgressDialog pd;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.login,container,false);

        getActivity().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        img=(ImageView)view.findViewById(R.id.img1);
        email=(EditText)view.findViewById(R.id.email);
        password=(EditText)view.findViewById(R.id.password);
        login=(Button)view.findViewById(R.id.login);
        reg=(TextView)view.findViewById(R.id.reg);
        pd=new ProgressDialog(getActivity());



        Resources r=getResources();
        int w=(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,250,r.getDisplayMetrics());
        int h=(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,281,r.getDisplayMetrics());

        int h1=(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,81,r.getDisplayMetrics());
        int w1=(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,250,r.getDisplayMetrics());

       ViewGroup.LayoutParams pp=img.getLayoutParams();
       pp.width=w1;
       pp.height=h1;
       img.setLayoutParams(pp);




       // ViewGroup.LayoutParams ppp=jnana2.getLayoutParams();
       // ppp.width=w;
        //ppp.height=h;
        //jnana2.setLayoutParams(ppp);

//        animation = new RotateAnimation(00.0f,360.0f, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
  //      animation.setFillAfter(false);
    //    animation.setDuration(200000);
      //  animation.setRepeatCount(300);
        //animation.setRepeatMode(Animation.REVERSE);
        // animation.setInterpolator(this, android.R.anim.decelerate_interpolator);


        //animation1 = new RotateAnimation(+00.0f,-360.0f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        //animation1.setFillAfter(false);
        //animation1.setDuration(200000);
        //animation1.setRepeatMode(Animation.REVERSE);
        //animation1.setRepeatCount(300);
        //  animation1.setInterpolator(this, android.R.anim.decelerate_interpolator);
        // Animation anim = new AlphaAnimation(0.0f, 1.0f);
        //anim.setDuration(5000); //You can manage the blinking time with this parameter
        //anim.setStartOffset(5);
        //anim.setRepeatMode(Animation.REVERSE);
        //anim.setRepeatCount(Animation.INFINITE);
        //tv1.startAnimation(anim);
        //tv2.startAnimation(anim);
        //ft.startAnimation(animation);
        //rl.startAnimation(animation1);
        reg.setOnClickListener(this);
        login.setOnClickListener(this);
        return view;
    }

    public void onClick(View v){
        if(v.getId()==R.id.login){
            login_the_user();}
         else if(v.getId()==R.id.reg){
            register();
        }
    }

    public void login_the_user(){
        if (SystemClock.elapsedRealtime() - mLastClickTime < 2000){
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();


        if (!isNetworkEnabled()) {
            // Toast.makeText(getApplicationContext(),"We are not uploading data",Toast.LENGTH_SHORT).show();

            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            AlertDialog alert = builder.setMessage("Please check your device internet connection.").
                    setTitle("Internet Unavailable").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            }).create();
            alert.show();}

         else {
            loguserin();
        }
    }

    public void register(){
        startActivity(new Intent(getActivity(),Register.class));
    }

    public boolean isNetworkEnabled(){
        ConnectivityManager connectivityManager = (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            return true;
        }
        else
            return false;
    }

    public void loguserin(){
        final String emails=email.getText().toString();
        final String pass=password.getText().toString();

        if(email.getText().toString().equals("") || password.getText().toString().equals("")){
            Toast.makeText(getActivity(),"Email or Password can't be left empty",Toast.LENGTH_SHORT).show();
            return;
        }
        pd.setMessage("Logging You In.....");
        pd.setCanceledOnTouchOutside(false);
        pd.show();



        StringRequest stringRequest=new StringRequest(Request.Method.POST,
                Constants.login,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj=new JSONObject(response);
                            if(obj.getBoolean("error")) {
                                Toast.makeText(getActivity(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                            }
                            else{
                                SharedPreferenceManager.getInstance(getActivity()).userlogin(obj.getString("full_name")
                                ,obj.getString("email"),obj.getString("phone"),obj.getString("gender")
                                ,obj.getString("college"),obj.getInt("uid"),obj.getString("transaction"),
                                        obj.getInt("status"));

                                Toast.makeText(getActivity(),"You are logged in as:"+obj.getString("full_name").toUpperCase().trim(),Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getActivity(),MainActivity.class));
                            } }catch (JSONException e) {
                            e.printStackTrace();
                        }
                        pd.dismiss();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(),"Can't connect to server right now.",Toast.LENGTH_SHORT).show();
                pd.dismiss();

            }
        }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put("email",emails);
                params.put("password",pass);
                return params;
            }
        };


        stringRequest.setShouldCache(false);
        RequestHandler.getmInstance(getActivity()).addToRequestQueue(stringRequest);

    }
}

