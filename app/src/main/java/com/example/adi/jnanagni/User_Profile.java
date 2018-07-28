package com.example.adi.jnanagni;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by adi on 3/17/2018.
 */

public class User_Profile extends Fragment implements View.OnClickListener{
    TextView emails,phs,clgs,gens,username,payments;
    Button logout_button,verify;
    ImageView img;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.user_profile,container,false);
      emails=(TextView)view.findViewById(R.id.emails);
        phs=(TextView)view.findViewById(R.id.phs);
        gens=(TextView)view.findViewById(R.id.genders);
        img=(ImageView)view.findViewById(R.id.imageView);
        clgs=(TextView)view.findViewById(R.id.clgs);
  //     payments=(TextView)view.findViewById(R.id.Payments);
    //    verify=(Button)view.findViewById(R.id.verify);





        logout_button=(Button)view.findViewById(R.id.logout_button);
        logout_button.setOnClickListener(this);
        username=(TextView)view.findViewById(R.id.user_name);

        if(SharedPreferenceManager.getInstance(getActivity()).isloggedin()){

            username.setText(MainActivity.sf.getString("full_name",null));


        emails.setText(MainActivity.sf.getString("email",null));

        phs.setText(MainActivity.sf.getString("phone",null));

        gens.setText(MainActivity.sf.getString("gender",null));
        clgs.setText(MainActivity.sf.getString("college",null));
   //     if(MainActivity.sf.getInt("status",0)==0)
     //   {
       //     payments.setText("Not-Paid");
         //   verify.setVisibility(View.VISIBLE);
           // verify.setOnClickListener(this);

        //}
        //else{
          //  payments.setText("Paid");
            //verify.setVisibility(View.GONE);


        //}

            Picasso.with(getContext())
                    .load("https://pikmail.herokuapp.com/"+MainActivity.sf.getString("email",null)+"?size=100").error(R.drawable.adi).into(img);



        }
        else{
            username.setText("GUEST USER");
            img.setImageResource(R.drawable.adi);}

        return view;

    }

    public void onClick(View v){
        if(v.getId()==R.id.logout_button){
        SharedPreferenceManager.getInstance(getContext()).logoff();
        startActivity(new Intent(getActivity(),MainActivity.class));
    }}
 //   else if(v.getId()==R.id.verify){

   //         AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
     //       AlertDialog alert = builder.setMessage("Payment ").
       //             setTitle("Update Payment Info").setPositiveButton("UPI", new DialogInterface.OnClickListener() {
         //       @Override
           //     public void onClick(DialogInterface dialogInterface, int i) {
             //       upi("UPI");
               //     dialogInterface.cancel();

                //}
           // }).setNegativeButton("Reciept",new DialogInterface.OnClickListener() {
             //   @Override
               // public void onClick(DialogInterface dialogInterface, int i) {
                 //   upi("Reciept");
                   // dialogInterface.cancel();
                //}}).create();
//            alert.show();

  //      }
    //}
    public void upi(String title) {
        String message;
        if(title.equals("UPI"))
        {  message= "\t\tUPI ID : rajgaj999@okaxis" +
                "\n\n1. Transfer an Amount of 400/- Rupees on the given UPI ID via your Phonepe, Tezz, BHIM or any other payment methods." + "(It is compulsory to mention your Name and Registered Contact No. in the \"Remarks\" field)" +
                "\n2. Submit the Transaction ID ,here in the following text field for Verification Purposes." +
                "\nFor any Help Call : 9450156382 (Aditya)";}
        else{
            message="After getting Recipt from Organiser, Please Submit your Recipt Number for Verification Purpose";
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(title).setMessage(message);

// Set up the input
        final ProgressDialog pd=new ProgressDialog(getContext());
        final EditText input = new EditText(getContext());

// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        builder.setView(input);

// Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(input.getText().toString().compareTo("")==0)
                {
                   // input.setError("This field can't be empty");
                    //input.requestFocus();
                }
                else{
                    pd.setMessage("Updating...");
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
                                            Toast.makeText(getActivity(), obj.getString("message"), Toast.LENGTH_LONG).show();
                                        }
                                        else{
                                            SharedPreferenceManager.getInstance(getActivity()).userlogin(obj.getString("full_name")
                                                    ,obj.getString("email"),obj.getString("phone"),obj.getString("gender")
                                                    ,obj.getString("college"),obj.getInt("uid"),obj.getString("transaction"),
                                                    obj.getInt("status"));

                                            Toast.makeText(getActivity(),"You are logged in as:"+obj.getString("full_name").toUpperCase().trim(),Toast.LENGTH_LONG).show();
                                            startActivity(new Intent(getActivity(),MainActivity.class));
                                        } }catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    pd.dismiss();

                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getActivity(),error.getMessage(),Toast.LENGTH_LONG).show();
                            pd.dismiss();

                        }
                    }
                    ){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String,String> params=new HashMap<>();
                            params.put("uid",String.valueOf(MainActivity.sf.getInt("uid",-1)));
                            params.put("transaction",input.getText().toString());
                            return params;
                        }
                    };


                    stringRequest.setShouldCache(false);
                    RequestHandler.getmInstance(getActivity()).addToRequestQueue(stringRequest);

                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();

    }
}
