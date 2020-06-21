package com.ndsl.bun133.game.map.block;

import java.awt.*;

public class Block {
    public String BlockID;
    public Image BlockImage;

    public Block(String id, Image image){
        this.BlockID=id;
        this.BlockImage=image;
    }
}
