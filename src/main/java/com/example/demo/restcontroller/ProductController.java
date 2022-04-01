package com.example.demo.restcontroller;

import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Product getProduct(@PathVariable String productId){
        if (productService.getProduct(productId)!=null) {
            return productService.getProduct(productId);
        } else {
            return new Product();
        }
    }

    @PostMapping("/Products")
    public Product addProduct(@RequestBody Product product){
        return productService.createProduct(product);
    }

    @PutMapping("/Products/{productId}")
    public Product updateProduct(@RequestBody Product product, @PathVariable String productId){
    	product.setId(productId);
    	return productService.updateProduct(product);
    }

    @DeleteMapping("/Products/{productId}")
    public String deleteProduct(@PathVariable String productId){
        if (productService.getProduct(productId)!=null) {
            productService.deleteProduct(productId);
            return "Product deleted which is associated with productId:" + productId.toString();
        } else {
            return "There is no Product which is associated with productId:" + productId.toString();
        }
    }
}
