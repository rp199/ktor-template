package com.rp199.template.routes

import io.kotest.assertions.json.shouldBeValidJson
import io.kotest.assertions.json.shouldEqualJson
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.testApplication

class SampleRouteKtTest : ShouldSpec() {

    init {
        should("application start and respond") {
            testApplication {

                client.get("/test").apply {
                    status shouldBe HttpStatusCode.OK
                    bodyAsText().shouldBeValidJson().shouldEqualJson(
                        """
                            {
                                "sample_value": "I did stuff"
                            }
                        """.trimIndent()
                    )
                }
            }
        }
    }
}