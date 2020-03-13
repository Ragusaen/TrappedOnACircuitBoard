package com.ragusa.game.player

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.ragusa.game.Direction
import com.ragusa.game.IRenderable
import com.ragusa.game.TileGrid
import com.ragusa.game.player.actions.*

class Player(val robot: Robot, val tileGrid: TileGrid) : IRenderable {
    val actions = ActionStack(tileGrid)

    private fun turnMove(dir: Direction) {
        if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT))
            actions.doAction(Turn(dir, robot))
        else
            if (!actions.doAction(Move(dir, robot)))
                actions.doAction(Turn(dir, robot))
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
            if (robot.hand == null)
                actions.doAction(PickUp(robot))
            else
                actions.doAction(Place(robot))
        }

        // Rotate hand
        if (Gdx.input.isKeyJustPressed(Input.Keys.R)) {
            actions.doAction(RotateHand(robot))
        }

        // Undo
        if (Gdx.input.isKeyJustPressed(Input.Keys.Z))
            actions.undoAction()
    }

    override fun render(batch: SpriteBatch, relativeTo: Vector2) {
        robot.hand?.render(batch, Vector2(10f,10f))
    }


}