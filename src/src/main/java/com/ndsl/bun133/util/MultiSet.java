package com.ndsl.bun133.util;

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
}
