package com.rp199.template.service

import kotlinx.serialization.Serializable

class SampleService {
    fun doStuff(): SampleResponse {
        return SampleResponse(sampleValue = "I did stuff")
    }
}

@Serializable
data class SampleResponse(
    val sampleValue: String
)