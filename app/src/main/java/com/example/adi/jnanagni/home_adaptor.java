package com.example.adi.jnanagni;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by adi on 2/27/2018.
 */

public class home_adaptor extends RecyclerView.Adapter<home_adaptor.ViewHolder>{
   List<home_model_class>model=new ArrayList<>();
    public home_adaptor(List<home_model_class> a){
        this.model=a;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.home_card,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        home_model_class mod=model.get(position);
        final String s=mod.getName();
        holder.img.setImageResource(mod.id);
        holder.home_text.setText(mod.getName().toUpperCase().replace("_","-"));
        if(mod.getType().equals("event")){

            holder.img.setOnClickListener(new View.OnClickListener() {
                @Override

                public void onClick(View v){
                    Intent intent=new Intent(v.getContext(),event_details.class);
                    intent.putExtra("event",s);
                    v.getContext().startActivity(intent);

                }
            });
    }}


    @Override
    public int getItemCount() {
        return model.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView home_text;
        public ImageView img;


        public ViewHolder(View itemview){
            super(itemview);
            home_text=itemview.findViewById(R.id.hometext);
            img=itemview.findViewById(R.id.home_card_img);
        }


    }
}
