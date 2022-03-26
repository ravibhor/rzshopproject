package com.rz.product.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.rz.core.shop.base.constants.UserErrorConstants;
import com.rz.core.shop.base.enums.StatusEnum;
import com.rz.core.shop.exceptions.UserApiException;
import com.rz.core.shop.user.services.CustomUserDetailsService;
import com.rz.product.base.constants.ProductErrorConstants;
import com.rz.product.dto.ProductDto;
import com.rz.product.dto.ProductImageDto;
import com.rz.product.exceptions.ProductApiException;
import com.rz.product.model.Product;
import com.rz.product.model.ProductImage;
import com.rz.product.repositories.ProductRepository;
import com.rz.product.services.ProductImageService;
import com.rz.product.services.ProductService;

@Service
@SuppressWarnings("unused")
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ProductImageService imageService;

	@Autowired
	private CustomUserDetailsService userDetailService;

	@Transactional
	@Override
	public ProductDto addProduct(ProductDto dto, MultipartFile[] files) throws Exception {
		
		//superadmin,admin
		String authenticateUser = userDetailService.authenticatedUser();
		if (authenticateUser == null) {
			throw new UserApiException(UserErrorConstants.USER_UNAUTHORIZED);
		}

		Product product = new Product();
		product = modelMapper.map(dto, Product.class);
		product.setStatus(StatusEnum.ACTIVE);
		product.setCreatedBy(authenticateUser);
		product = productRepository.save(product);

		// save image
		for (MultipartFile file : files) {
			if (!file.isEmpty()) {
				ProductImageDto imageDto = imageService.uploadImages(file, product.getProductId());
			}
		}

		ProductDto productDto = modelMapper.map(product, ProductDto.class);
		return productDto;
	}

	@Transactional
	@Override
	public ProductDto updateProduct(ProductDto dto, MultipartFile[] files, Long id) throws Exception {
		Optional<Product> prod = Optional.ofNullable(productRepository.findById(id)
				.orElseThrow(() -> new ProductApiException(ProductErrorConstants.PRODUCT_NOT_FOUND)));
		Product product = new Product();
		dto.setProductId(id);
		product = modelMapper.map(dto, Product.class);
		product.setStatus(StatusEnum.ACTIVE);
		product = productRepository.save(product);

		// update image

		for (MultipartFile file : files) {
			if (!file.isEmpty()) {
				ProductImageDto imageDto = imageService.uploadImages(file, product.getProductId());
			}
		}

		ProductDto productDto = modelMapper.map(product, ProductDto.class);
		return productDto;
	}

	@Transactional
	@Override
	public List<ProductDto> getProducts() throws Exception {
		List<ProductDto> list = new ArrayList<ProductDto>();
		List<Product> product = productRepository.findAll();
		if (product == null) {
			throw new ProductApiException(ProductErrorConstants.PRODUCT_NOT_FOUND);
		}

		for (Product prod : product) {
			List<ProductImageDto> imageDto = imageService.getImageByProductId(prod.getProductId());

			ProductDto dto = modelMapper.map(prod, ProductDto.class);
			dto.setImages(imageDto);
			list.add(dto);
		}
		return list;
	}

	@Transactional
	@Override
	public ProductDto getProduct(long id) throws Exception {
		Optional<Product> prod = Optional.ofNullable(productRepository.findById(id)
				.orElseThrow(() -> new ProductApiException(ProductErrorConstants.PRODUCT_NOT_FOUND)));

		// Get images by productid
		List<ProductImageDto> imageDto = imageService.getImageByProductId(prod.get().getProductId());
		List<ProductImageDto> list = new ArrayList<ProductImageDto>();
		for (ProductImageDto imgDto : imageDto) {
			ProductImageDto dto = modelMapper.map(imgDto, ProductImageDto.class);
			list.add(dto);
		}
		ProductDto dto = modelMapper.map(prod.get(), ProductDto.class);
		dto.setImages(imageDto);
		return dto;
	}

	@Transactional
	@Override
	public void deleteProduct(Long id) throws Exception {
		Optional<Product> prod = Optional.ofNullable(productRepository.findById(id)
				.orElseThrow(() -> new ProductApiException(ProductErrorConstants.PRODUCT_NOT_FOUND)));
		prod.get().setStatus(StatusEnum.DELETED);

		// deleted product images
		List<ProductImage> img = prod.get().getImages();
		for (ProductImage productImage : img) {
			productImage.setStatus(StatusEnum.DELETED);
		}
		productRepository.save(prod.get());
	}

}
