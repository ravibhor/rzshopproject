package com.rz.product.services.impl;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.apache.tomcat.websocket.AuthenticationException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rz.core.shop.base.constants.UserErrorConstants;
import com.rz.core.shop.base.enums.StatusEnum;
import com.rz.core.shop.exceptions.UserApiException;
import com.rz.core.shop.user.services.CustomUserDetailsService;
import com.rz.product.base.constants.FilesErrorConstants;
import com.rz.product.base.constants.ProductErrorConstants;
import com.rz.product.dto.ProductImageDto;
import com.rz.product.exceptions.FilesApiException;
import com.rz.product.exceptions.ProductApiException;
import com.rz.product.model.Product;
import com.rz.product.model.ProductImage;
import com.rz.product.repositories.ProductImageRepository;
import com.rz.product.repositories.ProductRepository;
import com.rz.product.services.ProductImageService;

@Service
public class ProductImageServiceImpl implements ProductImageService {

	public static String uploadDirectory = System.getProperty("user.dir") + "/uploads";

	@Autowired
	private ProductImageRepository imageRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CustomUserDetailsService userDetailService;

	@Value("${file.upload-dir}")
	private String UPLOAD_DIR;

	@PostConstruct
	public void init() {
		try {
			Files.createDirectories(Paths.get(UPLOAD_DIR).toAbsolutePath().normalize());
		} catch (IOException e) {
			throw new RuntimeException("Could not create upload folder!");
		}
	}

	@Transactional
	@Override
	public ProductImageDto uploadImages(MultipartFile file, long productId) throws Exception {

		//set role superadmin,admin
		String authenticateUser = userDetailService.authenticatedUser();
		if (authenticateUser == null) {
			throw new UserApiException(UserErrorConstants.USER_UNAUTHORIZED);
		}
		
		ProductImage img = imageRepository.findByProductImgName(file.getOriginalFilename());
		if (img != null) {
			throw new FilesApiException(FilesErrorConstants.FILE_ALREADY_EXISTS);
		}

		// get product details
		Optional<Product> prod = Optional.ofNullable(productRepository.findById(productId)
				.orElseThrow(() -> new ProductApiException(ProductErrorConstants.PRODUCT_NOT_FOUND)));

		Path path = Paths.get(UPLOAD_DIR + File.separator + file.getOriginalFilename());
		Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		ProductImage image = new ProductImage();
		image.setProductImgName(fileName);
		Path filePath = Paths.get(Paths.get(UPLOAD_DIR).toString());
		image.setProductImgUrl(filePath.toString());
		image.setProductImgType(file.getContentType());
		image.setProductImgPicByte(file.getBytes());
		image.setProductImgSize(file.getSize());

		// set product images
		image.setProduct(prod.get());

		image.setStatus(StatusEnum.ACTIVE);
		image.setCreatedBy(authenticateUser);
		image = imageRepository.save(image);
		ProductImageDto response = modelMapper.map(image, ProductImageDto.class);
		return response;
	}

	@Override
	public Resource load(String filename) {
		try {
			Path file = Paths.get(UPLOAD_DIR.toString()).resolve(filename).normalize();
			Resource resource = new UrlResource(file.toUri());

			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new RuntimeException("Could not read the file!");
			}
		} catch (MalformedURLException e) {
			throw new RuntimeException("Error: " + e.getMessage());
		}
	}

	@Override
	public ProductImageDto getImage(Long id) throws NumberFormatException {
		Optional<ProductImage> image = Optional.ofNullable(imageRepository.findById(id)
				.orElseThrow(() -> new FilesApiException(FilesErrorConstants.FILE_NOT_FOUND)));
		String filePath = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/image/download/")
				.path(image.get().getProductImgName()).toUriString();
		image.get().setProductImgUrl(filePath);
		ProductImageDto fileResponse = modelMapper.map(image.get(), ProductImageDto.class);
		return fileResponse;
	}

	@Override
	public List<ProductImageDto> getAllImages() {
		List<ProductImageDto> list = new ArrayList<ProductImageDto>();
		List<ProductImage> images = imageRepository.findAll();
		for (ProductImage image : images) {
			String filePath = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/image/download/")
					.path(image.getProductImgName()).toUriString();
			image.setProductImgUrl(filePath);
			ProductImageDto dto = modelMapper.map(image, ProductImageDto.class);
			list.add(dto);
		}
		return list;
	}

	@Transactional
	@Override
	public List<ProductImageDto> getImageByProductId(Long productId) throws Exception {
		List<ProductImageDto> list = new ArrayList<ProductImageDto>();
		List<ProductImage> images = imageRepository.findByProductId(productId);
		if (images.isEmpty()) {
			throw new FilesApiException(FilesErrorConstants.FILE_NOT_FOUND);
		}
		for (ProductImage image : images) {
			String filePath = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/image/download/")
					.path(image.getProductImgName()).toUriString();
			image.setProductImgUrl(filePath);
			ProductImageDto dto = modelMapper.map(image, ProductImageDto.class);
			list.add(dto);
		}
		return list;
	}

}
