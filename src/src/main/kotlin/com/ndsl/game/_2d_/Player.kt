package com.ndsl.game._2d_

import com.ndsl.game.util.DoubleDataSet
import com.ndsl.game.util.getUUID
import com.ndsl.graphics.display.Display
import com.ndsl.graphics.display.drawable.IDrawable
import com.ndsl.graphics.pos.Rect
import java.awt.Graphics
import kotlin.math.roundToInt

/**
 * Player Class
 */
class Player(var server: Server) : Entity {
    var mainCamera: Camera = Camera(this, server.map)
    lateinit var loc: EntityLocation

    /**
     * Server Will Call
     */
    fun onSpawn(spawnPoint: EntityLocation) {loc = spawnPoint}

    override fun getPos(): EntityLocation = loc
}

/**
 * Camera Class
 * Always Drawer
 */
class Camera(var entity: Entity, var map: Map) : IDrawable {
    var zoom: Double = 1.0

    override fun onDraw(p0: Graphics?, p1: Rect?) {
        val rect: Rect = getRect()
        map.getBlocksStream().map {
            DoubleDataSet<Rect, onMapBlock>(Rect(it.key.x, it.key.y, it.key.x + defaultBlockSize, it.key.y + defaultBlockSize), it.value)
        }.filter {
            rect.contain(it.a)
        }.forEach {
            it.b.onDraw(p0, Rect(it.b.loc.x - rect.left_up.x,
                    it.b.loc.y - rect.left_up.y,
                    (it.b.loc.x - rect.left_up.x + (defaultBlockSize * zoom)).roundToInt(),
                    (it.b.loc.y - rect.left_up.y + (defaultBlockSize * zoom)).roundToInt()))
        }
    }

    fun getRect(): Rect {
        val r:Rect=Rect(entity.getPos().x.toInt(),
            entity.getPos().y.toInt(),
            entity.getPos().x.toInt() + 1920,
            entity.getPos().y.toInt() + 1080).shift(-1920 / 2, -1080 / 2)
        r.zoom(1/zoom)
        return r
    }

    override fun getShowingRect(): Rect = Rect(0, 0, 1920, 1080)
    override fun isShowing(p0: Display?): Boolean = true
    private val id = "Camera-" + getUUID()
    override fun getID(): String = id
}

val defaultBlockSize: Int = 64