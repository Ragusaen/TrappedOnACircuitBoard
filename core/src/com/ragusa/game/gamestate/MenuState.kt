package com.ragusa.game.gamestate

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Button
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.utils.viewport.ScreenViewport
import com.ragusa.game.Assets
import com.ragusa.game.gamestate.UI.Menu
import com.ragusa.game.gamestate.UI.MenuItem
import com.ragusa.game.level.Level
import com.ragusa.game.level.LevelGroup
import com.ragusa.game.level.LevelItem
import com.ragusa.game.level.allLevels

class MenuState(val levelChosen: (Level?, Boolean) -> Unit) : GameState() {

    val skin = Skin()
    val stage: Stage = Stage(ScreenViewport())
    val button = TextButton()


    override fun setup() {


    }

    override fun update() {

    }

    override fun render(batch: SpriteBatch, relativeTo: Vector2) {

    }


}