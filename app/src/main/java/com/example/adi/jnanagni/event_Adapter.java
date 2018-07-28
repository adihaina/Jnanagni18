package com.example.adi.jnanagni;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.List;


/**
 * Created by adi on 2/27/2018.
 */

public class event_Adapter extends RecyclerView.Adapter<event_Adapter.ViewHolder> {
    List<all_event_model> itemList;
    public event_Adapter(List<all_event_model> a){
        this.itemList=a;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.all_events_card,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        all_event_model i=itemList.get(position);
        holder.textwa.setText(i.getName());
        final String s=i.getName();
        try {
            Class res = R.drawable.class;
            Field field = res.getField(i.getImage());
            int drawableId = field.getInt(null);
            holder.img.setImageResource(drawableId);
        }
        catch (Exception e) {

        }
     holder.img.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent intent=new Intent(v.getContext(),event_details.class);
             intent.putExtra("event",s);
             v.getContext().startActivity(intent);
         }
     });




    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textwa;
        public ImageView img;

        public ViewHolder(View itemview){
            super(itemview);

            textwa=itemview.findViewById(R.id.textwa);
            img=itemview.findViewById(R.id.img);
        }


    }
}
