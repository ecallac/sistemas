/**
 * 
 */
package com.common.repository;

import java.util.List;

import com.common.domain.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.common.domain.ReglaDetalle;

/**
 * @author efrain.calla
 *
 */
@Repository
public interface ReglaDetalleRepository extends JpaRepository<ReglaDetalle, Long>,CommonRepositoryCustom<ReglaDetalle>{
	List<ReglaDetalle> findByReglaCategoria(String categoria);
	List<ReglaDetalle> findByReglaCodigo(String codigo);
}
