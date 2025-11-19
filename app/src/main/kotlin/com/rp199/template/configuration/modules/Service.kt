package com.rp199.template.configuration.modules

import com.rp199.template.service.SampleService
import io.ktor.server.plugins.di.DependencyRegistry

fun DependencyRegistry.installServices() {
    provide {
        SampleService()
    }
}