package top.sankokomi.puritymusic.musiccolumn.net

import android.os.SystemClock
import retrofit2.http.GET
import retrofit2.http.Query
import top.sankokomi.puritymusic.basis.net.MusicApi
import top.sankokomi.puritymusic.musiccolumn.entity.MusicDetails
import top.sankokomi.puritymusic.musiccolumn.entity.MusicListDetails

interface MusicDetailApi : MusicApi {

    @GET("song/detail")
    suspend fun musicDetail(
        @Query("ids") ids: String,
        @Query("timestamp") timestamp: Long = SystemClock.uptimeMillis()
    ): MusicDetails

    //获取歌单详情
    @GET("playlist/detail")
    suspend fun playListDetail(
        @Query("id") id: Int,
        @Query("timestamp") timestamp: Long = SystemClock.uptimeMillis()
    ): MusicListDetails

    //获取歌单内的歌曲
    @GET("playlist/track/all")
    suspend fun playListTrackDetail(
        @Query("id") id: Int,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
        @Query("timestamp") timestamp: Long = SystemClock.uptimeMillis()
    ): MusicListDetails
}