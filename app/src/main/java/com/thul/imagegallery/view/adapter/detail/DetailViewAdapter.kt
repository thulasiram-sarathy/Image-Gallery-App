package com.thul.imagegallery.view.adapter.detail

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


class DetailViewAdapter(imageItems: List<ImageData>?, onImageClickListener : OnImageClickListener) : RecyclerView.Adapter<DetailViewAdapter.renewalViewHolder>() {

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
      R.layout.detail_item,
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
  }


  class renewalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


  }


}