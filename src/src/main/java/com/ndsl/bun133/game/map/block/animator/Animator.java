package com.ndsl.bun133.game.map.block.animator;

import com.ndsl.bun133.game.GameMain;
import com.ndsl.bun133.game.map.block.status.BlockStatus;
import com.ndsl.graphics.pos.Pos;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Animator {
    public List<Image> Image_List=new ArrayList<>();
    public Image Default_Image=null;
    public BlockStatus blockStatus;

    public Animator(Image image, BlockStatus blockStatus){
        Default_Image=image;
        this.blockStatus=blockStatus;
    }

    public Animator(List<Image> image_List, BlockStatus blockStatus){
        Image_List=image_List;
        this.blockStatus=blockStatus;
    }

    public void onDraw(Graphics g){
        onFrame();
        drawImage(g,getImage(frame_count),blockStatus.Block.pos.getLeft_UP());
    }

    public void drawImage(Graphics g, Image image, Pos point){
        g.drawImage(image,point.x,point.y,null);
        GameMain.logger.debug("[Animator]onDraw:{Image Point:"+point.toString()+"}");
    }

    public Image getImage(long passed_time) {
        if(Default_Image!=null){
            return Default_Image;
        }else{
            return Image_List.get((int) (passed_time%Image_List.size()));
        }
    }

    public long frame_count=0;
    public void onFrame(){
        ++frame_count;
    }
}
