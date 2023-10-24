/**
 * 
 */
package com.common.facade;

import java.util.List;

import com.DataTablesInput;
import com.DataTablesOutput;
import com.common.Column;
import com.common.Table;
import com.common.domain.*;
import com.common.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author efrain.calla
 *
 */
@Service
@Transactional(readOnly = true)
public class DatosMaestrosFacade {
	@Autowired
	TipoBaseService tipoBaseService;
	
	@Autowired
	EntidadService entidadService;
	
	@Autowired
	EntidadRolService entidadRolService;
	
	@Autowired
	ReglaDetalleService reglaDetalleService;

	@Autowired
	ReglaService reglaService;
	
	@Autowired
	TelefonoService telefonoService;

	@Autowired
	UbigeoService ubigeoService;

	@Autowired
	DireccionService direccionService;

	@Autowired
	EntidadRolAtributoService entidadRolAtributoService;

	@Autowired
	DatabaseService databaseService;

	@Autowired
	PersonaService personaService;

	@Autowired
	OrganizacionService organizacionService;

	public List<Table> findDatabaseTables(String catalog) throws Exception {
		return databaseService.findTables(catalog);
	}
	public List<Column> findDatabaseColumnsByTable(String table) throws Exception {
		return databaseService.findColumnsByTable(table);
	}
	
	public TipoBase findTipoBaseById(Long id) {
		return tipoBaseService.findById(id);
	}
	public TipoBase findTipoBaseByCodigo(String codigo) {
		return tipoBaseService.findByCodigo(codigo);
	}
	public List<TipoBase> findTipoBaseByCategoriaActivos(String categoria){
		return tipoBaseService.findByCategoriaActivos(categoria);
	}
	public List<TipoBase> findTipoBaseList(){
		return tipoBaseService.findAll();
	}
	public List<TipoBase> findTipoBaseByDescripcionContaining(String description) {
		return tipoBaseService.findByDescripcionContaining(description);
	}
	public List<TipoBase> findTipoBaseByCategoriaAndCodigo(String categoria,String codigo) {
		return tipoBaseService.findByCategoriaAndCodigo(categoria,codigo);
	}
	@Transactional(readOnly = false,rollbackFor=Exception.class)
	public void saveTipoBase(TipoBase tipoBase) {
		tipoBaseService.save(tipoBase);
	}
	@Transactional(readOnly = false,rollbackFor=Exception.class)
	public void saveTipoBase(List<TipoBase> tipoBaseList) {
		for (TipoBase tipoBase:
			 tipoBaseList) {
			tipoBaseService.save(tipoBase);
		}
	}
	public DataTablesOutput<TipoBase> findTipoBaseDataTablesList(DataTablesInput<TipoBase> dataTablesInput){
		return tipoBaseService.findDataTablesList(dataTablesInput);
	}
	
	public Persona findPersonaByEntidadId(Long entidadRolId) {
		EntidadRol entidadRol = entidadRolService.findById(entidadRolId);
		return personaService.findPersonaByEntidadId(entidadRol.getEntidad().getId());
	}
	public List<Persona> findByNombreOApellidoONumeroDocumento(String termino){
		return personaService.findByNombreOApellidoONumeroDocumento(termino);
	}
	public Persona findPersonaById(Long personaId) {
		return personaService.findById(personaId);
	}
	@Transactional(readOnly = false,rollbackFor=Exception.class)
	public void savePersona(Persona persona) {
		personaService.save(persona);
	}
	@Transactional(readOnly = false,rollbackFor=Exception.class)
	public void savePersona(List<Persona> personaList) {
		for (Persona persona: personaList) {
			personaService.save(persona);
		}
	}
	public DataTablesOutput<Persona> findPersonaDataTablesList(DataTablesInput<Persona> dataTablesInput){
		return personaService.findDataTablesList(dataTablesInput);
	}
	
	public Organizacion findOrganizacionByEntidadId(Long entidadRolId) {
		EntidadRol entidadRol = entidadRolService.findById(entidadRolId);
		return organizacionService.findOrganizacionByEntidadId(entidadRol.getEntidad().getId());
	}
	public List<Organizacion> findByRazonSocialONumeroIdentificacion(String termino){
		return organizacionService.findByRazonSocialONumeroIdentificacion(termino);
	}
	public Organizacion findOrganizacionById(Long organizacionId) {
		return organizacionService.findById(organizacionId);
	}
	@Transactional(readOnly = false,rollbackFor=Exception.class)
	public void saveOrganizacion(Organizacion organizacion) {
		organizacionService.save(organizacion);
	}
	@Transactional(readOnly = false,rollbackFor=Exception.class)
	public void saveOrganizacion(List<Organizacion> organizacionList) {
		for (Organizacion organizacion: organizacionList){
			organizacionService.save(organizacion);
		}
	}
	public DataTablesOutput<Organizacion> findOrganizacionDataTablesList(DataTablesInput<Organizacion> dataTablesInput){
		return organizacionService.findDataTablesList(dataTablesInput);
	}
	@Transactional(readOnly = false,rollbackFor=Exception.class)
	public void saveEntidadPersona(Persona persona) {
		entidadService.savePersona(persona);
	}
	@Transactional(readOnly = false,rollbackFor=Exception.class)
	public void saveEntidadOrganizacion(Organizacion organizacion) {
		entidadService.saveOrganizacion(organizacion);
	}

