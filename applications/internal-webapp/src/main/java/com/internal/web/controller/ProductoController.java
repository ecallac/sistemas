/**
 * 
 */
package com.internal.web.controller;

import com.BeanParser;
import com.DataTablesInput;
import com.DataTablesOutput;
import com.Utils;
import com.common.Categoria;
import com.common.Marca;
import com.common.Organizacion;
import com.common.Producto;
import com.internal.web.service.LoginService;
import com.internal.web.service.integration.ProductoIntegration;
import com.internal.web.utils.Constants;
import com.internal.web.utils.InternalUtils;
import com.internal.web.view.CategoriaView;
import com.internal.web.view.MarcaView;
import com.internal.web.view.OrganizacionView;
import com.internal.web.view.ProductoView;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author efrain.calla
 *
 */
@Controller
@RequestMapping("/" + ProductoController.NAME)
public class ProductoController {
	public static final String NAME = "producto";
	private Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	LoginService loginService;
	@Autowired
	ProductoIntegration productoIntegration;

	@RequestMapping(value = {"", "/"}, method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView list(HttpSession session, Principal principal) {
		ModelAndView modelAndView = new ModelAndView();
		try {
			loginService.addSessionObjects(session, principal);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		modelAndView.setViewName(ProductoController.NAME);
		return modelAndView;
	}

	@RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Map<String, Object> getAll() {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<Producto> list = productoIntegration.findList();
			if (list != null) {
				map.put("data", list);
			} else {
				map.put("data", new ArrayList<Producto>());
			}
			map.put(Constants.STATUS, Constants.OK);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			map.put(Constants.STATUS, Constants.ERROR);
			map.put(Constants.MESSAGE, Utils.getErrorMessage(Constants.ERROR_MESSAGE_GET, e.getMessage()));
		}
		return map;
	}

	@RequestMapping(value = "/save", method = {RequestMethod.POST})
	public @ResponseBody Map<String, Object> save(@RequestBody @Valid ProductoView view, BindingResult result, Principal principal) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (result.hasErrors()) {
			Map<String, String> errors = result.getFieldErrors().stream()
					.collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));

