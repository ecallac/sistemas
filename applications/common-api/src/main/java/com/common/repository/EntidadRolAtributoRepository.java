/**
 * 
 */
package com.common.repository;

import com.common.domain.EntidadRolAtributo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author efrain.calla
 *
 */
@Repository
public interface EntidadRolAtributoRepository extends JpaRepository<EntidadRolAtributo, Long>{
    List<EntidadRolAtributo> findByEntidadRolId(Long entidadRolId);
}
