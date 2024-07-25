package com.example.imagegalleryapp.di

import android.content.Context
import androidx.room.Room
import com.example.imagegalleryapp.data.local.dao.ImageDao
import com.example.imagegalleryapp.data.local.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    private const val DATABASE_NAME = "appDatabase"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context):AppDatabase{
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun provideImageDao(db: AppDatabase): ImageDao {
        return db.imageDao()
    }
}