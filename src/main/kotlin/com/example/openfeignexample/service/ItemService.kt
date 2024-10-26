package com.example.openfeignexample.service

import com.example.openfeignexample.controller.form.ItemForm
import com.example.openfeignexample.model.Item
import com.example.openfeignexample.repository.ItemRepository
import org.springframework.stereotype.Service

@Service
class ItemService(
	private val itemRepository: ItemRepository
){

	fun getItems(form: ItemForm): List<Item> {
		return itemRepository.getItems(form.keyword).result?.items?.map {
			Item(
				thumbnailSrc = it.imageUrl?.small ?: "",
				affiliateUrl = it.affiliateUrl ?: "",
				title = it.title ?: "名無し",
				price = it.prices?.price ?: "価格無し",
				review = it.review?.average ?: "レビュー無し",
				releaseDate = it.releaseDate?.toLocalDate()
			)
		} ?: emptyList()
	}
}
