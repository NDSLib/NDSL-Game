package com.ndsl.bun133.display.pos;

public class onDisplayLine {
    public onDisplayPos left_up;
    public onDisplayPos right_down;
    public onDisplayLine(onDisplayPos left_up, onDisplayPos right_down){
        this.left_up=left_up;
        this.right_down=right_down;
    }

    public String toString(){
        return "{Left_up:"+left_up.toString()+",Right_down:"+right_down.toString()+"}";
    }
}
