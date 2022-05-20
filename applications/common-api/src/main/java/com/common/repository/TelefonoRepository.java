/**
 * 
 */
package com.common.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.common.domain.Telefono;

/**
 * @author efrain.calla
 *
 */
@Repository
public interface TelefonoRepository  extends JpaRepository<Telefono, Long>{
	List<Telefono> findByEntidadId(Long entidadId);
	List<Telefono> findByEntidadIdAndTipo(Long entidadId,String tipo);
}
