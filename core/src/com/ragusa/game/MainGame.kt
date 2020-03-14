package com.ragusa.game

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.ragusa.game.player.Player
import com.ragusa.game.player.Robot
import com.ragusa.game.tiles.*
import com.ragusa.game.utility.Timer
import com.ragusa.game.utility.plus

class MainGame : ApplicationAdapter() {
    var batch: SpriteBatch? = null

    var tileGrid: TileGrid? = null
    var player: Player? = null

    val evaluationTimer = Timer(100) // Every 100 ms

    var viewPosition = Vector2(0f,0f)

    val renderList = mutableListOf<IRenderable>()

    companion object {
        const val debug: Boolean = true
        const val ciruitEvaluationRate: Long = 100 // milliseconds
    }

    override fun create() {
        Assets.LoadAll()
        Assets.manager.finishLoading()

        val robot = Robot()
        renderList.add(robot)

        robot.position = Vector2(0f,0f)
        tileGrid = TileGrid(robot)
        renderList.add(tileGrid!!)
        player = Player(robot, tileGrid!!)
        renderList.add(player!!)

        tileGrid!![0,1] = TileSource(true, false).withRotation(Direction.SOUTH)
        tileGrid!![0,0] = TileSplit(true, false)
        tileGrid!![-1,0] = TileBend(false, false).withRotation(Direction.EAST)
        tileGrid!![1,-1] = TileStraight(false, false)
        tileGrid!![2,-1] = TileBend(false, false)
        tileGrid!![-2,-2] = TileBend(false, true)
        tileGrid!![-1,-2] = TileAnd(true, false).withRotation(Direction.SOUTH)
        tileGrid!![0,-2] = TileSplit(false, false).withRotation(Direction.SOUTH)
        tileGrid!![-2,-3] = Tile(true, false)
        tileGrid!![-1,-3] = TileStraight(false, false)
        tileGrid!![0,-3] = Tile(true, false)
        tileGrid!![-1,-4] = TileStraight(false, false)


        tileGrid!!.evaluateCircuit()



        batch = SpriteBatch()
    }

    override fun render() {
        Gdx.gl.glClearColor(0.45f, 0.50f, 0.55f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        if (evaluationTimer.isOverDue()) {
            tileGrid!!.evaluateCircuit()
            evaluationTimer.restart()
        }

        player!!.userInput()

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            viewPosition += Vector2(-1f, 0f)
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
            viewPosition += Vector2(1f, 0f)
        if (Gdx.input.isKeyPressed(Input.Keys.UP))
            viewPosition += Vector2(0f, -1f)
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
            viewPosition += Vector2(0f, 1f)

        batch!!.begin()
        renderList.forEach { it.render(batch!!, viewPosition) }
        batch!!.end()
    }

}