package com.example.adi.jnanagni;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by adi on 3/17/2018.
 */

public class Home_screen extends Fragment {
    List<home_model_class> model1=new ArrayList<>();
    List<home_model_class> model2=new ArrayList<>();


    private int[] images={R.drawable.six,R.drawable.three,R.drawable.four,R.drawable.eight,R.drawable.ten,R.drawable.five,R.drawable.zero,R.drawable.one,R.drawable.two,R.drawable.seven,R.drawable.nine};
    private String[] initiatives={"Free Health Camp","NSS","Team-Pride","Swatch-Bharat","NCC","Namo-Ganga","Blood-Donation"};
    private int[] initiative={R.drawable.health,R.drawable.nss,R.drawable.pride,R.drawable.nukkar,R.drawable.ncc,R.drawable.namo,R.drawable.blood};
    private String[] event={"ameliorator","annihilator","codeathon","exgesis","cricket_keeda","hydroriser","inclino","nopc","tinkerer",
    "abhivyakti","craftsvilla","enthuse","freedoscrawl","kalakriti","treasure_hunt","third_vision","counter_strike","fifa","nfs",
    "fancy_footwork","kritika","lol","nautankishala","sargam","clash_on","celebrity-night","gyan_zara_hat_ke"};
    private int[] events={R.drawable.ameliorator,R.drawable.annihilator,R.drawable.codeathon,R.drawable.exgesis,R.drawable.cricket_keeda,R.drawable.hydroriser,R.drawable.inclino,R.drawable.nopc,R.drawable.tinkerer,
            R.drawable.abhivyakti,R.drawable.craftsvilla,R.drawable.enthuse,R.drawable.freedoscrawl,R.drawable.kalakriti,R.drawable.treasure_hunt,R.drawable.third_vision,R.drawable.counter_strike,R.drawable.fifa,R.drawable.nfs,
            R.drawable.fancy_footwork,R.drawable.kritika,R.drawable.lol,R.drawable.nautankishala,R.drawable.sargam,R.drawable.clash_on,R.drawable.celebrity_night,R.drawable.gyan_zara_hat_ke};

    RecyclerView recycle_initiative,recycle_event;
    RecyclerView.Adapter recycle_adapter1,recycle_adapter2;

    ViewPager viewpager;
    int currentPage = 0;
    Timer timer;
    final long DELAY_MS = 700;//delay in milliseconds before task is to be executed
    final long PERIOD_MS = 2000; // time in milliseconds between successive task executions.
    ScrollView sv;

    CustomSlideAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.home_screen,container,false);
        sv=(ScrollView)view.findViewById(R.id.sv);
        sv.post(new Runnable() {
            @Override
            public void run() {
                sv.scrollTo(0,0);
            }
        });


        for(int i=0;i<initiative.length;i++){
            home_model_class mod=new home_model_class(initiatives[i],initiative[i],"initiative");
            model1.add(mod);
        }
        for(int i=0;i<event.length;i++){
            home_model_class mod=new home_model_class(event[i],events[i],"event");
            model2.add(mod);
        }


        recycle_initiative=(RecyclerView)view.findViewById(R.id.recycle_initiatives);
        recycle_initiative.setHasFixedSize(true);
        recycle_initiative.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        recycle_adapter1=new home_adaptor(model1);
        recycle_initiative.setAdapter(recycle_adapter1);





        recycle_event=(RecyclerView)view.findViewById(R.id.recycle_events);
        recycle_event.setHasFixedSize(true);
        recycle_event.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        recycle_adapter2=new home_adaptor(model2);
        recycle_event.setAdapter(recycle_adapter2);







        viewpager=(ViewPager)view.findViewById(R.id.viewpager);
        adapter=new CustomSlideAdapter(getActivity());
        viewpager.setAdapter(adapter);


        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == adapter.getCount()) {
                    currentPage = 0;
                }
                viewpager.setCurrentItem(currentPage++, true);
            }
        };

        timer = new Timer(); // This will create a new Thread
        timer .schedule(new TimerTask() { // task to be scheduled

            @Override
            public void run() {
                handler.post(Update);
            }
        }, DELAY_MS, PERIOD_MS);

        return view;
    }

}
