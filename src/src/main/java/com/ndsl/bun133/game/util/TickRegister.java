package com.ndsl.bun133.game.util;

import java.util.ArrayList;
import java.util.List;

public class TickRegister {
    public TickRegister(){

    }

    public List<ITickEvent> tickEvents=new ArrayList<>();

    public TickRegister add(ITickEvent e){
        tickEvents.add(e);
        return this;
    }

    public void onTick(){
        for(ITickEvent e:tickEvents){
            e.onTick();
        }
    }
}
