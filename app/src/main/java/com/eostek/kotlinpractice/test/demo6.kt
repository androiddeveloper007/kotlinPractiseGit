package com.eostek.kotlin.practice

//kotlin��չ
//��չ�����������Ѷ�����������·����������ԭ�����޸�
fun main(){
	var user = User("zzp")
	user.print()
	
	
	//�⹹����
	val (name,age) = User1("jame",27)
	println(name)
	println(age)
	
	test1()
}

class User(var name:String)

fun User.print(){
	print("�û��� $name")
}
//��չ�����ͳ�Ա�������Թ��棬����ʱ���ȳ�Ա����

//��������ܷ���
//�����಻�ܼ̳������� (���ǿ���ʵ�ֽӿ�)
data class User1(val name:String,val age:Int)

//������ʹ�ý⹹����
val list:List<User1> = listOf(
	User1("one",1),
	User1("two",2),
	User1("three",3))

private fun test1(){
	list.forEach{(name,age) -> println("name:$name, age:$age")}
}

//�ܷ���
//sealed���Σ�������ö��
