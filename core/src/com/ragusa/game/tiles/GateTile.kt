package com.ragusa.game.tiles

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.ragusa.game.Assets
import com.ragusa.game.utility.setPosition

// Gate tiles are tiles that transform the signal. These are always delayed (atleast) one tick.
abstract class GateTile : WiredTile() {
    companion object {
        const val DEFAULT_TEXTURE: Int = -1
    }

    protected abstract val gateStateTextures: Map<Int,Texture>
    private val gateSprite = initSprite(Sprite(Assets.manager.get(Assets.textures.tiles.plain), 16, 16))

    override fun updateSprites() {
        super.updateSprites()
        gateSprite.rotation = -90f * direction.ordinal
    }

    override fun renderInternal(batch: SpriteBatch, relativeTo: Vector2) {
        super.renderInternal(batch, relativeTo)

        val state = getState()
        if (gateStateTextures.containsKey(state))
            gateSprite.texture = gateStateTextures[state]
        else // Use the default if the given state does not matter for the gate
            gateSprite.texture = gateStateTextures[DEFAULT_TEXTURE]
        gateSprite.setPosition(relativeTo)
        gateSprite.draw(batch)
    }
}

abstract class BinaryGateTile : GateTile() {
    final override val ports: Array<TilePort> = arrayOf(TilePort(Direction.NORTH), TilePort(Direction.SOUTH))

    final override val wireStateTextures = initTextureStates(mapOf(
            0b00 to Assets.textures.tiles.gate.binary.state_00,
            0b01 to Assets.textures.tiles.gate.binary.state_01,
            0b10 to Assets.textures.tiles.gate.binary.state_10,
            0b11 to Assets.textures.tiles.gate.binary.state_11
    ))

}

abstract class TernaryGateTile: GateTile() {
    final override val ports: Array<TilePort> = arrayOf(TilePort(Direction.NORTH), TilePort(Direction.EAST), TilePort(Direction.WEST))

    final override val wireStateTextures = initTextureStates(mapOf(
            0b000 to Assets.textures.tiles.gate.ternary.state_000,
            0b001 to Assets.textures.tiles.gate.ternary.state_001,
            0b010 to Assets.textures.tiles.gate.ternary.state_010,
            0b011 to Assets.textures.tiles.gate.ternary.state_011,
            0b100 to Assets.textures.tiles.gate.ternary.state_100,
            0b101 to Assets.textures.tiles.gate.ternary.state_101,
            0b110 to Assets.textures.tiles.gate.ternary.state_110,
            0b111 to Assets.textures.tiles.gate.ternary.state_111
    ))
}