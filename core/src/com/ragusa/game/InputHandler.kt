package com.ragusa.game

import com.badlogic.gdx.Input
import com.badlogic.gdx.InputAdapter

class InputHandler : InputAdapter() {

    override fun keyDown(keyCode: Int): Boolean {
        println(Input.Keys.toString(keyCode))
        return true
    }
}