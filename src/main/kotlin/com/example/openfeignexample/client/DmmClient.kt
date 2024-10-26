package com.example.openfeignexample.client

import com.example.openfeignexample.client.dto.GetItemsRequest
import com.example.openfeignexample.client.dto.GetItemsResponse
import com.example.openfeignexample.config.interceptor.DmmApiConfig
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.cloud.openfeign.SpringQueryMap
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping

@FeignClient(name = "DMMClient", url = "\${dmm-api.url}", configuration = [DmmApiConfig::class])
interface DmmClient {

	@GetMapping("/ItemList")
	fun getItems(@SpringQueryMap request: GetItemsRequest): ResponseEntity<GetItemsResponse>
}
