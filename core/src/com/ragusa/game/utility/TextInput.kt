package com.ragusa.game.utility
import com.badlogic.gdx.Input

class TextPromptListener(val action: (String?) -> Unit) : Input.TextInputListener {
    override fun input(text: String) {
        action(text)
    }

    override fun canceled() {
        action(null)
    }
}