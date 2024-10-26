package com.example.openfeignexample.model

import java.time.LocalDate

data class Item(
	val thumbnailSrc: String,
	val affiliateUrl: String,
	val title: String,
	val price: String,
	val review: String,
	val releaseDate: LocalDate?
)
