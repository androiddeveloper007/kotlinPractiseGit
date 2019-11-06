package com.eostek.kotlin.practice

//类和对象
fun main(){
	var people1 = People()
	var people2 = Person("jason")
}

class People{
	
	var name:String =""
	var age:Int = 0
	var sex:String = "男"
}

//主构造器
class Person constructor(firstName:String){
	init{
		println("this is $firstName")
	}
}

//抽象类
private open class Base{
	open fun f(){}
}

private abstract class Derived:Base(){
	override abstract fun f()
}

//类的继承
open class Student(var name:String,var age:Int){
	
}
class GoodStudent(name:String="sss",age:Int,var no:String,var score:Int) : Student(name,age){
	
	
}

//重写
open class Student1{
	open fun study(){
		println("我学习了5分钟")
	}
}
class GoodStudent1:Student1(){
	override fun study(){
		println("我学习了50分钟")
	}
}

//接口
interface MInterface {
	fun bar()
	fun foo(){
		println("foo")
	}
}
//一个类或者对象可以实现一个或多个接口
class Child : MInterface{
	override fun bar(){
		println("bar")
	}
}

//多重接口方法重写
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





