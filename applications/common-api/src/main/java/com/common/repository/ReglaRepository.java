/**
 * 
 */
package com.common.repository;

import com.common.domain.Regla;
import com.common.domain.ReglaDetalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author efrain.calla
 *
 */
@Repository
public interface ReglaRepository extends JpaRepository<Regla, Long>,CommonRepositoryCustom<Regla>{
	Regla findFirstByCodigo(String codigo);
	List<Regla> findByActivo(String activo);
}
