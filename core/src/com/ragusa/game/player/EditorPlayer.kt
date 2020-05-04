package com.ragusa.game.player

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.assets.AssetDescriptor
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.ragusa.game.Assets
import com.ragusa.game.IRenderable
import com.ragusa.game.level.Level
import com.ragusa.game.player.actions.*
import com.ragusa.game.tiles.*
import com.ragusa.game.tiles.finals.*
import com.ragusa.game.utility.minus
import com.ragusa.game.utility.plus
import com.ragusa.game.utility.setPosition
import com.ragusa.game.utility.times

class EditorPlayer(private val level: Level) : IRenderable {

    private fun turnMove(dir: Direction) {
        if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT))
            level.robot.direction = dir
        else
            level.robot.position += UndoableAction.dirToVec[dir]!!
    }

    private val handFrameOff = initHandSprite(Assets.UI.hand_frame_off)
    private val handFrameOn = initHandSprite(Assets.UI.hand_frame_on)

    private fun initHandSprite(asset: AssetDescriptor<Texture>): Sprite {
        val sprite = Sprite(Assets.manager.get(asset))
        sprite.setSize(TileAble.tileSize * 1.25f, TileAble.tileSize * 1.25f)
        sprite.setCenter(TileAble.tileSize * 1.25f / 2, TileAble.tileSize * 1.25f / 2)
        return sprite
    }

    private var currentTileIndex = 0
    private val handTiles: List<Tile> = allTiles.map {it.java.getDeclaredConstructor().newInstance()}

    init {
        handTiles.forEach { if (it is WiredTile) it.updateInternalState() }
    }

    private var locking = false
    private var insulating = false
    private var direction = Direction.NORTH

    fun userInput() {
        // Movement
        if (Gdx.input.isKeyJustPressed(Input.Keys.A))
            turnMove(Direction.WEST)
        if (Gdx.input.isKeyJustPressed(Input.Keys.D))
            turnMove(Direction.EAST)
        if (Gdx.input.isKeyJustPressed(Input.Keys.W))
            turnMove(Direction.NORTH)
        if (Gdx.input.isKeyJustPressed(Input.Keys.S))
            turnMove(Direction.SOUTH)


        // Place
        val targetPos = level.robot.position + UndoableAction.dirToVec[level.robot.direction]!!
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            val newTile = allTiles[currentTileIndex].java.getDeclaredConstructor().newInstance()
            newTile.isLocked = locking
            newTile.isInsulated = insulating
            newTile.direction = direction
            level.tileGrid[targetPos] = newTile
        }

        // Delete
        if (Gdx.input.isKeyJustPressed(Input.Keys.Q)) {
            level.tileGrid.removeAt(targetPos)
        }

        // Rotate hand
        if (Gdx.input.isKeyJustPressed(Input.Keys.R)) {
            direction += Direction.EAST
            for (t in handTiles)
                t.direction = direction
        }

        // Lock
        if (Gdx.input.isKeyJustPressed(Input.Keys.L)) {
            locking = !locking
            for (t in handTiles)
                t.isLocked = locking
        }

        // Insulate
        if (Gdx.input.isKeyJustPressed(Input.Keys.I)) {
            insulating = !insulating
            for (t in handTiles)
                t.isInsulated = insulating
        }

        // Pick tile
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1))
            currentTileIndex = 0
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_2))
            currentTileIndex = 1
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_3))
            currentTileIndex = 2
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_4))
            currentTileIndex = 3
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_5))
            currentTileIndex = 4
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_6))
            currentTileIndex = 5
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_7))
            currentTileIndex = 6
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_8))
            currentTileIndex = 7
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_9))
            currentTileIndex = 8
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_0))
            currentTileIndex = 9
        if (Gdx.input.isKeyJustPressed(Input.Keys.TAB))
            currentTileIndex = (currentTileIndex + 1) % allTiles.size

    }

    override fun render(batch: SpriteBatch, relativeTo: Vector2) {
        val offSet = Vector2(100f, 0f)
        val startPos = Vector2(Gdx.graphics.width / 2 - offSet.x * 5, TileAble.tileSize / 4f)
        for (i in handTiles.indices) {
            val drawPos = startPos + offSet * i.toFloat()
            val frameOffset = 0.125f * TileAble.tileSize

            val frame = if (i == currentTileIndex) handFrameOn else handFrameOff

            frame.setPosition(drawPos.x - frameOffset, drawPos.y - frameOffset)
            frame.draw(batch)
            handTiles[i].render(batch, drawPos)
        }
    }
}