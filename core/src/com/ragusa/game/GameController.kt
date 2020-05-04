package com.ragusa.game

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.ragusa.game.gamestate.EditLevelState
import com.ragusa.game.gamestate.GameState
import com.ragusa.game.gamestate.MenuState
import com.ragusa.game.gamestate.PlayLevelState
import com.ragusa.game.level.Level

class GameController {

    var gameState: GameState = MenuState(::playLevel)

    private fun playLevel(level: Level) {
        gameState = PlayLevelState(level)
    }


    fun update() {

    }

    fun render(batch: SpriteBatch) {

    }
}