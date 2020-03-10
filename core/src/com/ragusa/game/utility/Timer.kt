package com.ragusa.game.utility

class Timer(val duration: Long = 0) {
    var lastStartTime = System.currentTimeMillis()

    fun elapsed(): Long = System.currentTimeMillis() - lastStartTime
    fun elapsedSeconds(): Long = elapsed() / 1000

    fun isOverDue() = elapsed() > duration

    fun restart() {
        lastStartTime = System.currentTimeMillis()
    }
}