package com.example.mysampletask.ui.userProfile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.mysampletask.R
import com.example.mysampletask.databinding.FragmentUserProfileBinding
import com.example.mysampletask.ui.base.BaseFragment
import com.example.mysampletask.ui.imageCollection.ImageCollectionViewModel
import com.example.mysampletask.ui.utils.displayImage

class UserProfileFragment : BaseFragment(R.layout.fragment_user_profile) {
    lateinit var fragmentUserProfileBinding: FragmentUserProfileBinding
    private val imageCollectionViewModel: ImageCollectionViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentUserProfileBinding = FragmentUserProfileBinding.bind(view)
        setUpToolBar(R.string.profile_str,showNavIcon = true)
        setObservers()
    }

    private fun setObservers() {
        imageCollectionViewModel.imageSelectedData.observe(viewLifecycleOwner){
             fragmentUserProfileBinding.userProfile = it
            fragmentUserProfileBinding.userProfileImageIv.displayImage(context?:requireContext(),it.user?.profileImage?.large)
        }
    }

}