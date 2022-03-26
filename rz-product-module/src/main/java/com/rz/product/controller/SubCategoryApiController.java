package com.rz.product.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
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
import com.rz.core.shop.base.constants.Headers;
import com.rz.core.shop.base.constants.HttpStatusCodes;
import com.rz.core.shop.base.models.response.Response;
import com.rz.core.shop.user.services.CustomUserDetailsService;
import com.rz.product.base.enums.CategoriesMessages;
import com.rz.product.base.enums.SubCategoriesMessages;
import com.rz.product.dto.CategoryDto;
import com.rz.product.dto.CategoryListResponseModel;
import com.rz.product.dto.SubCategoryDto;
import com.rz.product.dto.SubCategoryListResponseModel;
import com.rz.product.services.SubCategoryService;

/**
 * @author ravi
 *
 */
@RestController
@RequestMapping(value = "/api/v1")
@SuppressWarnings({ "unused" })
public class SubCategoryApiController extends BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(SubCategoryApiController.class);

	@Autowired
	private SubCategoryService subCategoryService;

	/**
	 * add subcategory
	 * 
	 * @param subCategoryDto
	 * @param file
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/subcategory", produces = MediaType.APPLICATION_JSON_VALUE, consumes = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<Response> addSubCategory(@RequestParam("subCategoryDto") String subCategoryDto,
			@RequestPart("file") MultipartFile file) throws Exception {
		SubCategoryDto dto = new SubCategoryDto();
		ObjectMapper objectMapper = new ObjectMapper();
		dto = objectMapper.readValue(subCategoryDto, SubCategoryDto.class);
		dto = subCategoryService.addSubCategory(dto, file);
		LOGGER.info("Subcategory added success...");
		return createResponse(HttpStatusCodes.SUCCESS_CREATED, SubCategoriesMessages.SUB_CATEGORY_CREATED, null, 0,
				dto);
	}

	/**
	 * get sub categories
	 * 
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/subcategory", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> getSubCategories() throws Exception {
		List<SubCategoryDto> list = subCategoryService.getSubCategories();
		SubCategoryListResponseModel response = new SubCategoryListResponseModel();
		response.setSubCategoryList(list);
		LOGGER.info("Getall subcategory data...");
		return createResponse(HttpStatusCodes.SUCCESS_OK, SubCategoriesMessages.SUB_CATEGORY_SUCCESS, null, 0,
				response);
	}

	/**
	 * get sub category
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/subcategory/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> getSubCategory(@PathVariable long id) throws Exception {
		SubCategoryDto dto = subCategoryService.getSubCategory(id);
		LOGGER.info("Getall subcategory data...");
		return createResponse(HttpStatusCodes.SUCCESS_OK, SubCategoriesMessages.SUB_CATEGORY_SUCCESS, null, 0, dto);
	}

	/**
	 * update sub category
	 * 
	 * @param dto
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@PutMapping(value = "/subcategory/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> updateSubCategories(@RequestBody SubCategoryDto dto, @PathVariable long id)
			throws Exception {
		SubCategoryDto subCategoryDto = subCategoryService.updateSubCategory(dto, id);
		LOGGER.info("Updated subcategory data...");
		return createResponse(HttpStatusCodes.SUCCESS_OK, SubCategoriesMessages.SUB_CATEGORY_SUCCESS, null, 0,
				subCategoryDto);
	}

	/**
	 * delete sub category
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@DeleteMapping(value = "/subcategory/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> deleteSubCategories(@PathVariable long id) throws Exception {
		subCategoryService.deleteCategory(id);
		LOGGER.info("deleted subcategory...");
		return createResponse(HttpStatusCodes.SUCCESS_OK, SubCategoriesMessages.SUB_CATEGORY_DELETED, null, 0);
	}

	/**
	 * get subcategory by category id
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/subcategory/category/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> findByCategoryId(@PathVariable long id) throws Exception {
		List<SubCategoryDto> list = subCategoryService.findByCategoryId(id);
		SubCategoryListResponseModel response = new SubCategoryListResponseModel();
		response.setSubCategoryList(list);
		LOGGER.info("Getall subcategory data...");
		return createResponse(HttpStatusCodes.SUCCESS_OK, SubCategoriesMessages.SUB_CATEGORY_SUCCESS, null, 0,
				response);
	}

}
