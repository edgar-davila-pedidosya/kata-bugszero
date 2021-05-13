package com.games.uglytrivia

import org.junit.Test

class CurrentPLayerTest {

    @Test
    fun currentPlayerCanReturnsTheNextOne() {
        val current = CurrentPlayer("fede", 1)
        val next = current.next(listOf("carla", "fede", "jorge"))
        assert(next.index == 2)
        assert(next.name == "jorge")
    }

    @Test
    fun lastPlayerCanReturnsTheNextOne() {
        val current = CurrentPlayer("jorge", 2)
        val firstPlayerAgain = current.next(listOf("carla", "fede", "jorge"))
        assert(firstPlayerAgain.index == 0)
        assert(firstPlayerAgain.name == "carla")
    }
}