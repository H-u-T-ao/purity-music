package top.sankokomi.puritymusic.musiccolumn.net

import android.os.SystemClock
import retrofit2.http.GET
import retrofit2.http.Query
import top.sankokomi.puritymusic.musiccolumn.entity.MusicDetail

interface MusicDetailApi {

    @GET("song/detail")
    suspend fun musicDetail(
        @Query("ids") ids: String,
        @Query("timestamp") timestamp: Long = SystemClock.uptimeMillis()
    ): MusicDetail

}