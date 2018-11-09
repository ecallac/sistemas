/**
 * 
 */
package com.ecallac.rentcar.component;

import java.util.List;

import com.ecallac.rentcar.domain.Dinero;
import com.ecallac.rentcar.domain.Trabajo;

/**
 * @author efrain
 *
 */
public interface OperacionFacade {
	void saveOperacion(Trabajo trabajo,List<Dinero> dineroList,Long vehiculoId) throws Exception;
	List<Dinero> findList();
}
