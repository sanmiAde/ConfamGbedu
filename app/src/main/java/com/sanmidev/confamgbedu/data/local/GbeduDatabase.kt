package com.sanmidev.confamgbedu.data.local

import androidx.room.Database
import androidx.room.TypeConverters

@Database(entities = [GbeduEntity::class], version = 1, exportSchema = true)
@TypeConverters(Converters::class)
abstract class GbeduDatabase {
    abstract fun gbeduDao(): GbeduDao
}