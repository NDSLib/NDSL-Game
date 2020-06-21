package com.ndsl.bun133.game.map.block.animator;

import com.ndsl.bun133.display.Display;
import com.ndsl.bun133.game.GameMain;
import com.ndsl.bun133.game.map.block.status.BlockStatus;
import com.ndsl.bun133.game.map.pos.Point;

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

    public void onDraw(Display display){
        drawImage(display,getImage(display.passing_time),blockStatus.Block.pos.getLeft_UP());
    }

    public void drawImage(Display display, Image image, Point point){
        display.getGraphics().drawImage(image,point.x,point.y,null);
        GameMain.logger.debug("[Animator]onDraw:{Image Point:"+point.toString()+"}");
    }

    public Image getImage(long passed_time) {
        if(Default_Image!=null){
            return Default_Image;
        }else{
            return Image_List.get((int) (passed_time%Image_List.size()));
        }
    }
}
