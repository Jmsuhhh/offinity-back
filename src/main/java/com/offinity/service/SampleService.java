package com.offinity.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.offinity.dto.Sample;
import com.offinity.dto.SampleDto;
import com.offinity.mapper.SampleMapper;

@Service
public class SampleService {
	private SampleMapper sampleMapper;

	public SampleService(SampleMapper sampleMapper) {
		this.sampleMapper = sampleMapper;
	}
	
	public List<SampleDto> getSampleList(){
		List<Sample> listFromDB= sampleMapper.getSampleList();
		List<SampleDto> listToFront = new ArrayList<>();
		for(Sample s : listFromDB) {
			SampleDto dto = new SampleDto();
			dto.setSampleColumn(s.getSampleColumn());
			listToFront.add(dto);
		}
		return listToFront;
	}
	

}

