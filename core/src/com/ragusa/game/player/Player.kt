package com.ragusa.game.player

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.ragusa.game.tiles.Direction
import com.ragusa.game.IRenderable
import com.ragusa.game.level.Level
import com.ragusa.game.player.actions.*

class Player(val level: Level) : IRenderable {
    val actions = ActionStack(level.tileGrid)

    private fun turnMove(dir: Direction) {
        if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT))
            actions.doAction(Turn(dir, level.robot))
        else
            if (!actions.doAction(Move(dir, level.robot)))
                actions.doAction(Turn(dir, level.robot))
    }

    fun userInput() {
        // Movement
        if (Gdx.input.isKeyJustPressed(Input.Keys.A))
            turnMove(Direction.WEST)
        if (Gdx.input.isKeyJustPressed(Input.Keys.D))
            turnMove(Direction.EAST)
        if (Gdx.input.isKeyJustPressed(Input.Keys.W))
            turnMove(Direction.NORTH)
        if (Gdx.input.isKeyJustPressed(Input.Keys.S))
            turnMove(Direction.SOUTH)


        // Pick up / place
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            if (level.robot.hand == null)
                actions.doAction(PickUp(level.robot))
            else
                actions.doAction(Place(level.robot))
        }

        // Rotate hand
        if (Gdx.input.isKeyJustPressed(Input.Keys.R)) {
            actions.doAction(RotateHand(level.robot))
        }

        // Undo
        if (Gdx.input.isKeyJustPressed(Input.Keys.Z))
            actions.undoAction()
    }

    override fun render(batch: SpriteBatch, relativeTo: Vector2) {
        level.robot.hand?.render(batch, Vector2(10f,10f))
    }


}