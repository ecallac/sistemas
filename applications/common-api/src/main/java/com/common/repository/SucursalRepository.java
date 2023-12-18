/**
 * 
 */
package com.common.repository;

import com.common.domain.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author efrain.calla
 *
 */
@Repository
public interface SucursalRepository extends JpaRepository<Sucursal, Long>,CommonRepositoryCustom<Sucursal>{

	Sucursal findFirstByNombre(String nombre);
	List<Sucursal> findByEstadoAndOrganizacionIdOrderByNombreAsc(String estado,Long organizacionId);
}
