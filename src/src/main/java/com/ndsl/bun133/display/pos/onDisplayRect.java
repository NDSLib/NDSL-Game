package com.ndsl.bun133.display.pos;

import com.ndsl.bun133.game.map.pos.Point;

import java.awt.*;

public class onDisplayRect {
    public onDisplayPos left_up;
    public onDisplayPos right_down;
    public onDisplayRect(onDisplayPos left_up,onDisplayPos right_down){
        this.left_up=left_up;
        this.right_down=right_down;
    }

    public onDisplayRect(int left_up_x,int left_up_y,int right_down_x,int right_down_y){
        this(new onDisplayPos(left_up_x,left_up_y),new onDisplayPos(right_down_x,right_down_y));
    }

    public int getWidth(){return right_down.pos_x-left_up.pos_x;}

    public int getHeight(){return left_up.pos_y-right_down.pos_y;}

    public Point[] getPoints(){
        Point[] points=new Point[4];
        points[0]=left_up.toPoint();
        points[1]=new Point(left_up.pos_x,right_down.pos_y);
        points[2]=new Point(right_down.pos_x,left_up.pos_y);
        points[3]=right_down.toPoint();
        return points;
    }

    @Override
    public String toString() {
        return "{Left_up:"+this.left_up.toString()+",Right_down:"+this.right_down.toString()+"}";
    }


    public boolean isContain(Point point){
        return left_up.pos_x >= point.x && left_up.pos_y >= point.y && point.x >= right_down.pos_x && point.y >= right_down.pos_y;
    }

    public Rectangle toRectangle(){
        return new Rectangle(left_up.pos_x,right_down.pos_y,getWidth(),getHeight());
    }
}
