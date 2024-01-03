fun main() {
    val examples = arrayOf(
        Yatzy(1, 3, 5, 3, 5),
        Yatzy(1, 1, 1, 1, 1),
        Yatzy(4, 3, 5, 3, 5),
        Yatzy(2, 1, 2, 2, 1),
    )

    examples.forEach { example ->
        println("\nThrow: $example")
        example.evaluate().forEach { println("${it.key}: ${it.value}") }
    }
}
