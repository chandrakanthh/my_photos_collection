package com.example.mysampletask.data.usecases

import com.example.mysampletask.data.models.GetDetails
import com.example.mysampletask.data.services.BaseResponse
import com.example.mysampletask.data.services.Repository

class ImageCollectionUseCase(private val repository: Repository) {
    suspend fun getAllPhotos(): List<GetDetails>{
        return repository.getAllDetails()
    }
}