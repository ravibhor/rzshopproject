package com.rz.product.services;

import java.io.IOException;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.rz.product.dto.ProductImageDto;

public interface ProductImageService {

	public ProductImageDto uploadImages(MultipartFile file, long productId) throws IOException, Exception;

	public Resource load(String filename);

	public ProductImageDto getImage(Long id);

	public List<ProductImageDto> getAllImages();

	public List<ProductImageDto> getImageByProductId(Long productId) throws Exception;

}
