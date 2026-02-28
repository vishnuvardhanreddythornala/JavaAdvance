package com.example.productapp.Controller;

import com.example.productapp.Model.Product;
import com.example.productapp.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/products")
@RestController
public class ProductRestController {

    @Autowired
    private final ProductService service;

    public ProductRestController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public List<Product> getAllProducts(){
        return service.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id){
        return service.getProductById(id)
                .orElseThrow(() -> new RuntimeException("product not found"));
    }
    @PostMapping
    public String addProduct(@RequestBody Product product){
        service.saveProduct(product);
        return "Product Added Successfully";
    }
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id){
        service.deleteProduct(id);
        return "Product deleted successfully";
    }
}