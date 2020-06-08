package com.ragusa.game.level

import com.badlogic.gdx.assets.AssetDescriptor
import com.badlogic.gdx.math.Vector2
import com.ragusa.game.Assets
import com.ragusa.game.gamestate.UI.Menu

abstract class LevelNode(val name: String)

class LevelGroup(name: String, val elements: List<LevelNode>) : LevelNode(name)
class LevelItem(name: String, val level: AssetDescriptor<Level>) : LevelNode(name)

val allLevels = LevelGroup("Tutorial", listOf(
        LevelItem("Zero", Assets.levels.level1),
        LevelItem("One", Assets.levels.level2)
))