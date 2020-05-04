package com.ragusa.game

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.assets.AssetDescriptor
import com.badlogic.gdx.assets.AssetManager

object Assets {
    object tiles {
        val plain = AssetDescriptor("textures//tiles/plain.png", Texture::class.java);
        object bend {
            val state_11 = AssetDescriptor("textures//tiles/bend/state_11.png", Texture::class.java);
            val state_00 = AssetDescriptor("textures//tiles/bend/state_00.png", Texture::class.java);
        }
        object plus {
            val state_1111 = AssetDescriptor("textures//tiles/plus/state_1111.png", Texture::class.java);
            val state_0000 = AssetDescriptor("textures//tiles/plus/state_0000.png", Texture::class.java);
        }
        val screws = AssetDescriptor("textures//tiles/screws.png", Texture::class.java);
        object straight {
            val state_11 = AssetDescriptor("textures//tiles/straight/state_11.png", Texture::class.java);
            val state_00 = AssetDescriptor("textures//tiles/straight/state_00.png", Texture::class.java);
        }
        object goal {
            val state_1 = AssetDescriptor("textures//tiles/goal/state_1.png", Texture::class.java);
            val state_0 = AssetDescriptor("textures//tiles/goal/state_0.png", Texture::class.java);
        }
        object and {
            val state_000 = AssetDescriptor("textures//tiles/and/state_000.png", Texture::class.java);
            val state_111 = AssetDescriptor("textures//tiles/and/state_111.png", Texture::class.java);
        }
        val insulated = AssetDescriptor("textures//tiles/insulated.png", Texture::class.java);
        object diode {
            val state_10 = AssetDescriptor("textures//tiles/diode/state_10.png", Texture::class.java);
            val state_11 = AssetDescriptor("textures//tiles/diode/state_11.png", Texture::class.java);
            val state_00 = AssetDescriptor("textures//tiles/diode/state_00.png", Texture::class.java);
        }
        object inverter {
            val state_10 = AssetDescriptor("textures//tiles/inverter/state_10.png", Texture::class.java);
            val state_11 = AssetDescriptor("textures//tiles/inverter/state_11.png", Texture::class.java);
            val state_01 = AssetDescriptor("textures//tiles/inverter/state_01.png", Texture::class.java);
        }
        object xor {
            val state_101 = AssetDescriptor("textures//tiles/xor/state_101.png", Texture::class.java);
            val state_000 = AssetDescriptor("textures//tiles/xor/state_000.png", Texture::class.java);
            val state_110 = AssetDescriptor("textures//tiles/xor/state_110.png", Texture::class.java);
        }
        object split {
            val state_000 = AssetDescriptor("textures//tiles/split/state_000.png", Texture::class.java);
            val state_111 = AssetDescriptor("textures//tiles/split/state_111.png", Texture::class.java);
        }
        object gate {
            object binary {
                val state_10 = AssetDescriptor("textures//tiles/gate/binary/state_10.png", Texture::class.java);
                val state_11 = AssetDescriptor("textures//tiles/gate/binary/state_11.png", Texture::class.java);
                val state_01 = AssetDescriptor("textures//tiles/gate/binary/state_01.png", Texture::class.java);
                val state_00 = AssetDescriptor("textures//tiles/gate/binary/state_00.png", Texture::class.java);
            }
            object ternary {
                val state_101 = AssetDescriptor("textures//tiles/gate/ternary/state_101.png", Texture::class.java);
                val state_000 = AssetDescriptor("textures//tiles/gate/ternary/state_000.png", Texture::class.java);
                val state_100 = AssetDescriptor("textures//tiles/gate/ternary/state_100.png", Texture::class.java);
                val state_011 = AssetDescriptor("textures//tiles/gate/ternary/state_011.png", Texture::class.java);
                val state_110 = AssetDescriptor("textures//tiles/gate/ternary/state_110.png", Texture::class.java);
                val state_010 = AssetDescriptor("textures//tiles/gate/ternary/state_010.png", Texture::class.java);
                val state_001 = AssetDescriptor("textures//tiles/gate/ternary/state_001.png", Texture::class.java);
                val state_111 = AssetDescriptor("textures//tiles/gate/ternary/state_111.png", Texture::class.java);
            }
        }
        object or {
            val state_101 = AssetDescriptor("textures//tiles/or/state_101.png", Texture::class.java);
            val state_000 = AssetDescriptor("textures//tiles/or/state_000.png", Texture::class.java);
            val state_110 = AssetDescriptor("textures//tiles/or/state_110.png", Texture::class.java);
            val state_111 = AssetDescriptor("textures//tiles/or/state_111.png", Texture::class.java);
        }
        object source {
            val state_1 = AssetDescriptor("textures//tiles/source/state_1.png", Texture::class.java);
        }
    }
    object player {
        val off = AssetDescriptor("textures//player/off.png", Texture::class.java);
        val on = AssetDescriptor("textures//player/on.png", Texture::class.java);
    }
    object UI {
        val hand_frame_off = AssetDescriptor("textures//UI/hand_frame_off.png", Texture::class.java);
        val hand_frame_on = AssetDescriptor("textures//UI/hand_frame_on.png", Texture::class.java);
    }



    val manager = AssetManager()
    fun LoadAll() {
        manager.load(tiles.plain)
        manager.load(tiles.bend.state_11)
        manager.load(tiles.bend.state_00)
        manager.load(tiles.plus.state_1111)
        manager.load(tiles.plus.state_0000)
        manager.load(tiles.screws)
        manager.load(tiles.straight.state_11)
        manager.load(tiles.straight.state_00)
        manager.load(tiles.goal.state_1)
        manager.load(tiles.goal.state_0)
        manager.load(tiles.and.state_000)
        manager.load(tiles.and.state_111)
        manager.load(tiles.insulated)
        manager.load(tiles.diode.state_10)
        manager.load(tiles.diode.state_11)
        manager.load(tiles.diode.state_00)
        manager.load(tiles.inverter.state_10)
        manager.load(tiles.inverter.state_11)
        manager.load(tiles.inverter.state_01)
        manager.load(tiles.xor.state_101)
        manager.load(tiles.xor.state_000)
        manager.load(tiles.xor.state_110)
        manager.load(tiles.split.state_000)
        manager.load(tiles.split.state_111)
        manager.load(tiles.gate.binary.state_10)
        manager.load(tiles.gate.binary.state_11)
        manager.load(tiles.gate.binary.state_01)
        manager.load(tiles.gate.binary.state_00)
        manager.load(tiles.gate.ternary.state_101)
        manager.load(tiles.gate.ternary.state_000)
        manager.load(tiles.gate.ternary.state_100)
        manager.load(tiles.gate.ternary.state_011)
        manager.load(tiles.gate.ternary.state_110)
        manager.load(tiles.gate.ternary.state_010)
        manager.load(tiles.gate.ternary.state_001)
        manager.load(tiles.gate.ternary.state_111)
        manager.load(tiles.or.state_101)
        manager.load(tiles.or.state_000)
        manager.load(tiles.or.state_110)
        manager.load(tiles.or.state_111)
        manager.load(tiles.source.state_1)
        manager.load(player.off)
        manager.load(player.on)
        manager.load(UI.hand_frame_off)
        manager.load(UI.hand_frame_on)

    }
}