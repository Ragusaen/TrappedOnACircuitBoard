package com.ragusa.game

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.InputProcessor
import com.badlogic.gdx.assets.AssetDescriptor
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.ScreenViewport
import com.ragusa.game.gamestate.EditLevelState
import com.ragusa.game.gamestate.GameState
import com.ragusa.game.level.Level
import com.ragusa.game.level.LevelLoader
import com.ragusa.game.gamestate.PlayLevelState
import com.ragusa.game.level.LevelExporter

val controls = InputMapping.default

class GameAdapter : ApplicationAdapter() {
    var batch: SpriteBatch? = null
    var game: GameController? = null

    companion object {
        const val debug: Boolean = true
    }

    override fun create() {
        val resolver = InternalFileHandleResolver()
        Assets.manager.setLoader(Level::class.java, LevelLoader(resolver))

        Assets.loadAll()
        Assets.manager.finishLoading()

        batch = SpriteBatch()
        game = GameController()
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