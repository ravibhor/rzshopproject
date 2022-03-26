package com.rz.product.services.impl;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rz.core.shop.base.constants.UserErrorConstants;
import com.rz.core.shop.base.enums.StatusEnum;
import com.rz.core.shop.exceptions.UserApiException;
import com.rz.core.shop.user.services.CustomUserDetailsService;
import com.rz.product.base.constants.SubCategoryErrorConstants;
import com.rz.product.dto.SubCategoryDto;
import com.rz.product.exceptions.SubCategoryApiException;
import com.rz.product.model.SubCategory;
import com.rz.product.repositories.SubCategoryRepository;
import com.rz.product.services.SubCategoryService;

@Service
public class SubCategoryServiceImpl implements SubCategoryService {
	public static String uploadDirectory = System.getProperty("user.dir") + "/uploads";

	@Autowired
	private SubCategoryRepository subCategoryRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Value("${file.upload-dir}")
	private String UPLOAD_DIR;

	@Transactional
	@Override
	public SubCategoryDto addSubCategory(SubCategoryDto dto, MultipartFile file) throws Exception {

		String authenticateUser = userDetailsService.authenticatedUser();
		if (authenticateUser == null) {
			throw new UserApiException(UserErrorConstants.USER_UNAUTHORIZED);
		}

		SubCategory subCategory = new SubCategory();
		subCategory = modelMapper.map(dto, SubCategory.class);
		// save image data
		Path path = Paths.get(UPLOAD_DIR + File.separator + file.getOriginalFilename());
		Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		subCategory.setSubCategoryImgName(fileName);
		Path filePath = Paths.get(Paths.get(UPLOAD_DIR).toString());
		subCategory.setSubCategoryImgUrl(filePath.toString());
		subCategory.setSubCatgoryImgPicByte(file.getBytes());

		subCategory.setStatus(StatusEnum.ACTIVE);
		subCategory.setCreatedBy(authenticateUser);
		subCategory = subCategoryRepository.save(subCategory);
		SubCategoryDto subCategoryDto = modelMapper.map(subCategory, SubCategoryDto.class);
		return subCategoryDto;
	}

	@Transactional
	@Override
	public SubCategoryDto updateSubCategory(SubCategoryDto dto, Long id) throws Exception {

		Optional<SubCategory> sub = Optional.ofNullable(subCategoryRepository.findBySubCategoryId(id)
				.orElseThrow(() -> new SubCategoryApiException(SubCategoryErrorConstants.SUB_CATEGORY_NOT_FOUND)));

		String authenticateUser = userDetailsService.authenticatedUser();
		if (authenticateUser == null) {
			throw new UserApiException(UserErrorConstants.USER_UNAUTHORIZED);
		}

		SubCategory subCategory = new SubCategory();
		dto.setSubCategoryId(id);
		subCategory = modelMapper.map(dto, SubCategory.class);
		subCategory.setStatus(StatusEnum.ACTIVE);
		subCategory.setUpdatedBy(authenticateUser);
		subCategory = subCategoryRepository.save(subCategory);
		SubCategoryDto subCategoryDto = modelMapper.map(subCategory, SubCategoryDto.class);
		return subCategoryDto;
	}

	@Transactional
	@Override
	public List<SubCategoryDto> getSubCategories() throws Exception {
		List<SubCategory> list = subCategoryRepository.findAll();
		List<SubCategoryDto> dtoList = new ArrayList<SubCategoryDto>();
		if (list.isEmpty()) {
			throw new SubCategoryApiException(SubCategoryErrorConstants.SUB_CATEGORY_NOT_FOUND);
		}
		for (SubCategory subCategory : list) {
			String filePath = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/image/download/")
					.path(subCategory.getSubCategoryImgName()).toUriString();
			subCategory.setSubCategoryImgUrl(filePath.toString());
			SubCategoryDto dto = modelMapper.map(subCategory, SubCategoryDto.class);
			dtoList.add(dto);
		}
		return dtoList;
	}

	@Transactional
	@Override
	public void deleteCategory(Long id) throws Exception {
		Optional<SubCategory> subCategory = Optional.ofNullable(subCategoryRepository.findBySubCategoryId(id)
				.orElseThrow(() -> new SubCategoryApiException(SubCategoryErrorConstants.SUB_CATEGORY_NOT_FOUND)));

		subCategory.get().setStatus(StatusEnum.DELETED);
		subCategoryRepository.save(subCategory.get());
	}

	@Transactional
	@Override
	public SubCategoryDto getSubCategory(Long id) throws Exception {
		Optional<SubCategory> subCategory = Optional.ofNullable(subCategoryRepository.findBySubCategoryId(id)
				.orElseThrow(() -> new SubCategoryApiException(SubCategoryErrorConstants.SUB_CATEGORY_NOT_FOUND)));

		String filePath = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/image/download/")
				.path(subCategory.get().getSubCategoryImgName()).toUriString();
		subCategory.get().setSubCategoryImgUrl(filePath.toString());
		SubCategoryDto dto = modelMapper.map(subCategory.get(), SubCategoryDto.class);
		return dto;
	}

	@Transactional
	@Override
	public List<SubCategoryDto> findByCategoryId(Long categoryId) throws Exception {
		List<SubCategory> list = new ArrayList<SubCategory>();
		List<SubCategoryDto> dtoList = new ArrayList<SubCategoryDto>();
		subCategoryRepository.findByCategoryId(categoryId).forEach(list::add);
		if (list.isEmpty()) {
			throw new SubCategoryApiException(SubCategoryErrorConstants.SUB_CATEGORY_NOT_FOUND);
		}
		for (SubCategory subCategory : list) {
			String filePath = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/image/download/")
					.path(subCategory.getSubCategoryImgName()).toUriString();
			subCategory.setSubCategoryImgUrl(filePath);
			SubCategoryDto dto = modelMapper.map(subCategory, SubCategoryDto.class);
			dtoList.add(dto);
		}
		return dtoList;
	}

}
