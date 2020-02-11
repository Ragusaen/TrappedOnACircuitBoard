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



abstract class Tile: IRenderable {

    // Update the internal state of the tile.
    // This only concerns the state of the ports on this tile, not the connected ones
    abstract fun updateInternalState()

    private val baseSprite: Sprite = initSprite(Sprite(Assets.manager.get(Assets.tiles.plain), 16, 16))

    open fun updateSprites() {
        baseSprite.rotation = -90f * direction.ordinal
        baseSprite.setPosition(position.x, position.y)
    }

    var position: Vector2 = Vector2.Zero
        set(value) {
            field = value
            updateSprites()
        }

    var direction: Direction = Direction.NORTH
        set(value) {
            field = value
            updateSprites()
        }


    companion object {
        const val tileSize = 64f
    }

    override fun render(batch: SpriteBatch) {
        baseSprite.draw(batch)
    }

    protected fun initSprite(sprite: Sprite): Sprite {
        sprite.setSize(tileSize, tileSize)
        sprite.setOrigin(tileSize / 2, tileSize / 2)
        return sprite
    }

    protected fun initTextureStates(map: Map<Int, AssetDescriptor<Texture>>): Map<Int, Texture> =
            map.entries.map { Pair(it.key, Assets.manager.get(it.value)) }.toMap()

}

fun Tile.withRotation(rotation: Direction): Tile {
    this.direction = rotation
    return this
}