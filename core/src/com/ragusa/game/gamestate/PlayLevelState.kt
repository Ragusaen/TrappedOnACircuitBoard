package com.ragusa.game.gamestate

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.ragusa.game.level.Level
import com.ragusa.game.level.LevelExporter
import com.ragusa.game.level.RDLevelParser
import com.ragusa.game.player.Player
import com.ragusa.game.tiles.finals.TileGoal
import com.ragusa.game.utility.Timer
import com.ragusa.game.utility.minus
import com.ragusa.game.utility.plus
import com.ragusa.game.utility.times
import com.ragusa.game.GameController.FinishLevelAction as FLA

class PlayLevelState(level: Level, val editing: Boolean = false) : AbstractLevelState(level) {

    private val player = Player(level)

    val originalLevel: Level? = if (editing) RDLevelParser().parse(LevelExporter().export(level)) else null

    private val evaluationTimer = Timer(100) // Every 100 ms

    override fun render(batch: SpriteBatch, relativeTo: Vector2) {
        super.render(batch, relativeTo)
        player.render(batch, relativeTo)
    }

    override fun setup() {
        super.setup()

        level.tileGrid.evaluateCircuit()
        viewPosition = level.robot.position + Vector2(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat()) * 0.5f
    }

    override fun update() {
        super.update()

        if (evaluationTimer.isOverDue()) {
            level.tileGrid.evaluateCircuit()
            evaluationTimer.restart()
        }

        player.userInput()

        if (level.tileGrid[level.robot.position] is TileGoal) {
            //TODO: Level finished
        }
    }
}