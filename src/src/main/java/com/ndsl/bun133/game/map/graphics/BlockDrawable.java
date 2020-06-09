package com.ndsl.bun133.game.map.graphics;

import com.ndsl.bun133.display.Display;
import com.ndsl.bun133.display.drawable.Drawable;
import com.ndsl.bun133.display.drawable.DrawableType;
import com.ndsl.bun133.game.GameMain;
import com.ndsl.bun133.game.map.Map;
import com.ndsl.bun133.game.map.chunk.block.status.BlockStatus;
import com.ndsl.bun133.game.map.pos.onMapBlockPos;

import java.awt.*;

public class BlockDrawable extends Drawable {
    public onMapBlockPos pos;
    public Map CurrentMap;
    public BlockStatus BlockStatus;


    public BlockDrawable(Image image, onMapBlockPos pos, Map map, BlockStatus status){
        super();
//        super(status.animator.getImage(),pos.getLeft_UP());
        this.pos=pos;
        this.BlockStatus=status;
        this.CurrentMap=map;
        this.Drawabletype= DrawableType.BLOCK;
    }

    @Override
    public void draw(Display display) {
        BlockStatus.isUpdated=false;
        BlockStatus.animator.onDraw(display);
    }

    @Override
    public boolean isNeedDraw() {
        return BlockStatus.isUpdated;
    }

    @Override
    public boolean isShowing(Display display) {
        return CurrentMap.isShowing(pos,display);
    }

    @Override
    public void onAdded() {
        GameMain.logger.low_level_debug("[BlockDrawable]onAdded");
    }

    @Override
    public String toString() {
        return "{Type:BlockDrawable:{Point:"+this.BlockStatus.Block.pos.getRect().toString()+",onMapPos:"+this.BlockStatus.Block.pos.toString()+"}}";
    }
}
