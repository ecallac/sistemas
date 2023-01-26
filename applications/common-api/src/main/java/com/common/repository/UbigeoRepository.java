/**
 * 
 */
package com.common.repository;

import com.common.domain.Ubigeo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author efrain.calla
 *
 */
@Repository
public interface UbigeoRepository extends JpaRepository<Ubigeo, Long>{
	List<Ubigeo> findByParentUbigeoId(Long parentUbigeoId);
}
