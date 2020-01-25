package com.ragusa.game.tiles

import com.badlogic.gdx.graphics.Texture
import com.ragusa.game.Assets
import com.ragusa.game.Direction

class TileOr : Tile() {
    override val ports: Array<TilePort> = arrayOf(TilePort(Direction.NORTH), TilePort(Direction.EAST), TilePort(Direction.WEST))

    override fun updatePorts() {
        if (ports[1].isOn() || ports[2].isOn())
            ports[0].state = PortState.OUT
        else
            ports[0].state = PortState.OFF
    }

    override val stateTextures: Map<Int, Texture> = mapOf(
        0b000 to Assets.tiles.or.state_000,
        0b100 to Assets.tiles.or.state_100,
        0b101 to Assets.tiles.or.state_101,
        0b110 to Assets.tiles.or.state_110,
        0b111 to Assets.tiles.or.state_111
    ).entries.map { Pair(it.key, Assets.manager.get(it.value)) }.toMap()

}