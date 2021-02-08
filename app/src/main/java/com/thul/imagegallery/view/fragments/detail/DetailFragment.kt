package com.thul.imagegallery.view.fragments.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.thul.imagegallery.R
import com.thul.imagegallery.view.adapter.home.ImageGridAdapter
import com.thul.imagegallery.viewmodel.home.HomeViewModel

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.home_fragment.*


@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.detail_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    }
}
