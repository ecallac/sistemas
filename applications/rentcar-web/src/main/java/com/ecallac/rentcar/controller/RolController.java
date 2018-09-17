/**
 * 
 */
package com.ecallac.rentcar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecallac.rentcar.domain.Rol;
import com.ecallac.rentcar.service.RolService;

/**
 * @author efrain.calla
 *
 */
@RestController
@RequestMapping("/roles")
public class RolController {

	@Autowired
	private RolService rolService;
	
	@GetMapping(value = "/all")
	public List<Rol> findAll(){
		return rolService.findAll();
	}
	@GetMapping(value = "/allActive")
	public List<Rol> findAllActive(){
		return rolService.findAllByStatus("Y");
	}
}
