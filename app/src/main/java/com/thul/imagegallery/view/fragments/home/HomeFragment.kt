package com.thul.imagegallery.view.fragments.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.thul.imagegallery.R
import com.thul.imagegallery.model.ImageData
import com.thul.imagegallery.utils.OnImageClickListener
import com.thul.imagegallery.view.adapter.home.ImageGridAdapter
import com.thul.imagegallery.viewmodel.home.HomeViewModel

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.home_fragment.*


@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.home_fragment), OnImageClickListener {

    private val viewModel: HomeViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val navController = findNavController()
        val adapter = ImageGridAdapter(viewModel.imageGalleryList,this)
        val layoutManager = GridLayoutManager(requireContext(), 2)
        imageHomeRecyclerview.layoutManager = layoutManager
        imageHomeRecyclerview.adapter = adapter
    }

    override fun OnImageClick(imageData: ImageData, position: Int) {

        val bundle = Bundle().apply {
            putInt("count", position)
        }
        view?.let{
            Navigation.findNavController(it)
                .navigate(R.id.imageDetailFragment,bundle)
        }
    }
}
