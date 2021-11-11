package com.sanmidev.confamgbedu.data.local

import androidx.room.TypeConverter
import kotlinx.datetime.LocalDateTime

class Converters {
    @TypeConverter
    fun fromISOTimestamp(value: String?): LocalDateTime? {
        return value?.let { LocalDateTime.parse(it) }
    }

    @TypeConverter
    fun dateToISOTimestamp(date: LocalDateTime?): String? {
        return date?.toString()
    }
}