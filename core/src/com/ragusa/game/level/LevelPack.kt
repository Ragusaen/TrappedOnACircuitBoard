package com.ragusa.game.level

import com.badlogic.gdx.assets.AssetDescriptor
import com.badlogic.gdx.assets.AssetLoaderParameters
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader
import com.badlogic.gdx.assets.loaders.FileHandleResolver
import com.badlogic.gdx.files.FileHandle
import com.badlogic.gdx.utils.Array

class LevelPack(val name: String, val levels: List<String>) {

}

class LevelPackLoader(resolver: FileHandleResolver) : AsynchronousAssetLoader<LevelPack, LevelPackLoader.LevelPackParameters>(resolver) {
    class LevelPackParameters: AssetLoaderParameters<LevelPack>()

    var levelPack: LevelPack? = null

    override fun getDependencies(fileName: String?, file: FileHandle?, parameter: LevelPackParameters?): Array<AssetDescriptor<Any>>? {
        return null
    }

    override fun loadAsync(manager: AssetManager?, fileName: String?, file: FileHandle?, parameter: LevelPackParameters?
    ) {
        val contents = file!!.readString().replace("\r", "").split('\n')
        levelPack = LevelPack(contents.first(), contents.drop(1))
    }

    override fun loadSync(manager: AssetManager?, fileName: String?, file: FileHandle?, parameter: LevelPackParameters?): LevelPack {
        val l = levelPack!!
        levelPack = null
        return l
    }
}