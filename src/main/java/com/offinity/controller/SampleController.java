package com.offinity.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.offinity.dto.SampleDto;
import com.offinity.service.SampleService;

@RestController
public class SampleController {
	private SampleService sampleService;

	
	public SampleController(SampleService sampleService) {
		this.sampleService = sampleService;
	}


	@GetMapping("/api/sample")
	public List<SampleDto> getSampleList() {
		return sampleService.getSampleList();
	};
}
