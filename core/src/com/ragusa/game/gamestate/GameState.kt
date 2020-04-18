package com.ragusa.game.gamestate

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2

abstract class GameState {

    //  This method is called once
    abstract fun setup()

    // This method should be called each tick
    abstract fun update()
}