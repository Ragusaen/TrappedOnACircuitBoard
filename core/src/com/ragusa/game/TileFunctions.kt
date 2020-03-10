package com.ragusa.game

import com.badlogic.gdx.assets.AssetDescriptor
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.ragusa.game.tiles.Tile
import com.ragusa.game.tiles.TileAble

fun initSprite(sprite: Sprite): Sprite {
    sprite.setSize(TileAble.tileSize, TileAble.tileSize)
    sprite.setOrigin(TileAble.tileSize / 2, TileAble.tileSize / 2)
    return sprite
}

fun initTextureStates(map: Map<Int, AssetDescriptor<Texture>>): Map<Int, Texture> =
        map.entries.map { Pair(it.key, Assets.manager.get(it.value)) }.toMap()