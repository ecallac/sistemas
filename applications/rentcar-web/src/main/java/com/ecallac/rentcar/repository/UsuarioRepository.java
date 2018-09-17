/**
 * 
 */
package com.ecallac.rentcar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ecallac.rentcar.domain.Usuario;

/**
 * @author efrain.calla
 *
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	@Query("select u from Usuario u where u.username = :username")
	Usuario findAllByUsername(@Param("username") String username);
}