package com.sparta.baedallegend.menu.controller;

import com.sparta.baedallegend.menu.controller.dto.CreateMenuRequest;
import com.sparta.baedallegend.menu.service.MenuService;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MenuController {

	private final MenuService menuService;

	@PostMapping("/menu")
	public ResponseEntity<Void> createMenu(
		@RequestBody CreateMenuRequest createMenuRequest
	) {
		log.info(createMenuRequest.toString());
		final String menuId = menuService.create(createMenuRequest);

		URI uri = UriComponentsBuilder
			.fromUriString("/menu/{menu_id}")
			.buildAndExpand(menuId)
			.toUri();
		return ResponseEntity.created(uri).build();
	}

}
