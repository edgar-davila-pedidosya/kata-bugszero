package com.games.uglytrivia

import org.junit.Test
import kotlin.test.assertEquals

class AddPlayerTest {

    @Test
    fun addPlayerSetsGameAttributesAsExpected() {
        val playerName = "Pat"
        val game = Game()
        game.add(playerName)

        assert(game.players.contains(playerName))
        assert(game.places["Pat"] == 0)
        assert(game.purses["Pat"] == 0)
        assert(!game.inPenaltyBox["Pat"]!!)
        assertEquals(CurrentPlayer("Pat", 0), game.currentPlayer)
    }

    @Test
    fun dontHaveALimitForPlayers() {
        val game = Game()
        for (i in 0..9) {
            game.add("$i")
        }
        assert(game.players.size == 10)
    }

}