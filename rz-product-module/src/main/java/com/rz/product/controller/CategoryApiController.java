package com.rz.product.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rz.core.shop.base.BaseController;
import com.rz.core.shop.base.ListObjectModel;
import com.rz.core.shop.base.constants.HttpStatusCodes;
import com.rz.core.shop.base.enums.UserMessages;
import com.rz.core.shop.base.models.response.Response;
import com.rz.core.shop.user.controller.UserApiController;
import com.rz.core.shop.user.dto.UserDto;
import com.rz.product.base.enums.CategoriesMessages;
import com.rz.product.dto.CategoryDto;
import com.rz.product.dto.CategoryListResponseModel;
import com.rz.product.dto.ProductDto;
import com.rz.product.model.Category;
import com.rz.product.services.CategoryService;

/**
 * @author ravi
 *
 */
@RestController
@RequestMapping(value = "/api/v1")
@SuppressWarnings({ "unused" })
public class CategoryApiController extends BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CategoryApiController.class);

	@Autowired
	private CategoryService categoryService;

	/**
	 * add category
	 * 
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/category", produces = MediaType.APPLICATION_JSON_VALUE, consumes = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<Response> addCategory(@RequestParam("categoryDto") String categoryDto,@RequestPart("file") MultipartFile file)
			throws Exception {
		CategoryDto dto = new CategoryDto();
		ObjectMapper objectMapper = new ObjectMapper();
		dto = objectMapper.readValue(categoryDto, CategoryDto.class);
		dto = categoryService.addCategory(dto, file);
		LOGGER.info("Category added success...");
		return createResponse(HttpStatusCodes.SUCCESS_CREATED, CategoriesMessages.CATEGORY_CREATED, null, 0, dto);
	}

	/**
	 * get category by id
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/category/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> getCategory(@PathVariable long id) throws Exception {
		CategoryDto dto = categoryService.getCategory(id);
		LOGGER.info("Get category data...");
		return createResponse(HttpStatusCodes.SUCCESS_OK, 0, dto);
	}

	/**
	 * get all categories
	 * 
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/category", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> getCategories() throws Exception {
		List<CategoryDto> list = categoryService.getCategories();
		CategoryListResponseModel response = new CategoryListResponseModel();
		response.setCategoryList(list);
		LOGGER.info("Getall category data...");
		return createResponse(HttpStatusCodes.SUCCESS_OK, CategoriesMessages.CATEGORY_SUCCESS, null, 0, response);
	}

	/**
	 * delete category
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@DeleteMapping(value = "/category/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> deleteCategory(@PathVariable long id) throws Exception {
		categoryService.deleteCategory(id);
		LOGGER.info("Category deleted...");
		return createResponse(HttpStatusCodes.SUCCESS_OK, CategoriesMessages.CATEGORY_DELETED, null, 0);
	}

	/**
	 * update category
	 * 
	 * @param dto
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@PutMapping(value = "/category/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> updateCategory(@RequestBody CategoryDto dto, @PathVariable long id)
			throws Exception {
		CategoryDto categoryDto = categoryService.updateCategory(dto, id);
		LOGGER.info("Category updated...");
		return createResponse(HttpStatusCodes.SUCCESS_OK, CategoriesMessages.CATEGORY_SUCCESS, null, 0, categoryDto);
	}

}
