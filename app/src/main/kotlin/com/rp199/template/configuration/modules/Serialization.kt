package com.rp199.template.configuration.modules

import io.ktor.serialization.ContentConverter
import io.ktor.serialization.kotlinx.KotlinxSerializationConverter
import io.ktor.serialization.kotlinx.json.DefaultJson
import io.ktor.server.plugins.di.DependencyRegistry
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonNamingStrategy

@OptIn(ExperimentalSerializationApi::class)
fun DependencyRegistry.installSerialization() {
    provide<Json> {
        Json(DefaultJson) {
            namingStrategy = JsonNamingStrategy.SnakeCase
        }
    }

    provide<ContentConverter> {
        KotlinxSerializationConverter(format = resolve())
    }
}