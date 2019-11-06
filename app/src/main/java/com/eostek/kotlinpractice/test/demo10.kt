package com.eostek.kotlin.practice

import kotlin.reflect.KProperty
//委托，或者说委托模式。在kotlin中上升为语法
//两个对象参与处理同一个请求，接受请求的对象将请求委托给另一个对象处理
/*类委托*/
private interface Base1{
	fun print()
}

class BaseImpl(val x:Int):Base1{
	override fun print(){
		println(x)
	}
}

//通过by建立委托
private class Derived1(b : Base1) : Base1 by b

fun main(){
	val b = BaseImpl(10)
	Derived1(b).print()
	
	val e = Example()
    println(e.p)     // 访问该属性，调用 getValue() 函数

    e.p = "Runoob"   // 调用 setValue() 函数
    println(e.p)
}


/*属性委托*/
// 定义包含属性委托的类
class Example {
    var p: String by Delegate()
}

// 委托的类
class Delegate {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return "$thisRef, 这里委托了 ${property.name} 属性"
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        println("$thisRef 的 ${property.name} 属性赋值为 $value")
    }
}
