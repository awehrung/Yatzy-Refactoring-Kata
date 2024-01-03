class Yatzy(d1: Int, d2: Int, d3: Int, d4: Int, d5: Int) {

    private val dice: IntArray = intArrayOf(d1, d2, d3, d4, d5)

    fun ones() = dice.filter { it == 1 }.sum()

    fun twos() = dice.filter { it == 2 }.sum()

    fun threes() = dice.filter { it == 3 }.sum()

    fun fours() = dice.filter { it == 4 }.sum()

    fun fives() = dice.filter { it == 5 }.sum()

    fun sixes() = dice.filter { it == 6 }.sum()

    fun chance() = dice.sum()

    fun yatzy(): Int {
        val allSame = dice.groupBy { it }
            .filter { it.value.size == 5 }

        return if (allSame.isEmpty()) 0 else 50
    }

    fun onePair(): Int {
        val pairs = dice.groupBy { it }
            .filter { it.value.size >= 2 }
            .toSortedMap(Comparator.reverseOrder())

        return pairs.entries.take(1).sumOf { it.key * 2 }
    }

    fun twoPair(): Int {
        val pairs = dice.groupBy { it }
            .filter { it.value.size >= 2 }
            .toSortedMap(Comparator.reverseOrder())

        if (pairs.size < 2) {
            return 0
        }

        return pairs.entries.take(2).sumOf { it.key * 2 }
    }

    fun threeOfAKind(): Int {
        val threeOfAKinds = dice.groupBy { it }
            .filter { it.value.size >= 3 }

        return threeOfAKinds.entries.take(1).sumOf { it.key * 3 }
    }

    fun fourOfAKind(): Int {
        val fourOfAKinds = dice.groupBy { it }
            .filter { it.value.size >= 4 }

        return fourOfAKinds.entries.take(1).sumOf { it.key * 4 }
    }

    fun smallStraight(): Int {
        val singleDiceCount = dice.groupBy { it }
            .filter { it.value.size == 1 }
            .size

        return if (singleDiceCount == 5 && dice.max() == 5) 15 else 0
    }

    fun largeStraight(): Int {
        val singleDiceCount = dice.groupBy { it }
            .filter { it.value.size == 1 }
            .size

        return if (singleDiceCount == 5 && dice.min() == 2) 20 else 0
    }

    fun fullHouse(): Int {
        val threeOfAKinds = dice.groupBy { it }
            .filter { it.value.size >= 3 }
            .toMap()

        if (threeOfAKinds.isEmpty()) {
            return 0
        }

        val pairs = dice.filter { !threeOfAKinds.keys.contains(it) }
            .groupBy { it }
            .filter { it.value.size >= 2 }
            .toMap()

        if (pairs.isEmpty()) {
            return 0
        }

        return threeOfAKinds.entries.take(1).sumOf { it.key * 3 } + pairs.entries.take(1).sumOf { it.key * 2 }
    }

    fun evaluate(): Map<String, Int> {
        return mapOf(
            Pair("Ones", this.ones()),
            Pair("Twos", this.twos()),
            Pair("Threes", this.threes()),
            Pair("Fours", this.fours()),
            Pair("Fives", this.fives()),
            Pair("Sixes", this.sixes()),
            Pair("Yatzy", this.yatzy()),
            Pair("Chance", this.chance()),
            Pair("One pair", this.onePair()),
            Pair("Two pair", this.twoPair()),
            Pair("Three of a kind", this.threeOfAKind()),
            Pair("Four of a kind", this.fourOfAKind()),
            Pair("Small straight", this.smallStraight()),
            Pair("Large straight", this.largeStraight()),
            Pair("Full house", this.fullHouse()),
        )
    }

    override fun toString(): String {
        return dice.joinToString(separator = "/")
    }
}
