package com.example.mysampletask.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetUserDetails(
    @SerialName("username") var username: String? = null,
    @SerialName("name") var name: String? = null,
    @SerialName("first_name") var firstName: String? = null,
    @SerialName("last_name") var lastName: String? = null,
    @SerialName("bio") var bio: String? = null,
    @SerialName("location") var location: String? = null,
    @SerialName("profile_image") var profileImage: GetUserProfileImage? = GetUserProfileImage()
)
