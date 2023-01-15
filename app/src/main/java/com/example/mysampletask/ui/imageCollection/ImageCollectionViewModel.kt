package com.example.mysampletask.ui.imageCollection

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mysampletask.data.models.GetDetails
import com.example.mysampletask.data.services.BaseResponse
import com.example.mysampletask.data.usecases.ImageCollectionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageCollectionViewModel @Inject constructor(
    private val imageCollectionUseCase: ImageCollectionUseCase
) : ViewModel() {
    private val _imageCollectionData = MutableLiveData<BaseResponse<List<GetDetails>>>()
    val imageCollectionData : LiveData<BaseResponse<List<GetDetails>>> = _imageCollectionData

    val imageSelectedData = MutableLiveData<GetDetails>()

    init {
        getAllPhotos()
    }

    private fun getAllPhotos() {
        viewModelScope.launch {
            kotlin.runCatching {
                imageCollectionUseCase.getAllPhotos()
            }.onSuccess {
               _imageCollectionData.postValue(BaseResponse.Success(it))
            }.onFailure {
                _imageCollectionData.postValue(BaseResponse.Error(it.message))
            }
        }
    }

    fun selectedItem(item: GetDetails){
        imageSelectedData.postValue(item)
    }
}