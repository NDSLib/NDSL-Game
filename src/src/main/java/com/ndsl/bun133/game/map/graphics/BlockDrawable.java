package com.ndsl.bun133.game.map.graphics;

import com.ndsl.bun133.game.map.Map;
import com.ndsl.bun133.game.map.block.status.BlockStatus;
import com.ndsl.bun133.game.map.pos.onMapBlockPos;
import com.ndsl.graphics.display.drawable.IRealTimeCustomDrawable;
import com.ndsl.graphics.pos.Rect;

import java.awt.*;

import static com.ndsl.bun133.game.GameMain.logger;

public class BlockDrawable implements IRealTimeCustomDrawable {
    public onMapBlockPos pos;
    public Map CurrentMap;
    public BlockStatus BlockStatus;


    public BlockDrawable(Image image, onMapBlockPos pos, Map map, BlockStatus status){
        super();
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
        BlockStatus.animator.onDraw(g);
        logger.debug("[BlockDrawable]"+this.toString()+"is Drawing");
    }

    @Override
    public Rect getShowingRect() {
        logger.low_level_debug("RectGet");
        return pos.getRect();
    }
}
