package com.eostek.kotlinpractice.test.apractise

//符合函数
fun main(){
    //kotlin中函数可以定义成变量形式
    val add5 = {i:Int -> i+5}
    val multiplyBy2 = {i:Int -> i*2}
    //函数叠加调用
    println("multiplyBy2(add5(3)):   "+multiplyBy2(add5(3)))

}