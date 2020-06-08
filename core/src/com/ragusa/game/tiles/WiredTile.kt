package com.ragusa.game.tiles

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.ragusa.game.Assets
import com.ragusa.game.utility.setPosition
import java.security.InvalidKeyException

abstract class WiredTile : Tile() {
    // Update the internal state of the tile.
    // This only concerns the state of the ports on this tile, not the connected ones
    abstract fun updateInternalState()

    abstract val ports: Array<TilePort>

    protected abstract val wireStateTextures: Map<Int, Texture>

    private val wireSprite = initSprite(Sprite(Assets.manager.get(Assets.textures.tiles.plain), 16, 16));

    protected open fun getState(): Int {
        var state: Int = 0
        for (port in ports) {
            if (port.isOn())
                state += 1
            state = state shl 1
        }
        state = state shr 1

        // Debug
        if (!wireStateTextures.containsKey(state))
            throw InvalidKeyException("The tile has not defined a sprite for state $state")

        return state
    }

    override fun updateSprites() {
        super.updateSprites()
        wireSprite.rotation = -90f * direction.ordinal
    }

    override fun renderInternal(batch: SpriteBatch, relativeTo: Vector2) {
        super.renderInternal(batch, relativeTo)
        wireSprite.setPosition(relativeTo)
        wireSprite.texture = wireStateTextures[getState()];
        wireSprite.draw(batch)
    }

}