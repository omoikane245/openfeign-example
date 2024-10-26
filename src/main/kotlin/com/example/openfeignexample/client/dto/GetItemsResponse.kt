package com.example.openfeignexample.client.dto

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

data class GetItemsResponse(
	val result: ItemResponse? = null
) {

	data class ItemResponse(
		val status: Int? = null,
		@field:JsonProperty("total_count")
		val totalCount: Int? = null,
		val items: List<Item> = emptyList()
	)

	data class Item(
		@field:JsonProperty("service_code")
		val serviceCode: String?,
		@field:JsonProperty("service_name")
		val serviceName: String?,
		@field:JsonProperty("floor_code")
		val floorCode: String?,
		@field:JsonProperty("floor_name")
		val floorName: String?,
		@field:JsonProperty("category_name")
		val categoryName: String?,
		@field:JsonProperty("content_id")
		val contentId: String?,
		@field:JsonProperty("product_id")
		val productId: String?,
		@field:JsonProperty("title")
		val title: String?,
		@field:JsonProperty("volume")
		val volume: String?,
		@field:JsonProperty("review")
		val review: Review?,
		@field:JsonProperty("URL")
		val url: String?,
		@field:JsonProperty("URLsp")
		val urlSp: String?,
		@field:JsonProperty("affiliateURL")
		val affiliateUrl: String?,
		@field:JsonProperty("affiliateURLsp")
		val affiliateUrlSp: String?,
		@field:JsonProperty("imageURL")
		val imageUrl: ItemImageURL?,
		@field:JsonProperty("sampleImageURL")
		val sampleImageURL: SampleImageURL?,
		@field:JsonProperty("prices")
		val prices: Price?,
		@field:JsonProperty("date")
		@field:JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
		val releaseDate: LocalDateTime?,
		@field:JsonProperty("iteminfo")
		val itemInfo: ItemInfo?
	)

	data class Review(
		val count: Int?,
		val average: String?
	)

	data class ItemImageURL(
		val list: String?,
		val small: String?,
		val large: String?
	)

	data class SampleImageURL(
		@field:JsonProperty("sample_s")
		val sampleS: Image?,
		@field:JsonProperty("sample_l")
		val sampleL: Image?
	)

	data class Image(
		@field:JsonProperty("image")
		val imageList: List<String>
	)

	data class Price(
		val price: String?,
		@field:JsonProperty("list_price")
		val listPrice: String?,
		val deliveries: Deliveries?,
	)

	data class Deliveries(
		val delivery: List<Delivery>,
	)

	data class Delivery(
		val type: String?,
		@field:JsonProperty("price")
		val price: Int?,
		@field:JsonProperty("last_price")
		val lastPrice: Int?
	)

	data class ItemInfo(
		@field:JsonProperty("genre")
		val genres: List<Genre> = emptyList(),
		@field:JsonProperty("series")
		val series: List<Series> = emptyList(),
		@field:JsonProperty("maker")
		val makers: List<Maker> = emptyList()
	)

	data class Genre(
		val id: String,
		val name: String
	)

	data class Series(
		val id: String,
		val name: String
	)

	data class Maker(
		val id: String,
		val name: String
	)
}
