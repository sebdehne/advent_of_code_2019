package com.dehnes.adventofcode_2019

import org.junit.jupiter.api.Test

class Day05 {

    val originalCode = inputText(5).split(",").map { it.toLong() }

    @Test
    fun main() {
        intcodeComputer(originalCode, { 1 }, ::println) // 5074395
        intcodeComputer(originalCode, { 5 }, ::println) // 8346937
    }
}