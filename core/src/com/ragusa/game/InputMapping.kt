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
        val LookRight: Int,
        val MenuChoose: Int,
        val PickUpPlace: Int,
        val Rotate: Int,
        val Remove: Int,
        val Turn: Int,
        val Undo: Int,
        val Lock: Int,
        val Insulate: Int,
        val NextItem: Int,
        val SwitchPlayEdit: Int
) {
    companion object {
        val default = InputMapping(
                Input.Keys.UP,
                Input.Keys.DOWN,
                Input.Keys.LEFT,
                Input.Keys.RIGHT,
                Input.Keys.W,
                Input.Keys.S,
                Input.Keys.A,
                Input.Keys.D,
                Input.Keys.ENTER,
                Input.Keys.SPACE,
                Input.Keys.R,
                Input.Keys.Q,
                Input.Keys.SHIFT_LEFT,
                Input.Keys.Z,
                Input.Keys.L,
                Input.Keys.I,
                Input.Keys.TAB,
                Input.Keys.P
        )
    }
}