package com.ragusa.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.ScreenViewport
import com.ragusa.game.gamestate.EditLevelState
import com.ragusa.game.gamestate.GameState
import com.ragusa.game.gamestate.MenuState
import com.ragusa.game.gamestate.PlayLevelState
import com.ragusa.game.level.Level
import com.ragusa.game.player.Robot
import com.ragusa.game.tiles.TileGrid

class GameController: IRenderable {

    var gameState: GameState = MenuState(::levelChosen)

    var lastMenu: MenuState? = null

    enum class FinishLevelAction { Menu, NextLevel, Edit }

    fun finishLevel(fla: FinishLevelAction) {
        if (fla == FinishLevelAction.Menu) {
            lastMenu!!.currentMenu.currentIndex++
            gameState = lastMenu!!
        } else if (fla == FinishLevelAction.Edit) {
            switchPlayEdit()
        }
    }

    private fun levelChosen(level: Level?, asEdit: Boolean) {
        lastMenu = gameState as MenuState
        if (asEdit) {
            if (level == null) {
                val robot = Robot()
                gameState = EditLevelState(Level("No name", "Unknown", TileGrid(robot), robot), ::finishLevel)
            } else
                gameState = EditLevelState(level, ::finishLevel)
        } else
            gameState = PlayLevelState(level!!, ::finishLevel)

        gameState.setup()
    }


    fun update() {
        gameState.update()

        if (Gdx.input.isKeyJustPressed(controls.SwitchPlayEdit)) {
            switchPlayEdit()
        }

    }

    private fun switchPlayEdit() {
        if (gameState is EditLevelState)
            gameState = PlayLevelState((gameState as EditLevelState).level, ::finishLevel, true)
        else if (gameState is PlayLevelState && (gameState as PlayLevelState).editing)
            gameState = EditLevelState((gameState as PlayLevelState).originalLevel!!, ::finishLevel)
    }

    override fun render(batch: SpriteBatch, relativeTo: Vector2) {
        gameState.render(batch, Vector2.Zero)
    }
}