/**
 * 
 */
package com.security.service;

import com.security.domain.Session;

/**
 * @author efrain.calla
 *
 */
public interface SessionService extends BaseService<Session>{
	Session findBySessionKey(String sessionKey);
}
