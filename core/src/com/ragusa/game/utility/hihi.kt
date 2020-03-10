package com.ragusa.game.utility

import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Vector2

fun Sprite.setPosition(vector: Vector2) {
    this.setPosition(vector.x, vector.y)
}

operator fun Vector2.plus(other: Vector2): Vector2 {
    return Vector2(this.x + other.x, this.y + other.y)
}



operator fun Vector2.minus(other: Vector2): Vector2 {
    return Vector2(this.x - other.x, this.y - other.y)
}

operator fun Vector2.times(other: Float): Vector2 {
    return Vector2(this.x * other, this.y * other)
}