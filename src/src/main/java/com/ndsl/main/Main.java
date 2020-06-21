package com.ndsl.main;

import com.ndsl.bun133.game.GameMain;
import com.ndsl.bun133.game.map.Map;
import com.ndsl.graphics.display.drawable.Drawable;
import com.ndsl.graphics.display.scene.Scene;

import static com.ndsl.bun133.game.GameMain.logger;

public class Main {
    @SuppressWarnings("InfiniteLoopStatement")
    public static void main(String[] args) throws InterruptedException {
        GameMain gamemain = new GameMain();
        gamemain.main_display.setDebugMode(true);
        Map default_Map=new Map(gamemain.main_display,gamemain.tickRegister,"default_map");
        gamemain.drawer.setMap(default_Map);
//        Scene map_scene=genMapScene(gamemain);
        DisplayDrawableUpdate(gamemain);
        //Test
        //Passed
//        map_scene.add(new Drawable(Blocks.TEST_BLOCK.BlockImage,new Rect(100,100,100+GameMain.BLOCK_SIZE,100+GameMain.BLOCK_SIZE),"block_test"));
//        map_scene.copyToDisplay(gamemain.main_display);
        logger.debug("Drawable_Count:"+gamemain.main_display.drawableList.size());
        while (true) {
            gamemain.run();
//            DisplayDrawableUpdate(gamemain);
//            GameMain.logger.println("Test");
//            Thread.sleep(1);
            default_Map.setDebug(gamemain.main_display);
            gamemain.main_display.debugger.setDebug(gamemain.main_display);
            if(gamemain.main_display.limiter.onUpdate()){
                gamemain.main_display.update();
            }
//            gamemain.display.update();
        }
    }

    public static Scene genMapScene(GameMain gamemain){
        Scene map_scene=new Scene("map_scene");
        for(Drawable drawable:gamemain.drawer.getAllDrawables()){
            map_scene.add(drawable);
        }
        return map_scene;
    }

    public static void DisplayDrawableUpdate(GameMain main){
        genMapScene(main).copyToDisplay(main.main_display);
    }
}
