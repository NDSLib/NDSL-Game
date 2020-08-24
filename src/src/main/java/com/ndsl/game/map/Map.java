package com.ndsl.game.map;

import com.ndsl.game.map.entity.Entity;
import com.ndsl.graphics.display.Display;
import com.ndsl.graphics.display.drawable.IDrawable;
import com.ndsl.graphics.pos.Pos;
import com.ndsl.graphics.pos.Rect;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Map implements IDrawable {
    public int map_width;
    public int map_height;
    public String map_name;
    boolean isInit=false;
    public Map(String map_name,int width, int height){
        this.map_width=width;
        this.map_height=height;
        this.map_name=map_name;
    }

    public void onTick() {
        this.tickRegister.onTick();
    }

    @Override
    public void onDraw(Graphics graphics, Rect rect) {
        drawer.onDraw(graphics);
    }

    @Override
    public Rect getShowingRect() {
        return null;
    }

    @Override
    public boolean isShowing(Display display) {
        return false;
    }

    @Override
    public String getID() {
        return "map"+map_name;
    }

    public TickRegister tickRegister=new TickRegister();
    public class TickRegister{
        public List<ITickable> listeners=new ArrayList<ITickable>();
        private void onTick(){
            for(ITickable t : listeners){
                t.onTick(Map.this);
            }
        }
    }

    public EntityManager entityManager=new EntityManager();
    public class EntityManager{
        private List<Entity> entities=new ArrayList<Entity>();
        public void add(Entity e){
            entities.add(e);
        }

        public Entity getEntity(String id){
            for(Entity e:entities){
                if(e.EntityID().equals(id)){
                    return e;
                }
            }
            return null;
        }
    }

    public PlayerManager playerManager=new PlayerManager();
    public class PlayerManager{
        private List<Player> players=new ArrayList<>();
        public void addPlayer(Player player){
            players.add(player);
            entityManager.add(player.entity);
        }
    }

    public MapDrawer drawer=new MapDrawer();
    public class MapDrawer{
        public void onDraw(Graphics g, Pos shift_pos){
            
        }
    }
}
