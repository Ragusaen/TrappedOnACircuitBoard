package com.ragusa.game

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.ragusa.game.player.Robot
import com.ragusa.game.tiles.*
import com.ragusa.game.utility.*

class TileGrid(val robot: Robot) : IRenderable, Iterable<Tile> {

    class TileGridIterator(tileGrid: TileGrid) : Iterator<Tile> {
        private val tilesIterator = tileGrid.tiles.entries.map { it.value }.iterator()
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

    class WiredTileIterator(tileGrid: TileGrid) : Iterator<WiredTile> {
        private val tilesIterator = tileGrid.tiles.entries.map { it.value }.iterator()
        private var next: WiredTile? = null

        override fun hasNext(): Boolean {
            next = null
            while (tilesIterator.hasNext()) {
                val nextTile = tilesIterator.next()
                if (nextTile is WiredTile) {
                    next = nextTile
                    return true
                }
            }
            return false
        }

        override fun next(): WiredTile {
            return next!!
        }

    }

    class GateTileIterator(tileGrid: TileGrid) : Iterator<GateTile> {
        private val tilesIterator = tileGrid.tiles.entries.map { it.value }.iterator()
        private var next: GateTile? = null

        override fun hasNext(): Boolean {
            next = null
            while (tilesIterator.hasNext()) {
                val nextTile = tilesIterator.next()
                if (nextTile is GateTile) {
                    next = nextTile
                    return true
                }
            }
            return false
        }

        override fun next(): GateTile {
            return next!!
        }

    }

    private val tiles: MutableMap<Pair<Int, Int>, Tile> = mutableMapOf()

    override fun render(batch: SpriteBatch, relativeTo: Vector2) {
        for ((pos, tile) in tiles) {
            tile.render(batch, relativeTo + pos.toVector() * TileAble.tileSize)
        }
        robot.render(batch, relativeTo)
    }


    fun evaluateCircuit() {

        for (tile in WiredTileIterator(this))
            tile.updateInternalState()

        for (i in 0 until tiles.size) { // DEBUG; replace step with
            for (tile in WiredTileIterator(this)) {
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

                // Instantly update internal states of non gate tiles
                if (tile !is GateTile)
                    tile.updateInternalState()
            }
        }

        val robotTile = this[robot.position]
        robot.isOn = robotTile is WiredTile && robotTile.ports.any {it.isOn()}
    }

    operator fun get(x: Int, y: Int): Tile? = tiles[Pair(x,y)]
    operator fun get(v: Vector2): Tile? = get(v.x.toInt(), v.y.toInt())

    operator fun set(x: Int, y: Int, value: Tile) {
        tiles[Pair(x,y)] = value
        updateConnections(x,y)
    }
    operator fun set(v: Vector2, value: Tile) = set(v.x.toInt(), v.y.toInt(), value)

    fun removeAt(x: Int, y: Int) = tiles.remove(Pair(x,y))
    fun removeAt(v: Vector2) = removeAt(v.x.toInt(), v.y.toInt())

    private fun updateConnections(x: Int, y: Int) {
        val center = Pair(x,y)
        connectTiles(center, Pair(x, y + 1), Direction.NORTH)
        connectTiles(center, Pair(x + 1, y), Direction.EAST)
        connectTiles(center, Pair(x, y - 1), Direction.SOUTH)
        connectTiles(center, Pair(x - 1, y), Direction.WEST)
    }

    private fun connectTiles(from: Pair<Int, Int>, to: Pair<Int, Int>, direction: Direction) {
        // If from and to coordinates are within bounds of tiles
        if (isWiredTile(from) && isWiredTile(to)) {
            val fromTile = tiles[from] as WiredTile
            val toTile = tiles[to] as WiredTile

            // Find the connected ports, return if they don't exist
            val fromPort = fromTile.ports.singleOrNull {it.direction + fromTile.direction == direction} ?: return
            val toPort = toTile.ports.singleOrNull {it.direction + toTile.direction == -direction} ?: return

            fromPort.connectedPort = toPort
            toPort.connectedPort = fromPort
        }
    }

    private fun isWiredTile(c: Pair<Int, Int>): Boolean = c in tiles && tiles[c] is WiredTile

    override operator fun iterator() = TileGridIterator(this)

}