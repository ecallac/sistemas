/**
 * 
 */
package com.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.common.domain.EntidadRol;

/**
 * @author efrain.calla
 *
 */
@Repository
public interface EntidadRolRepository extends JpaRepository<EntidadRol, Long>{

}
