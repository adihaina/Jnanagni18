package com.example.adi.jnanagni;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adi on 2/27/2018.
 */

public class all_events extends Fragment {
    List<String> images=null;
    RecyclerView recycle;
    RecyclerView.Adapter adapter;

    public all_events(){}
    public void setValue(List<String> a){
        this.images=null;
        this.images=a;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.all_events,container,false);

        recycle=(RecyclerView)view.findViewById(R.id.recycle);
        recycle.setHasFixedSize(true);
        recycle.setLayoutManager(new LinearLayoutManager(getContext()));
        List<all_event_model> itemList=new ArrayList<>();
        if(images!=null){
        for(String s:images){
            if(s!=null){
            all_event_model aa=new all_event_model(s,s);
            itemList.add(aa);}
        }}


        adapter=new event_Adapter(itemList);
        recycle.setAdapter(adapter);
        return view;
    }

}