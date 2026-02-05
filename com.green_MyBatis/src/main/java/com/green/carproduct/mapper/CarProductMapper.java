package com.green.carproduct.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.green.carproduct.CarProductDTO;

@Mapper
public interface CarProductMapper {
	
	// 추상메서드만 들어감
	// carProduct 검색하는 메서드
	public List<CarProductDTO> getAllCarProduct();
	
	public CarProductDTO a();
	
}
