/**
 * 
 */
package com.common.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.common.domain.TipoBase;

/**
 * @author efrain.calla
 *
 */
@Repository
public interface TipoBaseRepository  extends JpaRepository<TipoBase, Long>{
	TipoBase findFirstByCodigo(String codigo);
	List<TipoBase> findByCategoria(String categoria);
	List<TipoBase> findByCategoriaAndActivo(String categoria,String activo);
}
