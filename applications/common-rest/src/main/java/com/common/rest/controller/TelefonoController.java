/**
 * 
 */
package com.common.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.common.services.TelefonoService;

/**
 * @author efrain
 *
 */
@RestController
public class TelefonoController {
	@Autowired
	TelefonoService telefonoService;
	
}
