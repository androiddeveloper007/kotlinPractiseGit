package com.eostek.kotlin.practice

//������������Double Float Long Int Short Byte
fun main(){
//	test2()
	test3()
}
private fun test1(){
	var a = 123L//������
	var b = 0xff//16����
	var c = 0b0101010101//2����
	//��֧��8���ƣ���
	var d = 0.1332423f//float
	
	
}

//Boolean
private fun test2(){
	var a:Boolean = true;
	var b = true
	
}

//����
private fun test3(){
	var a = arrayOf(1,2,3)
	var b = Array(3, {i -> (i*2)})
	//��ȡ��������
	println(a[0])
	println(b[1])
	//kotlin�������ǲ����ͱ�ã�����˵����������ͳһ��
	var c: IntArray = intArrayOf(1,2,3)
	var d: DoubleArray = doubleArrayOf(1.0,2.0,3.0)
}


