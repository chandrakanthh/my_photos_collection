package com.example.mysampletask.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetDetails(

    @SerialName("description")
    var description: String? = null,
    @SerialName("alt_description")
    var altDescription: String? = null,
    @SerialName("urls")
    var urls: GetImageUrls? = GetImageUrls(),
    @SerialName("user")
    var user: GetUserDetails? = GetUserDetails()

)
