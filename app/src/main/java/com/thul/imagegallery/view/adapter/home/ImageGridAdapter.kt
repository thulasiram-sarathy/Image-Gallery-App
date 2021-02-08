package com.thul.imagegallery.view.adapter.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.thul.imagegallery.R
import com.thul.imagegallery.model.ImageData
import com.thul.imagegallery.utils.GridImageView
import com.thul.imagegallery.utils.OnImageClickListener
import java.util.*
import kotlin.collections.ArrayList


class ImageGridAdapter(imageItems: List<ImageData>?, onImageClickListener : OnImageClickListener) : RecyclerView.Adapter<ImageGridAdapter.renewalViewHolder>() {

  private var list = ArrayList<ImageData>()
  var listener : OnImageClickListener? = null
  val imagesList = ArrayList(imageItems)

  private lateinit var context: Context

  init {
    this.list = imagesList
    this.listener = onImageClickListener
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): renewalViewHolder {
    val itemView = LayoutInflater.from(parent.context).inflate(
      R.layout.image_grid_item,
      parent,
      false
    )
    context = itemView.context
    return renewalViewHolder(itemView)
  }

  override fun getItemCount(): Int {
    return list.size
  }

  override fun onBindViewHolder(holder: renewalViewHolder, position: Int) {
    val data = list[position]
    loadPhoto(holder.gridImage,data.url)
    holder.gridImage.setOnClickListener {
      listener?.OnImageClick(data,position)
    }
  }

  fun renewalNotify(cardItems: List<ImageData>) {
    val initPosition = list.size
    list.clear()
    list.addAll(cardItems)
    notifyItemRangeInserted(initPosition, list.size)
  }

  class renewalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var gridImage = itemView.findViewById<GridImageView>(R.id.gridImage)!!

  }

  private fun loadPhoto(imageView: GridImageView, url: String?) {
    Picasso.get()
      .load(url)
      .fit()
      .centerCrop()
      .placeholder(R.drawable.grid_placeholder)
      .into(imageView)
  }
}