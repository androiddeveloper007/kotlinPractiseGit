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
	//�ַ���ģ��
	var d = "hello boy"
	println("$d wo ai ni.")
	//$����֧�ַ�б��ת�壬����Ҫ��ôд
	println("${'$'}9.9")
}