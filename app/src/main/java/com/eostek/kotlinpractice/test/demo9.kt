package com.eostek.kotlin.practice

//������ʽ�Ͷ�������
//����һ��������΢�޸ĵĶ���ʵ��������Ҫ��������
fun main(){
	//ͨ��������ʽ����Խ����������ֱ�ӵõ�һ������
	val site = object{
		var name: String = "����̳�"
		var url: String = "www.baidu.comm"
	}
	println(site.name)
	println(site.url)
}

//��������
class MyClass{
	companion object Factory{
		fun create(): MyClass = MyClass()
	}
}

private fun test(){
	val instance = MyClass.create()//���ʵ�������ڲ�Ԫ��
}
//������ʽ�Ͷ�������֮����������
/*
 ������ʽ����ʹ�����ǵĵط�����ִ�е�
�����������ڵ�һ�α����ʵ�ʱ�ӳٳ�ʼ����
��������ĳ�ʼ��������Ӧ���౻���أ�������ʱ��
 �� Java ��̬��ʼ������������ƥ��
 */