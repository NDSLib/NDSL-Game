package com.ndsl.bun133.game.map.graphics;

import com.ndsl.bun133.game.map.Map;
import com.ndsl.bun133.game.map.block.status.BlockStatus;
import com.ndsl.bun133.game.map.pos.onMapBlockPos;
import com.ndsl.graphics.display.drawable.ICustomDrawable;
import com.ndsl.graphics.pos.Rect;

import java.awt.*;

public class BlockDrawable implements ICustomDrawable {
    public onMapBlockPos pos;
    public Map CurrentMap;
    public BlockStatus BlockStatus;


    public BlockDrawable(Image image, onMapBlockPos pos, Map map, BlockStatus status){
        super();
//        super(status.animator.getImage(),pos.getLeft_UP());
        this.pos=pos;
        this.BlockStatus=status;
        this.CurrentMap=map;
    }


    @Override
    public String toString() {
        return "{Type:BlockDrawable:{Point:"+this.BlockStatus.Block.pos.getRect().toString()+",onMapPos:"+this.BlockStatus.Block.pos.toString()+"}}";
    }

    @Override
    public void onDraw(Graphics g, Rect showingRect) {
        BlockStatus.isUpdated=false;
        BlockStatus.animator.onDraw(g);
    }
}
