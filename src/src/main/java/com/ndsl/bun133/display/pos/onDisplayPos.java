package com.ndsl.bun133.display.pos;

import com.ndsl.bun133.game.map.pos.Point;

public class onDisplayPos {
    public int pos_x;
    public int pos_y;
    public onDisplayPos(int x,int y){
        this.pos_x=x;
        this.pos_y=y;
    }

    public Point toPoint(){
        return new Point(pos_x,pos_y);
    }

    public String toString(){
        return "{Pos_x:"+this.pos_x+",Pos_y:"+this.pos_y+"}";
    }
}
