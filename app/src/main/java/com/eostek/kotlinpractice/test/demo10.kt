package com.eostek.kotlin.practice

import kotlin.reflect.KProperty
//ί�У�����˵ί��ģʽ����kotlin������Ϊ�﷨
//����������봦��ͬһ�����󣬽�������Ķ�������ί�и���һ��������
/*��ί��*/
private interface Base1{
	fun print()
}

class BaseImpl(val x:Int):Base1{
	override fun print(){
		println(x)
	}
}

//ͨ��by����ί��
private class Derived1(b : Base1) : Base1 by b

fun main(){
	val b = BaseImpl(10)
	Derived1(b).print()
	
	val e = Example()
    println(e.p)     // ���ʸ����ԣ����� getValue() ����

    e.p = "Runoob"   // ���� setValue() ����
    println(e.p)
}


/*����ί��*/
// �����������ί�е���
class Example {
    var p: String by Delegate()
}

// ί�е���
class Delegate {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return "$thisRef, ����ί���� ${property.name} ����"
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        println("$thisRef �� ${property.name} ���Ը�ֵΪ $value")
    }
}
