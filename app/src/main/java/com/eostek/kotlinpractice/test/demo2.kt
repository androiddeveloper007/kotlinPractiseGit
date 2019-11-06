package com.eostek.kotlin.practice

//基本数据类型Double Float Long Int Short Byte
fun main(){
//	test2()
	test3()
}
private fun test1(){
	var a = 123L//长整型
	var b = 0xff//16进制
	var c = 0b0101010101//2进制
	//不支持8进制！！
	var d = 0.1332423f//float
	
	
}

//Boolean
private fun test2(){
	var a:Boolean = true;
	var b = true
	
}

//数组
private fun test3(){
	var a = arrayOf(1,2,3)
	var b = Array(3, {i -> (i*2)})
	//读取数组内容
	println(a[0])
	println(b[1])
	//kotlin中数组是不可型变得，就是说数据类型是统一的
	var c: IntArray = intArrayOf(1,2,3)
	var d: DoubleArray = doubleArrayOf(1.0,2.0,3.0)
}


