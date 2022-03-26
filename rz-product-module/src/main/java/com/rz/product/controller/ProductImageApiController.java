package com.rz.product.controller;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rz.core.shop.base.BaseController;
import com.rz.core.shop.base.constants.HttpStatusCodes;
import com.rz.core.shop.base.models.response.Response;
import com.rz.product.base.enums.FilesMessages;
import com.rz.product.dto.ImageListResponseModel;
import com.rz.product.dto.ProductDto;
import com.rz.product.dto.ProductImageDto;
import com.rz.product.model.Product;
import com.rz.product.repositories.ProductRepository;
import com.rz.product.services.ProductImageService;

/**
 * @author ravi
 *
 */
@RestController
@RequestMapping(value = "/api/v1")
@SuppressWarnings({ "unused" })
public class ProductImageApiController extends BaseController {

	public static String uploadDirectory = System.getProperty("user.dir") + "/uploads";

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductImageApiController.class);

	@Autowired
	private ProductImageService imageService;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ModelMapper modelMapper;

	/**
	 * upload multiple product images
	 * 
	 * @param files
	 * @param productId
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/image/upload/{productId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)

	public ResponseEntity<Response> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files,
			@PathVariable Long productId) throws Exception {

		List<ProductImageDto> listOfImages = new ArrayList<ProductImageDto>();
		for (MultipartFile file : files) {
			if (!file.isEmpty()) {
				ProductImageDto dto = imageService.uploadImages(file, productId);
				listOfImages.add(dto);
			}
		}
		ImageListResponseModel response = new ImageListResponseModel();
		response.setImageList(listOfImages);
		LOGGER.info("Upload multiple images...");
		return createResponse(HttpStatusCodes.SUCCESS_OK, FilesMessages.FILE_SUCCESS, null, 0, response);
	}

	/**
	 * get image
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/image/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> getImage(@PathVariable Long id) {
		ProductImageDto dto = imageService.getImage(id);
		LOGGER.info("Get image...");
		return createResponse(HttpStatusCodes.SUCCESS_OK, FilesMessages.FILE_SUCCESS, null, 0, dto);
	}

	/**
	 * get list images
	 * 
	 * @return
	 */
	@GetMapping(value = "/image", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> getImages() {
		List<ProductImageDto> list = imageService.getAllImages();
		ImageListResponseModel response = new ImageListResponseModel();
		response.setImageList(list);
		LOGGER.info("Getall Product data...");
		return createResponse(HttpStatusCodes.SUCCESS_OK, FilesMessages.FILE_SUCCESS, null, 0, response);
	}

	/**
	 * download image
	 * 
	 * @param imageName
	 * @param request
	 * @return
	 */
	@GetMapping("/image/download/{imageName:.+}")
	public ResponseEntity<Resource> downloadImage(@PathVariable String imageName, HttpServletRequest request) {
		Resource resource = imageService.load(imageName);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; imageName=\"" + resource.getFilename() + "\"")
				.body(resource);
	}

	/**
	 * get image by product id
	 * 
	 * @param productId
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/image/product/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> getImageByProductId(@PathVariable Long productId) throws Exception {
		List<ProductImageDto> list = imageService.getImageByProductId(productId);
		Optional<Product> product = productRepository.findById(productId);
		ProductDto productDto = modelMapper.map(product.get(), ProductDto.class);
		productDto.setImages(list);
		LOGGER.info("Get image...");
		return createResponse(HttpStatusCodes.SUCCESS_OK, FilesMessages.FILE_SUCCESS, null, 0, productDto);
	}

}
