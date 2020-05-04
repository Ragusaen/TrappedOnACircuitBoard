package com.ragusa.game.gamestate

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.ragusa.game.Assets
import com.ragusa.game.level.Level

class MenuState(val playLevel: ((Level)->Unit)) : GameState() {

    override fun setup() {

    }

    override fun update() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.P)) {
            playLevel(Assets.)
        }
    }

}