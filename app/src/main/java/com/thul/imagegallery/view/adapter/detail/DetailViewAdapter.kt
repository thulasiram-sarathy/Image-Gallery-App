package com.thul.imagegallery.view.adapter.detail

import android.content.Context
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import com.squareup.picasso.Picasso
import com.thul.imagegallery.R
import com.thul.imagegallery.model.ImageData
import com.thul.imagegallery.utils.GridImageView
import com.thul.imagegallery.utils.OnImageClickListener
import java.text.ParseException
import java.text.SimpleDateFormat
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
    loadPhoto(holder.mainImage,data.hdurl)
    loadTextView(holder.title,data.title)
    loadTextView(holder.copyright,data.copyright)
//    loadTextView(holder.date,data.date)
    dateConverter(holder,position)
    loadTextView(holder.description,data.explanation)
    holder.mLayout!!.addPanelSlideListener(object : SlidingUpPanelLayout.PanelSlideListener {
      override fun onPanelSlide(panel: View?, slideOffset: Float) {
        Log.i(TAG, "onPanelSlide, offset " + slideOffset);
      }

      override fun onPanelStateChanged(
        panel: View?,
        previousState: SlidingUpPanelLayout.PanelState?,
        newState: SlidingUpPanelLayout.PanelState?
      ) {
        Log.i(TAG, "onPanelStateChanged " + newState);
      }

    })
    holder.mLayout!!.setFadeOnClickListener(object : View.OnClickListener{
      override fun onClick(p0: View?) {
        holder.mLayout!!.panelState = SlidingUpPanelLayout.PanelState.COLLAPSED
      }

    })
  }


  class renewalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var mainImage = itemView.findViewById<AppCompatImageView>(R.id.mainImage)!!
    var mLayout = itemView.findViewById<SlidingUpPanelLayout>(R.id.sliding_layout)!!
    var date= itemView.findViewById<TextView>(R.id.date)!!
    var description= itemView.findViewById<TextView>(R.id.description)!!
    var title= itemView.findViewById<TextView>(R.id.title)!!
    var copyright= itemView.findViewById<TextView>(R.id.copyright)!!

  }

  private fun loadPhoto(imageView: AppCompatImageView, url: String?) {
    Picasso.get()
      .load(url)
      .fit()
      .centerCrop()
      .placeholder(R.drawable.grid_placeholder)
      .into(imageView)
  }
  private fun loadTextView(textView: TextView, text: String?) {
    if(text != null){
      textView.text = Html.fromHtml(text)
    }else{
      textView.text = "-"
    }

  }
  private fun dateConverter(holder: renewalViewHolder, position: Int){
    val input = SimpleDateFormat("yyyy-MM-dd")
        val output = SimpleDateFormat("dd MMMM yyyy")

        var d: Date? = null
        try {
            d = input.parse(list.get(position).date)
            holder.date.setText(output.format(d))
        } catch (e: ParseException) {
            e.printStackTrace()
        }
  }
  companion object {
    private const val TAG = "DetailViewAdapter"
  }
}