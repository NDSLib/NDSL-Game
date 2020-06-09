package com.ndsl.bun133.game.map.chunk.block.status;

import com.ndsl.bun133.game.GameMain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NBT {
    public Map<String, String> String_map=new HashMap<>();
    public Map<String, Boolean> Bool_map=new HashMap<>();
    public Map<String, Integer> Int_map=new HashMap<>();
    public Map<String, List> List_map=new HashMap<>();

    public NBT add(String key, String value) {
        String_map.put(key, value);
        return this;
    }

    public NBT add(String key, Boolean value) {
        Bool_map.put(key, value);
        return this;
    }

    public NBT add(String key, Integer value) {
        Int_map.put(key, value);
        return this;
    }

    public NBT add(String key, List value) {
        List_map.put(key, value);
        return this;
    }

    public boolean isExist(String key){
        return String_map.containsKey(key) || Bool_map.containsKey(key) || Int_map.containsKey(key) || List_map.containsKey(key);
    }

    public String getString(String key){
        if(String_map.containsKey(key)){
            return String_map.get(key);
        }
        GameMain.logger.error("[NBT]Not Found Value for Key:"+key);
        return null;
    }

    public Boolean getBool(String key){
        if(Bool_map.containsKey(key)){
            return Bool_map.get(key);
        }
        GameMain.logger.error("[NBT]Not Found Value for Key:"+key);
        return null;
    }

    public Integer getInt(String key){
        if(Int_map.containsKey(key)){
            return Int_map.get(key);
        }
        GameMain.logger.error("[NBT]Not Found Value for Key:"+key);
        return null;
    }

    public List getList(String key){
        if(List_map.containsKey(key)){
            return List_map.get(key);
        }
        GameMain.logger.error("[NBT]Not Found Value for Key:"+key);
        return null;
    }
}
