package com.ragusa.game.UI

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.ragusa.game.IRenderable
import com.ragusa.game.gamestate.GameState
import com.ragusa.game.utility.plus

infix fun Int.mod(n: Int) = (this % n + n) % n

abstract class MenuItem(val text: String)

class SubMenuItem(text: String, val subMenu: Menu) : MenuItem(text)

class LeafMenuItem(text: String, val action: () -> GameState) : MenuItem(text)

open class Menu(val items: List<MenuItem>) {
    var currentIndex = 0
        set(value) {
            field = value mod items.size
        }

    var parent: Menu? = null

    fun fixParents(): Menu {
        items.filterIsInstance<SubMenuItem>().forEach {
            it.subMenu.parent = this
            it.subMenu.fixParents()
        }
        return this
    }
}
