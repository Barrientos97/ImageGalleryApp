package com.example.imagegalleryapp.presentation.adapter

import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.imagegalleryapp.R
import com.example.imagegalleryapp.data.local.entity.ImageEntity
import com.example.imagegalleryapp.databinding.ItemImageBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ImageAdapter(
    private val iOnClickListener: IOnClickListener
): ListAdapter<ImageEntity, ImageAdapter.BindViewHolder>(DiffCalback)  {

    interface IOnClickListener {
        fun clickInsert(mode: ImageEntity)
        fun clickDelete(mode: ImageEntity)
    }

    private object DiffCalback : DiffUtil.ItemCallback<ImageEntity>() {
        override fun areItemsTheSame(oldItem: ImageEntity, newItem: ImageEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ImageEntity, newItem: ImageEntity): Boolean {
            return oldItem == newItem
        }
    }

    inner class BindViewHolder(
        private val binding: ItemImageBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun bind(model: ImageEntity){
            val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            binding.tvDate.text = dateFormat.format(Date(model.dateAdded))

            val uri = Uri.parse(model.path)
            try {
                val contentResolver = binding.root.context.contentResolver
                val inputStream = contentResolver.openInputStream(uri)
                val drawable = Drawable.createFromStream(inputStream, uri.toString())
                binding.imageView.setImageDrawable(drawable)
                inputStream?.close()
            } catch (e: Exception) {
                e.printStackTrace()
                binding.imageView.setImageResource(R.drawable.ic_apps) // Imagen de placeholder en caso de error
            }

            binding.imageView.setOnClickListener {
                iOnClickListener.clickInsert(model)
            }
            binding.ibEliminar.setOnClickListener {
                iOnClickListener.clickDelete(model)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageAdapter.BindViewHolder {
        return BindViewHolder(
        ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ImageAdapter.BindViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}