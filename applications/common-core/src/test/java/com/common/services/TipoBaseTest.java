/**
 * 
 */
package com.common.services;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.common.domain.TipoBase;

/**
 * @author EFRAIN
 * @dateCreated 4 mar. 2017 22:48:09
 */
public class TipoBaseTest extends AbstractUnitTest{
	
	@Autowired
	TipoBaseService tipoBaseService;
	
	@Test
	public void testFindAllUsers(){
		List<TipoBase> tipoBases =tipoBaseService.findAllTipoBases();
		logger.info("#####"+tipoBases);
	}
}
