package com.ragusa.game

import com.badlogic.gdx.Input



data class InputMapping(
        val MoveUp: Int,
        val MoveDown: Int,
        val MoveLeft: Int,
        val MoveRight: Int,
        val LookUp: Int,
        val LookDown: Int,
        val LookLeft: Int,
        val LookRight: Int
) {
    companion object {
        val default = InputMapping(
                Input.Keys.W,
                Input.Keys.D,
                Input.Keys.A,
                Input.Keys.S,
                Input.Keys.UP,
                Input.Keys.DOWN,
                Input.Keys.LEFT,
                Input.Keys.RIGHT
        )
    }
}