package com.ragusa.game

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.assets.AssetDescriptor
import com.badlogic.gdx.assets.AssetManager
import com.ragusa.game.level.Level
import com.badlogic.gdx.graphics.g2d.BitmapFont

object Assets {
object textures {
object player{
val off = AssetDescriptor("textures/player/off.png", Texture::class.java)
val on = AssetDescriptor("textures/player/on.png", Texture::class.java)
}
object tiles{
object and{
val state_000 = AssetDescriptor("textures/tiles/and/state_000.png", Texture::class.java)
val state_111 = AssetDescriptor("textures/tiles/and/state_111.png", Texture::class.java)
}
object bend{
val state_00 = AssetDescriptor("textures/tiles/bend/state_00.png", Texture::class.java)
val state_11 = AssetDescriptor("textures/tiles/bend/state_11.png", Texture::class.java)
}
object diode{
val state_00 = AssetDescriptor("textures/tiles/diode/state_00.png", Texture::class.java)
val state_10 = AssetDescriptor("textures/tiles/diode/state_10.png", Texture::class.java)
val state_11 = AssetDescriptor("textures/tiles/diode/state_11.png", Texture::class.java)
}
object gate{
object binary{
val state_00 = AssetDescriptor("textures/tiles/gate/binary/state_00.png", Texture::class.java)
val state_01 = AssetDescriptor("textures/tiles/gate/binary/state_01.png", Texture::class.java)
val state_10 = AssetDescriptor("textures/tiles/gate/binary/state_10.png", Texture::class.java)
val state_11 = AssetDescriptor("textures/tiles/gate/binary/state_11.png", Texture::class.java)
}
object ternary{
val state_000 = AssetDescriptor("textures/tiles/gate/ternary/state_000.png", Texture::class.java)
val state_001 = AssetDescriptor("textures/tiles/gate/ternary/state_001.png", Texture::class.java)
val state_010 = AssetDescriptor("textures/tiles/gate/ternary/state_010.png", Texture::class.java)
val state_011 = AssetDescriptor("textures/tiles/gate/ternary/state_011.png", Texture::class.java)
val state_100 = AssetDescriptor("textures/tiles/gate/ternary/state_100.png", Texture::class.java)
val state_101 = AssetDescriptor("textures/tiles/gate/ternary/state_101.png", Texture::class.java)
val state_110 = AssetDescriptor("textures/tiles/gate/ternary/state_110.png", Texture::class.java)
val state_111 = AssetDescriptor("textures/tiles/gate/ternary/state_111.png", Texture::class.java)
}
}
object goal{
val state_0 = AssetDescriptor("textures/tiles/goal/state_0.png", Texture::class.java)
val state_1 = AssetDescriptor("textures/tiles/goal/state_1.png", Texture::class.java)
}
val insulated = AssetDescriptor("textures/tiles/insulated.png", Texture::class.java)
object inverter{
val state_01 = AssetDescriptor("textures/tiles/inverter/state_01.png", Texture::class.java)
val state_10 = AssetDescriptor("textures/tiles/inverter/state_10.png", Texture::class.java)
val state_11 = AssetDescriptor("textures/tiles/inverter/state_11.png", Texture::class.java)
}
object or{
val state_000 = AssetDescriptor("textures/tiles/or/state_000.png", Texture::class.java)
val state_101 = AssetDescriptor("textures/tiles/or/state_101.png", Texture::class.java)
val state_110 = AssetDescriptor("textures/tiles/or/state_110.png", Texture::class.java)
val state_111 = AssetDescriptor("textures/tiles/or/state_111.png", Texture::class.java)
}
val plain = AssetDescriptor("textures/tiles/plain.png", Texture::class.java)
object plus{
val state_0000 = AssetDescriptor("textures/tiles/plus/state_0000.png", Texture::class.java)
val state_1111 = AssetDescriptor("textures/tiles/plus/state_1111.png", Texture::class.java)
}
val screws = AssetDescriptor("textures/tiles/screws.png", Texture::class.java)
object source{
val state_1 = AssetDescriptor("textures/tiles/source/state_1.png", Texture::class.java)
}
object split{
val state_000 = AssetDescriptor("textures/tiles/split/state_000.png", Texture::class.java)
val state_111 = AssetDescriptor("textures/tiles/split/state_111.png", Texture::class.java)
}
object straight{
val state_00 = AssetDescriptor("textures/tiles/straight/state_00.png", Texture::class.java)
val state_11 = AssetDescriptor("textures/tiles/straight/state_11.png", Texture::class.java)
}
object xor{
val state_000 = AssetDescriptor("textures/tiles/xor/state_000.png", Texture::class.java)
val state_101 = AssetDescriptor("textures/tiles/xor/state_101.png", Texture::class.java)
val state_110 = AssetDescriptor("textures/tiles/xor/state_110.png", Texture::class.java)
}
}
object UI{
val hand_frame_off = AssetDescriptor("textures/UI/hand_frame_off.png", Texture::class.java)
val hand_frame_on = AssetDescriptor("textures/UI/hand_frame_on.png", Texture::class.java)
val menu_item_off = AssetDescriptor("textures/UI/menu_item_off.png", Texture::class.java)
val menu_item_on = AssetDescriptor("textures/UI/menu_item_on.png", Texture::class.java)
}
}
object levels {
val level1 = AssetDescriptor("levels/level1.lvl", Level::class.java)
val level2 = AssetDescriptor("levels/level2.lvl", Level::class.java)
val testlevel = AssetDescriptor("levels/testlevel.lvl", Level::class.java)
}
object fonts {
val volleyball_fnt = AssetDescriptor("fonts/volleyball.fnt", BitmapFont::class.java)
val volleyball_png = AssetDescriptor("fonts/volleyball.png", BitmapFont::class.java)
}



