package com.ragusa.game.tiles

import com.badlogic.gdx.assets.AssetDescriptor
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.ragusa.game.Assets
import com.ragusa.game.Direction
import com.ragusa.game.IRenderable
import com.ragusa.game.initSprite
import com.ragusa.game.utility.plus
import com.ragusa.game.utility.setPosition
import java.security.InvalidKeyException


// Describes the state of the port
enum class PortState {
    OFF, // No power at port
    IN, // Port is receiving power from connected port
    OUT // Port is transmitting power to connected port
}

data class TilePort(val direction: Direction) {
    var state = PortState.OFF
    var connectedPort: TilePort? = null

    fun isOn(): Boolean = state == PortState.IN || state == PortState.OUT
}



open class Tile(val isLocked: Boolean, val isInsulated: Boolean): TileAble() {

    private val baseSprite: Sprite = initSprite(Sprite(Assets.manager.get(Assets.tiles.plain), 16, 16))
    private val lockedSprite = if (isLocked) initSprite(Sprite(Assets.manager.get(Assets.tiles.screws))) else null
    private val insulatedSprite=  if (isInsulated) initSprite(Sprite(Assets.manager.get(Assets.tiles.insulated))) else null

    open fun updateSprites() {
        baseSprite.rotation = -90f * direction.ordinal
    }

    override var direction: Direction = Direction.NORTH
        set(value) {
            field = value
            updateSprites()
        }

    final override fun render(batch: SpriteBatch, relativeTo: Vector2) {
        renderInternal(batch, relativeTo)
        if (isLocked) {
            lockedSprite!!.setPosition(relativeTo)
            lockedSprite.draw(batch)
        }

        if (isInsulated) {
            insulatedSprite!!.setPosition(relativeTo)
            insulatedSprite.draw(batch)
        }
    }

    open fun renderInternal(batch: SpriteBatch, relativeTo: Vector2) {
        baseSprite.setPosition(relativeTo)
        baseSprite.draw(batch)
    }


}

fun Tile.withRotation(rotation: Direction): Tile {
    this.direction = rotation
    return this
}