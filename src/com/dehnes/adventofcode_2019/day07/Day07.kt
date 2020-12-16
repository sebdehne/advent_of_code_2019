package com.dehnes.adventofcode_2019.day07

import com.dehnes.adventofcode_2019.*
import java.util.*

val originalCode = inputText(7).split(",").map { it.toInt() }

fun main() {
    var outputSignal = 0
    combinations(listOf(0, 1, 2, 3, 4), emptyList()) {
        var signal = 0
        it.forEach { phase ->
            val l = LinkedList(listOf(phase, signal))
            intcodeComputer(originalCode, { l.removeFirst() }) { signal = it }
        }
        if (signal > outputSignal) {
            outputSignal = signal
        }
    }
    println(outputSignal) // 844468

    var outputSignal2 = 0
    combinations(listOf(5, 6, 7, 8, 9), emptyList()) { phasees ->
        var signal = 0

        // calc output for phases
        var done = false
        val states = mutableMapOf<Int, State>()
        while (!done) {
            for (p in phasees) {
                val result = if (states[p] == null) {
                    val s = State(originalCode.toMutableList(), 0)
                    states[p] = s
                    intcodeComputer_(s, p)
                } else {
                    intcodeComputer_(states[p]!!, signal)
                }

                when (result) {
                    is Terminated -> done = true
                    is Output -> signal = result.value
                }
            }
        }

        if (signal > outputSignal2) {
            outputSignal2 = signal
        }

    }
    println(outputSignal2) // 4215746

}

fun combinations(input: List<Int>, current: List<Int>, fn: (List<Int>) -> Unit) {
    if (input.isNotEmpty()) {
        input.forEach { i ->
            combinations(input.filterNot { it == i }, current + i, fn)
        }
    } else {
        fn(current)
    }
}