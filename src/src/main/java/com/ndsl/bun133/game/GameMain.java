package com.ndsl.bun133.game;

import com.ndsl.bun133.game.map.Map;
import com.ndsl.bun133.game.map.graphics.BlockDrawable;
import com.ndsl.bun133.game.util.TickRegister;
import com.ndsl.bun133.logger.Logger;
import com.ndsl.graphics.display.Display;
import com.ndsl.graphics.pos.Pos;

import java.awt.*;
import java.util.List;


public class GameMain {
    /** Blockの解像度 **/
    public static final int BLOCK_SIZE=64;
    /** 1チャンクの1辺のブロック数 **/
    public static final int CHUNK_SIZE=255;
    public static final int CHUNK_SIZE_HALF=128;
    public static final Logger logger=new Logger(true,false);

    public static final boolean Drawable_Debug=true;
    public static final Color Debug_Color=new Color(255,0, 215);

    public TickRegister tickRegister=new TickRegister();
    public Display main_display=new Display("Title",3,new com.ndsl.graphics.pos.Rect(new Pos(100,100),new Pos(600,600)));
    public GameMain(){
        logger.debug("[GameMain]onStart");
//        main_display.addDrawable(new Drawable(new Animator("Test_Block",new GImage(Blocks.TEST_BLOCK.BlockImage))));
//        display.addDrawable(map.getAll().toArray(new Drawable[0]));
        logger.debug("[GameMain]Init End");
    }

    public void tick(){
        tickRegister.onTick();
    }

    public drawer drawer=new drawer();
    public class drawer{
        private drawer(){}

        public Map currentMap=null;
        public drawer setMap(Map map){currentMap=map;return this;}

        public List<BlockDrawable> getAllDrawables(){
            return currentMap.getAll();
        }

    }
}
