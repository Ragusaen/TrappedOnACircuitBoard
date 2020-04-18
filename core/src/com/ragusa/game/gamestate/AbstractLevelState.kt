package com.ragusa.game.gamestate

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.ragusa.game.IRenderable
import com.ragusa.game.level.Level
import com.ragusa.game.utility.plus

abstract class AbstractLevelState(val level: Level) : GameState(), IRenderable {

    private var viewPosition = Vector2(0f,0f)


    override fun update() {
        updateViewPosition()
    }

    override fun setup() {
    }

    override fun render(batch: SpriteBatch, relativeTo: Vector2) {
        level.render(batch, relativeTo + viewPosition)
    }



    private fun updateViewPosition() {
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            viewPosition += Vector2(-1f, 0f)
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
            viewPosition += Vector2(1f, 0f)
        if (Gdx.input.isKeyPressed(Input.Keys.UP))
            viewPosition += Vector2(0f, -1f)
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
            viewPosition += Vector2(0f, 1f)
    }
}