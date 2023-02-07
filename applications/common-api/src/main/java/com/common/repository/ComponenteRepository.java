/**
 * 
 */
package com.common.repository;

import com.common.domain.Componente;
import com.common.domain.Marca;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

/**
 * @author efrain.calla
 *
 */
@Repository
public interface ComponenteRepository extends JpaRepository<Componente, Long>,CommonRepositoryCustom<Componente>{
	List<Componente> findByStatusOrderByDescripcionAsc(String status);
}
