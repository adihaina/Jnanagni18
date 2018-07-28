package com.example.adi.jnanagni;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by adi on 3/14/2018.
 */

public class SharedPreferenceManager {

    private static final String Sps="mypef";
    private static final String full_name="full_name";
    private static final String email="email";
    private static final String phone="phone";
    private static final String gender="gender";
    private static final String college="college";
    private static final String transaction="transaction";
    private static final String uid="uid";
    private static final String status="status";




    private static SharedPreferenceManager mInstance;
    private static Context context;

    private SharedPreferenceManager(Context context){
        this.context=context;
    }

    public static synchronized SharedPreferenceManager getInstance(Context context){
        if(mInstance==null)
            mInstance=new SharedPreferenceManager(context);
        return mInstance;
    }

    public boolean userlogin(String mfull_name,String memail,String mphone,String mgender,String mcollege,int muid,String mtransaction
                                                    ,int mstatus){
        MainActivity.edit.putString(full_name,mfull_name);
        MainActivity.edit.putString(email,memail);
        MainActivity.edit.putString(phone,mphone);
        MainActivity.edit.putString(gender,mgender);
        MainActivity.edit.putString(college,mcollege);
        MainActivity.edit.putInt(uid,muid);
        MainActivity.edit.putString(transaction,mtransaction);
        MainActivity.edit.putInt(status,mstatus);
        MainActivity.edit.apply();
        return true;
        }



    public boolean isloggedin(){
        if (MainActivity.sf.getString(email,null)!=null)
            return true;

        return false;
    }

    public boolean logoff(){
        MainActivity.edit.clear();
        MainActivity.edit.apply();

            return true;
    }



    }







