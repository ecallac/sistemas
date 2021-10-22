/**
 * 
 */
package com.security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.security.domain.User;

/**
 * @author efrain.calla
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByUserName(String userName);
	List<User> findByStatus(String status);
	User findByUserNameAndPassword(String userName, String password);
}
