/**
 * 
 */
package com.security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.security.domain.Permission;

/**
 * @author efrain.calla
 *
 */
@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long>{
	@Query("select rp from Permission rp where rp.enabled='Y' and rp.module.id=:moduleId order by rp.description asc")
	List<Permission> findEnabledPermissionsByModuleId(@Param("moduleId")Long moduleId);
	List<Permission> findAllByEnabledOrderByDescription(String enabled);
	List<Permission> findAllByModuleNameAndEnabledOrderByDescriptionAsc(String name,String enabled);
	List<Permission> findAllByParentPermissionIdOrderByDescriptionAsc(Long parentPermissionId);
}
