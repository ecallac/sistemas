/**
 * 
 */
package com.common.repository;

import com.common.domain.Area;
import com.common.domain.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author efrain.calla
 *
 */
@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long>,CommonRepositoryCustom<Empleado>{
	List<Empleado> findByReportaId(Long reportaId);
	List<Empleado> findBySupervisorausenciasId(Long supervisorausenciasId);
	Empleado findFirstByCodigo(String codigo);
	List<Empleado> findByEstado(String estado);
}
