/**
 * 
 */
package com.common.repository;

import com.common.domain.Area;
import com.common.domain.Ubigeo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author efrain.calla
 *
 */
@Repository
public interface AreaRepository extends JpaRepository<Area, Long>,CommonRepositoryCustom<Area>{
	List<Area> findByParentAreaId(Long parentAreaId);
	Area findFirstByNombre(String nombre);
	List<Area> findByActivo(String activo);
}
