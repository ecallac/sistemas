/**
 * 
 */
package com.common.rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.common.client.bean.TipoBaseBean;
import com.common.client.canonical.CanonicalResponse;
import com.common.domain.TipoBase;
import com.common.services.TipoBaseService;
import com.common.utils.BeanParser;
import com.common.utils.CommonUtil;

/**
 * @author EFRAIN
 * @dateCreated 17 sept. 2017 22:47:35
 */
@RestController
public class TipoBaseController {
	@Autowired
	TipoBaseService tipoBaseService;
	
	
	@RequestMapping(value = "/getTipoBasesXCategoriasActivas",method = RequestMethod.GET)
	@ResponseBody
    public CanonicalResponse getTipoBasesXCategoriasActivas(@RequestParam(value = "categoria", required = true) String categoria) {
		System.out.println(":::getTipoBasesXCategoriasActivas");
		List<TipoBase> tipoBases = tipoBaseService.findTipoBasesXCategoriaActivos(categoria);
        if (!CommonUtil.containElemnts(tipoBases)) {
        	System.out.println(":::error");
        	CanonicalResponse canonicalResponse = new CanonicalResponse();
        	canonicalResponse.setStatus(CanonicalResponse.STATUS_ERROR);
        	canonicalResponse.setMessage("Rows was not found in the database!");
        	return canonicalResponse;
        }
        CanonicalResponse canonicalResponse = new CanonicalResponse();
    	canonicalResponse.setStatus(CanonicalResponse.STATUS_OK);
    	canonicalResponse.setObjectBean(convertList(tipoBases));
        return canonicalResponse;
    }
	
	@RequestMapping(value = "/tipobase/getTipoBasesXCodigo",method = RequestMethod.GET)
	@ResponseBody
    public CanonicalResponse getTipoBasesXCodigo(@RequestParam(value = "codigo", required = true) String codigo) {
		System.out.println(":::getTipoBasesXCodigo");
		TipoBase tipoBase = tipoBaseService.findByTiposBaseXCodigo(codigo);
        if (tipoBase!=null) {
        	System.out.println(":::error");
        	CanonicalResponse canonicalResponse = new CanonicalResponse();
        	canonicalResponse.setStatus(CanonicalResponse.STATUS_ERROR);
        	canonicalResponse.setMessage("Rows was not found in the database!");
        	return canonicalResponse;
        }
        CanonicalResponse canonicalResponse = new CanonicalResponse();
    	canonicalResponse.setStatus(CanonicalResponse.STATUS_OK);
    	canonicalResponse.setObjectBean(convert(tipoBase));
        return canonicalResponse;
    }
	
	private List<TipoBaseBean> convertList(List<TipoBase> tipoBases){
		List<TipoBaseBean> tipoBaseBeans = new ArrayList<>();
		for (TipoBase tipoBase : tipoBases) {
			TipoBaseBean tipoBaseBean = convert(tipoBase);
			tipoBaseBeans.add(tipoBaseBean);
		}
		
		return tipoBaseBeans;
	}
	
	private TipoBaseBean convert(TipoBase tipoBase){
		return (TipoBaseBean) BeanParser.parseObjectToNewClass(tipoBase, TipoBaseBean.class, null);
	}
}
