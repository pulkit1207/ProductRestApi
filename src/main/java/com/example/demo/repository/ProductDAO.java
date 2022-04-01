package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.entity.Product;

public interface ProductDAO extends MongoRepository<Product /*class*/, String /*primary key*/>{
	
	

}
