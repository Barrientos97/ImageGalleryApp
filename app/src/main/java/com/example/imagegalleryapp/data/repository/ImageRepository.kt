package com.example.imagegalleryapp.data.repository

import androidx.lifecycle.LiveData
import com.example.imagegalleryapp.data.local.dao.ImageDao
import com.example.imagegalleryapp.data.local.entity.ImageEntity
import javax.inject.Inject

class ImageRepository @Inject constructor(
    private val imageDao: ImageDao
) {
    suspend fun getAllImages(): List<ImageEntity> {
        return imageDao.getAllImages()
    }

    suspend fun insert(image: ImageEntity) {
        imageDao.insertImage(image)
    }

    suspend fun delete(image: ImageEntity) {
        imageDao.deleteImage(image)
    }
}