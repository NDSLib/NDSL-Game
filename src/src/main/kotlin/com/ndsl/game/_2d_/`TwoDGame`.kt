package com.ndsl.game._2d_

import com.ndsl.game.util.Logger
import com.ndsl.graphics.display.Display
import com.ndsl.graphics.display.drawable.base.Drawable
import com.ndsl.graphics.pos.Rect

class TwoDGame {
    lateinit var map:Map
    lateinit var server:MapServer
    lateinit var gameServer: Server
    lateinit var player:Player
    lateinit var display:Display

    private fun init(){
        display= Display("NDSL/Game",2, Rect(0,0,1920,1080))
        map=Map()
        server= MapServer()
        gameServer= Server(map,server)
        player = Player(gameServer)
        gameServer.join(player)
        player.onSpawn(EntityLocation(map,0.0F,0.0F))
    }

    fun start(){
        logger.showDebug=true
        logger.info("Starting...")
        init()
        display.addDrawable(Drawable(player.mainCamera))
        logger.info("init end...")
        logger.debug("Debug Start")
        debugGen(map,0,0,100,100)
        logger.debug("Debug End")
        logger.info("Map Size is:${map.getBlocksStream().count()}")
        while (true){
            if(display.limiter.onUpdate()) display.update()
        }
    }

    private fun debugGen(map:Map,x1: Int, y1: Int, x2: Int, y2: Int) {
        for (x in x1..x2){
            for (y in y1..y2){
                map.getBlock(Location(map,x,y))
            }
        }
    }
}

val logger:Logger = Logger(System.out)