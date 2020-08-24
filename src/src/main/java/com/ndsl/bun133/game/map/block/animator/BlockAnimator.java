package com.ndsl.bun133.game.map.block.animator;

import com.ndsl.bun133.game.map.block.status.BlockStatus;
import com.ndsl.graphics.display.drawable.animate.TimeScaledAnimator;
import com.ndsl.graphics.display.drawable.img.GImage;

public class BlockAnimator extends TimeScaledAnimator {
    public BlockStatus status;
    public BlockAnimator(BlockStatus blockStatus, GImage image){
        this(blockStatus,new GImage[]{image});
    }

    public BlockAnimator(BlockStatus blockStatus, GImage... image_List){
        super(blockStatus.Block.bd.getID()+"_animator",1000,image_List);
        this.status=blockStatus;
    }

    public BlockAnimator(BlockStatus blockStatus, long perFrametime, GImage image_List){
        this(blockStatus,perFrametime,new GImage[]{image_List});
    }

    public BlockAnimator(BlockStatus blockStatus, long perFrametime, GImage... image_List){
        super(blockStatus.Block.bd.getID()+"_animator",perFrametime,image_List);
        this.status=blockStatus;
    }
}
