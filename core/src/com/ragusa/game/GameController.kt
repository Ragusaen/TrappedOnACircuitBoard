package com.ragusa.game

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.ragusa.game.gamestate.EditLevelState
import com.ragusa.game.gamestate.GameState
import com.ragusa.game.gamestate.MenuState
import com.ragusa.game.gamestate.PlayLevelState
import com.ragusa.game.level.Level
import com.ragusa.game.player.Robot
import com.ragusa.game.tiles.TileGrid

class GameController: IRenderable {

    var gameState: GameState = MenuState(::levelChosen)

    private fun levelChosen(level: Level?, asEdit: Boolean) {
        if (asEdit) {
            if (level == null) {
                val robot = Robot()
                gameState = EditLevelState(Level("No name", "Unknown", TileGrid(robot), robot))
            } else
                gameState = EditLevelState(level)
        } else
            gameState = PlayLevelState(level!!)
    }


    fun update() {
        gameState.update()
    }

    override fun render(batch: SpriteBatch, relativeTo: Vector2) {
        gameState.render(batch, Vector2.Zero)
    }
}