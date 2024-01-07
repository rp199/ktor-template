package com.rp199.template.configuration.modules

import com.rp199.template.service.SampleService
import org.koin.dsl.module

val services = module {
    single {
        SampleService()
    }
}