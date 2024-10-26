package com.example.openfeignexample.client.dto

data class GetItemsRequest(
	val site: String = "DMM.com",
	val keyword: String,
	val hits: Int = 100,
	val sort: String = "date"
)
