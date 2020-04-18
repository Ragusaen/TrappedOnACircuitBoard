package com.ragusa.game

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.assets.AssetDescriptor
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.ragusa.game.gamestate.EditLevelState
import com.ragusa.game.level.Level
import com.ragusa.game.level.LevelLoader
import com.ragusa.game.gamestate.PlayLevelState
import com.ragusa.game.level.LevelExporter

class MainGame : ApplicationAdapter() {
    var batch: SpriteBatch? = null

    val renderList = mutableListOf<IRenderable>()

    var playLevelState: PlayLevelState? = null
    var editLevelState: EditLevelState? = null

    companion object {
        const val debug: Boolean = true
        const val ciruitEvaluationRate: Long = 100 // milliseconds
    }

    override fun create() {
        val resolver = InternalFileHandleResolver()
        Assets.manager.setLoader(Level::class.java, LevelLoader(resolver))

        val levelDescriptor = AssetDescriptor("levels\\testlevel.lvl", Level::class.java)

        Assets.LoadAll()
        Assets.manager.load(levelDescriptor)
        Assets.manager.finishLoading()

        val lvl = Assets.manager.get(levelDescriptor)

        playLevelState = PlayLevelState(lvl)
        playLevelState!!.setup()

        editLevelState = EditLevelState(lvl)
        editLevelState!!.setup()
        renderList.add(editLevelState!!)

        batch = SpriteBatch()


    }

    override fun render() {
        Gdx.gl.glClearColor(0.45f, 0.50f, 0.55f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        editLevelState!!.update()

        if (Gdx.input.isKeyJustPressed(Input.Keys.P)) {
            println(LevelExporter().export(editLevelState!!.level))
        }

        batch!!.begin()
        renderList.forEach { it.render(batch!!, Vector2.Zero) }
        batch!!.end()
    }

}