package com.ragusa.game

enum class Direction {
    NORTH, EAST, SOUTH, WEST;

    operator fun plus(other: Direction): Direction {
        val newOrdinal = ((this.ordinal + other.ordinal) % Direction.values().size)
        return Direction.values()[newOrdinal]
    }

    operator fun unaryMinus(): Direction {
        val newOrdinal = (this.ordinal + 2) % 4
        return Direction.values()[newOrdinal]
    }
}

