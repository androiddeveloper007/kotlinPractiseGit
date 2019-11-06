package com.eostek.kotlin.practice

fun main(){
	test1()
}

//String
private fun test1(){
	var a:String = "fsjkdljsl"
	var b = a[0]
	for(c in a){
//		print(c)
	}
	var c="""
			ni shi hao hai zi,
			wo hen xi huan ni"""
	println(c)
	//字符串模板
	var d = "hello boy"
	println("$d wo ai ni.")
	//$符不支持反斜杠转义，你需要这么写
	println("${'$'}9.9")
}