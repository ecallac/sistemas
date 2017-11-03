/**
 * 
 */
package com.common.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.common.services.ReglaService;

/**
 * @author efrain
 *
 */
@RestController
public class ReglaController {
	@Autowired
	ReglaService reglaService;
	
}
