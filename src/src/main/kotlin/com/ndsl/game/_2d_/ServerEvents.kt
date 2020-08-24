package com.ndsl.game._2d_

open class ServerEventBase(var server:Server, var mill:Long){
    companion object{
        fun gen(server: Server):ServerEventBase{
            return ServerEventBase(server,System.currentTimeMillis())
        }
    }
}

class PlayerJoinEvent(server: Server,var player:Player) : ServerEventBase(server,System.currentTimeMillis()){

}

class PlayerLeaveEvent(server: Server,var player:Player) : ServerEventBase(server,System.currentTimeMillis()){

}