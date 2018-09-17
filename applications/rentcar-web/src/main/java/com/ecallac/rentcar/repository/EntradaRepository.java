/**
 * 
 */
package com.ecallac.rentcar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecallac.rentcar.domain.Entrada;

/**
 * @author efrain.calla
 *
 */
@Repository
public interface EntradaRepository extends JpaRepository<Entrada, Long> {

}
