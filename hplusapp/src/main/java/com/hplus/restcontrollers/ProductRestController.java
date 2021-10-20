package com.hplus.restcontrollers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hplus.model.Product;
import com.hplus.repository.ProductRepository;

@RestController
public class ProductRestController {
	@Autowired 
	ProductRepository productRepository;
	
	@GetMapping("/rest/products")
	@ResponseBody
	public List<Product> getProducts(){
		List<Product> products = new ArrayList<>();
        productRepository.findAll().forEach(product -> products.add(product));
        return products;
	}
	
	@GetMapping("/rest/products/{name}")
	public ResponseEntity getProductByName(@PathVariable("name") String name) {
		List<Product> products = productRepository.searchByName(name);
		return new ResponseEntity<>(products, HttpStatus.OK);
	}
	
	@PostMapping("/rest/createproduct")
	public ResponseEntity createProduct(@RequestBody Product product)
	{
		System.out.println("create product controller");
		if(product!=null) {
			productRepository.save(product);
			return new ResponseEntity<>("product is uploaded",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
		}
	}
}
