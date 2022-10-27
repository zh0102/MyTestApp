package com.zh.ktdemo.kt1


/**
 * @ClassName: MyClass
 * @Description: TODO
 * @Author: ZHW
 * @Date: 2022/9/26 下午6:40
 */
@Retention(AnnotationRetention.SOURCE)
@MustBeDocumented
@Target(
    AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.CLASS, AnnotationTarget.FUNCTION,
    AnnotationTarget.VALUE_PARAMETER)
annotation class MyClass(val why: String)
