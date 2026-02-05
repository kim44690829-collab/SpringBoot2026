package com.green.carproduct;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.carproduct.mapper.CarProductMapper;

@Service
public class CarProductService {

	// mapper DI - 의존객체 삽입을 안하면 SQL문을 사용할수 없음.
	@Autowired
	CarProductMapper carProductmapper;
	
	public List<CarProductDTO> getAllCarProduct(){
		System.out.println("CarProductService : getAllCarProduct() 메서드 확인");
		return carProductmapper.getAllCarProduct();
	}
	
}
