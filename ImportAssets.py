import os
from pathlib import Path

out = "package com.ragusa.game\n\nimport com.badlogic.gdx.graphics.Texture\nimport com.badlogic.gdx.assets.AssetDescriptor\nimport com.badlogic.gdx.assets.AssetManager\nimport com.ragusa.game.level.Level\n\nobject Assets {\n"
load = "\n    val manager = AssetManager()\n    fun LoadAll() {\n"

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
            nameWOExtension = os.path.splitext(de)[0]
            out += "val " + nameWOExtension + " = AssetDescriptor(\"" + (outPath / de).as_posix() + "\", " + className + "::class.java)\n"
            load += "manager.load(" + outPath.as_posix().replace("/", ".") + "." + nameWOExtension + ")\n"



def LoadDir(className, dirName):
    global out
    out += "object " + dirName + " {\n"
    LoadDirUtil(root / dirName, className, Path(dirName))
    out += "}\n"



LoadDir("Texture", "textures")
LoadDir("Level", "levels")


out += "\n\n" + load + "\n    }\n}"

f = open(Path(os.getcwd()) / "core/src/com/ragusa/game/Assets.kt", "w")
f.write(out)
f.close()

    
