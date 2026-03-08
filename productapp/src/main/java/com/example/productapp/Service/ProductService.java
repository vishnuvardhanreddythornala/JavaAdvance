package com.example.productapp.Service;

import com.example.productapp.Model.Product;
import com.example.productapp.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private  final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }
    public void saveProduct(Product product){
        repository.save(product);
    }
    public List<Product> getAllProducts(){
        return  repository.findAll();
    }
    public Optional<Product> getProductById(Long id){
        return repository.findById(id);
    }

    public void deleteProduct(long id){
        repository.deleteById(id);
    }

}
