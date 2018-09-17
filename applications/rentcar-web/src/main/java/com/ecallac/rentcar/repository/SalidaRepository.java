/**
 * 
 */
package com.ecallac.rentcar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecallac.rentcar.domain.Salida;

/**
 * @author efrain.calla
 *
 */
@Repository
public interface SalidaRepository extends JpaRepository<Salida, Long> {

}
