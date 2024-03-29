/**
 * 
 */
package com.common.repository;

import com.common.domain.Marca;
import com.common.domain.ReglaDetalle;
import com.common.domain.Telefono;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author efrain.calla
 *
 */
@Repository
public interface MarcaRepository extends JpaRepository<Marca, Long>,CommonRepositoryCustom<Marca>{
	List<Marca> findByStatusOrderByDescripcionAsc(String status);
	Marca findFirstByNombre(String nombre);
}
