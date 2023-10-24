/**
 * 
 */
package com.common.repository;

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
public interface PersonaRepository extends JpaRepository<Persona, Long>,CommonRepositoryCustom<Persona>{
	@Query("select rp from Persona rp where rp.entidad.id = :entidadId")
	Persona findPersonaByEntidadId(@Param("entidadId")Long entidadId);
	@Query("select rp from Persona rp WHERE rp.nombres like :termino% or rp.apellidos like :termino% or rp.numeroidentificacion like :termino%")
	List<Persona> findByNombreOApellidoONumeroDocumento(@Param("termino")String termino);
}
