package com.ragusa.game.player

import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.ragusa.game.Assets
import com.ragusa.game.tiles.Direction
import com.ragusa.game.tiles.initSprite
import com.ragusa.game.tiles.initTextureStates
import com.ragusa.game.tiles.Tile
import com.ragusa.game.tiles.TileAble
import com.ragusa.game.utility.*

class Robot: TileAble() {
    var isOn = true
        set(value) {
            sprite.texture = textures[if (value) 1 else 0]
            field = value
    }

    override var direction = Direction.NORTH
        set(value) {
            sprite.rotation = value.ordinal * -90f
            field = value
        }

    var position = Vector2(0f, 0f)

    var hand: Tile? = null



    val textures = initTextureStates(mapOf(
            0 to Assets.player.off,
            1 to Assets.player.on
    ))
    val sprite: Sprite = initSprite(Sprite(textures[1], 16, 16))

    override fun render(batch: SpriteBatch, relativeTo: Vector2) {
        sprite.setPosition(relativeTo + position * tileSize)
        sprite.draw(batch)
    }
}