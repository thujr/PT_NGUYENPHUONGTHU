package com.ra.service.product;

import com.google.cloud.storage.Blob;
import com.ra.model.dto.ProductRequestDTO;
import com.ra.model.entity.Product;
import com.ra.repository.ProductRepository;
import com.ra.service.file.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImp implements ProductService{
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private FileService fileService ;
    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product save(ProductRequestDTO productRequestDTO) {
        String img=fileService.uploadFileServer(productRequestDTO.getImage());
        Product productNew= Product.builder()
                .prDescription(productRequestDTO.getPrDescription())
                .image(img)
                .productName(productRequestDTO.getProductName())
                .price(productRequestDTO.getPrice())
                .status(productRequestDTO.isStatus())
                .build();
        return productRepository.save(productNew);
//
    }

    @Override
    public Product getBlogById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteBlg(Long id) {
       productRepository.deleteById(id);
    }
}
