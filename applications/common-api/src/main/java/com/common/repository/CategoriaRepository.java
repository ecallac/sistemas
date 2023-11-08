/**
 * 
 */
package com.common.repository;

import com.common.domain.Area;
import com.common.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author efrain.calla
 *
 */
@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long>,CommonRepositoryCustom<Categoria>{
	List<Categoria> findByCategoriapadreId(Long categoriapadreId);
	Categoria findFirstByNombre(String nombre);
	List<Categoria> findByStatus(String status);
}
