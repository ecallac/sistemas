/**
 * 
 */
package com.ecallac.rentcar.service;

import java.util.Date;
import java.util.List;

import com.ecallac.rentcar.domain.Alquiler;
import com.ecallac.rentcar.domain.Vehiculo;

/**
 * @author efrain
 *
 */
public interface AlquilerService {
	List<Alquiler> findList();
	Alquiler findById(Long id);
	void save(Alquiler entity);
	void delete(Long id,String deletedBy);
	void updateStatus(Long id,String status,String changedBy);
	Alquiler findByVehiculoIdAndFecha(Long vehiculoId,Date date);
}
