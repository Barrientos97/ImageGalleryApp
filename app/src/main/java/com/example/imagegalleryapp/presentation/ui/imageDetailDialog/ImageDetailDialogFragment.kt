package com.example.imagegalleryapp.presentation.ui.imageDetailDialog

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.DialogFragment
import com.example.imagegalleryapp.data.local.entity.ImageEntity
import com.example.imagegalleryapp.databinding.FragmentImageDetailDialogBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class ImageDetailDialogFragment : DialogFragment() {

    private lateinit var binding: FragmentImageDetailDialogBinding
    private lateinit var image: ImageEntity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentImageDetailDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Forzar el modo claro
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        binding.imageView.setImageURI(Uri.parse(image.path))
        binding.tvName.text = image.name
        binding.tvDate.text = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date(image.dateAdded))
    }

    companion object {
        fun newInstance(image: ImageEntity): ImageDetailDialogFragment {
            val fragment = ImageDetailDialogFragment()
            fragment.image = image
            return fragment
        }
    }
}