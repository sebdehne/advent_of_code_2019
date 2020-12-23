package com.dehnes.adventofcode_2019.day09

import com.dehnes.adventofcode_2019.inputText
import com.dehnes.adventofcode_2019.intcodeComputer

val originalCode = inputText(9).split(",").map { it.toLong() }

fun main() {
    intcodeComputer(originalCode, { 1 }, ::println) // 2789104029
    intcodeComputer(originalCode, { 2 }, ::println) // 32869
}