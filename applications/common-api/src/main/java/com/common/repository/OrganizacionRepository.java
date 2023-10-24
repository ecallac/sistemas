/**
 * 
 */
package com.common.repository;

import com.common.domain.Area;
import com.common.domain.Entidad;
import com.common.domain.Organizacion;
import com.common.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author efrain.calla
 *
 */
@Repository
public interface OrganizacionRepository extends JpaRepository<Organizacion, Long>,CommonRepositoryCustom<Organizacion>{
	@Query("select rp from Organizacion rp where rp.entidad.id = :entidadId")
	Organizacion findOrganizacionByEntidadId(@Param("entidadId")Long entidadId);
	@Query("select rp from Organizacion rp WHERE rp.razonsocial like :termino% or rp.numeroidentificacion like :termino%")
	List<Organizacion> findByRazonSocialONumeroIdentificacion(@Param("termino")String termino);
}
