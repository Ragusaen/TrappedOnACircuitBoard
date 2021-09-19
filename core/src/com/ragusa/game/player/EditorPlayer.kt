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
import com.ragusa.game.controls
import com.ragusa.game.level.Level
import com.ragusa.game.player.actions.*
import com.ragusa.game.tiles.*
import com.ragusa.game.tiles.finals.*
import com.ragusa.game.utility.minus
import com.ragusa.game.utility.plus
import com.ragusa.game.utility.setPosition
import com.ragusa.game.utility.times

class EditorPlayer(level: Level) : AbstractPlayer(level) {

    override fun turnMove(dir: Direction) {
        if (Gdx.input.isKeyPressed(controls.Turn))
            level.robot.direction = dir
        else
            level.robot.position += UndoableAction.dirToVec[dir]!!
    }

    private val handFrameOff = initHandSprite(Assets.textures.UI.hand_frame_off)
    private val handFrameOn = initHandSprite(Assets.textures.UI.hand_frame_on)

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

    override fun userInput() {
        super.userInput()

        // Place
        if (Gdx.input.isKeyJustPressed(controls.PickUpPlace)) {
            val newTile = allTiles[currentTileIndex].java.getDeclaredConstructor().newInstance()
            newTile.isLocked = locking
            newTile.isInsulated = insulating
            newTile.direction = direction
            actions.doAction(EditPlace(newTile, level.robot))
        }

        // Delete
        if (Gdx.input.isKeyJustPressed(controls.Remove)) {
            actions.doAction(EditRemove(level.robot))
        }

        // Rotate hand
        if (Gdx.input.isKeyJustPressed(controls.Rotate)) {
            direction += Direction.EAST
            for (t in handTiles)
                t.direction = direction
        }

        // Lock
        if (Gdx.input.isKeyJustPressed(controls.Lock)) {
            locking = !locking
            for (t in handTiles)
                t.isLocked = locking
        }

        // Insulate
        if (Gdx.input.isKeyJustPressed(controls.Insulate)) {
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

        // Select the next item
        if (Gdx.input.isKeyJustPressed(controls.NextItem))
            currentTileIndex = (currentTileIndex + 1) % allTiles.size

    }

    override fun render(batch: SpriteBatch, relativeTo: Vector2) {
        val offSet = Vector2(100f, 0f)
        val startPos = Vector2(Gdx.graphics.width / 2 - offSet.x * (allTiles.size / 2), TileAble.tileSize / 4f)
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