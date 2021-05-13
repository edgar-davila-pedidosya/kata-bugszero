package com.games.uglytrivia

data class CurrentPlayer(val name: String, val index: Int) {

    fun next(players: List<String>) : CurrentPlayer {
        val nextIndex = if (index + 1 == players.size) 0 else index + 1
        return CurrentPlayer(players[nextIndex], nextIndex)
    }
}
