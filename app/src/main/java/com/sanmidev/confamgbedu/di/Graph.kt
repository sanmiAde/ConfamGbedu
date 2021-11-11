package com.sanmidev.confamgbedu.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.sanmidev.confamgbedu.data.local.GbeduDatabase
import com.sanmidev.confamgbedu.data.local.GbeduEntity
import com.sanmidev.confamgbedu.domain.model.Meta
import com.sanmidev.confamgbedu.domain.model.Rating
import com.sanmidev.confamgbedu.domain.model.ReleaseType
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

//TODO Use Hilt or another DI
object Graph {
    lateinit var database: GbeduDatabase

    private val mainDispatcher: CoroutineDispatcher
        get() = Dispatchers.Main

    private val ioDispatcher: CoroutineDispatcher
        get() = Dispatchers.IO

    fun provide(context: Context) {
        database = Room.databaseBuilder(
            context,
            GbeduDatabase::class.java, "gbebu_database"
        ).addCallback(object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                seed()
            }
        }).build()
    }

    fun seed() {
        runBlocking {
            repeat(10) {
                database.gbeduDao().insertGbedu(
                    GbeduEntity(
                        0, "legendary$it", "Mike$it", Rating.EIGHT, Meta(
                            ReleaseType.EP, Clock.System.now().toLocalDateTime(
                                TimeZone.UTC
                            ), Clock.System.now().toLocalDateTime(TimeZone.UTC)
                        )
                    )
                )
            }
        }
    }
}