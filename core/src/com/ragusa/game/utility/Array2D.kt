package com.ragusa.game.utility

class Array2D<T>(val width: Int, val height: Int) {

    @Suppress("UNCHECKED_CAST")
    private val items = arrayOfNulls<Any?>(width * height) as Array<T?>

    val size get() = width * height

    operator fun get(x: Int, y: Int): T? {
        if (x < 0 || x >= width || y < 0 || y >= height)
            throw IndexOutOfBoundsException()

        return items[x + y * width]
    }

    operator fun get(pair: Pair<Int, Int>): T? {
        return get(pair.first, pair.second)
    }

    operator fun set(x: Int, y: Int, value: T?) {
        items[x + y * width] = value
    }

    operator fun iterator() = items.iterator()

    operator fun contains(xy: Pair<Int, Int>): Boolean {
        return xy.first >= 0 && xy.second >= 0 && xy.first < width && xy.second < height
    }
}
