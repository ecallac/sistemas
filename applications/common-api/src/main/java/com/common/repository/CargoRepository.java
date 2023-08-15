/**
 * 
 */
package com.common.repository;

import com.common.domain.Area;
import com.common.domain.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author efrain.calla
 *
 */
@Repository
public interface CargoRepository extends JpaRepository<Cargo, Long>,CommonRepositoryCustom<Cargo>{
	List<Cargo> findByParentCargoId(Long parentCargoId);
	List<Cargo> findByActivo(String activo);
	Cargo findFirstByNombre(String nombre);
}
