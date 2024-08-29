package com.sparta.baedallegend.shop.controller;

import com.sparta.baedallegend.shop.controller.dto.CreateShopRequest;
import com.sparta.baedallegend.shop.controller.dto.FindAllShopResponse;
import com.sparta.baedallegend.shop.controller.dto.ReadOneShopRequest;
import com.sparta.baedallegend.shop.controller.dto.ReadOneShopResponse;
import com.sparta.baedallegend.shop.service.ShopService;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/shop")
@RequiredArgsConstructor
public class ShopController {

	private final ShopService shopService;

	@PostMapping("/{userId}")
	public ResponseEntity<Void> create(@PathVariable("userId") Long userId,
		@RequestBody CreateShopRequest createShopRequest) {

		final String shopId = shopService.create(userId, createShopRequest);

		URI uri = UriComponentsBuilder
			.fromUriString("/shop/{shopId}")
			.buildAndExpand(shopId)
			.toUri();

		return ResponseEntity.created(uri).build();
	}

	@GetMapping
	public ResponseEntity<Page<FindAllShopResponse>> findAll(
		@RequestParam(value = "page", defaultValue = "0") int page,
		@RequestParam(value = "size", defaultValue = "1") int size) {
		PageRequest pageRequest = PageRequest.of(page, size);
		Page<FindAllShopResponse> responses = shopService.findAll(pageRequest);
		return ResponseEntity.ok().body(responses);
	}

	@GetMapping("/details")
	public ResponseEntity<ReadOneShopResponse> readOne(@RequestBody ReadOneShopRequest request) {
		ReadOneShopResponse response = shopService.readOne(request);
		return ResponseEntity.ok().body(response);
	}

}
