package com.example.imagegalleryapp.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.imagegalleryapp.data.local.entity.ImageEntity

@Dao
interface ImageDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertImage(image: ImageEntity)

//    @Query("SELECT * FROM images")
//    fun getAllImages(): LiveData<List<ImageEntity>>


    @Query("SELECT * FROM images ORDER BY id ASC")
    suspend fun getAllImages(): List<ImageEntity>

    @Delete
    suspend fun deleteImage(image: ImageEntity)

}