/**
 * 
 */
package com.common.repository;

import com.common.domain.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author efrain.calla
 *
 */
@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long>, CommonRepositoryCustom<Producto> {
	Producto findFirstByNombre(String nombre);
}
