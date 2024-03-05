package uz.developers.eventapp.app

import android.app.Application
import uz.developers.eventapp.pref.MyShared

class App :Application() {
    override fun onCreate() {
        super.onCreate()
        MyShared.init(this)
    }
}