/**
 * 
 */
package com.ecallac.rentcar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecallac.rentcar.domain.Dia;

/**
 * @author efrain.calla
 *
 */
@Repository
public interface DiaRepository extends JpaRepository<Dia, Long> {

}
