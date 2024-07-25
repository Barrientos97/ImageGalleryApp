package com.example.imagegalleryapp.presentation.ui.main

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.OpenableColumns
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.imagegalleryapp.R
import com.example.imagegalleryapp.data.local.entity.ImageEntity
import com.example.imagegalleryapp.databinding.ActivityMainBinding
import com.example.imagegalleryapp.presentation.adapter.ImageAdapter
import com.example.imagegalleryapp.presentation.ui.imageDetailDialog.ImageDetailDialogFragment
import com.example.imagegalleryapp.presentation.util.Message
import dagger.hilt.android.AndroidEntryPoint
import tpc.com.uy.t2parking.util.models.CustomDialogData

@AndroidEntryPoint
class MainActivity : AppCompatActivity() , ImageAdapter.IOnClickListener {

    private lateinit var binding: ActivityMainBinding
    private val imageViewModel: MainViewModel by viewModels()

    companion object {
        private const val PICK_IMAGE_REQUEST = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Forzar el modo claro
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        initListener()

    }

    private fun initListener() {
        binding.includeLayout.toolbar.apply {
            title = "Apps de Galeria"
        }

        binding.rvLista.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = ImageAdapter(this@MainActivity)
        }

        imageViewModel.images.observe(this) { images ->
            images?.let {
                (binding.rvLista.adapter as ImageAdapter).submitList(it)
            }
        }

        binding.fabNuevo.setOnClickListener {
            selectImage()
        }
    }

    private fun selectImage() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "image/*"
        }
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }

    private fun getImageNameFromUri(uri: Uri): String {
        var name = ""
        contentResolver.query(uri, null, null, null, null)?.use { cursor ->
            val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
            cursor.moveToFirst()
            name = cursor.getString(nameIndex)
        }
        return name
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            data?.data?.also { uri ->
                try {
                    // Tomar permisos persistentes para la URI seleccionada
                    val takeFlags: Int = Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                    contentResolver.takePersistableUriPermission(uri, takeFlags)

                    val imageName = getImageNameFromUri(uri)
                    val dateAdded = System.currentTimeMillis()
                    val image = ImageEntity(name = imageName, dateAdded = dateAdded, path = uri.toString())
                    imageViewModel.insert(image)
                } catch (e: SecurityException) {
                    Log.e("PhotoPicker", "No se pudo tomar permisos persistentes para la URI", e)
                }
            }
        }
    }

    override fun clickInsert(mode: ImageEntity) {
        val dialogFragment = ImageDetailDialogFragment.newInstance(mode)
        dialogFragment.show(supportFragmentManager, "ImageDetailDialog")
    }

    override fun clickDelete(mode: ImageEntity) {

        Message.showPopUpDialog(
            this,
            CustomDialogData(
                message = getString(R.string.text_message_delete),
                firstOnClick = {imageViewModel.delete(mode)
                }
            ),layoutInflater
        )
    }
}