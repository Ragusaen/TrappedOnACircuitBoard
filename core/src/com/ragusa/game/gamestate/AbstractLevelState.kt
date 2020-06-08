package com.ragusa.game.gamestate

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.ragusa.game.Assets
import com.ragusa.game.GameController.FinishLevelAction as FLA
import com.ragusa.game.IRenderable
import com.ragusa.game.controls
import com.ragusa.game.level.Level
import com.ragusa.game.tiles.TileAble
import com.ragusa.game.utility.minus
import com.ragusa.game.utility.plus
import com.ragusa.game.utility.times

abstract class AbstractLevelState(val level: Level,
                                  val finishLevel: (FLA) -> Unit) : GameState() {

    protected val font = Assets.manager.get(Assets.fonts.volleyball_fnt)

    protected var viewPosition = Vector2(0f,0f)

    companion object {
        val lookSpeed = 8f
    }


    override fun update() {
        updateViewPosition()
    }

    override fun setup() {
        viewPosition = (level.robot.position - Vector2(.5f, .5f)) * TileAble.tileSize + Vector2(Gdx.graphics.width / 2f, Gdx.graphics.height / 2f)
    }

    override fun render(batch: SpriteBatch, relativeTo: Vector2) {
        font.draw(batch, level.name, 40f, Gdx.graphics.height - 40f)
        font.data.setScale(0.5f)
        font.draw(batch, level.author, 40f, Gdx.graphics.height - 80f)
        font.data.setScale(1f)
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