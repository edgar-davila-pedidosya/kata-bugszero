package com.games.uglytrivia

class Game {
    var players = mutableListOf<String>()
    var places = mutableMapOf<String, Int>()
    var purses =  mutableMapOf<String, Int>()
    var inPenaltyBox =  mutableMapOf<String, Boolean>()

    var popQuestions = mutableListOf<String>()
    var scienceQuestions = mutableListOf<String>()
    var sportsQuestions = mutableListOf<String>()
    var rockQuestions = mutableListOf<String>()

    var currentPlayer : CurrentPlayer? = null
    var isGettingOutOfPenaltyBox: Boolean = false

    val isPlayable: Boolean
        get() = howManyPlayers() >= 2

    init {
        for (i in 0..49) {
            popQuestions.addLast("Pop Question " + i)
            scienceQuestions.addLast("Science Question " + i)
            sportsQuestions.addLast("Sports Question " + i)
            rockQuestions.addLast(createRockQuestion(i))
        }
    }

    fun createRockQuestion(index: Int): String {
        return "Rock Question " + index
    }

    fun add(playerName: String): Boolean {


        players.add(playerName)
        places[playerName] = 0
        purses[playerName] = 0
        inPenaltyBox[playerName] = false
        if (howManyPlayers() == 1) {
            currentPlayer = CurrentPlayer(playerName, 0)
        }

        println("$playerName was added")
        println("They are player number " + players.size)
        return true
    }

    fun howManyPlayers() = players.size

    fun roll(roll: Int) {
        if (!isPlayable) throw  NotEnoughPlayersException()

        println(currentPlayer!!.name + " is the current player")
        println("They have rolled a " + roll)

        if (inPenaltyBox[currentPlayer!!.name]!!) {
            if (roll % 2 != 0) {
                isGettingOutOfPenaltyBox = true

                println(currentPlayer!!.name + " is getting out of the penalty box")
                movePlayerAndAskQuestion(roll)
            } else {
                println(currentPlayer!!.name + " is not getting out of the penalty box")
                isGettingOutOfPenaltyBox = false
            }

        } else {

            movePlayerAndAskQuestion(roll)
        }

    }

    private fun movePlayerAndAskQuestion(roll: Int) {
        places[currentPlayer!!.name] = places[currentPlayer!!.name]!! + roll

        if (places[currentPlayer!!.name]!! > 11) places[currentPlayer!!.name] = places[currentPlayer!!.name]!! - 12

        println(currentPlayer!!.name
                + "'s new location is "
                + places[currentPlayer!!.name])
        println("The category is " + currentCategory())
        askQuestion()
    }

    private fun askQuestion() {
        if (currentCategory() === "Pop")
            println(popQuestions.removeFirst())
        if (currentCategory() === "Science")
            println(scienceQuestions.removeFirst())
        if (currentCategory() === "Sports")
            println(sportsQuestions.removeFirst())
        if (currentCategory() === "Rock")
            println(rockQuestions.removeFirst())
    }


    private fun currentCategory(): String {
        if (places[currentPlayer!!.name] == 0) return "Pop"
        if (places[currentPlayer!!.name] == 4) return "Pop"
        if (places[currentPlayer!!.name] == 8) return "Pop"
        if (places[currentPlayer!!.name] == 1) return "Science"
        if (places[currentPlayer!!.name] == 5) return "Science"
        if (places[currentPlayer!!.name] == 9) return "Science"
        if (places[currentPlayer!!.name] == 2) return "Sports"
        if (places[currentPlayer!!.name] == 6) return "Sports"
        return if (places[currentPlayer!!.name] == 10) "Sports" else "Rock"
    }

    fun wasCorrectlyAnswered(): Boolean {
        if (inPenaltyBox[currentPlayer!!.name]!!) {
            if (isGettingOutOfPenaltyBox) {
                println("Answer was correct!!!!")
                currentPlayer = currentPlayer!!.next(players)
                purses[currentPlayer!!.name] = purses[currentPlayer!!.name]!! + 1
                println(currentPlayer!!.name
                        + " now has "
                        + purses[currentPlayer!!.name]
                        + " Gold Coins.")

                return didPlayerWin()
            } else {
                currentPlayer = currentPlayer!!.next(players)
                return true
            }


        } else {

            println("Answer was corrent!!!!")
            purses[currentPlayer!!.name] = purses[currentPlayer!!.name]!! + 1
            println(currentPlayer!!.name
                    + " now has "
                    + purses[currentPlayer!!.name]
                    + " Gold Coins.")

            val winner = didPlayerWin()
            currentPlayer = currentPlayer!!.next(players)

            return winner
        }
    }

    fun wrongAnswer(): Boolean {
        println("Question was incorrectly answered")
        println(currentPlayer!!.name + " was sent to the penalty box")
        inPenaltyBox[currentPlayer!!.name] = true

        currentPlayer = currentPlayer!!.next(players)
        return true
    }


    private fun didPlayerWin(): Boolean {
        return purses[currentPlayer!!.name] != 6
    }
}

fun MutableList<String>.removeFirst(): String {
    return this.removeAt(0)
}
fun MutableList<String>.addLast(element: String) {
    this.add(element)
}