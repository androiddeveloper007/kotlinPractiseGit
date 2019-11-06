package com.eostek.kotlinpractice.test.apractise

//闭包，就是函数的运行环境是一个封闭的环境。在环境中有自由变量。
// 也可以理解为函数中的函数。kotlin 中可以把函数当作参数和返回值来用。
fun main(){
//    val x = makeFun()
//    x()
//    x()
//    x()
//    x()

//    val x = fibonacci()
//    println(x())
//    println(x())
//    println(x())
//    println(x())
//    println(x())
//    println(x())
//    println(x())
//    println(x())

    //因为闭包是内存中始终保存着变量，所以每次运行都在原有基础上进行操作。
    // 不会因为函数运行结束就清除内存变量。
    for (i in fibonacci3()) {
        if (i > 100) break
        println(i)
    }
}

fun makeFun():()->Unit{
    var count=0
    return fun(){
        println(++count)
    }
}

fun fibonacci():()->Int{
    var first = 0
    var second = 1
    return fun():Int{
        var result = second
        second += first
        first = second - first
        return result
    }
}

fun fibonacci2():()->Int{
    var first = 0
    var second = 1
    return fun():Int{
        var result = first + second
        first = second
        second = result
        return first
    }
}

fun fibonacci3(): Iterable<Int> {
    var first = 0
    var second = 1
    return Iterable {
        object : IntIterator() {
            override fun nextInt(): Int {
                val result = first + second
                first = second
                second = result
                return first
            }

            override fun hasNext() = true
        }
    }
}