package com.ndsl.bun133.util;

import java.net.URL;

@Deprecated
public class ResourceLocation {
    public String filename;
    public ResourceLocation(String filename){
        this.filename=filename;
    }

    public void init(){
        URL url = Thread.currentThread().getContextClassLoader().getResource(filename);
        assert url != null;
    }
}
