package com.dehnes.adventofcode_2019.day05

import com.dehnes.adventofcode_2019.inputText
import com.dehnes.adventofcode_2019.intcodeComputer

val originalCode = inputText(5).split(",").map { it.toInt() }

fun main() {
    intcodeComputer(originalCode, { 1 }, ::println) // 5074395
    intcodeComputer(originalCode, { 5 }, ::println) // 8346937
}