package com.ragusa.game.tiles

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

class TilePort(val direction: Direction) {
    var state = PortState.OFF
    var connectedPort: TilePort? = null

    fun isOn(): Boolean = state == PortState.IN || state == PortState.OUT
}



abstract class Tile: IRenderable {
    abstract val ports: Array<TilePort>

    // Update the internal state of the tile.
    // This only concerns the state of the ports on this tile, not the connected ones
    abstract fun updatePorts()

    protected abstract val stateTextures: Map<Int, Texture>


    protected val sprite: Sprite = initSprite()

    var position: Vector2 = Vector2.Zero
        set(value) {
            sprite.setPosition(value.x, value.y)
            field = value
        }

    var direction = Direction.NORTH
        set(value) {
            sprite.rotation = -90f * value.ordinal
            field = value
        }


    companion object {
        val tileSize = 64f
    }

    fun debugState(batch: SpriteBatch) {
        val font = BitmapFont()

        for (port in ports) {
            val relpos = when(port.direction + direction) {
                Direction.NORTH -> Vector2(0f,1f)
                Direction.EAST -> Vector2(1f,0f)
                Direction.SOUTH -> Vector2(0f,-1f)
                Direction.WEST -> Vector2(-1f,0f)
            }
            val pos = Vector2(position).add(relpos.scl(tileSize / 3).add(Vector2(tileSize / 2, tileSize / 2)))
            font.draw(batch, port.state.name, pos.x, pos.y)
        }
    }

    override fun render(batch: SpriteBatch) {
        sprite.texture = stateTextures[getState()]
        sprite.draw(batch)
    }

    private fun getState(): Int {
        var state: Int = 0
        for (port in ports) {
            if (port.isOn())
                state += 1
            state = state shl 1
        }
        state = state shr 1

        // Debug
        if (!stateTextures.containsKey(state))
            throw InvalidKeyException("The tile has not defined a sprite for state $state")

        return state
    }

    private fun initSprite(): Sprite {
        val sprite = Sprite(Assets.manager.get(Assets.tiles.plain), 16, 16)
        sprite.setSize(tileSize, tileSize)
        sprite.setOrigin(tileSize / 2, tileSize / 2)
        return sprite
    }

}

fun Tile.withRotation(rotation: Direction): Tile {
    this.direction = rotation
    return this
}