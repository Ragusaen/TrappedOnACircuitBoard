package com.ragusa.game

import com.badlogic.gdx.assets.AssetDescriptor
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.ragusa.game.tiles.Tile

fun initSprite(sprite: Sprite): Sprite {
    sprite.setSize(Tile.tileSize, Tile.tileSize)
    sprite.setOrigin(Tile.tileSize / 2, Tile.tileSize / 2)
    return sprite
}

fun initTextureStates(map: Map<Int, AssetDescriptor<Texture>>): Map<Int, Texture> =
        map.entries.map { Pair(it.key, Assets.manager.get(it.value)) }.toMap()