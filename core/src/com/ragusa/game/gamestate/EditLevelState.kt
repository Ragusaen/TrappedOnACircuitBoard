package com.ragusa.game.gamestate

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.ragusa.game.level.Level
import com.ragusa.game.level.LevelExporter
import com.ragusa.game.player.EditorPlayer
import com.ragusa.game.GameController.FinishLevelAction as FLA

class EditLevelState(level: Level, finishLevel: (FLA) -> Unit) : AbstractLevelState(level, finishLevel) {

    private val editorPlayer = EditorPlayer(level)

    override fun setup() {
        super.setup()
        level.tileGrid.evaluateCircuit()
        level.robot.isOn = true
    }

    override fun update() {
        super.update()
        editorPlayer.userInput()

        if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT) && Gdx.input.isKeyJustPressed(Input.Keys.S)) {
            val file = Gdx.files.external("Documents/My Games/Trapped On A Circuit Board/${level.name}.lvl")
            file.writeString(LevelExporter().export(level), false)
        }
    }

    override fun render(batch: SpriteBatch, relativeTo: Vector2) {
        super.render(batch, relativeTo)
        editorPlayer.render(batch, relativeTo)
    }
}