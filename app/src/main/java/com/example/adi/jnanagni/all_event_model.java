package com.example.adi.jnanagni;

/**
 * Created by adi on 2/27/2018.
 */

public class all_event_model {
    String name,image;

    public all_event_model(String image,String name){
        this.image=image;
        this.name=name.toUpperCase().replace("_","-");
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }
}
