package com.example.mysampletask.ui.imageCollection

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.mysampletask.R
import com.example.mysampletask.databinding.FragmentEnlargeImageBinding
import com.example.mysampletask.ui.base.BaseFragment
import com.example.mysampletask.ui.utils.displayImage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EnlargeImageFragment : BaseFragment(R.layout.fragment_enlarge_image) {
    private lateinit var fragmentEnlargeImageBinding: FragmentEnlargeImageBinding
    private val imageCollectionViewModel: ImageCollectionViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentEnlargeImageBinding = FragmentEnlargeImageBinding.bind(view)
        setUpToolBar(showNavIcon = true)
        initiateViews()


    }

    private fun initiateViews() {
        imageCollectionViewModel.imageSelectedData.observe(viewLifecycleOwner){
            fragmentEnlargeImageBinding.itemDetails = it
            fragmentEnlargeImageBinding.fullImageIv.displayImage(context?:requireContext(),it.urls?.full)
        }
    }


}