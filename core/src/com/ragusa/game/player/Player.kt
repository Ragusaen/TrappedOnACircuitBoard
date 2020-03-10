package com.ragusa.game.player

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.math.Vector2
import com.ragusa.game.TileGrid
import com.ragusa.game.player.actions.Move
import com.ragusa.game.player.actions.UndoableAction

class Player(val robot: Robot, val tileGrid: TileGrid) {

    val actions = ActionStack(tileGrid)

    fun userInput() {
        // Movement
        if (Gdx.input.isKeyJustPressed(Input.Keys.A))
            actions.doAction(Move(Move.LEFT, robot))
        if (Gdx.input.isKeyJustPressed(Input.Keys.D))
            actions.doAction(Move(Move.RIGHT, robot))
        if (Gdx.input.isKeyJustPressed(Input.Keys.W))
            actions.doAction(Move(Move.UP, robot))
        if (Gdx.input.isKeyJustPressed(Input.Keys.S))
            actions.doAction(Move(Move.DOWN, robot))

        // Undo
        if (Gdx.input.isKeyJustPressed(Input.Keys.Z))
            actions.undoAction()
    }
}