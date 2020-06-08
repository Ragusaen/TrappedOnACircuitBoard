package com.ragusa.game.gamestate.UI

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.ragusa.game.IRenderable
import com.ragusa.game.utility.plus

abstract class MenuChildItem(val menuItem: MenuItem, val callback: (Int) -> Unit) : IRenderable {
    override fun render(batch: SpriteBatch, relativeTo: Vector2) {
        menuItem.render(batch, relativeTo)
    }
}

abstract class NameItem(val name: String, val callback: (Int) -> Unit)

open class Menu(val position: Vector2, var parent: Menu?, items: List<NameItem>) : IRenderable{
    var currentIndex = 0
        set(value) {
            menuItems[field].menuItem.isChosen = false
            field = value % menuItems.size
            menuItems[field].menuItem.isChosen = true
        }

    class NameCallback(name: String, callback: (Int) -> Unit) : NameItem(name, callback)
    class NameMenu(name: String, val menu: Menu, callback: (Int) -> Unit): NameItem(name, callback)
    class ItemCallback(menuItem: MenuItem, callback: (Int) -> Unit) : MenuChildItem(menuItem, callback)
    class MenuItemMenu(menuItem: MenuItem, val menu: Menu, callback: (Int) -> Unit) : MenuChildItem(menuItem, callback)

    companion object {
        private val itemSpacing = -96f
    }

    val menuItems = items.withIndex().map {
        val mci = it.value
        val mi = MenuItem(mci.name, Vector2(0f, it.index * itemSpacing))
        if (mci is NameCallback)
            ItemCallback(mi, mci.callback)
        else
            MenuItemMenu(mi, (mci as NameMenu).menu,  mci.callback)
    }

    init {
        currentIndex = 0
    }

    override fun render(batch: SpriteBatch, relativeTo: Vector2) {
        menuItems.forEach {it.menuItem.render(batch, relativeTo + position)}
    }

    fun update() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP))
            currentIndex = (menuItems.size + currentIndex - 1) % menuItems.size
        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN))
            currentIndex = (currentIndex + 1) % menuItems.size
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER))
            menuItems[currentIndex].callback(currentIndex)
    }

}