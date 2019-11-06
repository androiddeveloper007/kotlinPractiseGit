package com.eostek.kotlinpractice.test.apractise

import java.io.File
//使用kotlin中的readText等函数统计文本中字符出现次数
fun main(){
    print("this is practise 01")
    val textPath = "f:\\text.txt"
    //建立一个HashMap，用来存储统计的结果
    val map = HashMap<Char, Int>()
    //读取文件内容，转换成Char进行遍历统计
    File(textPath).readText().toCharArray().filterNot(Char::isWhitespace).forEach {
        val count=map[it]
        if(count==null){
            map[it]=1
        }else{
            map[it]=count+1
        }
    }
    map.forEach{ (t, u) ->println("字符\"$t\"出现了 $u 次")}
}