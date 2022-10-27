package com.zh.ktapp.http


/**
 * @ClassName: CResponse
 * @Description: TODO
 * @Author: ZHW
 * @Date: 2022/10/12 下午3:10
 */
data class CResponse<T>(
    val code: Int,
    val `data`: T,
    val message: String,
    val warnMessage: String
)

