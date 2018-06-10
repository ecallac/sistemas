/**
 * 
 */
package com.common.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.common.dao.common.BaseDaoSupport;
import com.common.domain.Organizacion;
import com.common.domain.Persona;
import com.common.domain.TipoBase;
import com.common.utils.CommonConstants;

/**
 * @author efrain.calla
 *
 */
@Transactional
@Repository
public class EntidadDaoImpl extends BaseDaoSupport implements EntidadDao {
	@Autowired
	public EntidadDaoImpl(@Qualifier("sessionFactory")SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	@SuppressWarnings("unchecked")
	public List<Organizacion> findOrganizacionPorNombre(String nombre) {
		// TODO Auto-generated method stub
		return (List<Organizacion>) getHibernateTemplate().findByNamedParam("FROM Organizacion WHERE razonsocial like :nombre","nombre", "%"+nombre+"%");
	}

	@SuppressWarnings("unchecked")
	public List<Persona> findPersonaPorNombre(String nombre) {
		// TODO Auto-generated method stub
		return (List<Persona>) getHibernateTemplate().findByNamedParam("FROM Persona WHERE nombres like :nombre or apellidos like :nombre","nombre", "%"+nombre+"%");
	}

	@SuppressWarnings("unchecked")
	public List<Persona> findPersonaPorNombreApellidoYNumeroDocumento(String termino) {
		// TODO Auto-generated method stub
		return (List<Persona>) getHibernateTemplate().findByNamedParam("FROM Persona WHERE nombres like :termino or apellidos like :termino or numeroidentificacion like :termino" ,"termino", termino+"%");
	}

	@SuppressWarnings("unchecked")
	public Persona findPersonaByEntidadId(Long entidadId) {
		String [] params = {"entidadId"};
		Object [] objects = {entidadId};
		List<Persona> list = (List<Persona>) getHibernateTemplate().findByNamedParam("FROM Persona WHERE entidad.id = :entidadId", params,objects);
		return CollectionUtils.isNotEmpty(list)?list.get(0):null;
	}

	@SuppressWarnings("unchecked")
	public Organizacion findOrganizacionByEntidadId(Long entidadId) {
		String [] params = {"entidadId"};
		Object [] objects = {entidadId};
		List<Organizacion> list = (List<Organizacion>) getHibernateTemplate().findByNamedParam("FROM Organizacion WHERE entidad.id = :entidadId", params,objects);
		return CollectionUtils.isNotEmpty(list)?list.get(0):null;
	}
	
	
}
