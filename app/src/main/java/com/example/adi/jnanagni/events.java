package com.example.adi.jnanagni;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by adi on 2/27/2018.
 */


public class events extends Fragment {
    String a[]={"ameliorator","annihilator","codeathon","cipher","weber","speedomer","concatenation","cuandigo","electricio","electroguisal","exgesis","hydroriser","inclino","nopc","tinkerer"};
    String b[]={"abhivyakti","craftsvilla","enthuse","freedoscrawl","kalakriti","gyan_zara_hat_ke","startup_fair","cricket_keeda","treasure_hunt","qcognito","third_vision"};
    String c[]={"bowling","cube","dart","mini_militia","throw_ball"};
    String d[]={"badminton","carrom","chess","counter_strike","fifa","nfs","clash_on","table_tennis"};
    String e[]={"fancy_footwork","kritika","lol","nautankishala","sargam"};
    String f[]={"celebrity_night"};
    List<String> technical=new ArrayList<>(Arrays.asList(a));
    List<String> nontechnical=new ArrayList<>(Arrays.asList(b));
    List<String> fun=new ArrayList<>(Arrays.asList(c));
    List<String> game=new ArrayList<>(Arrays.asList(d));
    List<String> cultural=new ArrayList<>(Arrays.asList(e));
    List<String> celeb=new ArrayList<>(Arrays.asList(f));


    ViewPager viewPager;
    TabLayout tabLayout;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.events,container,false);


        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        viewPager.setAdapter(new sliderAdapter(getChildFragmentManager()));
        tabLayout = (TabLayout) view.findViewById(R.id.sliding_tabs);
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });


        return view;
    }
    private class sliderAdapter extends FragmentPagerAdapter {


        final  String tabs[]={"Technical","Non-Technical","Fun","Game","Cultural","Celebrity Night"};
        public sliderAdapter(FragmentManager fm) {
            super(fm);
        }




        @Override
        public Fragment getItem(int position) {
            all_events t1=new all_events();
            switch (position){
                case 0:
                    t1.setValue(technical);
                    return t1;
                case 1:
                    t1.setValue(nontechnical);
                    return t1;
                case 2:
                    t1.setValue(fun);
                    return t1;
                case 3:
                    t1.setValue(game);
                    return t1;
                case 4:
                    t1.setValue(cultural);
                    return t1;
                case 5:
                    t1.setValue(celeb);
                    return t1;





            }
            return null;
        }

        @Override
        public int getCount() {
            return 6;
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return tabs[position];
        }
    }
}

