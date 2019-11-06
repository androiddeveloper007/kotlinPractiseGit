package com.eostek.kotlin.practice

fun main(){
//	test1()
//	test2("g")
	test3()
}

//区间
private fun test1(){
	var a = 9
	println(a in 1..8)
}

//when
private fun test2(a:String){
	when(a){
		"x" -> println("this is x")
		"y" -> println("this is x")
		"z" -> println("this is x")
		else -> println("this isn't anything")
	}
}

//List中迭代器使用
private fun test3(){
	var array = listOf("apple","banana","kiwi")
	for(index in array.indices){
		println("index at $index of array is ${array[index]}")
	}
}