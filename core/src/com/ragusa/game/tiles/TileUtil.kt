package com.ragusa.game.tiles

import com.badlogic.gdx.assets.AssetDescriptor
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.ragusa.game.Assets
import com.ragusa.game.tiles.finals.*

fun initSprite(sprite: Sprite): Sprite {
    sprite.setSize(TileAble.tileSize, TileAble.tileSize)
    sprite.setOrigin(TileAble.tileSize / 2, TileAble.tileSize / 2)
    return sprite
}

fun initTextureStates(map: Map<Int, AssetDescriptor<Texture>>): Map<Int, Texture> =
        map.entries.map { Pair(it.key, Assets.manager.get(it.value)) }.toMap()


val allTiles = listOf(
        Tile::class,
        TileSource::class,
        TileStraight::class,
        TileBend::class,
        TileSplit::class,
        TilePlus::class,
        TileInverter::class,
        TileAnd::class,
        TileOr::class,
        TileXor::class,
        TileGoal::class
)

// Must be in same order as allTiles
val tileNames = listOf(
        "plain",
        "source",
        "straight",
        "bend",
        "split",
        "plus",
        "inverter",
        "and",
        "or",
        "xor",
        "goal"
)