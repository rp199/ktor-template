package com.rp199.template.configuration.modules

import io.ktor.serialization.ContentConverter
import io.ktor.serialization.kotlinx.KotlinxSerializationConverter
import io.ktor.serialization.kotlinx.json.DefaultJson
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonNamingStrategy
import org.koin.dsl.bind
import org.koin.dsl.module

@OptIn(ExperimentalSerializationApi::class)
val serialization = module {
    single<Json> {
        Json(DefaultJson) {
            namingStrategy = JsonNamingStrategy.SnakeCase
        }
    }

    single {
        KotlinxSerializationConverter(format = get<Json>())
    } bind ContentConverter::class

}