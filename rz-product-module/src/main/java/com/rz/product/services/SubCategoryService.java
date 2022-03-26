package com.rz.product.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.rz.product.dto.SubCategoryDto;

public interface SubCategoryService {
	
	public SubCategoryDto addSubCategory(SubCategoryDto dto,MultipartFile file) throws Exception;

	public SubCategoryDto updateSubCategory(SubCategoryDto dto, Long id) throws Exception;

	public List<SubCategoryDto> getSubCategories() throws Exception;
	
	public SubCategoryDto getSubCategory(Long id) throws Exception;

	public void deleteCategory(Long id) throws Exception;
	
	public List<SubCategoryDto> findByCategoryId(Long categoryId) throws Exception;
}
