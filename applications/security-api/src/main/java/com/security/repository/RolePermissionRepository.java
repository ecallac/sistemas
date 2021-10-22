/**
 * 
 */
package com.security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.security.domain.RolePermission;

/**
 * @author efrain.calla
 *
 */
@Repository
public interface RolePermissionRepository extends JpaRepository<RolePermission, Long> {
    List<RolePermission> findByPermissionId(Long permissionId);
    List<RolePermission> findByRoleId(Long roleId);
}
