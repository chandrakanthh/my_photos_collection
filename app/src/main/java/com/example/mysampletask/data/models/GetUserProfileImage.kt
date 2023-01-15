package com.example.mysampletask.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetUserProfileImage(
    @SerialName("small") var small: String? = null,
    @SerialName("medium") var medium: String? = null,
    @SerialName("large") var large: String? = null
)
