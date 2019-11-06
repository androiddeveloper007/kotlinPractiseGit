package com.eostek.kotlin.practice

//对象表达式和对象声明
//创建一个对类轻微修改的对象实例，不需要声明子类
fun main(){
	//通过对象表达式可以越过类声明，直接得到一个对象
	val site = object{
		var name: String = "菜鸟教程"
		var url: String = "www.baidu.comm"
	}
	println(site.name)
	println(site.url)
}

//伴生对象
class MyClass{
	companion object Factory{
		fun create(): MyClass = MyClass()
	}
}

private fun test(){
	val instance = MyClass.create()//访问到对象的内部元素
}
//对象表达式和对象声明之间的语义差异
/*
 对象表达式是在使用他们的地方立即执行的
对象声明是在第一次被访问到时延迟初始化的
伴生对象的初始化是在相应的类被加载（解析）时，
 与 Java 静态初始化器的语义相匹配
 */