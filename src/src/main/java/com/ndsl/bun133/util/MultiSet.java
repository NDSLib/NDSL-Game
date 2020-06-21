package com.ndsl.bun133.util;

import com.ndsl.bun133.game.GameMain;

public class MultiSet<T,S> {
    public T t_value=null;
    public S s_value=null;

    public MultiSet(T t,S s){
        this.t_value=t;
        this.s_value=s;
    }

    public S getS_value() {
        return s_value;
    }

    public T getT_value() {
        return t_value;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof MultiSet<?,?>){
            MultiSet<?,?> set=(MultiSet<?, ?>) obj;
            return set.t_value==t_value&&set.s_value==s_value;
        }else{
            GameMain.logger.warn("[MultiSet]Object not Mapped to MultiSet");
            return false;
        }
    }

    @Override
    public String toString() {
        return "{T:{"+t_value.toString()+"},S:{"+s_value.toString()+"}}";
    }
}
