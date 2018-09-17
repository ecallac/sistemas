/**
 * 
 */
package com.ecallac.rentcar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecallac.rentcar.domain.Rol;
import com.ecallac.rentcar.repository.RolRepository;

/**
 * @author efrain.calla
 *
 */
@Service
public class RolServiceImpl implements RolService{
	@Autowired
	RolRepository rolRepository;

	@Override
	public List<Rol> findAll() {
		return rolRepository.findAll();
	}

	@Override
	public List<Rol> findAllByStatus(String status) {
		// TODO Auto-generated method stub
		return rolRepository.findAllByStatus(status);
	}
	
}
