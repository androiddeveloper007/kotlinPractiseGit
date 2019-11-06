package com.eostek.kotlinpractice.test

class demo12 {
    fun main() {
        print(test())
    }

    //换行显示
    fun test():String {
        val text = """
            first line
            |second line
            |third line.
        """.trimMargin()
        return text
    }
}