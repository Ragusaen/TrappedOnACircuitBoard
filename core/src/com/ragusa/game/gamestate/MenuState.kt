package com.ragusa.game.gamestate

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.ragusa.game.Assets
import com.ragusa.game.gamestate.UI.Menu
import com.ragusa.game.gamestate.UI.MenuItem
import com.ragusa.game.level.Level
import com.ragusa.game.level.LevelGroup
import com.ragusa.game.level.LevelItem
import com.ragusa.game.level.allLevels

class MenuState(val levelChosen: (Level?, Boolean) -> Unit) : GameState() {

    val editMenu = Menu(menuPosition, null, listOf(
            Menu.NameCallback("New Level") {levelChosen(null, true)}
    ))

    // Create main menu
    var currentMenu = Menu(menuPosition, null, listOf(
            Menu.NameMenu("Play", createLevelMenu(allLevels), { chooseSubMenu(0) }),
            Menu.NameMenu("Edit", editMenu, {chooseSubMenu(1)}),
            Menu.NameCallback("Exit", {})
    ))

    companion object {
        val menuPosition = Vector2(100f, 400f)
    }

    init {
        currentMenu.menuItems.filterIsInstance<Menu.MenuItemMenu>().forEach { it.menu.parent = currentMenu }
    }

    override fun setup() {

    }

    override fun update() {
        currentMenu.update()
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            val p = currentMenu.parent
            if (p != null)
                currentMenu = p
        }
    }

    override fun render(batch: SpriteBatch, relativeTo: Vector2) {
        currentMenu.render(batch, relativeTo)
    }

    private fun chooseSubMenu(index: Int) {
        currentMenu = (currentMenu.menuItems[index] as Menu.MenuItemMenu).menu
    }

    private fun createLevelMenu(start: LevelGroup) : Menu {
        val childList = start.elements.withIndex().map { lni ->
            val ln = lni.value
            if (ln is LevelItem)
                Menu.NameCallback(ln.name) { levelChosen(Assets.manager.get(ln.level), false)}
            else
                Menu.NameMenu(ln.name, createLevelMenu(ln as LevelGroup)) {chooseSubMenu(lni.index)}
        }
        val new = Menu(menuPosition, null, childList)
        childList.filterIsInstance<Menu.NameMenu>().forEach {it.menu.parent = new}

        return new
    }

}