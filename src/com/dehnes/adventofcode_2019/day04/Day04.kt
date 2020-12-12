package com.dehnes.adventofcode_2019.day04

fun Int.duplicates() = this.toString().toList().groupBy { it }
fun Int.increasesOnly() = this.toString().let { it.toList().sorted() == it.toList() }

val input = 265275..781584

fun main() {
    // 960
    println("Part1: " + input.count { candidate -> candidate.duplicates().maxOf { it.value.size } > 1 && candidate.increasesOnly() })

    // 626
    println("Part2: " + input.count { candidate -> candidate.duplicates().any { it.value.size == 2 } && candidate.increasesOnly() })
}