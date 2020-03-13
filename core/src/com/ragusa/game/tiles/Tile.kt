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



abstract class Tile: TileAble() {

    // Update the internal state of the tile.
    // This only concerns the state of the ports on this tile, not the connected ones
    abstract fun updateInternalState()

    private val baseSprite: Sprite = initSprite(Sprite(Assets.manager.get(Assets.tiles.plain), 16, 16))

    open fun updateSprites() {
        baseSprite.rotation = -90f * direction.ordinal
    }

    override var direction: Direction = Direction.NORTH
        set(value) {
            field = value
            updateSprites()
        }

    override fun render(batch: SpriteBatch, relativeTo: Vector2) {
        baseSprite.setPosition(relativeTo)
        baseSprite.draw(batch)
    }

}

fun Tile.withRotation(rotation: Direction): Tile {
    this.direction = rotation
    return this
}