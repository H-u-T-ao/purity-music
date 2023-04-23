package top.sankokomi.puritymusic.basis.entity

/**
 * 用户基本信息
 *
 * @param id 用户唯一标识符
 * @param nickName 用户昵称
 * @param avatar 用户头像 URL
 * */
data class UserPub(
    val id: Long,
    val nickName: String,
    val avatar: String
)