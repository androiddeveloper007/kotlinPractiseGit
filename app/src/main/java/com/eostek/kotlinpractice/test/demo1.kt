package com.eostek.kotlinpractice.test

fun main(){
	System.out.println(ddd(1,2))
	test2(1,2,3,4,5,6)
}
fun ddd(x:Int,y:Int) = x*y
//�ɱ�����Ͳ��ɱ����
private fun test1(){
	var a =0
	val b = 1
	a = b
//	b =a
}
//�ɱ䳤�ȵĲ���
private fun test2(vararg i:Int){
	for(j in i){
		System.out.println(j)
	}
}