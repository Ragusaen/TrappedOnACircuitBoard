package com.ragusa.game.level

import com.badlogic.gdx.assets.AssetDescriptor
import com.badlogic.gdx.assets.AssetLoaderParameters
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader
import com.badlogic.gdx.assets.loaders.FileHandleResolver
import com.badlogic.gdx.files.FileHandle
import com.badlogic.gdx.utils.Array

class LevelLoader(resolver: FileHandleResolver) : AsynchronousAssetLoader<Level, LevelLoader.LevelParameter>(resolver) {
    class LevelParameter : AssetLoaderParameters<Level>()

    var level: Level? = null

    override fun loadSync(assetManager: AssetManager?, fileName: String?, file: FileHandle?, param: LevelParameter?): Level {
        val level = this.level!!
        this.level = null
        return level
    }

    override fun loadAsync(assetManager: AssetManager?, fileName: String?, file: FileHandle?, param: LevelParameter?) {
        level = null
        level = RDLevelParser().parse(file!!.readString())
    }

    override fun getDependencies(p0: String?, p1: FileHandle?, p2: LevelParameter?): Array<AssetDescriptor<Any>>? {
        return null
    }
}