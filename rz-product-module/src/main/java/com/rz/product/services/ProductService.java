package com.rz.product.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.rz.product.dto.ProductDto;

public interface ProductService {

	public ProductDto addProduct(ProductDto dto, MultipartFile[] files) throws Exception;

	public ProductDto updateProduct(ProductDto dto, MultipartFile[] files, Long id) throws Exception;

	public List<ProductDto> getProducts() throws Exception;

	public ProductDto getProduct(long id) throws Exception;

	public void deleteProduct(Long id) throws Exception;

}
