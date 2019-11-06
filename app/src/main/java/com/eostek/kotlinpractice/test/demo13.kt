package com.eostek.kotlinpractice.test

class demo13{
    fun main() {
        test()
        test1()
    }

    //三元表达式
    fun test() {
        val x = 1
        val y = 2
        var a = if(x > y) "x比y大" else "y比x大"
        print(a)
    }

    //与，或，异或，左移右移
    fun test1() {
        val a = true
        val b = false
        val andResult = a and b
        val orResult = a or b
        val xorResult = a xor b
        val x = 8
        val leftShiftResult = x shl 2
        val rightShiftResult = x shr 2
        print("""
            $andResult
            |$orResult
            |$xorResult
            |$leftShiftResult
            |$rightShiftResult
        """.trimIndent())
    }
}