package com.games.uglytrivia

import org.junit.Test
import kotlin.test.assertFalse

class PlayableGameTest {

    @Test(expected = NotEnoughPlayersException::class)
    fun failsToStartTheGameIfThereAreLessThanTwoPlayers() {
        val game = Game()
        game.add("Pat")
        assertFalse(game.isPlayable)
        game.roll(1)
    }

    @Test
    fun canBePlayedIfThereAreMoreThanOnePlayer() {
        val game = Game()
        game.add("Pat")
        game.add("Pato")
        assert(game.isPlayable)
        game.roll(1)
    }
}