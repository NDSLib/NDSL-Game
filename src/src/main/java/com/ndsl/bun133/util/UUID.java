package com.ndsl.bun133.util;

import java.util.Random;

public class UUID {
    public static Random random=new Random();
    public static long getUUID(){
        long l=System.currentTimeMillis();
        l = l << 6;
        l+=(random.nextLong()*4);
        return l;
    }
}
