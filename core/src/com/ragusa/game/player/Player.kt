package com.ragusa.game.player

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.ragusa.game.tiles.Direction
import com.ragusa.game.IRenderable
import com.ragusa.game.controls
import com.ragusa.game.level.Level
import com.ragusa.game.player.actions.*

class Player(level: Level) : AbstractPlayer(level) {
    private val actions = ActionStack(level.tileGrid)

    override fun turnMove(dir: Direction) {
        if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT))
            actions.doAction(Turn(dir, level.robot))
        else
            if (!actions.doAction(Move(dir, level.robot)))
                actions.doAction(Turn(dir, level.robot))
    }

    override fun userInput() {
        super.userInput()

        // Pick up / place
        if (Gdx.input.isKeyJustPressed(controls.PickUpPlace)) {
            if (level.robot.hand == null)
                actions.doAction(PickUp(level.robot))
            else
                actions.doAction(Place(level.robot))
        }

        // Rotate hand
        if (Gdx.input.isKeyJustPressed(controls.Rotate)) {
            actions.doAction(RotateHand(level.robot))
        }

        // Undo
        if (Gdx.input.isKeyJustPressed(controls.Undo))
            actions.undoAction()
    }

    override fun render(batch: SpriteBatch, relativeTo: Vector2) {
        level.robot.hand?.render(batch, Vector2(10f,10f))
    }


}