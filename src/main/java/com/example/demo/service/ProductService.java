package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductDAO;

@Service
public class ProductService {
	
	@Autowired
     ProductDAO productDao;

	public List<Product> getAllProducts() {
		return  productDao.findAll();
	}

	public Product getProduct(String productId) {
		Optional<Product> product =productDao.findById(productId);
		if(product.isPresent())
		{
			return product.get();
		}
		else
		{
			return new Product();
		}
	}

	public Product createProduct(Product product) 
	{
		return productDao.save(product);
	}
	
	public void deleteProduct(String productID)
	{
		productDao.deleteById(productID);
	}

	public Product updateProduct(Product product) 
	{
		
		return productDao.save(product);
	}

}
