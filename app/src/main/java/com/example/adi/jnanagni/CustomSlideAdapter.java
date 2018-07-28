package com.example.adi.jnanagni;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by adi on 3/17/2018.
 */

class CustomSlideAdapter extends PagerAdapter{
    private Context context;
    private LayoutInflater layoutInflater;
    private int[] images={R.drawable.six,R.drawable.four,R.drawable.eight,R.drawable.three,R.drawable.ten,R.drawable.five,R.drawable.zero,R.drawable.one,R.drawable.two,R.drawable.seven,R.drawable.nine};

    public CustomSlideAdapter(Context context) {
        this.context=context;
        }

    public int getCount(){
        return images.length;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item_view=layoutInflater.inflate(R.layout.slider,container,false);
        ImageView viewpage_image=(ImageView)item_view.findViewById(R.id.viewpage_image);
        viewpage_image.setImageResource(images[position]);
        container.addView(item_view);
        return item_view;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view==(ImageView)object);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ImageView) object);
    }
}
