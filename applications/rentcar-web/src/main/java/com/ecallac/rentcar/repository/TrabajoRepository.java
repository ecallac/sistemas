/**
 * 
 */
package com.ecallac.rentcar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecallac.rentcar.domain.Trabajo;

/**
 * @author efrain.calla
 *
 */
@Repository
public interface TrabajoRepository extends JpaRepository<Trabajo, Long> {

}