package com.ragusa.game.player

import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.ragusa.game.Assets
import com.ragusa.game.IRenderable
import com.ragusa.game.initSprite
import com.ragusa.game.initTextureStates

class Player : IRenderable {
    var isOn = true
        set (value) {
            sprite.texture = textures[if (value) 1 else 0]
            field = value
        }

    val textures = initTextureStates(mapOf(
        0 to Assets.player.off,
        1 to Assets.player.on
    ))
    val sprite: Sprite = initSprite(Sprite(textures[0], 16, 16))

    override fun render(batch: SpriteBatch) {
        sprite.draw(batch)
    }


}