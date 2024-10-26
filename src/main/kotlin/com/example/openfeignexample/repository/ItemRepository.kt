package com.example.openfeignexample.repository

import com.example.openfeignexample.client.DmmClient
import com.example.openfeignexample.client.dto.GetItemsRequest
import com.example.openfeignexample.client.dto.GetItemsResponse
import com.example.openfeignexample.exception.EmptyMessageException
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Repository

@Repository
class ItemRepository(
	private val dmmClient: DmmClient
) {

	fun getItems(keyword: String): GetItemsResponse {
		val request = GetItemsRequest(keyword = keyword)
		val response = dmmClient.getItems(request)
		return response.body ?: throw EmptyMessageException("DMM.comから商品情報を取得できませんでした。")
	}
}
