package com.ndsl.bun133.game.map.block;

import com.ndsl.graphics.display.drawable.img.GImage;

public class Block {
    public String BlockID;
    public GImage BlockImage;

    public Block(String id, GImage image){
        this.BlockID=id;
        this.BlockImage=image;
    }
}
