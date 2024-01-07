package com.rp199.template.configuration.plugin

import com.rp199.template.configuration.AppConfig
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.application.install
import io.ktor.server.metrics.micrometer.MicrometerMetrics
import io.ktor.server.plugins.callid.CallId
import io.ktor.server.plugins.callid.callIdMdc
import io.ktor.server.plugins.callloging.CallLogging
import io.ktor.server.request.httpMethod
import io.ktor.server.request.path
import io.ktor.server.request.userAgent
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import io.micrometer.core.instrument.binder.jvm.ClassLoaderMetrics
import io.micrometer.core.instrument.binder.jvm.JvmGcMetrics
import io.micrometer.core.instrument.binder.jvm.JvmMemoryMetrics
import io.micrometer.core.instrument.binder.jvm.JvmThreadMetrics
import io.micrometer.core.instrument.binder.system.FileDescriptorMetrics
import io.micrometer.core.instrument.binder.system.ProcessorMetrics
import io.micrometer.core.instrument.binder.system.UptimeMetrics
import io.micrometer.prometheus.PrometheusConfig
import io.micrometer.prometheus.PrometheusMeterRegistry
import org.slf4j.event.Level
import java.util.UUID

fun Application.installCallLogging(appConfig: AppConfig) {
    install(CallLogging) {
        level = Level.INFO
        disableDefaultColors()
        filter { call ->
            call.request.path().startsWith("/")
                    && !call.request.path().startsWith("/.well-known")
        }
        mdc("application") {
            appConfig.applicationName
        }
        mdc("path") {
            it.request.path()
        }
        mdc("method") {
            it.request.httpMethod.toString()
        }
        mdc("status") {
            it.response.status()?.toString()
        }
        callIdMdc("call-id")
        mdc("user-agent") {
            it.request.userAgent()
        }

    }
    install(CallId) {
        generate { UUID.randomUUID().toString() }
    }
}

fun Application.installMetrics(appConfig: AppConfig) {
    val appMicrometerRegistry = PrometheusMeterRegistry(PrometheusConfig.DEFAULT).apply {
        config().commonTags("application", appConfig.applicationName)
    }

    install(MicrometerMetrics) {
        registry = appMicrometerRegistry
        meterBinders = listOf(
            ClassLoaderMetrics(),
            JvmMemoryMetrics(),
            JvmGcMetrics(),
            ProcessorMetrics(),
            JvmThreadMetrics(),
            FileDescriptorMetrics(),
            UptimeMetrics()
        )
    }
    routing {
        get("/.well-known/metrics") {
            call.respond(appMicrometerRegistry.scrape())
        }
    }
}