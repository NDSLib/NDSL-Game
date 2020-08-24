package com.ndsl.main;

import com.ndsl.bun133.game.GameMain;
import com.ndsl.bun133.game.map.Map;
import com.ndsl.bun133.game.map.graphics.BlockDrawable;
import com.ndsl.bun133.util.FileMaster;
import com.ndsl.graphics.display.drawable.animate.TimeScaledAnimator;
import com.ndsl.graphics.display.drawable.base.Drawable;
import com.ndsl.graphics.display.drawable.img.GImage;
import com.ndsl.graphics.display.layer.Layer;
import com.ndsl.graphics.display.scene.Scene;
import com.ndsl.graphics.pos.Pos;

import java.io.IOException;

import static com.ndsl.bun133.game.GameMain.logger;

public class Main {
    @SuppressWarnings("InfiniteLoopStatement")
    public static void main(String[] args) throws InterruptedException, IOException {
        GameMain gamemain = new GameMain();
        gamemain.main_display.setDebugMode(true);
        Map default_Map=new Map(gamemain.main_display,gamemain.tickRegister,"default_map");
        gamemain.drawer.setMap(default_Map);
        DisplayDrawableUpdate(gamemain);
        logger.debug("Drawable_Count:"+gamemain.main_display.layerManager.get("block_layer").drawableList.size());
        while (true) {
            gamemain.tick();
            default_Map.setDebug(gamemain.main_display);
            gamemain.main_display.debugger.setDebug(gamemain.main_display);
            if(gamemain.main_display.limiter.onUpdate()){
                gamemain.main_display.update();
            }
        }
    }

    public static Scene genMapScene(GameMain gamemain) throws IOException {
        Scene map_scene=new Scene("map_scene");
        Layer layer=new Layer("block_layer");
        map_scene.add(layer,1);
        for(BlockDrawable drawable:gamemain.drawer.getAllDrawables()){
            layer.add(new Drawable(drawable));
        }
        layer.add(new Drawable(new TimeScaledAnimator("Test_Block",1000,new Pos(8,64),GImage.getAll(FileMaster.RESOURCE_LOCATION+"game\\textures\\blocks\\OhNo.png",FileMaster.RESOURCE_LOCATION+"game\\textures\\blocks\\sand.png"))));
        return map_scene;
    }

    public static void DisplayDrawableUpdate(GameMain main) throws IOException {
        genMapScene(main).copyToDisplay(main.main_display);
    }
}
