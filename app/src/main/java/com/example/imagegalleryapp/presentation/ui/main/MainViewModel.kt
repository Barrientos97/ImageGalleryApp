package com.example.imagegalleryapp.presentation.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imagegalleryapp.data.local.entity.ImageEntity
import com.example.imagegalleryapp.data.repository.ImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    application: Application,
    private val repository: ImageRepository
) : ViewModel(){

    // LiveData que contiene la lista de todas las imágenes
    private val _images = MutableLiveData<List<ImageEntity>>()
    val images: LiveData<List<ImageEntity>> get() = _images

    init {
        loadImages()
    }

    // Cargar las imágenes desde el repositorio (base de datos)
    fun loadImages() = viewModelScope.launch {
        _images.value = repository.getAllImages()
    }

    // Insertar una nueva imagen en el repositorio (base de datos)
    fun insert(image: ImageEntity) = viewModelScope.launch {
        repository.insert(image)
        loadImages() // Recargar las imágenes después de insertar
    }

    // Eliminar una imagen del repositorio (base de datos)
    fun delete(image: ImageEntity) = viewModelScope.launch {
        repository.delete(image)
        loadImages() // Recargar las imágenes después de eliminar
    }

}