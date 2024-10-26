package com.example.openfeignexample.constant

import feign.Logger
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.logging.LogLevel
import org.springframework.context.annotation.Configuration

@Configuration
class DmmApiConfigProperty {

	@Value("\${dmm-api.url}")
	val url: String = ""

	@Value("\${dmm-api.api-id}")
	val apiId: String = ""

	@Value("\${dmm-api.affiliate-id}")
	val affiliateId: String = ""

	@Value("\${dmm-api.retry-max-count}")
	val retryMaxCount: Int = 0

	@Value("\${dmm-api.retry-period}")
	val retryPeriod: Long = 0

	@Value("\${dmm-api.retry-duration}")
	val retryDuration: Long = 0
}
