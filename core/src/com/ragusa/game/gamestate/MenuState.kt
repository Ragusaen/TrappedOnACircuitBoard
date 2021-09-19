package com.ragusa.game.gamestate

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.*
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.ragusa.game.Assets
import com.ragusa.game.UI.LeafMenuItem
import com.ragusa.game.UI.Menu
import com.ragusa.game.UI.MenuItem
import com.ragusa.game.UI.SubMenuItem
import com.ragusa.game.controls

class MenuState(var menu: Menu, val switchGameState: (GameState) -> Unit) : GameState() {
    val skin = Skin()
    val stage: Stage = Stage()
    val vg = VerticalGroup()
    val selectedButtonStyle = TextButton.TextButtonStyle()
    val unselectedButtonStyle = TextButton.TextButtonStyle()

    val textButtons = mutableListOf<TextButton>()

    var currentSelectedIdx = 0

    private fun createMenuItem(menuItem: MenuItem) {
        val button = TextButton(menuItem.text,  unselectedButtonStyle)
        button.label.setFontScale(0.8f)
        button.pad(15f)
//        button.setOrigin(button.width / 2, button.height / 2)

        textButtons.add(button)
        vg.addActor(button)
    }

    init {
        Gdx.input.inputProcessor = stage

        // Setup buttonstyle
        selectedButtonStyle.font = Assets.manager.get(Assets.fonts.volleyball_fnt)
        unselectedButtonStyle.font = Assets.manager.get(Assets.fonts.volleyball_fnt)
        selectedButtonStyle.up = TextureRegionDrawable(TextureRegion(Assets.manager.get(Assets.textures.UI.menu_item_on)))
        unselectedButtonStyle.up = TextureRegionDrawable(TextureRegion(Assets.manager.get(Assets.textures.UI.menu_item_off)))

        vg.setFillParent(true)
        vg.pad(100f)
        vg.center().left().columnLeft()
        vg.space(15f)

        configureMenu()

        stage.addActor(vg)
    }

    private fun configureMenu() {
        textButtons.clear()
        vg.clear()
        menu.items.forEach {
            createMenuItem(it)
        }
    }

    fun goBack() {
        if (menu.parent != null) {
            menu = menu.parent!!
            configureMenu()
        }
    }


    override fun setup() {


    }

    override fun update() {
        if (Gdx.input.isKeyJustPressed(controls.MoveUp)) menu.currentIndex--
        if (Gdx.input.isKeyJustPressed(controls.MoveDown)) menu.currentIndex++

        if (Gdx.input.isKeyJustPressed(controls.MenuChoose)) {
            val sm = menu.items[menu.currentIndex]
            when (sm) {
                is LeafMenuItem -> switchGameState(sm.action())
                is SubMenuItem -> { menu = sm.subMenu; configureMenu() }
            }
        }

        for ((i, tb) in textButtons.withIndex()) {
            tb.style = if (i == menu.currentIndex) selectedButtonStyle else unselectedButtonStyle
        }
    }

    override fun render(batch: SpriteBatch, relativeTo: Vector2) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        stage.act(Gdx.graphics.deltaTime)
        stage.draw()
    }


}