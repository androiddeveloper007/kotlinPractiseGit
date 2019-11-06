package com.eostek.kotlin.practice

//��Ͷ���
fun main(){
	var people1 = People()
	var people2 = Person("jason")
}

class People{
	
	var name:String =""
	var age:Int = 0
	var sex:String = "��"
}

//��������
class Person constructor(firstName:String){
	init{
		println("this is $firstName")
	}
}

//������
private open class Base{
	open fun f(){}
}

private abstract class Derived:Base(){
	override abstract fun f()
}

//��ļ̳�
open class Student(var name:String,var age:Int){
	
}
class GoodStudent(name:String="sss",age:Int,var no:String,var score:Int) : Student(name,age){
	
	
}

//��д
open class Student1{
	open fun study(){
		println("��ѧϰ��5����")
	}
}
class GoodStudent1:Student1(){
	override fun study(){
		println("��ѧϰ��50����")
	}
}

//�ӿ�
interface MInterface {
	fun bar()
	fun foo(){
		println("foo")
	}
}
//һ������߶������ʵ��һ�������ӿ�
class Child : MInterface{
	override fun bar(){
		println("bar")
	}
}

//���ؽӿڷ�����д
interface A{
	fun aaa(){println("aaa")}
	fun bbb()
}
interface B{
	fun bbb(){println("bbb")}
	fun ccc(){println("ccc")}
}
class C : A,B{
	override fun aaa(){
		super<A>.aaa()
	}
	override fun bbb(){
		super<B>.bbb()
		println("A's bbb")
	}
	override fun ccc(){
		super<B>.ccc()
	}
}





