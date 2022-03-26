package com.rz.product.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rz.core.shop.base.BaseController;
import com.rz.core.shop.base.constants.HttpStatusCodes;
import com.rz.core.shop.base.models.response.Response;
import com.rz.product.base.enums.CategoriesMessages;
import com.rz.product.base.enums.ProductMessages;
import com.rz.product.dto.CategoryDto;
import com.rz.product.dto.CategoryListResponseModel;
import com.rz.product.dto.ProductDto;
import com.rz.product.dto.ProductListResponseModel;
import com.rz.product.services.CategoryService;
import com.rz.product.services.ProductService;

/**
 * @author ravi
 *
 */
@RestController
@RequestMapping(value = "/api/v1")
@SuppressWarnings({ "unused" })
public class ProductApiController extends BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductApiController.class);

	@Autowired
	private ProductService productService;

	/**
	 * add product
	 * 
	 * @param productDto
	 * @param files
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/product", produces = MediaType.APPLICATION_JSON_VALUE, consumes = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<Response> addProduct(@RequestParam("productDto") String productDto,
			@RequestParam("file") MultipartFile[] files) throws Exception {
		ProductDto dto = new ProductDto();
		ObjectMapper objectMapper = new ObjectMapper();
		dto = objectMapper.readValue(productDto, ProductDto.class);
		ProductDto prodDto = productService.addProduct(dto, files);
		LOGGER.info("Product added success...");
		return createResponse(HttpStatusCodes.SUCCESS_CREATED, ProductMessages.PRODUCT_CREATED, null, 0, prodDto);
	}

	/**
	 * get product
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/product/{id}", produces = MediaType.APPLICATION_JSON_VALUE)

	public ResponseEntity<Response> getProduct(@PathVariable long id) throws Exception {
		ProductDto dto = productService.getProduct(id);
		LOGGER.info("Get Product data...");
		return createResponse(HttpStatusCodes.SUCCESS_OK, 0, dto);
	}

	/**
	 * get list products
	 * 
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/product", produces = MediaType.APPLICATION_JSON_VALUE)

	public ResponseEntity<Response> getProducts() throws Exception {
		List<ProductDto> list = productService.getProducts();
		ProductListResponseModel response = new ProductListResponseModel();
		response.setProductList(list);
		LOGGER.info("Getall Product data...");
		return createResponse(HttpStatusCodes.SUCCESS_OK, ProductMessages.PRODUCT_SUCCESS, null, 0, response);
	}

	/**
	 * delete product
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@DeleteMapping(value = "/product/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> deleteProduct(@PathVariable long id) throws Exception {
		productService.deleteProduct(id);
		LOGGER.info("Product deleted...");
		return createResponse(HttpStatusCodes.SUCCESS_OK, ProductMessages.PRODUCT_DELETED, null, 0);
	}

	/**
	 * update product
	 * 
	 * @param productDto
	 * @param files
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@PutMapping(value = "/product/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<Response> updateProduct(@RequestParam("productDto") String productDto,
			@RequestParam("file") MultipartFile[] files, @PathVariable long id) throws Exception {
		ProductDto dto = new ProductDto();
		ObjectMapper objectMapper = new ObjectMapper();
		dto = objectMapper.readValue(productDto, ProductDto.class);
		ProductDto prodDto = productService.updateProduct(dto, files, id);
		LOGGER.info("Product updated...");
		return createResponse(HttpStatusCodes.SUCCESS_OK, ProductMessages.PRODUCT_SUCCESS, null, 0, prodDto);
	}

}
