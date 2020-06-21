package com.ndsl.bun133.game.map.block.status;

import com.ndsl.bun133.game.map.block.animator.Animator;
import com.ndsl.bun133.game.map.block.onMapBlock;

public class BlockStatus {
    public onMapBlock Block;

    public BlockStatus(onMapBlock block){
        this.Block=block;
        this.animator=new Animator(block.block.BlockImage,this);
    }


    public NBT nbt=new NBT();

    public Animator animator;

    public Animator getAnimator(){
        return animator;
    }
}