/**
 * 
 */
package com.ecallac.rentcar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecallac.rentcar.domain.Rol;

/**
 * @author efrain.calla
 *
 */
@Repository
public interface RolRepository extends JpaRepository<Rol, Long>{
	List<Rol> findAllByStatus(String status);
}
