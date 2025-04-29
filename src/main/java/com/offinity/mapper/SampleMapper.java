package com.offinity.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.offinity.dto.Sample;

@Mapper
public interface SampleMapper {

	public List<Sample> getSampleList();
}
