/**
 * 
 */
package com.common.dao;

import java.util.List;

import com.common.dao.common.BaseDao;
import com.common.domain.Organizacion;
import com.common.domain.Persona;

/**
 * @author efrain.calla
 *
 */
public interface EntidadDao extends BaseDao {

	List<Organizacion> findOrganizacionPorNombre(String nombre);
	List<Persona> findPersonaPorNombre(String nombre);
	List<Persona> findPersonaPorNombreApellidoYNumeroDocumento(String termino);
}
