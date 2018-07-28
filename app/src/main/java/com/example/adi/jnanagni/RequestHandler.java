package com.example.adi.jnanagni;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by adi on 3/14/2018.
 */

public class RequestHandler {
    private static RequestHandler mInstance;
    private RequestQueue mrequestQueue;
    private static Context context;

    private RequestHandler(Context context){
        this.context=context;
        this.mrequestQueue=getMrequestQueue();
    }

    public static synchronized RequestHandler getmInstance(Context context){
        if(mInstance==null)
            mInstance=new RequestHandler(context);
        return mInstance;
    }

    public RequestQueue getMrequestQueue()
    {
        if(mrequestQueue==null){
            mrequestQueue= Volley.newRequestQueue(context.getApplicationContext());

        }
        return mrequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req){
        getMrequestQueue().add(req);
    }


}
