package com.sxd.learnkotlin.basic

fun main() {
    // val var
    val a=10    // 常量定义，无法修改。类型自动推断
//    a=12    // 修改报错
    var b = 12 // 变量定义，可修改
    b = 13      // 修改成功
    println("$a,$b")
//    b = "a" // 类型推断成功后，再更改类型会报错

    // 类型推断、
    var a1 = 21 // 推断a1为Int
    var a2 = 22L // 推断为 Long
    var a3 = 22.3 // 默认推断为Double
    var a4 = 22.4F // 推断为Float
    var a5 = true   // 推断为Boolean
    var a6 = 'a'    // 推断为 char
    // 基础类型（Int/Long/Double/Boolean/Char）、
// 可空类型 `?`、
    var b1:Int?
    b1 = 12

// 类型转换（`as`/`toInt()`/智能转换）、
// `Any`/`Unit`/`Nothing`
}