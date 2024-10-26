package com.example.openfeignexample.config.interceptor

import com.example.openfeignexample.client.constant.DmmHeaders
import com.example.openfeignexample.constant.DmmApiConfigProperty
import com.example.openfeignexample.util.ConfigUtil
import feign.Logger
import feign.Request
import feign.RequestInterceptor
import feign.Response
import feign.RetryableException
import feign.Retryer
import feign.codec.ErrorDecoder
import org.apache.commons.io.IOUtils
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import java.io.InputStream
import java.lang.Exception
import java.net.URLDecoder

class DmmApiConfig(
	private val property: DmmApiConfigProperty,
	private val configUtil: ConfigUtil
) {

	private val log = LoggerFactory.getLogger(this::class.java)

	@Bean
	fun requestInterceptor(): RequestInterceptor {
		return RequestInterceptor { requestTemplate ->
			requestTemplate.query(DmmHeaders.DMM_API_ID, property.apiId)
			requestTemplate.query(DmmHeaders.AFFILIATE_ID, property.affiliateId)

			requestTemplate.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
			requestTemplate.header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
		}
	}

	@Bean
	fun retry(): Retryer {
		return Retryer.Default(
			property.retryPeriod,
			property.retryDuration,
			property.retryMaxCount
		)
	}

	@Bean
	fun errorDecoder(): ErrorDecoder {
		return object : ErrorDecoder {

			override fun decode(methodKey: String?, response: Response?): Exception {
				val status = response?.status()
				if (status == HttpStatus.GATEWAY_TIMEOUT.value()) {
					val request = response.request()
					val method = request?.httpMethod()
					return RetryableException(status, "504: Gateway Timeout.", method, null, request)
				}

				return ErrorDecoder.Default().decode(methodKey, response)
			}
		}
	}

	@Bean
	fun logLevel(): Logger.Level {
		return Logger.Level.FULL
	}

	@Bean
	fun logger(): Logger {
		return object : Logger() {

			override fun log(configKey: String?, format: String?, vararg args: Any?) {
				// Not Used
			}

			override fun logRetry(configKey: String?, logLevel: Level?) {
				log.info(configKey, "RETRYING!!")
			}

			override fun logRequest(configKey: String?, logLevel: Level?, request: Request?) {
				if (logLevel != Level.FULL) {
					return
				}

				val url = request?.requestTemplate()?.path()
				val method = request?.httpMethod()
				val query = buildQuery(request?.requestTemplate()?.queries() ?: emptyMap())
				val body = buildBody(request?.body()?.inputStream())
				log.info(configUtil.getLogMessage("BE8001"), url, method, query, body)
			}

			override fun logAndRebufferResponse(configKey: String?, logLevel: Level?, response: Response?, elapsedTime: Long): Response {
				if (logLevel != Level.FULL) {
					return response!!
				}

				val request = response?.request()
				val url = request?.requestTemplate()?.path()
				val method = request?.httpMethod()
				val query = buildQuery(request?.requestTemplate()?.queries() ?: emptyMap())
				val body = buildBody(request?.body()?.inputStream())
				val status = response?.status()
				log.info(configUtil.getLogMessage("BE8002"), url, method, query, body, status, elapsedTime)
				return response!!
			}

			private fun buildQuery(queryMap: Map<String, Collection<String>>): Map<String, Collection<String>> {
				return queryMap.filterNot {
					it.key == DmmHeaders.DMM_API_ID ||
						it.key == DmmHeaders.AFFILIATE_ID
				}.mapValues {
					it.value.map { value ->
						URLDecoder.decode(value, Charsets.UTF_8)
					}
				}
			}

			private fun buildBody(body: InputStream?): String {
				if (body == null) {
					return "{}"
				}

				return IOUtils.toString(body, Charsets.UTF_8)
			}
		}
	}
}
