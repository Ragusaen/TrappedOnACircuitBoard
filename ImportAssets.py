import os

global out
out = "package com.ragusa.game\n\nimport com.badlogic.gdx.graphics.Texture\nimport com.badlogic.gdx.assets.AssetDescriptor\nimport com.badlogic.gdx.assets.AssetManager\n\n"
load = "\n    val manager = AssetManager()\n    fun LoadAll() {\n"

rootName = "Assets"

def LoadAllUtil(path, rPath, self, indent, pack, classType):
    print( rPath)
    global out
    global load
    global rootName
    contents = os.listdir(path)
    out += indent + "object " + str(self) + " {\n"
    for s in contents:
        nPath = path + "\\\\" + s
        if os.path.isdir(nPath):
            k = rPath + ("\\\\" if self != rootName else "") + s
            np = pack + "." if pack != "" else pack
            LoadAllUtil(nPath, k, s, indent + "    ", np + self if self != rootName else np, classType)
        else:
            out += indent + "    val " + str(os.path.splitext(s)[0]) + " = AssetDescriptor(\"" + rPath + "\\\\" + s + "\", " + classType + "::class.java);\n"
            load += "        manager.load(" + pack + ("." if pack != "" else "") + self + "." + str(os.path.splitext(s)[0]) + ")\n"

    if self != rootName:
        out += indent + "}\n"

def LoadAll(root, rootName, classType, startFolder):
    LoadAllUtil(root + "\\" + startFolder, startFolder + "\\", rootName, "", "", classType)

root = os.getcwd() + "\\core\\assets"

LoadAll(root, rootName, "Texture", "textures\\")

out += "\n\n" + load + "\n    }\n}"

f = open(os.getcwd() + "\\core\\src\\com\\ragusa\\game\\Assets.kt", "w")
f.write(out)
f.close()

    
