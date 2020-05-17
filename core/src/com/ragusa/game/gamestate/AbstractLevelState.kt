package com.ragusa.game.gamestate

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.ragusa.game.IRenderable
import com.ragusa.game.controls
import com.ragusa.game.level.Level
import com.ragusa.game.utility.plus
import com.ragusa.game.utility.times

abstract class AbstractLevelState(val level: Level) : GameState() {

    private var viewPosition = Vector2(0f,0f)

    companion object {
        val lookSpeed = 8f
    }


    override fun update() {
        updateViewPosition()
    }

    override fun setup() {
    }

    override fun render(batch: SpriteBatch, relativeTo: Vector2) {
        level.render(batch, relativeTo + viewPosition)
    }



    private fun updateViewPosition() {
        if (Gdx.input.isKeyPressed(controls.LookRight))
            viewPosition += Vector2(-1f, 0f) * lookSpeed
        if (Gdx.input.isKeyPressed(controls.LookLeft))
            viewPosition += Vector2(1f, 0f) * lookSpeed
        if (Gdx.input.isKeyPressed(controls.LookUp))
            viewPosition += Vector2(0f, -1f) * lookSpeed
        if (Gdx.input.isKeyPressed(controls.LookDown))
            viewPosition += Vector2(0f, 1f) * lookSpeed
    }
}