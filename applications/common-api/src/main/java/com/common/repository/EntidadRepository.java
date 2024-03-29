/**
 * 
 */
package com.common.repository;

import java.util.List;

import com.common.domain.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.common.domain.Entidad;
import com.common.domain.Organizacion;
import com.common.domain.Persona;

/**
 * @author efrain.calla
 *
 */
@Repository
public interface EntidadRepository extends JpaRepository<Entidad, Long>{
//	@Query("select rp from Persona rp where rp.entidad.id = :entidadId")
//	Persona findPersonaByEntidadId(@Param("entidadId")Long entidadId);
//
//	@Query("select rp from Organizacion rp where rp.entidad.id = :entidadId")
//	Organizacion findOrganizacionByEntidadId(@Param("entidadId")Long entidadId);
//
//	@Query("select rp from Persona rp WHERE rp.nombres like :termino% or rp.apellidos like :termino% or rp.numeroidentificacion like :termino%")
//	List<Persona> findByNombreOApellidoONumeroDocumento(@Param("termino")String termino);
//
//	@Query("select rp from Organizacion rp WHERE rp.razonsocial like :termino% or rp.numeroidentificacion like :termino%")
//	List<Organizacion> findByRazonSocialONumeroIdentificacion(@Param("termino")String termino);
}
