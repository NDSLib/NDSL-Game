package com.ndsl.game.map.entity;

import com.ndsl.game.pos.onMapEntityPos;
import com.ndsl.graphics.pos.Rect;

import java.awt.*;

public abstract class Entity {
    public abstract void onDraw(Graphics g, Rect nowRect);
    public abstract String EntityName();
    public abstract String EntityID();
    public abstract Rect EntityBounds();
    public abstract void moveTo(onMapEntityPos pos);

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Entity){
            Entity e=(Entity)obj;
            return e.EntityID().equals(this.EntityID());
        }else{
            return false;
        }
    }

    @Override
    public String toString() {
        return "[Entity]"+EntityID();
    }
}
