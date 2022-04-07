package com.example.demo.restcontroller;

import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/Products")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/Products/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable String productId){
    	Product p = productService.getProduct(productId);
        if (null!= p) {
            return new ResponseEntity<Product>(p, HttpStatus.OK);
        } else {
            return new ResponseEntity<Product>(new Product(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/Products")
    public ResponseEntity<Product> addProduct(@RequestBody Product product){
    	Product p = productService.createProduct(product);
        return new ResponseEntity<Product>(p, HttpStatus.CREATED);
    }

    @PutMapping("/Products/{productId}")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product, @PathVariable String productId){
    	
    	product.setId(productId);
    	Product p = productService.getProduct(productId);
    	Product prod = productService.createProduct(product);
    	
        if (null!= p) {
            return new ResponseEntity<Product>(prod, HttpStatus.OK);
        } else {
            return new ResponseEntity<Product>(prod, HttpStatus.CREATED);
        }
    }

    @DeleteMapping("/Products/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable String productId){
    	Product p = productService.getProduct(productId);
        if (p!=null) {
            productService.deleteProduct(productId);
            return new ResponseEntity<String>("Product deleted which is associated with productId: " + productId.toString(), HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("There is no Product which is associated with productId:" + productId.toString(), HttpStatus.NOT_FOUND);
        }
    }
}
