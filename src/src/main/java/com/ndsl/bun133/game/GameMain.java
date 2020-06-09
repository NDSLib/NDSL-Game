package com.ndsl.bun133.game;

import com.ndsl.bun133.display.Display;
import com.ndsl.bun133.display.drawable.Drawable;
import com.ndsl.bun133.display.key.KeyInput;
import com.ndsl.bun133.game.map.Map;
import com.ndsl.bun133.game.map.pos.Point;
import com.ndsl.bun133.game.register.Blocks;
import com.ndsl.bun133.game.util.TickRegister;
import com.ndsl.bun133.logger.Logger;

import java.awt.*;

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


    public Display display=new Display("Test",100,100,500,500);

    public KeyInput keyInput=new KeyInput(display);
    public Map map=new Map(display,keyInput,tickRegister);


    public GameMain(){
        logger.debug("[GameMain]onStart");
        display.addDrawable(new Drawable(Blocks.TEST_BLOCK.BlockImage,new Point(100,100)));
        display.addDrawable(map.getAll().toArray(new Drawable[0]));
        logger.debug("[GameMain]Init End");
    }

    public void run(){
        tickRegister.onTick();
    }
}
