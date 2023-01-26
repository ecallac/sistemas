/**
 * 
 */
package com.common.repository;

import com.common.domain.Direccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author efrain.calla
 *
 */
@Repository
public interface DireccionRepository extends JpaRepository<Direccion, Long>{
	List<Direccion> findByEntidadIdAndEsprincipal(Long entidadId,String esprincipal);
	List<Direccion> findByEntidadId(Long entidadId);
}
