package com.eostek.kotlinpractice.test

fun main(){
	System.out.println(ddd(1,2))
	test2(1,2,3,4,5,6)
}
fun ddd(x:Int,y:Int) = x*y
//可变变量和不可变变量
private fun test1(){
	var a =0
	val b = 1
	a = b
//	b =a
}
//可变长度的参数
private fun test2(vararg i:Int){
	for(j in i){
		System.out.println(j)
	}
}