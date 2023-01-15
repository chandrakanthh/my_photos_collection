package com.example.mysampletask.ui.imageCollection

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mysampletask.R
import com.example.mysampletask.data.models.GetDetails
import com.example.mysampletask.data.services.BaseResponse
import com.example.mysampletask.databinding.FragmentImageCollectionBinding
import com.example.mysampletask.ui.base.BaseFragment
import com.example.mysampletask.ui.utils.displayToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImageCollectionFragment : BaseFragment(R.layout.fragment_image_collection) {
    lateinit var fragmentImageCollectionBinding: FragmentImageCollectionBinding
    private val imageCollectionViewModel: ImageCollectionViewModel by activityViewModels()
    private val imageCollectionAdapter: ImageCollectionAdapter by lazy {
        ImageCollectionAdapter(
            requireContext(),
            arrayListOf()
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentImageCollectionBinding = FragmentImageCollectionBinding.bind(view)
        setUpToolBar(R.string.photos_str,false)
        initiateViews()
        setObservers()
        setOnListeners()
    }

    private fun setOnListeners() {
        imageCollectionAdapter.selectedItemListenerOnClick { itemDetails->
            imageCollectionViewModel.selectedItem(itemDetails)
            findNavController().navigate(R.id.action_imageCollectionFrag_to_enlargeImageFragment)
        }

        imageCollectionAdapter.selectedProfileListenerOnClick { userDetails->
            imageCollectionViewModel.selectedItem(userDetails)
            findNavController().navigate(R.id.action_imageCollectionFrag_to_userProfileFragment)
        }
    }

    private fun initiateViews() = with(fragmentImageCollectionBinding) {
        val llm = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        imgCollectionsRv.layoutManager = llm
        imgCollectionsRv.adapter = imageCollectionAdapter
        val dividerItemDecoration = DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
        imgCollectionsRv.addItemDecoration(dividerItemDecoration)
    }

    private fun setObservers() {
        imageCollectionViewModel.imageCollectionData.observe(viewLifecycleOwner) {
            when(it){
                is BaseResponse.Error -> {
                    context?.displayToast(it.message.toString())
                }
                is BaseResponse.Loading -> {
                    context?.displayToast("loading...")
                }
                is BaseResponse.Success -> {
                    it.data?.let { list->
                        imageCollectionAdapter.updateItems(list as ArrayList<GetDetails>)
                    }
                }
            }
        }
    }

}