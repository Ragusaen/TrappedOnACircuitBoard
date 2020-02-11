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

    companion object {
        const val debug: Boolean = true
    }

    override fun create() {
        Assets.LoadAll()
        Assets.manager.finishLoading()

        tileGrid = TileGrid()

        tileGrid!![1,1] = TileSource()
        tileGrid!![3,3] = TileInverter().withRotation(Direction.SOUTH)
        tileGrid!![3,4] = TileBend().withRotation(Direction.SOUTH)
        tileGrid!![2,4] = TileBend().withRotation(Direction.EAST)
        tileGrid!![2,3] = TileStraight()
        tileGrid!![3,2] = TileBend().withRotation(Direction.WEST)
        tileGrid!![1,2] = TileBend().withRotation(Direction.EAST)
        tileGrid!![2,2] = TileXor()

        tileGrid!!.evaluateCircuit()

        batch = SpriteBatch()
    }

    override fun render() {
        Gdx.gl.glClearColor(0.45f, 0.50f, 0.55f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            for (gt in TileGrid.GateTileIterator(tileGrid!!))
                gt.nextTick()
            tileGrid!!.evaluateCircuit()
        }


        batch!!.begin()
        tileGrid!!.render(batch!!)
        batch!!.end()
    }

}