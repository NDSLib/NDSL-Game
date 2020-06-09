package com.ndsl.bun133.game.map.pos;

import com.ndsl.bun133.game.GameMain;
import com.ndsl.bun133.game.map.Map;


/**
 * @author Bun133
 * @memo left_up is (1,1)
 * @memo right_down is (Map.Map_Size,Map.Map_Size)
 * @see Map
 */
public class onMapBlockPos {
    public int pos_x;
    public int pos_y;
    public Map map;

    public onMapBlockPos(int x, int y, Map map){
        this.pos_x=x;
        this.pos_y=y;
        this.map=map;
    }

    public Rect getRect(){
        return new Rect(getLeft_UP(),getRight_Down());
    }

    public Point getLeft_UP(){
        return getCenter().add(-GameMain.BLOCK_SIZE/2,-GameMain.BLOCK_SIZE/2);
    }

    public Point getRight_Down(){
        return getCenter().add(GameMain.BLOCK_SIZE/2,GameMain.BLOCK_SIZE/2);
    }

    public Point getCenter(){
        return new Point(pos_x*GameMain.BLOCK_SIZE + map.shift_x,pos_y*GameMain.BLOCK_SIZE + map.shift_y);
    }

    public onMapBlockPos add(int shift_x, int shift_y) {
        pos_x+=shift_x;
        pos_y+=shift_y;
        return this;
    }

    public boolean isWrong(){
        return pos_x < 0 || pos_y < 0 || pos_x > Map.Map_Size || pos_y > Map.Map_Size;
    }

    @Override
    public String toString() {
        return "{Pos_x:"+pos_x+" Pos_y:"+pos_y+"}";
    }
}
