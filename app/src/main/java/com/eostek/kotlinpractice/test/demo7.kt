package com.eostek.kotlin.practice

//����
fun main(){
	
}

class Box<T>(t:T){
	var value = t
}

private fun test1(){
	val box:Box<Int> = Box<Int>(1)
	//����
	val box1 = Box(1)
	val boxString = Box<String>("aaa")
}