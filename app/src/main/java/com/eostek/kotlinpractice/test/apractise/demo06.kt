package com.eostek.kotlinpractice.test.apractise

//泛型
//泛型类
class Box<T>(t:T){
    var value=t
}
//泛型函数
fun <T> doPrintInt(content: T){
    when(content){
        is Int -> print("整型数字为$content")
        is String -> println("字符串转换大写：${content.toUpperCase()}")
        else -> println("$content 不是整型也不是字符串")
    }
}

