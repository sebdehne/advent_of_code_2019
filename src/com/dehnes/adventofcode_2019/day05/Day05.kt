package com.dehnes.adventofcode_2019.day05

import com.dehnes.adventofcode_2019.inputText

val originalCode = inputText(5).split(",").map { it.toInt() }

fun runCode(sq: Sequence<Int>) {
    val code = originalCode.toMutableList()
    var index = 0
    while (true) {
        val opcode = code[index].toString().padStart(5, '0')
        val op = opcode.substring(3).toInt()
        val modes = opcode.substring(0, 3).reversed().map { it == '1' }
        val addr = { n: Int ->
            if (modes[n - 1]) index + n else code[index + n]
        }

        when (op) {
            1 -> {
                code[addr(3)] = code[addr(1)] + code[addr(2)]
                index += 4
            }
            2 -> {
                code[addr(3)] = code[addr(1)] * code[addr(2)]
                index += 4
            }
            3 -> {
                code[addr(1)] = sq.take(1).first()
                index += 2
            }
            4 -> {
                println(code[addr(1)])
                index += 2
            }
            5 -> {
                if (code[addr(1)] != 0) {
                    index = code[addr(2)]
                } else {
                    index += 3
                }
            }
            6 -> {
                if (code[addr(1)] == 0) {
                    index = code[addr(2)]
                } else {
                    index += 3
                }
            }
            7 -> {
                if (code[addr(1)] < code[addr(2)]) {
                    code[addr(3)] = 1
                } else {
                    code[addr(3)] = 0
                }
                index += 4
            }
            8 -> {
                if (code[addr(1)] == code[addr(2)]) {
                    code[addr(3)] = 1
                } else {
                    code[addr(3)] = 0
                }
                index += 4
            }
            99 -> return
            else -> error("?: $op")
        }
    }
}


fun main() {
    runCode(sequence { yield(1) }) // 5074395
    runCode(sequence { yield(5) }) // 8346937
}