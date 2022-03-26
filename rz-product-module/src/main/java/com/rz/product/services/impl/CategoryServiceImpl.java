package com.rz.product.services.impl;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.tomcat.websocket.AuthenticationException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rz.core.shop.base.constants.UserErrorConstants;
import com.rz.core.shop.base.enums.StatusEnum;
import com.rz.core.shop.exceptions.UserApiException;
import com.rz.core.shop.user.services.CustomUserDetailsService;
import com.rz.product.base.constants.CategoryErrorConstants;
import com.rz.product.base.constants.FilesErrorConstants;
import com.rz.product.dto.CategoryDto;
import com.rz.product.dto.ProductImageDto;
import com.rz.product.dto.SubCategoryDto;
import com.rz.product.exceptions.CategoryApiException;
import com.rz.product.exceptions.FilesApiException;
import com.rz.product.model.Category;
import com.rz.product.model.ProductImage;
import com.rz.product.repositories.CategoryRepository;
import com.rz.product.services.CategoryService;
import com.rz.product.services.ProductImageService;
import com.rz.product.services.SubCategoryService;

@Service
@SuppressWarnings({ "unused" })
public class CategoryServiceImpl implements CategoryService {

	public static String uploadDirectory = System.getProperty("user.dir") + "/uploads";

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ProductImageService imageService;

	@Autowired
	private CustomUserDetailsService userDetailService;

	@Autowired
	private SubCategoryService subCategoryService;

	@Value("${file.upload-dir}")
	private String UPLOAD_DIR;

	@Transactional
	@Override
	public CategoryDto addCategory(CategoryDto dto, MultipartFile file) throws Exception {

		// superadmin,admin
		String authenticateUser = userDetailService.authenticatedUser();
		if (authenticateUser == null) {
			throw new UserApiException(UserErrorConstants.USER_UNAUTHORIZED);
		}

		Category category = new Category();
		category = modelMapper.map(dto, Category.class);
		// category.setCreatedBy(authenticateUser);
		category.setStatus(StatusEnum.ACTIVE);

		// save image data
		Path path = Paths.get(UPLOAD_DIR + File.separator + file.getOriginalFilename());
		Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		category.setCategoryImgName(fileName);
		Path filePath = Paths.get(Paths.get(UPLOAD_DIR).toString());
		category.setCategoryImgUrl(filePath.toString());
		category.setCatgoryImgPicByte(file.getBytes());

		category = categoryRepository.save(category);

		CategoryDto categoryDto = modelMapper.map(category, CategoryDto.class);
		return categoryDto;
	}

	@Override
	public CategoryDto updateCategory(CategoryDto dto, Long id) throws Exception {
		Optional<Category> category = Optional.ofNullable(categoryRepository.findById(id)
				.orElseThrow(() -> new CategoryApiException(CategoryErrorConstants.CATEGORY_NOT_FOUND)));
		Category categoryModel = new Category();
		dto.setCategoryId(id);
		categoryModel = modelMapper.map(dto, Category.class);
		categoryModel = categoryRepository.save(categoryModel);
		CategoryDto categoryDto = modelMapper.map(categoryModel, CategoryDto.class);
		return categoryDto;
	}

	@Override
	public List<CategoryDto> getCategories() throws Exception {
		List<CategoryDto> list = new ArrayList<CategoryDto>();
		List<Category> category = (List<Category>) categoryRepository.findAll();

		if (category == null) {
			throw new CategoryApiException(CategoryErrorConstants.CATEGORY_NOT_FOUND);
		}
		for (Category category2 : category) {
			String filePath = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/image/download/")
					.path(category2.getCategoryImgName()).toUriString();
			category2.setCategoryImgUrl(filePath);
			CategoryDto dto = modelMapper.map(category2, CategoryDto.class);
			list.add(dto);
		}
		return list;
	}

	@Override
	public CategoryDto getCategory(Long id) throws Exception {
		Optional<Category> category = Optional.ofNullable(categoryRepository.findById(id)
				.orElseThrow(() -> new CategoryApiException(CategoryErrorConstants.CATEGORY_NOT_FOUND)));
		String filePath = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/image/download/")
				.path(category.get().getCategoryImgName()).toUriString();
		category.get().setCategoryImgUrl(filePath);

		// get sub categories
		List<SubCategoryDto> subCategoryDtoList = subCategoryService.findByCategoryId(id);

		CategoryDto dto = modelMapper.map(category.get(), CategoryDto.class);
		dto.setSubCategory(subCategoryDtoList);
		return dto;
	}

	@Transactional
	@Override
	public void deleteCategory(Long id) throws Exception {
		Optional<Category> category = Optional.ofNullable(categoryRepository.findById(id)
				.orElseThrow(() -> new CategoryApiException(CategoryErrorConstants.CATEGORY_NOT_FOUND)));
		category.get().setStatus(StatusEnum.DELETED);
		categoryRepository.save(category.get());
	}

}
