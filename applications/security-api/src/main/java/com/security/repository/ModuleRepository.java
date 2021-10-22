/**
 * 
 */
package com.security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.security.domain.Module;

/**
 * @author efrain.calla
 *
 */
@Repository
public interface ModuleRepository extends JpaRepository<Module, Long> {
    @Modifying
    @Query("update Module o set o.enabled =:enabled where o.id = :id")
    int updateEnabledIndicator( @Param("enabled") String enabled, @Param("id") Long id);
    List<Module> findAllByEnabledOrderByDescription(String enabled);
    Module findFirstByName(String name);
}
