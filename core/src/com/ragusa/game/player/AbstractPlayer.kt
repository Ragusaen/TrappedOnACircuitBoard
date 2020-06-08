package com.ragusa.game.player

import com.badlogic.gdx.Gdx
import com.ragusa.game.IRenderable
import com.ragusa.game.controls
import com.ragusa.game.level.Level
import com.ragusa.game.player.actions.PickUp
import com.ragusa.game.player.actions.Place
import com.ragusa.game.player.actions.RotateHand
import com.ragusa.game.tiles.Direction

abstract class AbstractPlayer(val level: Level) : IRenderable {

    protected abstract fun turnMove(direction: Direction)

    open fun userInput() {
        // Movement
        if (Gdx.input.isKeyJustPressed(controls.MoveLeft))
            turnMove(Direction.WEST)
        if (Gdx.input.isKeyJustPressed(controls.MoveRight))
            turnMove(Direction.EAST)
        if (Gdx.input.isKeyJustPressed(controls.MoveUp))
            turnMove(Direction.NORTH)
        if (Gdx.input.isKeyJustPressed(controls.MoveDown))
            turnMove(Direction.SOUTH)
    }
}