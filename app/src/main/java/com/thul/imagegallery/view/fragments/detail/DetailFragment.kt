package com.thul.imagegallery.view.fragments.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.thul.imagegallery.R
import com.thul.imagegallery.model.ImageData
import com.thul.imagegallery.utils.OnImageClickListener
import com.thul.imagegallery.view.adapter.detail.DetailViewAdapter
import com.thul.imagegallery.view.adapter.home.ImageGridAdapter
import com.thul.imagegallery.viewmodel.home.HomeViewModel

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.detail_fragment.*
import kotlinx.android.synthetic.main.home_fragment.*
import kotlin.math.abs


@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.detail_fragment), OnImageClickListener {
    private val animate = false
    private var count = 0
    private val viewModel: HomeViewModel by activityViewModels()
    private val animator = ViewPager2.PageTransformer { page, position ->
        page.apply {
            translationY = if (animate) abs(position) * 500f else 0f
            translationX = 0f
            scaleX = 1f
            scaleY = 1f
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        count = arguments?.getInt("count")!!
        val adapter = DetailViewAdapter(viewModel.imageGalleryList,this)
        viewpager.setPageTransformer(animator)
        viewpager.adapter = adapter
        viewpager.setCurrentItem(count, false)
    }

    override fun OnImageClick(imageData: ImageData, position: Int) {

    }
}
