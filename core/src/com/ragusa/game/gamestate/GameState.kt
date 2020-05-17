package com.ragusa.game.gamestate

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.ragusa.game.IRenderable

abstract class GameState : IRenderable{

    //  This method is called once
    abstract fun setup()

    // This method should be called each tick
    abstract fun update()
}