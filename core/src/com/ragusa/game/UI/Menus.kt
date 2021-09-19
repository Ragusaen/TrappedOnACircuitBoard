package com.ragusa.game.UI

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.ragusa.game.Assets
import com.ragusa.game.gamestate.EditLevelState
import com.ragusa.game.gamestate.PlayLevelState
import com.ragusa.game.level.Level
import com.ragusa.game.level.RDLevelParser
import com.ragusa.game.tiles.TileGrid

private val campaignMenu = Menu(
    Assets.levelpacks.map {
        SubMenuItem(it.key, Menu(
            it.value.map {
                LeafMenuItem(it.key) {
                    Assets.manager.load(it.value)
                    Assets.manager.finishLoading()
                    val lvl = Assets.manager.get(it.value)!!
                    PlayLevelState(lvl)
                }
            }
        ))
    }
)

fun getCustomLevels(edit: Boolean = false) = Gdx.files.external("Documents/My Games/Trapped On A Circuit Board/").list()
    .filter { it.file().extension == "lvl" }.map {
        val lvl = RDLevelParser().parse(it.readString())

        LeafMenuItem(lvl.name) {
            if (edit) EditLevelState(lvl) else PlayLevelState(lvl)
        }
    }

private var customMapsMenu = Menu(
    getCustomLevels()
)

val mainMenu = Menu(
    listOf(
        SubMenuItem("Campaign Levels", campaignMenu),
        SubMenuItem("Custom Levels", customMapsMenu),
        SubMenuItem("Level Editor",
            Menu(listOf(LeafMenuItem("New Level") {EditLevelState(Level.emptyLevel())}) + getCustomLevels(true)))
    )
).apply { fixParents() }