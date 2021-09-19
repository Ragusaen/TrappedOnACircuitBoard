package com.ragusa.game

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.FitViewport
import com.ragusa.game.level.Level
import com.ragusa.game.level.LevelLoader
import com.ragusa.game.level.LevelPack
import com.ragusa.game.level.LevelPackLoader

val controls = InputMapping.colemak

class GameAdapter : ApplicationAdapter() {
    var batch: SpriteBatch? = null
    var game: GameController? = null

    var stage: Stage? = null

    companion object {
        const val debug: Boolean = true
    }

    override fun create() {
        val resolver = InternalFileHandleResolver()
        Assets.manager.setLoader(Level::class.java, LevelLoader(resolver))
        Assets.manager.setLoader(LevelPack::class.java, LevelPackLoader(resolver))

        Assets.loadAll()
        Assets.manager.finishLoading()

        batch = SpriteBatch()
        game = GameController()

        val uiViewport = FitViewport(1280f, 720f )
        stage = Stage(uiViewport, batch)
        Gdx.input.inputProcessor = stage
    }

    override fun render() {
        Gdx.gl.glClearColor(0.45f, 0.50f, 0.55f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        game!!.update()

        batch!!.begin()
        game!!.render(batch!!, Vector2.Zero)
        batch!!.end()
    }

}