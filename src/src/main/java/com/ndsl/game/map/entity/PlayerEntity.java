package com.ndsl.game.map.entity;

import com.ndsl.game.map.Player;
import com.ndsl.game.pos.onMapEntityPos;
import com.ndsl.graphics.pos.Rect;

import java.awt.*;

public class PlayerEntity extends Entity{
    public static final Rect defaultBounds = new Rect(0,0,100,150);
    public Player player;
    public Rect bound;
    public PlayerEntity(Player player){
        this.player=player;
//        this.bound=new Rect(defaultBounds.left_up,defaultBounds.right_down);//.shift(player.playerInfo.pos.x,player.playerInfo.pos.y);
    }

    @Override
    public void onDraw(Graphics g, Rect nowRect) {
        bound=new Rect((int)g.getClipBounds().getWidth()/2-defaultBounds.getWidth()/2,
                       (int)g.getClipBounds().getHeight()/2-defaultBounds.getHeight()/2,
                       (int)g.getClipBounds().getWidth()/2+defaultBounds.getWidth()/2,
                       (int)g.getClipBounds().getHeight()/2+defaultBounds.getHeight()/2);
        g.drawImage(player.playerInfo.texture.export(),bound.left_up.x,bound.left_up.y,null);
    }

    @Override
    public String EntityName() {
        return "[Player]"+player.getName();
    }

    @Override
    public String EntityID() {
        return "[Player]"+player.getPlayerUUID();
    }

    @Override
    public Rect EntityBounds() {
        return bound==null?defaultBounds:bound;
    }

    @Override
    public void moveTo(onMapEntityPos pos) {
        player.playerInfo.pos=pos;
    }

    public PlayerMoveHelper mover = new PlayerMoveHelper();
    public class PlayerMoveHelper{
    }
}
