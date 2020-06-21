package com.ndsl.bun133.game.map;

import com.ndsl.bun133.game.map.block.onMapBlock;
import com.ndsl.bun133.game.map.gen.IGenerator;
import com.ndsl.bun133.game.map.graphics.BlockDrawable;
import com.ndsl.bun133.game.map.pos.onMapBlockPos;
import com.ndsl.bun133.game.map.pos.onMapRect;
import com.ndsl.bun133.game.register.Blocks;
import com.ndsl.bun133.game.util.ITickEvent;
import com.ndsl.bun133.game.util.TickRegister;
import com.ndsl.graphics.display.Display;
import com.ndsl.graphics.display.drawable.GUIBase;
import com.ndsl.graphics.display.drawable.RealTimeDrawable;
import com.ndsl.graphics.display.drawable.StringGui;
import com.ndsl.graphics.display.key.KeyInputHandler;
import com.ndsl.graphics.pos.Pos;
import com.ndsl.graphics.pos.Rect;
import org.jetbrains.annotations.NotNull;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.ndsl.bun133.game.GameMain.logger;

public class Map implements ITickEvent {
    /**
     * Map Size for Width and Height.
     * @See onMapRect
     */
    public static final int Map_Size=101;

//    private java.util.Map<ChunkPos, Chunk> ChunkMap=new HashMap<ChunkPos,Chunk>();

    private java.util.Map<onMapBlockPos, onMapBlock> BlockMap=new HashMap<>();

    public Display display;

    public KeyInputHandler keyInput;

    public String map_name;

    public Map(Display display, TickRegister tickRegister,String map_name){
        this.display=display;
//        generator.generateChunk(new ChunkPos(0,0));
        BlockMap=generator.genMap();
        this.keyInput=display.keyHandler;
        tickRegister.add(this);
        this.map_name=map_name;
    }

    @Deprecated
    public List<BlockDrawable> getAll(){
        List<BlockDrawable> drawables=new ArrayList<>();
        for(onMapBlock block:BlockMap.values()){
            drawables.add(block.getDrawable());
        }
        logger.debug("[Map]BlockListSize is "+drawables.size());
        return drawables;
    }

    public List<RealTimeDrawable> getAllDrawables(){
        List<RealTimeDrawable> drawables = new ArrayList<>();
        for(BlockDrawable block:getAll()){
            drawables.add(new RealTimeDrawable(block,genIDForBlock(block)));
        }
        return drawables;
    }

    private String genIDForBlock(BlockDrawable block){
        return "[BlockDrawable]"+map_name+":"+block.pos.pos_x+","+block.pos.pos_y;
    }

    public int shift_x;
    public int shift_y;

    public boolean isShowing(@NotNull onMapBlock block, Display display){
        return this.isShowing(block.pos,display);
    }

    public boolean isShowing(@NotNull onMapBlockPos pos, Display display) {
        return getShowingRect(display).contain(pos.getRect());
    }

    public Rect getShowingRect(Display display){
        Rect rect=new Rect(new Pos(-shift_x,-shift_y),new Pos(-shift_x+display.getWidth(),-shift_y+display.getHeight()));
        logger.low_level_debug("[Map]ShowingRect:"+rect.toString());
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
        logger.low_level_debug("[Map]onTick");
        if (keyInput.isKeyPressing(KeyEvent.VK_W)){
            logger.low_level_debug("[Map]KeyInput:UP");
            shift_y--;
        }
        if (keyInput.isKeyPressing(KeyEvent.VK_S)){
            logger.low_level_debug("[Map]KeyInput:DOWN");
            shift_y++;
        }
        if (keyInput.isKeyPressing(KeyEvent.VK_A)){
            logger.low_level_debug("[Map]KeyInput:LEFT");
            shift_x--;
        }
        if (keyInput.isKeyPressing(KeyEvent.VK_D)){
            logger.low_level_debug("[Map]KeyInput:RIGHT");
            shift_x++;
        }
    }

    public void setDebug(Display main_display) {
        main_display.addGui(genDebugGUI());
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

            logger.debug("[MapGenerator]GeneratedMapBlocks Size is "+block_list.size());
            return block_list;
        }
    }

    public GUIBase genDebugGUI(){
        return new GUIBase(new StringGui(this.toString()),new Pos(10,320),"Map_Debug");
    }

    @Override
    public String toString() {
        return "MapName:"+this.map_name+"\n"+"Shift_x:"+shift_x+"\n"+"Shift_y:"+shift_y;
    }
}
