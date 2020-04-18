package com.ragusa.game.level

import com.ragusa.game.tiles.allTiles
import com.ragusa.game.tiles.tileNames

class LevelExporter {

    fun export(level: Level): String {

        var output = """
            name ${level.name}
            author ${level.author}
            
            robot ${level.robot.position.x.toInt()} ${level.robot.position.y.toInt()}
            
            tiles
            
        """.trimIndent()

        for (entry in level.tileGrid.getInternalTiles()) {
            val i = allTiles.indexOfFirst { it == entry.value::class }
            val strName = tileNames[i]

            val ins = if (entry.value.isInsulated) "insulated" else "exposed"
            val loc = if (entry.value.isLocked) "locked" else "unlocked"
            val dir = entry.value.direction.name.toLowerCase()

            output += """
                ${entry.key.first} ${entry.key.second} $strName $dir $ins $loc
                
            """.trimIndent()
        }

        return output
    }
}