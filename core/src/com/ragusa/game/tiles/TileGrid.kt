package com.ragusa.game.tiles

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.ragusa.game.Array2D
import com.ragusa.game.Direction
import com.ragusa.game.IRenderable

class TileGrid : IRenderable, Iterable<Tile> {

    class TileGridIterator(tileGrid: TileGrid) : Iterator<Tile> {
        private val tilesIterator = tileGrid.tiles.iterator()
        private var next: Tile? = null

        override fun hasNext(): Boolean {
            next = null
            while (tilesIterator.hasNext()) {
                next = tilesIterator.next()
                if (next != null)
                    return true
            }
            return false
        }

        override fun next(): Tile {
            return next!!
        }

    }

    private val tiles = Array2D<Tile>(10, 10)

    override fun render(batch: SpriteBatch) {
        for (tile: Tile? in tiles) {
            tile?.render(batch)
        }
    }


    fun evaluateCircuit(maxSteps: Int = tiles.size) {
        // Reset all ports to default state
        for (tile in tiles) {
            if (tile != null) {
                for (port in tile.ports) {
                    port.state = PortState.OFF
                }
                tile.updatePorts()
            }
        }

        var steps = 0
        //
        for (i in 0 until tiles.size) { // DEBUG; replace step with
            for (tile in this) {
                for (port in tile.ports) {
                    if (port.connectedPort == null)
                        continue

                    // If this port is OFF and the connected port is OUT, then set this to IN
                    if (port.state == PortState.OFF && port.connectedPort!!.state == PortState.OUT)
                        port.state = PortState.IN

                    // If this port is IN and the connected port is OFF, then set this to OFF
                    else if (port.state == PortState.IN && port.connectedPort!!.state == PortState.OFF)
                        port.state = PortState.OFF
                }

                tile.updatePorts()

                if ( ++steps >= maxSteps)
                    return
            }
        }
    }

    operator fun get(x: Int, y: Int): Tile? = tiles[x,y]

    operator fun set(x: Int, y: Int, value: Tile) {
        value.position = Vector2(x.toFloat(), y.toFloat()).scl(Tile.tileSize)
        tiles[x,y] = value
        updateConnections(x,y)
    }

    private fun updateConnections(x: Int, y: Int) {
        val center = Pair(x,y)
        connectTiles(center, Pair(x, y + 1), Direction.NORTH)
        connectTiles(center, Pair(x + 1, y), Direction.EAST)
        connectTiles(center, Pair(x, y - 1), Direction.SOUTH)
        connectTiles(center, Pair(x - 1, y), Direction.WEST)
    }

    private fun connectTiles(from: Pair<Int, Int>, to: Pair<Int, Int>, direction: Direction) {
        if (from in tiles && tiles[from] != null && tiles[to] != null) {
            val fromTile = tiles[from]!!
            val toTile = tiles[to]!!

            val fromPort = fromTile.ports.singleOrNull {it.direction + fromTile.direction == direction}
            val toPort = toTile.ports.singleOrNull {it.direction + toTile.direction == -direction}

            if (fromPort != null && toPort != null) {
                fromPort.connectedPort = toPort
                toPort.connectedPort = fromPort
            }
        }
    }


    override operator fun iterator() = TileGridIterator(this)

}