/**
 * 
 */
package com.ecallac.rentcar.repository;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ecallac.rentcar.domain.Alquiler;

/**
 * @author efrain.calla
 *
 */
@Repository
public interface AlquilerRepository extends JpaRepository<Alquiler, Long> {
	List<Alquiler> findAllByStatusIn(Collection<String> statusList);
	@Modifying
	@Query("update Alquiler o "
			+ "set o.status =:status, o.updatedBy =:updatedBy, o.dateUpdated =:dateUpdated, o.version =(o.version+1) "
			+ "where o.id = :id")
	int updateStatus(
			@Param("status") String status
			,@Param("updatedBy") String updatedBy
			,@Param("dateUpdated") Date dateUpdated
			,@Param("id") Long id);
	@Query("select a from Alquiler a where a.vehiculo.id =:vehiculoId and :fecha between a.fechainicio and a.fechafin")
	Optional<Alquiler> findAllByVehiculoIdAndFecha(@Param("vehiculoId") Long vehiculoId,@Param("fecha") Date fecha);
}
