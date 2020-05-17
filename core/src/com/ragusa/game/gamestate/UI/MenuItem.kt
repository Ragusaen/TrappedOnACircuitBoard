package com.ragusa.game.gamestate.UI

import com.badlogic.gdx.graphics.g2d.GlyphLayout
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.ragusa.game.Assets
import com.ragusa.game.IRenderable
import com.ragusa.game.tiles.initSprite

class SubMenuItem(text: String, position: Vector2, val parent: Menu) : MenuItem(text, position)

open class MenuItem(private val text: String, private val position: Vector2) : IRenderable {

    companion object {
        private val bf = Assets.manager.get(Assets.fonts.volleyball_fnt)
    }

    private val itemTextures = mapOf(
            false to Assets.manager.get(Assets.textures.UI.menu_item_off),
            true to Assets.manager.get(Assets.textures.UI.menu_item_on)
    )

    private val sprite = initSprite(Sprite(itemTextures[false]), Vector2(512f, 64f))

    private val textPos = initTextPos()

    var isChosen = false
        set(value) {
            sprite.texture = itemTextures[value]
            field = value
        }

    override fun render(batch: SpriteBatch, relativeTo: Vector2) {
        sprite.setPosition(relativeTo.x + position.x, relativeTo.y + position.y)
        sprite.draw(batch)
        bf.draw(batch, text, relativeTo.x + textPos.x, relativeTo.y + textPos.y)
    }

    private fun initTextPos(): Vector2 {
        val gl = GlyphLayout()
        gl.setText(bf, text)
        return Vector2( position.x + (sprite.width - gl.width) / 2, position.y + (sprite.height + gl.height) / 2)
    }
}
