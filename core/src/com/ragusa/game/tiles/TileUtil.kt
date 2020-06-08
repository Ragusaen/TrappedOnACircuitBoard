package com.ragusa.game.tiles

import com.badlogic.gdx.assets.AssetDescriptor
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Vector2
import com.ragusa.game.Assets
import com.ragusa.game.tiles.finals.*
import java.lang.reflect.Modifier
import kotlin.reflect.full.createInstance
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.javaField

fun initSprite(sprite: Sprite, size: Vector2 = Vector2(TileAble.tileSize, TileAble.tileSize)): Sprite {
    sprite.setSize(size.x, size.y)
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
        TileDiode::class,
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
        "diode",
        "inverter",
        "and",
        "or",
        "xor",
        "goal"
)