package com.laptrinhjavaweb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laptrinhjavaweb.dto.DonViDTO;
import com.laptrinhjavaweb.entity.DonViEntity;
import com.laptrinhjavaweb.repository.DonViRepository;
import com.laptrinhjavaweb.service.IDonViService;
@Service
public class DonViService implements IDonViService {
    @Autowired
    private DonViRepository donViRepository;
	@Override
	public List<DonViDTO> findAll() {
		// TODO Auto-generated method stub
		List<DonViEntity> list =donViRepository.findAll();
		List<DonViDTO> dtos =new ArrayList<>();
		
		for (DonViEntity entity : list) {
			DonViDTO  dto =new DonViDTO();
			dto.setIddv(entity.getIddv());
			dto.setTendv(entity.getTendv());
			dtos.add(dto);
		}
		
		return dtos;
	}

}
