package com.example.openfeignexample.controller

import com.example.openfeignexample.controller.form.ItemForm
import com.example.openfeignexample.service.ItemService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping

@Controller
class ItemController(
	private val itemService: ItemService
) {

	@GetMapping("/")
	fun index(model: Model): String {
		model["itemForm"] = ItemForm()
		model["widget_data_id"] = System.getenv("WIDGET_DATA_ID")
		return "item"
	}

	@PostMapping("/search")
	fun search(
		model: Model,
		@Validated @ModelAttribute itemForm: ItemForm,
		bindingResult: BindingResult
	): String {
		model["itemForm"] = itemForm
		model["widget_data_id"] = System.getenv("WIDGET_DATA_ID")
		if (bindingResult.hasErrors()) {
			return "item"
		}

		model["items"] = itemService.getItems(itemForm)
		return "item"
	}
}
