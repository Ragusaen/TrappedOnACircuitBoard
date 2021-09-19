package com.ragusa.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.ScreenViewport
import com.ragusa.game.UI.mainMenu
import com.ragusa.game.gamestate.*
import com.ragusa.game.level.Level
import com.ragusa.game.player.Robot
import com.ragusa.game.tiles.TileGrid

class GameController: IRenderable {
    var menu: MenuState = MenuState(mainMenu, ::switchGameState)

    var gameState: GameState = menu

    enum class FinishLevelAction { Menu, NextLevel, Edit }

    fun switchGameState(gs: GameState) {
        gameState = gs
    }

    fun update() {
        gameState.update()

        if (gameState is AbstractLevelState && Gdx.input.isKeyJustPressed(controls.SwitchPlayEdit)) {
            switchPlayEdit()
        }

        if (Gdx.input.isKeyJustPressed(controls.MenuBack)) {
            when (gameState) {
                is AbstractLevelState -> gameState = menu
                is MenuState -> menu.goBack()
            }
        }

    }

    private fun switchPlayEdit() {
        if (gameState is EditLevelState)
            gameState = PlayLevelState((gameState as EditLevelState).level, true)
        else if (gameState is PlayLevelState && (gameState as PlayLevelState).editing)
            gameState = EditLevelState((gameState as PlayLevelState).originalLevel!!)
    }

    override fun render(batch: SpriteBatch, relativeTo: Vector2) {
        gameState.render(batch, Vector2.Zero)
    }
}