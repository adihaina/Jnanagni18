package com.example.adi.jnanagni;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.SystemClock;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

public class Register extends AppCompatActivity {
    TextInputLayout name_layout, email_layout, phone_layout, password_layout, college_layout;
    EditText name, phone, email, password, college;
    RadioButton radioButton;
    RadioGroup radioGroup;
    Button regbut;
    ProgressDialog pd;
    private long mLastClickTime = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);



        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        Drawable d=getResources().getDrawable(R.drawable.bggg);

        getSupportActionBar().setBackgroundDrawable(d);
        pd=new ProgressDialog(this);




        name_layout = (TextInputLayout) findViewById(R.id.name_layout);
        phone_layout = (TextInputLayout) findViewById(R.id.phone_layout);
        email_layout = (TextInputLayout) findViewById(R.id.email_layout);
        password_layout = (TextInputLayout) findViewById(R.id.password_layout);
        college_layout = (TextInputLayout) findViewById(R.id.college_layout);

        name = (EditText) findViewById(R.id.name);
        phone = (EditText) findViewById(R.id.phone);
        password = (EditText) findViewById(R.id.password);
        college = (EditText) findViewById(R.id.college);
        email = (EditText) findViewById(R.id.email);

        regbut = (Button) findViewById(R.id.register);


       name.addTextChangedListener(new MyTextWatcher(name));
        email.addTextChangedListener(new MyTextWatcher(email));
        password.addTextChangedListener(new MyTextWatcher(password));
        phone.addTextChangedListener(new MyTextWatcher(phone));
        college.addTextChangedListener(new MyTextWatcher(college));
        radioGroup=(RadioGroup)findViewById(R.id.radio_gender_group);
        radioButton=(RadioButton)radioGroup.findViewById(R.id.radio_male);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                radioButton=(RadioButton)radioGroup.findViewById(i);
            }
        });


         regbut.setOnClickListener(new View.OnClickListener()

    {
        @Override
        public void onClick (View view){
        //if internet is not available
            if (SystemClock.elapsedRealtime() - mLastClickTime < 2000){
                return;
            }
            mLastClickTime = SystemClock.elapsedRealtime();


            if (!isNetworkEnabled()) {
           // Toast.makeText(getApplicationContext(),"We are not uploading data",Toast.LENGTH_SHORT).show();

            AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
            AlertDialog alert = builder.setMessage("Please check your device internet connection.").
                    setTitle("Internet Unavailable").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            }).create();
            alert.show();

        }
        //otherwise do
        else {
            submitDetails();
        }
    }
    });


}

      public void submitDetails(){
          if(!validateFirstName())
              return;
          if (!validateEmail())
              return;
          if (!validatePhone())
              return;
          if (!validatePassword())
              return;
          if (!validatePassword())
              return;


          pd.setMessage("Registering ....");
          pd.setCanceledOnTouchOutside(false);
          pd.show();

          StringRequest stringRequest=new StringRequest(Request.Method.POST,
                  Constants.register,
                  new Response.Listener<String>() {
                      @Override
                      public void onResponse(String response) {
                          try {
                              JSONObject obj=new JSONObject(response);
                              Toast.makeText(getApplicationContext(),obj.getString("message"),Toast.LENGTH_LONG).show();
                              if(obj.getBoolean("error")==false){
                              }

                          } catch (JSONException e) {
                              e.printStackTrace();
                          }
                          pd.dismiss();



                      }
                  }, new Response.ErrorListener() {
              @Override
              public void onErrorResponse(VolleyError error) {
                  Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();

                  regbut.setEnabled(true);

                  pd.dismiss();

              }
          }
          ){
              @Override
              protected Map<String, String> getParams() throws AuthFailureError {
                  Map<String,String> params=new HashMap<>();
                  params.put("full_name",name.getText().toString());
                  params.put("email",email.getText().toString().trim());
                  params.put("phone",phone.getText().toString().trim());
                  params.put("password",password.getText().toString().trim());
                  params.put("college",college.getText().toString().trim());
                  params.put("gender",radioButton.getText().toString());
                  return params;
              }
          };
          stringRequest.setShouldCache(false);

          RequestHandler.getmInstance(this).addToRequestQueue(stringRequest);



      }


       public boolean isNetworkEnabled(){
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
           if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                   connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
               //we are connected to a network
               return true;
           }
           else
               return false;}





        private class MyTextWatcher implements TextWatcher {
        View view;
        private MyTextWatcher(View view) {
            this.view=view;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            switch (view.getId()){
                case R.id.name:
                    validateFirstName();
                    break;
                case R.id.email:
                    validateEmail();
                    break;
                case R.id.phone:
                    validatePhone();
                    break;
                case R.id.password:
                    validatePassword();
                    break;
                case R.id.college:
                    validateCollege();
                    break;
            }
        }


    }

    private boolean validateCollege() {
        String clg=college.getText().toString();

        if (clg.isEmpty()){
            college_layout.setError("Field can't be empty!");
            requestFocus(college);

            return false;
        }else{
            college_layout.setErrorEnabled(false);
        }
        return true;
    }


    private boolean validatePassword() {
        String pass=password.getText().toString().trim();

        if (pass.isEmpty()){
            password_layout.setError("Field can't be empty!");
            requestFocus(password);

            return false;
        }else{
            password_layout.setErrorEnabled(false);
        }
        return true;
    }


    private boolean validateFirstName() {
        String first=name.getText().toString();

        if (first.isEmpty()){
            name_layout.setError("Field can't be empty!");
            requestFocus(name);

            return false;
        }else{
            name_layout.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validatePhone() {
        String phoneNo=phone.getText().toString().trim();
        if(phoneNo.isEmpty()|| !isValidPhone(phoneNo)){
            phone_layout.setError("invalid phone number");
            requestFocus(phone);

            return false;
        }else if(phoneNo.toCharArray().length!=10){
            phone_layout.setError("number must contain 10 digits");
            requestFocus(phone);
            return false;
        }else{
            phone_layout.setErrorEnabled(false);
        }

        return true;

    }

    private boolean isValidPhone(String phone) {
        return !TextUtils.isEmpty(phone) && Patterns.PHONE.matcher(phone).matches();
    }

    private boolean validateEmail() {
        String emailStr=email.getText().toString().trim();
        if(emailStr.isEmpty() || !isValidEmail(emailStr)){
            email_layout.setError("invalid email");
            email.requestFocus();

            return false;
        }else {
            email_layout.setErrorEnabled(false);
        }
        return true;
    }

    private boolean isValidEmail(String email){
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void requestFocus(View view){
        if(view.requestFocus()){
            this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }



}
