package com.ragusa.game.gamestate

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.ragusa.game.level.Level
import com.ragusa.game.player.Player
import com.ragusa.game.utility.Timer

class PlayLevelState(level: Level) : AbstractLevelState(level) {

    private val player = Player(level)

    private val evaluationTimer = Timer(100) // Every 100 ms

    override fun render(batch: SpriteBatch, relativeTo: Vector2) {
        super.render(batch, relativeTo)
        player.render(batch, relativeTo)
    }

    override fun setup() {
        super.setup()

        level.tileGrid.evaluateCircuit()
    }

    override fun update() {
        super.update()

        if (evaluationTimer.isOverDue()) {
            level.tileGrid.evaluateCircuit()
            evaluationTimer.restart()
        }

        player.userInput()
    }
}