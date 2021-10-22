/**
 * 
 */
package com.security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.security.domain.Role;

/**
 * @author efrain.calla
 *
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	List<Role> findAllByEnabledOrderByDescription(String enabled);
}
