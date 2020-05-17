package com.ragusa.game.level

import com.ragusa.game.tiles.Direction
import com.ragusa.game.tiles.TileGrid
import com.ragusa.game.player.Robot
import com.ragusa.game.tiles.*

class LevelParserException(message: String) : Exception(message)

// Recursive descent parser for the level language
class RDLevelParser {

    var tileGrid: TileGrid? = null
    var name = "No name"
    var author = "Unknown"
    var robot: Robot? = null

    private val tokenizer = Tokenizer()


    fun parse(str: String): Level {
        tokenizer.inputString = str
        robot = Robot()
        tileGrid = TileGrid(robot!!)
        name = "No name"
        author = "Unknown"

        level()
        return Level(name, author, tileGrid!!, robot!!)
    }

    private class Tokenizer {
        private var peek: String? = null

        var inputString = ""
        private var index = 0

        private var nameMode = false

        fun advance(): String {
            val ret = peek()
            peek = next()
            return ret
        }

        private fun next(): String {
            if (index in inputString.indices) {
                index--
                do {
                    val next = inputString[++index]
                } while (!(next != '\r' && next != '\n' && next != ' ' && next != '\t') && index in inputString.indices)
            }


            var buffer = ""
            while (index in inputString.indices) {
                val next = inputString[index]
                if (nameMode) {
                    if (next != '\n') {
                        if (next != '\r' || next != '\t') {
                            buffer += next
                            index++
                        }
                    } else {
                        nameMode = false
                        break
                    }
                } else {
                    index++
                    if (next != '\r' && next != '\n' && next != ' ' && next != '\t') {
                        buffer += next
                    } else {
                        if (buffer == "name" || buffer == "author")
                            nameMode = true
                        break
                    }
                }
            }
            peek = buffer
            return buffer
        }

        fun peek(): String {
            if (peek == null)
                peek = next()
            return peek!!
        }
    }

    private fun level() {
        levelinfo()
        seperation()
        robot()
        seperation()
        mapdata()
    }

    private fun levelinfo() {
        match("name")
        name = tokenizer.advance()
        seperation()
        match("author")
        author = tokenizer.advance()
    }


    private fun robot() {
        match("robot")
        robot!!.position.x = tokenizer.advance().toInt().toFloat()
        robot!!.position.y = tokenizer.advance().toInt().toFloat()

    }

    private fun mapdata() {
        match("tiles")
        seperation()
        mapentries()
    }

    private fun mapentries() {
        if (tokenizer.peek() != "") {
            mapentry()
            seperation()
            mapentries()
        }
    }

    private fun seperation() {/*
        val s = Tokenizer.advance()
        if (s.toCharArray().any {it != '\n' && it != '\r'})
            throw LevelParserException("Expected new line but got ${s.replace("(\n|\r)".toRegex(), "")}")*/
    }

    private fun mapentry() {
        val x = tokenizer.advance().toInt()
        val y = tokenizer.advance().toInt()
        val tile = getTile(tokenizer.advance())
        tile.direction = getDirection()
        tile.isInsulated = choose("insulated", "exposed")
        tile.isLocked = choose("locked", "unlocked")

        tileGrid!![x, y] = tile
    }

    private fun getTile(tileType: String): Tile {
        val index = tileNames.indexOf(tileType)

        if (index == -1)
            throw LevelParserException("Expected ${tileNames.joinToString(", ")} but found $tileType")

        return allTiles[index].java.getDeclaredConstructor().newInstance()
    }

    private fun getDirection(): Direction {
        val s = tokenizer.advance()
        return when(s) {
            "north" -> Direction.NORTH
            "east" -> Direction.EAST
            "south" -> Direction.SOUTH
            "west" -> Direction.WEST
            else -> throw LevelParserException("Expected north, east, south or west, but found $s")
        }
    }

    private fun match(expected: String) {
        val s = tokenizer.advance()
        if (s != expected) {
            throw LevelParserException("Excepted $expected but found $s")
        }
    }


    private fun choose(trueCase: String, falseCase: String): Boolean {
        val input = tokenizer.advance()
        if (input == trueCase) {
            return true
        } else if (input == falseCase) {
            return false
        }
        throw LevelParserException("Expected '$trueCase' or '$falseCase' but got $input")
    }
}