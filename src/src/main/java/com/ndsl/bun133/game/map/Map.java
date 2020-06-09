package com.ndsl.bun133.game.map;

import com.ndsl.bun133.display.Display;
import com.ndsl.bun133.display.key.KeyInput;
import com.ndsl.bun133.game.GameMain;
import com.ndsl.bun133.game.map.chunk.block.onMapBlock;
import com.ndsl.bun133.game.map.gen.IGenerator;
import com.ndsl.bun133.game.map.graphics.BlockDrawable;
import com.ndsl.bun133.game.map.pos.Rect;
import com.ndsl.bun133.game.map.pos.onMapBlockPos;
import com.ndsl.bun133.game.map.pos.onMapRect;
import com.ndsl.bun133.game.register.Blocks;
import com.ndsl.bun133.game.util.ITickEvent;
import com.ndsl.bun133.game.util.TickRegister;
import org.jetbrains.annotations.NotNull;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Map implements ITickEvent {
    /**
     * Map Size for Width and Height.
     * @See onMapRect
     */
    public static final int Map_Size=101;

//    private java.util.Map<ChunkPos, Chunk> ChunkMap=new HashMap<ChunkPos,Chunk>();

    private java.util.Map<onMapBlockPos, onMapBlock> BlockMap=new HashMap<>();

    public Display display;

    public KeyInput keyInput;

    public Map(Display display, KeyInput keyInput, TickRegister tickRegister){
        this.display=display;
//        generator.generateChunk(new ChunkPos(0,0));
        BlockMap=generator.genMap();
        this.keyInput=keyInput;
        tickRegister.add(this);
    }

    public List<BlockDrawable> getAll(){
        List<BlockDrawable> drawables=new ArrayList<>();
        for(onMapBlock block:BlockMap.values()){
            drawables.add(block.getDrawable());
        }
        GameMain.logger.debug("[Map]BlockListSize is "+drawables.size());
        return drawables;
    }

    public int shift_x;
    public int shift_y;

    public boolean isShowing(@NotNull onMapBlock block, Display display){
        return this.isShowing(block.pos,display);
    }

    public boolean isShowing(@NotNull onMapBlockPos pos, Display display) {
        return getShowingRect(display).isContain(pos.getRect());
    }

    public Rect getShowingRect(Display display){
        Rect rect=new Rect(-shift_x,-shift_y,-shift_x+display.getWidth(),-shift_y+display.getHeight());
        GameMain.logger.low_level_debug("[Map]ShowingRect:"+rect.toString());
        return rect;
    }

//    @Deprecated
//    public Chunk getChunk(ChunkPos pos){
//        if (!ChunkMap.containsKey(pos)) {
//            generator.generateChunk(pos);
//        }
//        return ChunkMap.get(pos);
//    }


    public generator generator=new generator();

    @Override
    public void onTick() {
        GameMain.logger.low_level_debug("[Map]onTick");
        if (keyInput.getKey(KeyEvent.VK_UP)){
            GameMain.logger.debug("[Map]KeyInput:UP");
            shift_y--;
        }
        if (keyInput.getKey(KeyEvent.VK_DOWN)){
            GameMain.logger.debug("[Map]KeyInput:DOWN");
            shift_y++;
        }
        if (keyInput.getKey(KeyEvent.VK_LEFT)){
            GameMain.logger.debug("[Map]KeyInput:LEFT");
            shift_x--;
        }
        if (keyInput.getKey(KeyEvent.VK_RIGHT)){
            GameMain.logger.debug("[Map]KeyInput:RIGHT");
            shift_x++;
        }
    }

    public class generator{
        private generator(){}

        public List<IGenerator> generatorList=new ArrayList<>();

//        @Deprecated
//        public Chunk generateChunk(ChunkPos pos){
//            Chunk chunk=new Chunk(pos,Map.this);
//            for(IGenerator generator:generatorList){
//                chunk = generator.gen(chunk);
//            }
//            return chunk;
//        }

        public java.util.Map<onMapBlockPos, onMapBlock> genMap(){
            java.util.Map<onMapBlockPos, onMapBlock> block_list = new HashMap<>();

            onMapRect rect=new onMapRect(new onMapBlockPos(1,1, Map.this),new onMapBlockPos(Map.Map_Size, Map.Map_Size, Map.this));

            for(onMapBlockPos pos:rect.getAll(Map.this)){
                block_list.put(pos,new onMapBlock(Blocks.TEST_BLOCK,pos, Map.this));
            }

            GameMain.logger.debug("[MapGenerator]GeneratedMapBlocks Size is "+block_list.size());
            return block_list;
        }
    }
}
