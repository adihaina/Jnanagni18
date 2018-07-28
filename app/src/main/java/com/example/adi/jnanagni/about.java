package com.example.adi.jnanagni;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by adi on 2/26/2018.
 */

public class about extends Fragment {
    Animation animation,animation1;
    TextView text;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.about_main,container,false);
       String s="Gurukul Kangri Vishwavidyalaya is not just a mere institution but it's an academy where the cerebral and cultural erudition receptacles are interminably augmenting since 1902. On behalf of Faculty of Engineering and Technology, Gurukul Kangri Vishwavidyalaya, we introduce you the Jnanagni 18, the National level Techno-Cultural Fest, which began its journey from 2006 with huge success.\n" +
                "\n" +
               "\n" +
                "Jnanagni is an effort with the message to have \"Enlightenment Everywhere\" - the Enlightenment of Knowledge, Creativity and Success. Jnanagni aims to celebrate the festival of Experimentation and Learning. The Plethora of Events that Jnanagni promises is designed in a way to Challenge all the Dimension of a Student's Cerebrum and we try to keep assure that the Winner is 'Knowledge' in the end. It will be a pleasure to have you become a part of Jnanagni by way of Sponsorship, Valuable Suggestions, Academic Inputs. Sponsoring Jnanagni means ubiquitous reach, unprecedented mass viability and a growth to your brand equity."
                +"\n";

        text=(TextView)view.findViewById(R.id.text);
        text.setText(s);


        return view;
    }
}
