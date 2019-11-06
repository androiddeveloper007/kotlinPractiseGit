package com.eostek.kotlin.practice

//kotlin拓展
//拓展函数可以在已定义类中添加新方法，不会对原类做修改
fun main(){
	var user = User("zzp")
	user.print()
	
	
	//解构声明
	val (name,age) = User1("jame",27)
	println(name)
	println(age)
	
	test1()
}

class User(var name:String)

fun User.print(){
	print("用户名 $name")
}
//拓展函数和成员函数可以共存，调用时优先成员函数

//数据类和密封类
//数据类不能继承其他类 (但是可以实现接口)
data class User1(val name:String,val age:Int)

//遍历中使用解构声明
val list:List<User1> = listOf(
	User1("one",1),
	User1("two",2),
	User1("three",3))

private fun test1(){
	list.forEach{(name,age) -> println("name:$name, age:$age")}
}

//密封类
//sealed修饰，类似于枚举
