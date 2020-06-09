package com.ndsl.bun133.game.map.chunk.block.status;

import com.ndsl.bun133.game.map.chunk.block.animator.Animator;
import com.ndsl.bun133.game.map.chunk.block.onMapBlock;

public class BlockStatus {
    public boolean isUpdated=true;
    public onMapBlock Block;

    public BlockStatus(onMapBlock block){
        this.Block=block;
        this.animator=new Animator(block.block.BlockImage,this);
    }

    public void onUpdate(){
        isUpdated=true;
    }

    public NBT nbt=new NBT();

    public Animator animator;

    public Animator getAnimator(){
        return animator;
    }
}