			map.put(Constants.STATUS, Constants.ERROR);
			map.put("validated", false);
			map.put("messages", errors);
			return map;
		}

		try {
			Producto bean = (Producto) BeanParser.parseObjectToNewClass(view, Producto.class, null);
			if (StringUtils.isNotBlank(view.getCategoriaId())) {
				Categoria categoria = new Categoria();
				categoria.setId(Long.valueOf(view.getCategoriaId()));
				bean.setCategoria(categoria);
			}
			if (StringUtils.isNotBlank(view.getMarcaId())) {
				Marca marca = new Marca();
				marca.setId(Long.valueOf(view.getMarcaId()));
				bean.setMarca(marca);
			}
			if (StringUtils.isNotBlank(view.getLaboratorioId())) {
				Organizacion laboratorio = new Organizacion();
				laboratorio.setId(Long.valueOf(view.getLaboratorioId()));
				bean.setLaboratorio(laboratorio);
			}
			if (bean.getId() == null) {
				bean.setCreatedBy(principal.getName());
			} else {
				bean.setUpdatedBy(principal.getName());
			}
			productoIntegration.save(bean);

			map.put(Constants.STATUS, Constants.OK);
			map.put(Constants.MESSAGE, Utils.getSuccessMessage(Constants.SUCCESS_MESSAGE));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			map.put(Constants.STATUS, Constants.ERROR);
			map.put(Constants.MESSAGE, Utils.getErrorMessage(Constants.ERROR_MESSAGE, e.getMessage()));
		} finally {
			map.put("validated", true);
		}
		return map;
	}

	@RequestMapping(value = "/load", method = {RequestMethod.POST})
	public @ResponseBody Map<String, Object> load(@RequestBody ProductoView view) {
		Map<String, Object> map = new HashMap<String, Object>();

		try {
			Producto bean = productoIntegration.findById(view.getId());
			ProductoView viewStored = (ProductoView) BeanParser.parseObjectToNewClass(bean, ProductoView.class, null);
			if (bean.getCategoria() != null) {
				viewStored.setCategoria((CategoriaView) BeanParser.parseObjectToNewClass(bean.getCategoria(), CategoriaView.class, null));
				viewStored.setCategoriaId(bean.getCategoria().getId().toString());
			}
			if (bean.getMarca() != null) {
				viewStored.setMarca((MarcaView) BeanParser.parseObjectToNewClass(bean.getMarca(), MarcaView.class, null));
				viewStored.setMarcaId(bean.getMarca().getId().toString());
			}
			if (bean.getLaboratorio() != null) {
				viewStored.setLaboratorio((OrganizacionView) BeanParser.parseObjectToNewClass(bean.getLaboratorio(), OrganizacionView.class, null));
				viewStored.setLaboratorioId(bean.getLaboratorio().getId().toString());
			}
			map.put("viewBean", viewStored);
			map.put(Constants.STATUS, Constants.OK);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			map.put(Constants.STATUS, Constants.ERROR);
			map.put(Constants.MESSAGE, Utils.getErrorMessage(Constants.ERROR_MESSAGE_GET, e.getMessage()));
		}
		return map;
	}

	@RequestMapping(value = "/verifyNombre", method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody Map<String, Object> verifyNombre(@RequestParam(value = "nombre", required = true) String nombre) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Producto bean = productoIntegration.findByNombre(nombre);
			if (bean == null) {
				map.put(Constants.STATUS, Constants.OK);
			} else {
				map.put(Constants.STATUS, Constants.ERROR);
				map.put(Constants.MESSAGE, Constants.ERROR_MESSAGE_EXIST);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			map.put(Constants.STATUS, Constants.ERROR);
			map.put(Constants.MESSAGE, Utils.getErrorMessage(Constants.ERROR_MESSAGE_GET, e.getMessage()));
		}
		return map;
	}

	@RequestMapping(value = "/findByPage", method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody Map<String, Object> findByPage(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Map<String, Object> parameterMap = InternalUtils.getParameterMap(request);
			DataTablesInput<Producto> dataTablesInput = InternalUtils.createDataTablesInput(parameterMap);
			dataTablesInput.setObject(new Producto());

			DataTablesOutput<Producto> dataTablesOutput = productoIntegration.findDataTables(dataTablesInput);
			if (dataTablesOutput != null) {
				map.put("data", castProductoToProductoViewList(dataTablesOutput.getData()));
				map.put("draw", dataTablesOutput.getDraw());
				map.put("recordsTotal", dataTablesOutput.getRecordsTotal());
				map.put("recordsFiltered", dataTablesOutput.getRecordsFiltered());
			} else {
				map.put("data", new ArrayList<ProductoView>());
			}

			map.put(Constants.STATUS, Constants.OK);
			map.put(Constants.MESSAGE, Utils.getSuccessMessage(Constants.SUCCESS_MESSAGE));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			map.put(Constants.STATUS, Constants.ERROR);
			map.put(Constants.MESSAGE, Utils.getErrorMessage(Constants.ERROR_MESSAGE, e.getMessage()));
		}
		return map;
	}

	public List<ProductoView> castProductoToProductoViewList(List<Producto> list) throws Exception {
		return list.stream().map(bean -> {
			ProductoView view = (ProductoView) BeanParser.parseObjectToNewClass(bean, ProductoView.class, null);
			view.setCategoria((CategoriaView) BeanParser.parseObjectToNewClass(bean.getCategoria(), CategoriaView.class, null));
			view.setMarca((MarcaView) BeanParser.parseObjectToNewClass(bean.getMarca(), MarcaView.class, null));
			view.setLaboratorio((OrganizacionView) BeanParser.parseObjectToNewClass(bean.getLaboratorio(), OrganizacionView.class, null));
			return view;
		}).collect(Collectors.toList());
	}
}
