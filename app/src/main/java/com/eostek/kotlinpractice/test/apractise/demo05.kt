package com.eostek.kotlinpractice.test.apractise

import java.io.BufferedReader
import java.io.FileReader

//文件读取方法
fun main(){
    /*try {
        val br = BufferedReader(FileReader("hello.txt"))
        with(br){
            var line:String?
            while (true){
                line = readLine()?:break
                println(line)
            }
            close()
        }
    } catch (e: Exception) {
        println(e.message)
    }*/
    try {
        BufferedReader(FileReader("f:\\text.txt")).use {
            var line:String?
            while (true){
                line = it.readLine()?:break
                println(line)
            }
        }
    } catch (e: Exception) {
        println(e.message)
    }
}