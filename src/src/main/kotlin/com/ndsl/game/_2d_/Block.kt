package com.ndsl.game._2d_

import com.ndsl.graphics.pos.Rect
import java.awt.Graphics
import java.awt.Image

abstract class Block{
    abstract fun hasCollision(): Boolean
    abstract fun onDraw(g:Graphics, rect:Rect)
    abstract fun getMaxStrength():Long
}

abstract class BlockBase(val StrengthMax:Long):Block(){
    override fun getMaxStrength(): Long = StrengthMax
}

class ImageBlock(var img: Image,var isCollide:Boolean=false,strength:Long):BlockBase(strength) {
    override fun hasCollision(): Boolean = isCollide
    override fun onDraw(g: Graphics, rect: Rect) {
        g.drawImage(img,rect.left_up.x,rect.left_up.y,rect.width,rect.height,null)
    }
}

class onMapBlock(var block:Block,var loc:Location) : placeable{
    fun onDraw(p0: Graphics?, p1: Rect?) {
        if(p0!=null && p1!=null){
            block.onDraw(p0,p1)
        }
    }

    override fun getLocation(): Location = loc
    override fun hasCollidion(): Boolean = block.hasCollision()
    override fun getMaxStrength(): Long = block.getMaxStrength()

    var strength: Long = getMaxStrength()
    override fun getNowStrength(): Long = strength
    override fun onDamamage(damage: Long){strength-=damage}

}