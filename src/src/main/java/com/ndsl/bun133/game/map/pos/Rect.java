package com.ndsl.bun133.game.map.pos;

import com.ndsl.bun133.game.GameMain;

import java.awt.*;

@Deprecated
public class Rect {
    public Point left_up;
    public Point right_down;
    public Rect(Point left_up, Point right_down){
        this.left_up=left_up;
        this.right_down=right_down;
    }

    public Rect(int left_up_x,int left_up_y,int right_down_x,int right_down_y){
        this(new Point(left_up_x,left_up_y),new Point(right_down_x,right_down_y));
    }

    public Point[] getPoints(){
        Point[] points=new Point[4];
        points[0]=left_up;
        points[1]=new Point(left_up.x,right_down.y);
        points[2]=new Point(right_down.x,left_up.y);
        points[3]=right_down;
        return points;
    }

    @Deprecated
    /**
     * @see intersects( Point )
     */
    public boolean isContain(Point point) {
        return left_up.x >= point.x && left_up.y >= point.y && point.x >= right_down.x && point.y >= right_down.y;
//        return x < r.x + r.width && x + width > r.x && y < r.y + r.height && y + height > r.y;
    }
    @Deprecated
    /**
     * @see intersects( Rect )
     */
    public boolean isContain(Rect rect){
//        for(Point point:getPoints()){
//            if(rect.isContain(point)) {
//                GameMain.logger.warn("[Rect]isContain:true_1");
//                return true;
//            }
//        }
//        int x1=left_up.x;
//        int x2=right_down.x;
//        int x3=rect.left_up.x;
//        int x4=rect.right_down.x;
//        int y1=left_up.y;
//        int y2=right_down.y;
//        int y3=rect.left_up.y;
//        int y4=rect.right_down.y;
//        boolean b=(x1 < x4) && (x3 < x2) && (y1 < y4) && (y3 < y2);
//        GameMain.logger.debug(b ? "[Rect]isContain:true" : "[Rect]isContain:false");
//        return b;
//        return left_up.x < rect.left_up.x + rect.getWidth() && left_up.x + getWidth() > rect.left_up.x && rect.left_up.y < rect.left_up.y + rect.getHeight() && rect.left_up.y + rect.getHeight() > rect.left_up.y;
//        for(Point point:rect.getPoints()){
//            if(isContain(point)) {
//                GameMain.logger.warn("[Rect]isContain:true_2");
//                return true;
//            }
//        }
//        GameMain.logger.low_level_debug("[Rect]isContain:false");
//        return false;
        boolean b=intersects(rect);
        GameMain.logger.debug(b ? "[Rect]isContain:true" : "");
        return b;
    }


//    @Deprecated
//    /**
//     * @see intersects( onDisplayRect )
//     */
//    public boolean isContain(onDisplayRect rect){
//        for(Point point:rect.getPoints()){
//            if(isContain(point)) return true;
//        }
//        return false;
//        boolean b=intersects(rect);
//        GameMain.logger.debug(b ? "[Rect]isContain:true" : "");
//        return b;
//    }

//    @Deprecated
//    public boolean isOverlap(onDisplayRect rect){
//        for(Point point:getPoints()){
//            if(rect.isContain(point)) return true;
//        }
//        for(Point point:rect.getPoints()){
//            if(isContain(point)) return true;
//        }
//        return false;
//    }

    public String toString(){
        return "{Left_up:"+this.left_up.toString()+",Right_down:"+this.right_down.toString()+"}";
    }

    public int getHeight(){
        return left_up.y-right_down.y;
    }

    public int getWidth(){
        return right_down.x-left_up.x;
    }

    public Rectangle toRectangle(){
        return new Rectangle(left_up.x,right_down.y,getWidth(),getHeight());
    }

    public boolean intersects(Rect rect){
        for(Point point:rect.getPoints()){
            if(intersects(point)) return true;
        }
        return false;
    }

//    @Deprecated
//    public boolean intersects(onDisplayRect rect){
//        for(Point point:rect.getPoints()){
//            if(intersects(point)) return true;
//        }
//        return false;
//    }

    public boolean intersects(Point point){
        return left_up.x<=point.x && point.x<=right_down.x && left_up.y<=point.y && point.y<=right_down.y;
    }
}
