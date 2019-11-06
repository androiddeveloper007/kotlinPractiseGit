package com.eostek.kotlinpractice.test.apractise

//高阶函数
fun main(){
    val list = listOf(1,2,3,4,5)
    val newList = list.map{it*2+3}
    val newList2=list.map(Int::toDouble)//转换成double类型
    newList.forEach(::println)
    newList2.forEach(::println)
    //双冒号操作符 表示把一个方法当做一个参数，传递到另一个方法中进行使用，就是引用一个方法。
}