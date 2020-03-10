package com.ragusa.game.player

import com.ragusa.game.TileGrid
import com.ragusa.game.player.actions.UndoableAction

class ActionStack(val tileGrid: TileGrid) {
    val stack = mutableListOf<UndoableAction>()

    fun doAction(action: UndoableAction) {
        // Do action, but only add it if it was successful
        if (action.doAction(tileGrid))
            stack.add(action)
    }

    fun undoAction() {
        if (stack.isNotEmpty()) {
            stack.last().undoAction()
            stack.removeAt(stack.size - 1)
        }
        // Give some feedback to the user
    }
}