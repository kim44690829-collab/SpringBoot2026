package com.green.book.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.book.dao.DataDAO;

@Service
public class BookService {
	@Autowired
	DataDAO dDao;
	
	
}
