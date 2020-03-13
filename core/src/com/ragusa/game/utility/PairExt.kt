package com.ragusa.game.utility

import com.badlogic.gdx.math.Vector2

fun Pair<Int, Int>.toVector(): Vector2 {
    return Vector2(first.toFloat(), second.toFloat())
}