/**
 * 
 */
package com.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.security.domain.Session;

/**
 * @author efrain.calla
 *
 */
public interface SessionRepository extends JpaRepository<Session, Long> {
	Session findBySessionKey(String sessionKey);
}
