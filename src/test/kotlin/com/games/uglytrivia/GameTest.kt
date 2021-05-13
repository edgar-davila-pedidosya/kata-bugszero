package com.games.uglytrivia

import com.trivia.runner.GameRunner
import org.junit.Assert
import org.junit.Test
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.PrintStream
import java.nio.file.Paths
import java.util.*
import java.util.stream.IntStream

class GameTest {

    @Test
    fun itsLockedDown() {

        val randomizer = Random(123455)
        val resultStream = ByteArrayOutputStream()
        System.setOut(PrintStream(resultStream))

        IntStream.range(1, 15).forEach { GameRunner.playGame(randomizer) }

        val verified = File(Paths.get("").toAbsolutePath().toString()
                + "/GameTest.itsLockedDown.approved.txt").readText(Charsets.UTF_8)
        Assert.assertEquals(verified, resultStream.toString())
    }
}