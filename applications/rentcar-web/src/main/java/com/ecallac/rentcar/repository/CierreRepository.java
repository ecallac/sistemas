/**
 * 
 */
package com.ecallac.rentcar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecallac.rentcar.domain.Cierre;

/**
 * @author efrain.calla
 *
 */
@Repository
public interface CierreRepository extends JpaRepository<Cierre, Long> {

}
