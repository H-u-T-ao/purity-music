package top.sankokomi.puritymusic.basis.app

import android.app.Application

abstract class BasicApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Global.app = this@BasicApplication
    }

}