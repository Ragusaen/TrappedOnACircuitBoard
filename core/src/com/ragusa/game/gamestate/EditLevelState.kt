package com.ragusa.game.gamestate

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.ragusa.game.level.Level
import com.ragusa.game.player.EditorPlayer

class EditLevelState(level: Level) : AbstractLevelState(level) {

    private val editorPlayer = EditorPlayer(level)

    override fun setup() {
        super.setup()
        level.tileGrid.evaluateCircuit()
        level.robot.isOn = true
    }

    override fun update() {
        super.update()
        editorPlayer.userInput()
    }

    override fun render(batch: SpriteBatch, relativeTo: Vector2) {
        super.render(batch, relativeTo)
        editorPlayer.render(batch, relativeTo)
    }
}