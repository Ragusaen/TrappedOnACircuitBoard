package com.ragusa.game

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.ragusa.game.tiles.*

class MainGame : ApplicationAdapter() {
    var batch: SpriteBatch? = null

    var tileGrid: TileGrid? = null

    var step = 0

    companion object {
        const val debug: Boolean = true
    }

    override fun create() {
        Assets.LoadAll()
        Assets.manager.finishLoading()

        tileGrid = TileGrid()

        tileGrid!![1,1] = TileSource()
        tileGrid!![1,2] = TileStraight()
        tileGrid!![1,3] = TileBend().withRotation(Direction.EAST)
        tileGrid!![2,3] = TileXor()
        tileGrid!![2,4] = TileBend().withRotation(Direction.EAST)
        tileGrid!![3,4] = TileBend().withRotation(Direction.SOUTH)
        tileGrid!![3,3] = TileBend().withRotation(Direction.WEST)



        batch = SpriteBatch()
    }

    override fun render() {
        Gdx.gl.glClearColor(0.45f, 0.50f, 0.55f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE))
            step++

        tileGrid!!.evaluateCircuit()

        batch!!.begin()
        tileGrid!!.render(batch!!)

        for (tile in tileGrid!!) {
            tile.debugState(batch!!)
        }
        batch!!.end()
    }

}