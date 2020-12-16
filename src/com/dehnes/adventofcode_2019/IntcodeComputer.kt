package com.dehnes.adventofcode_2019

fun intcodeComputer(inputCode: List<Int>, inputSource: () -> Int, output: (Int) -> Unit) {
    val state = State(
            inputCode.toMutableList(),
            0
    )

    var i: Int? = null
    while (true) {
        val r = intcodeComputer_(state, i)
        i = null
        when (r) {
            is Terminated -> return
            is InputNeeded -> i = inputSource()
            is Output -> output(r.value)
        }
    }
}

class State(val inputCode: MutableList<Int>, var index: Int)

sealed class Result
data class Output(val value: Int) : Result()
object InputNeeded : Result()
object Terminated : Result()

fun intcodeComputer_(state: State, input: Int?): Result {
    val code = state.inputCode
    var usingInput: Int? = input
    while (true) {
        val opcode = code[state.index].toString().padStart(5, '0')
        val op = opcode.substring(3).toInt()
        val modes = opcode.substring(0, 3).reversed().map { it == '1' }
        val addr = { n: Int ->
            if (modes[n - 1]) state.index + n else code[state.index + n]
        }

        when (op) {
            1 -> {
                code[addr(3)] = code[addr(1)] + code[addr(2)]
                state.index += 4
            }
            2 -> {
                code[addr(3)] = code[addr(1)] * code[addr(2)]
                state.index += 4
            }
            3 -> {
                if (usingInput != null) {
                    code[addr(1)] = usingInput
                    usingInput = null
                    state.index += 2
                } else {
                    return InputNeeded
                }
            }
            4 -> {
                val o = code[addr(1)]
                state.index += 2
                return Output(o)
            }
            5 -> {
                if (code[addr(1)] != 0) {
                    state.index = code[addr(2)]
                } else {
                    state.index += 3
                }
            }
            6 -> {
                if (code[addr(1)] == 0) {
                    state.index = code[addr(2)]
                } else {
                    state.index += 3
                }
            }
            7 -> {
                if (code[addr(1)] < code[addr(2)]) {
                    code[addr(3)] = 1
                } else {
                    code[addr(3)] = 0
                }
                state.index += 4
            }
            8 -> {
                if (code[addr(1)] == code[addr(2)]) {
                    code[addr(3)] = 1
                } else {
                    code[addr(3)] = 0
                }
                state.index += 4
            }
            99 -> return Terminated
            else -> error("?: $op")
        }
    }
}