import os
from pathlib import Path

out = "package com.ragusa.game\n\nimport com.badlogic.gdx.graphics.Texture\nimport com.badlogic.gdx.assets.AssetDescriptor\nimport com.badlogic.gdx.assets.AssetManager\nimport com.ragusa.game.level.Level\nimport com.badlogic.gdx.graphics.g2d.BitmapFont\n\nobject Assets {\n"
load = "\n    val manager = AssetManager()\n    fun loadAll() {\n"

root = Path(os.getcwd()) / "core" / "assets"


def LoadDirUtil(path, className, outPath):
    global out
    global load
    dirElements = os.listdir(path)
    for de in dirElements:
        if os.path.isdir(path / de):
            out += "object " + de + "{\n"
            LoadDirUtil(path / de, className, outPath / de)
            out += "}\n"
        else:
            nameWOExt = os.path.splitext(de)[0]
            name = de.replace(".", "_") if any(os.path.splitext(e)[0] == nameWOExt and e != de for e in dirElements) else nameWOExt
            out += "val " + name + " = AssetDescriptor(\"" + (outPath / de).as_posix() + "\", " + className + "::class.java)\n"
            load += "manager.load(" + outPath.as_posix().replace("/", ".") + "." + name + ")\n"



def LoadDir(className, dirName):
    global out
    out += "object " + dirName + " {\n"
    LoadDirUtil(root / dirName, className, Path(dirName))
    out += "}\n"



LoadDir("Texture", "textures")
LoadDir("Level", "levels")
LoadDir("BitmapFont", "fonts")


out += "\n\n" + load + "\n    }\n}"

f = open(Path(os.getcwd()) / "core/src/com/ragusa/game/Assets.kt", "w")
f.write(out)
f.close()

    
