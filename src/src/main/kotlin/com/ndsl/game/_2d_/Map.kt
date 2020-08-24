package com.ndsl.game._2d_

/**
 * Map Class
 * It tells only place able
 * so Entity is not tell able.
 */
class Map {
    var BlockMap: MutableMap<Location, onMapBlock> = mutableMapOf()
    fun getBlock(loc: Location): onMapBlock? {
        return BlockMap[loc]
    }

    fun setBlock(loc: Location, block: onMapBlock) {
        BlockMap[loc] = block
    }
}

abstract class Location {
    abstract fun getMap(): Map
    abstract fun getX(): Int
    abstract fun getY(): Int
}

abstract class EntityLocation {
    abstract fun getMap(): Map
    abstract fun getX(): Float
    abstract fun getY(): Float
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

    var listeners:MutableList<ServerListener> = mutableListOf()

    fun register(listener: ServerListener){
        listeners.add(listener)
    }
}

interface ServerListener {
    fun onJoin(e: PlayerJoinEvent)
    fun onLeave(e: PlayerLeaveEvent)
}