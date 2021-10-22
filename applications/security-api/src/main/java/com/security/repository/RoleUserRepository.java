/**
 * 
 */
package com.security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.security.domain.RoleUser;

/**
 * @author efrain.calla
 *
 */
@Repository
public interface RoleUserRepository extends JpaRepository<RoleUser, Long> {
    List<RoleUser> findByUserId(Long userId);
    List<RoleUser> findByRoleId(Long roleId);
}
