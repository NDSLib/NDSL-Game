package com.ndsl.game.map;

import com.ndsl.game.map.entity.Entity;
import com.ndsl.game.map.entity.PlayerEntity;
import com.ndsl.game.pos.onMapEntityPos;
import com.ndsl.graphics.display.Display;
import com.ndsl.graphics.display.drawable.IDrawable;
import com.ndsl.graphics.display.drawable.img.GImage;
import com.ndsl.graphics.pos.Pos;
import com.ndsl.graphics.pos.Rect;

import java.awt.*;

public class Player {
    public Info playerInfo;
    public Map currentMap;
    public PlayerEntity entity;
    public Player(Map map,Info info){
        this.currentMap=map;
        this.playerInfo=info;
        entity = genPlayerEntity();
        camera=new Camera(currentMap,this.entity);
    }

    private PlayerEntity genPlayerEntity() {
        PlayerEntity playerEntity=new PlayerEntity(this);
        return playerEntity;
    }

    public String getName(){
        return this.playerInfo.name;
    }

    public String getPlayerUUID(){
        return "[UUID]"+getName();
    }

    public class Info{
        public String name;
        public GImage texture;
        public onMapEntityPos pos;
        public Info(String name, GImage texture, onMapEntityPos spawnPos){
            this.texture=texture;
            this.name=name;
            this.pos=spawnPos;
        }
    }


    public Camera camera;
    public class Camera implements IDrawable {
        public int x;
        public int y;
        public Map currentMap;
        public Entity targetEntity;
        public Camera(Map map,int x, int y){
            this.x=x;
            this.y=y;
            this.currentMap=map;
        }

        public Camera(Map map,Entity targetEntity){
            this.currentMap=map;
            this.targetEntity=targetEntity;
        }

        public Camera(Map map){
            this(map,0,0);
        }

        @Override
        public void onDraw(Graphics graphics, Rect rect) {
            currentMap.drawer.draw(graphics,new Pos(x,y));
        }

        @Override
        public Rect getShowingRect() {
            return null;
        }

        @Override
        public boolean isShowing(Display display) {
            return true;
        }

        @Override
        public String getID() {
            return "[Camera]"+targetEntity.EntityID();
        }
    }
}
