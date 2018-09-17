/**
 * 
 */
package com.ecallac.rentcar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecallac.rentcar.domain.Alquiler;

/**
 * @author efrain.calla
 *
 */
@Repository
public interface AlquilerRepository extends JpaRepository<Alquiler, Long> {

}
