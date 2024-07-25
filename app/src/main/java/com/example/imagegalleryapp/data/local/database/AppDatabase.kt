package com.example.imagegalleryapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.imagegalleryapp.data.local.dao.ImageDao
import com.example.imagegalleryapp.data.local.entity.ImageEntity

@Database(
    entities = [
        ImageEntity::class
    ], version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun imageDao(): ImageDao

}