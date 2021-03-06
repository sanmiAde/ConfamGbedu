package com.sanmidev.confamgbedu

import android.app.Application
import com.airbnb.mvrx.Mavericks
import com.sanmidev.confamgbedu.di.Graph

class ConfamGbeduApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Graph.provide(this)

        Mavericks.initialize(this)
    }
}