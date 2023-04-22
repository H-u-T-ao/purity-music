package top.sankokomi.puritymsic.basis.net

/**
 * 最简单的返回形式，有些接口是这样返回的
 *
 * @param code Http 响应码
 * @param data 代表是否请求成功
 * */
data class SimpleResponse(
    val code: Int,
    val data: Boolean
)
