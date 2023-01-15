package com.example.mysampletask.data.services

import com.example.mysampletask.data.models.GetDetails
import com.example.mysampletask.ui.utils.Constants
import io.ktor.client.request.*


class Repository(private val remoteService: RemoteService) {

    @Throws(Exception::class)
    suspend fun getAllDetails(): List<GetDetails> {
        return remoteService.httpClient.get {
            url(remoteService.getUrl(Constants.kPhotos))
            headers {
                remoteService.addHeaders(this)
            }
            parameter("client_id","8634366274bd23efb9b023fb9b2c6502e67f7dd5d6a7962b3b49fbee170940f8")
        }
    }
}