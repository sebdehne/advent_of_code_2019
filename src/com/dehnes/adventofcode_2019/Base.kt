package com.dehnes.adventofcode_2019

import java.io.File

fun inputText(day: Int) = File(fileName(day)).readText()
fun inputLines(day: Int) = File(fileName(day)).readLines()

private fun fileName(day: Int) = "resources/Day" + day.toString().padStart(2, '0') + ".txt"

