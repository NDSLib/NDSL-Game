package com.ndsl.bun133.game.map.pos;

@Deprecated
public class Point {
    public int x;
    public int y;
    public Point(int x,int y){
        this.x=x;
        this.y=y;
    }

    public Point add(int shift_x, int shift_y){
        this.x+=shift_x;
        this.y+=shift_y;
        return this;
    }

    public String toString(){
        return "{Pos_x:"+this.x+",Pos_y:"+this.y+"}";
    }
}
