/**
 * 
 */
package com.common.repository;

import java.util.List;

import com.common.domain.Componente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.common.domain.TipoBase;

/**
 * @author efrain.calla
 *
 */
@Repository
public interface TipoBaseRepository  extends JpaRepository<TipoBase, Long>,CommonRepositoryCustom<TipoBase>{
	TipoBase findFirstByCodigo(String codigo);
	List<TipoBase> findByCategoria(String categoria);
	List<TipoBase> findByCategoriaAndActivoOrderByCodigoAsc(String categoria,String activo);
	List<TipoBase> findByDescripcionIgnoreCaseContaining(String description);
	List<TipoBase> findByCategoriaAndCodigo(String categoria, String codigo);

}
