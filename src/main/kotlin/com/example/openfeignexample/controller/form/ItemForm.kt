package com.example.openfeignexample.controller.form

import jakarta.validation.constraints.NotBlank

data class ItemForm(
	@field:NotBlank
	val keyword: String = ""
)
