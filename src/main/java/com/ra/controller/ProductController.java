package com.ra.controller;

import com.google.cloud.storage.Blob;
import com.ra.model.dto.ProductRequestDTO;
import com.ra.model.entity.Product;
import com.ra.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("")
    public ResponseEntity<List<Product>> findAll(){
        List<Product> products=productService.findAll();
        return ResponseEntity.ok(products);
    }

    @PostMapping("")
    public  ResponseEntity<Product> create(@ModelAttribute ProductRequestDTO productRequestDTO){
        Product product=productService.save(productRequestDTO);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Product> findProductById(@PathVariable Long id){
        Product product=productService.getBlogById(id);
        return new ResponseEntity<>(product,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<Product> update(@PathVariable Long id,@RequestBody ProductRequestDTO productRequestDTO){
        productRequestDTO.setProductId(id);
        Product productUpdate=productService.save(productRequestDTO);
        return  new ResponseEntity<>(productUpdate,HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        productService.deleteBlg(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
//    mamammamama
}

