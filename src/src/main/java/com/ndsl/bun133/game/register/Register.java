package com.ndsl.bun133.game.register;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Register {
    public Map<String, Image> ImageMap=new HashMap<>();
    public Map<String, String> StringMap=new HashMap<>();
    public Register(){

    }

    public Register add(String key, Image image){
        this.ImageMap.put(key, image);
        return this;
    }

    public Register add(String key, String value){
        this.StringMap.put(key, value);
        return this;
    }

    public Image getImage(String key){
        return this.ImageMap.get(key);
    }

    public String getString(String key){
        return this.StringMap.get(key);
    }
}