    val manager = AssetManager()
    fun loadAll() {
manager.load(textures.player.off)
manager.load(textures.player.on)
manager.load(textures.tiles.and.state_000)
manager.load(textures.tiles.and.state_111)
manager.load(textures.tiles.bend.state_00)
manager.load(textures.tiles.bend.state_11)
manager.load(textures.tiles.diode.state_00)
manager.load(textures.tiles.diode.state_10)
manager.load(textures.tiles.diode.state_11)
manager.load(textures.tiles.gate.binary.state_00)
manager.load(textures.tiles.gate.binary.state_01)
manager.load(textures.tiles.gate.binary.state_10)
manager.load(textures.tiles.gate.binary.state_11)
manager.load(textures.tiles.gate.ternary.state_000)
manager.load(textures.tiles.gate.ternary.state_001)
manager.load(textures.tiles.gate.ternary.state_010)
manager.load(textures.tiles.gate.ternary.state_011)
manager.load(textures.tiles.gate.ternary.state_100)
manager.load(textures.tiles.gate.ternary.state_101)
manager.load(textures.tiles.gate.ternary.state_110)
manager.load(textures.tiles.gate.ternary.state_111)
manager.load(textures.tiles.goal.state_0)
manager.load(textures.tiles.goal.state_1)
manager.load(textures.tiles.insulated)
manager.load(textures.tiles.inverter.state_01)
manager.load(textures.tiles.inverter.state_10)
manager.load(textures.tiles.inverter.state_11)
manager.load(textures.tiles.or.state_000)
manager.load(textures.tiles.or.state_101)
manager.load(textures.tiles.or.state_110)
manager.load(textures.tiles.or.state_111)
manager.load(textures.tiles.plain)
manager.load(textures.tiles.plus.state_0000)
manager.load(textures.tiles.plus.state_1111)
manager.load(textures.tiles.screws)
manager.load(textures.tiles.source.state_1)
manager.load(textures.tiles.split.state_000)
manager.load(textures.tiles.split.state_111)
manager.load(textures.tiles.straight.state_00)
manager.load(textures.tiles.straight.state_11)
manager.load(textures.tiles.xor.state_000)
manager.load(textures.tiles.xor.state_101)
manager.load(textures.tiles.xor.state_110)
manager.load(textures.UI.hand_frame_off)
manager.load(textures.UI.hand_frame_on)
manager.load(textures.UI.menu_item_off)
manager.load(textures.UI.menu_item_on)
manager.load(levels.level1)
manager.load(levels.level2)
manager.load(levels.testlevel)
manager.load(fonts.volleyball_fnt)
manager.load(fonts.volleyball_png)

    }
}