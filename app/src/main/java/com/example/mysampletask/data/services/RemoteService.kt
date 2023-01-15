package com.example.mysampletask.data.services

import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.http.*

class RemoteService() {
    private val baseUrl = "https://api.unsplash.com/"
    val httpClient = HttpClient(Android){
        install(JsonFeature) {
            serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                ignoreUnknownKeys = true
                isLenient = true
            })

            engine {
                connectTimeout = 60_000
                socketTimeout = 60_000
            }
        }

        install(Logging){
            logger = Logger.SIMPLE
            level = LogLevel.ALL
        }
    }

    @Throws(Exception::class)
    fun getUrl(endPoint: String) : Url = URLBuilder(baseUrl + endPoint).build()

    @Throws(Exception::class)
    fun addHeaders(headersBuilder: HeadersBuilder){
        headersBuilder.apply {
            append("content-type","application/json")
        }
    }

}