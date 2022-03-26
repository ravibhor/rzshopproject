package com.rz.core.shop.user.service.impl;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rz.core.shop.base.enums.StatusEnum;
import com.rz.core.shop.user.dto.RolesDto;
import com.rz.core.shop.user.model.Role;
import com.rz.core.shop.user.repositories.RoleRepository;
import com.rz.core.shop.user.services.RolesService;

@Service
@SuppressWarnings({ "unused" })
public class RolesServiceImpl implements RolesService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RolesServiceImpl.class);

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public RolesDto createRoles(RolesDto rolesDto) throws Exception {
		Role role = new Role();
		BeanUtils.copyProperties(rolesDto, role);
		role.setStatus(StatusEnum.ACTIVE);
		// save user
		role = roleRepository.save(role);
		RolesDto dto = modelMapper.map(role, RolesDto.class);
		return dto;
	}

}
