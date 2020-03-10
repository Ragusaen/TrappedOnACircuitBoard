package com.ragusa.game

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2

interface IRenderable {
    fun render(batch: SpriteBatch, relativeTo: Vector2)
}