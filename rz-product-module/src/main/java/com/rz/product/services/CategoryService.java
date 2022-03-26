package com.rz.product.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.rz.product.dto.CategoryDto;

public interface CategoryService {

	public CategoryDto addCategory(CategoryDto dto, MultipartFile file) throws Exception;

	public CategoryDto updateCategory(CategoryDto dto, Long id) throws Exception;

	public List<CategoryDto> getCategories() throws Exception;

	public CategoryDto getCategory(Long id) throws Exception;

	public void deleteCategory(Long id) throws Exception;

}
