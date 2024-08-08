package com.example.note.data

import androidx.room.TypeConverter
import java.util.Date

/*
Room (or more specifically SQLite) do not support native date type, hence require self defined type converter to save data
 */
class DateConverter {
    @TypeConverter
    fun toDate(dateLong: Long?): Date? {
        return dateLong?.let { Date(it) }
    }

    @TypeConverter
    fun fromDate(date: Date?): Long? {
        return date?.time
    }
}