/**
 * 
 */
package com.common.dao;

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
	TipoBaseDao tipoBaseDao;
	
	@SuppressWarnings("unchecked")
	@Test
	public void testFindAllTipoBases(){
		List<TipoBase> tipoBases = (List<TipoBase>) tipoBaseDao.findAll(TipoBase.class);
		logger.info("#####"+tipoBases);
	}
	
	@Test
	public void testFindByCategoriasActivas(){
		List<TipoBase> tipoBases = (List<TipoBase>) tipoBaseDao.findByTiposBaseXCategoriasActivas("MODULE_STATUS");
		logger.info("#####"+tipoBases);
	}
}
