package com.example.adi.jnanagni;

/**
 * Created by adi on 3/18/2018.
 */

public class home_model_class {
    String name;
    String type;
    int id;

    public String getType() {
        return type;
    }

    public home_model_class(String name, int id, String type) {
        this.name = name;
        this.id = id;
        this.type=type;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
