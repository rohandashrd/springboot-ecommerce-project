package com.springboot.ecommerce.service;


import com.springboot.ecommerce.payload.ProductDTO;
import com.springboot.ecommerce.payload.ProductResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ProductService {
    ProductDTO addProduct(ProductDTO productDTO, Long categoryId);

    ProductResponse getAllProducts(Integer pageNumber, Integer pageSize,
                                   String sortBy,String sortOrder);

    ProductResponse searchByCategory(Long categoryId, Integer pageNumber,
                                     Integer pageSize, String sortBy,
                                     String sortOrder);

    ProductResponse searchProductByKeyword(String keyword, Integer pageNumber,
                                           Integer pageSize, String sortBy,
                                           String sortOrder);

    ProductDTO updateProduct(ProductDTO productDTO, Long productId);

    ProductDTO deleteProduct(Long productId);

    ProductDTO updateProductImage(Long productId, MultipartFile image) throws IOException;
}