	@Transactional(readOnly = false,rollbackFor=Exception.class)
	public void saveEntidadRol(EntidadRol entidadRol) {
		entidadRol.setEntidad(entidadService.findById(entidadRol.getEntidad().getId()));
		entidadRolService.save(entidadRol);
	}
	
	public List<Telefono> findTelefonoByEntidadId(Long entidadId){
		return telefonoService.findByEntidadId(entidadId);
	}
	public Telefono findTelefonoById(Long id) {
		return telefonoService.findById(id);
	}
	@Transactional(readOnly = false,rollbackFor=Exception.class)
	public void saveTelefono(Telefono telefono) {
		telefono.setEntidad(entidadService.findById(telefono.getEntidad().getId()));
		telefonoService.save(telefono);
	}

	public  List<ReglaDetalle> findReglaByCategoria(String categoria) {
		return reglaDetalleService.findByReglaCategoria(categoria);
	}

	public  List<ReglaDetalle> findReglaDetalleByCodigo(String codigo) {
		return reglaDetalleService.findByReglaCodigo(codigo);
	}

	public ReglaDetalle findReglaDetalleById(Long id) {
		return reglaDetalleService.findById(id);
	}
	public DataTablesOutput findReglaDetalleDataTablesList(DataTablesInput<ReglaDetalle> dataTablesInput){
		return reglaDetalleService.findDataTablesList(dataTablesInput);
	}
	@Transactional(readOnly = false,rollbackFor=Exception.class)
	public void saveReglaDetalle(ReglaDetalle entidad) {
		entidad.setRegla(reglaService.findById(entidad.getRegla().getId()));
		reglaDetalleService.save(entidad);
	}
	@Transactional(readOnly = false,rollbackFor=Exception.class)
	public void saveReglaDetalle(List<ReglaDetalle> entidadList) {
		for (ReglaDetalle reglaDetalle :
				entidadList) {
			reglaDetalle.setRegla(reglaService.findById(reglaDetalle.getRegla().getId()));
			reglaDetalleService.save(reglaDetalle);
		}
	}

	public  Regla findReglaByCodigo(String codigo) {
		return reglaService.findByCodigo(codigo);
	}

	public Regla findReglaById(Long id) {
		return reglaService.findById(id);
	}
	public DataTablesOutput findReglaDataTablesList(DataTablesInput<Regla> dataTablesInput){
		return reglaService.findDataTablesList(dataTablesInput);
	}
	@Transactional(readOnly = false,rollbackFor=Exception.class)
	public void saveRegla(Regla entidad) {
		reglaService.save(entidad);
	}
	@Transactional(readOnly = false,rollbackFor=Exception.class)
	public void saveRegla(List<Regla> entidadList) {
		for (Regla regla:
				entidadList) {
			reglaService.save(regla);
		}

	}
	public  List<Regla> findReglaByActivo(String activo) {
		return reglaService.findByActivo(activo);
	}

	public List<Ubigeo> findUbigeoByParentUbigeoId(Long parentUbigeoId){
		return ubigeoService.findByParentUbigeoId(parentUbigeoId);
	}
	public Ubigeo findUbigeoById(Long id){
		return ubigeoService.findById(id);
	}
	@Transactional(readOnly = false,rollbackFor=Exception.class)
	public void saveUbigeo(Ubigeo ubigeo) {
		ubigeo.setParentUbigeo(ubigeoService.findById(ubigeo.getParentUbigeo().getId()));
		ubigeoService.save(ubigeo);
	}
	public List<Direccion> findDireccionByEntidadId(Long entidadId){
		return direccionService.findByEntidadId(entidadId);
	}
	public Direccion findDireccionById(Long id){
		return direccionService.findById(id);
	}
	@Transactional(readOnly = false,rollbackFor=Exception.class)
	public void saveDireccion(Direccion direccion) {
		direccion.setUbigeo(ubigeoService.findById(direccion.getUbigeo().getId()));
		direccionService.save(direccion);
	}
	public EntidadRolAtributo findEntidadRolAtributoById(Long id){
		return entidadRolAtributoService.findById(id);
	}
	public List<EntidadRolAtributo> findEntidadRolAtributoByEntidadRolId(Long entidadRolId){
		return entidadRolAtributoService.findByEntidadRolId(entidadRolId);
	}
	@Transactional(readOnly = false,rollbackFor=Exception.class)
	public void saveEntidadRolAtributo(EntidadRolAtributo entidadRolAtributo) {
		entidadRolAtributo.setEntidadRol(entidadRolService.findById(entidadRolAtributo.getEntidadRol().getId()));
		entidadRolAtributoService.save(entidadRolAtributo);
	}
}
