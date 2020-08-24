package com.ndsl.game._2d_
import com.ndsl.game._2d_.test.TestBlock

/**
 * Map Class
 * It tells only place able
 * so Entity is not tell able.
 */
class Map {
    private var BlockMap: MutableMap<Location, onMapBlock> = mutableMapOf()
    fun getBlock(loc: Location): onMapBlock {
        return if(BlockMap[loc]!=null){
            BlockMap[loc]!!
        }else{
            genBlock(loc)
        }
    }

    private fun genBlock(loc: Location): onMapBlock {
        BlockMap[loc] = onMapBlock(TestBlock,loc)
        return BlockMap[loc]!!
    }

    fun setBlock(loc: Location, block: onMapBlock) {
        BlockMap[loc] = block
    }

    fun getBlocksStream()= BlockMap.entries.stream()
}

class Location(var map:Map,var x:Int,var y:Int) {
}

class EntityLocation(var map:Map,var x:Float,var y:Float) {
}

@Suppress("ClassName", "SpellCheckingInspection")
interface placeable {
    /**
     * Location where it is
     */
    fun getLocation(): Location

    /**
     * if return true,player can t walk cross.
     */
    fun hasCollidion(): Boolean

    /**
     * @return MaxStrength of this
     */
    fun getMaxStrength(): Long

    /**
     * @return Now Strength of this
     */
    fun getNowStrength(): Long

    /**
     * Damaged by anything.
     */
    fun onDamamage(damage:Long)
}

/**
 * MapServer Class
 * (even if local game,it's necessary)
 * It tells Entity data
 */
class MapServer {
    //TODO(Not implemented)
}

/**
 * Game Server
 * (maybe even if local game,need able)
 * It tells Map,MapServer.
 */
class Server(var map: Map, var server: MapServer) {
    var playerList: MutableList<Player> = mutableListOf()

    fun join(player:Player):Map{
        playerList.add(player)
        listeners.stream().filter { it!=null }.forEach { it.onJoin(PlayerJoinEvent(this,player)) }
        return map
    }

    fun leave(player:Player){
        playerList.remove(player)
        listeners.stream().filter { it!=null }.forEach { it.onLeave(PlayerLeaveEvent(this,player)) }
    }

    private var listeners:MutableList<ServerListener> = mutableListOf()

    fun register(listener: ServerListener){
        listeners.add(listener)
    }
}

interface ServerListener {
    fun onJoin(e: PlayerJoinEvent)
    fun onLeave(e: PlayerLeaveEvent)
}

class testListener : ServerListener{
    override fun onJoin(e: PlayerJoinEvent) {
        println("Player:"+e.player+" joined!")
    }

    override fun onLeave(e: PlayerLeaveEvent) {
        println("Player:"+e.player+" left!")
    }
}