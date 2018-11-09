/**
 * 
 */
package com.ecallac.rentcar.component;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ecallac.rentcar.domain.Alquiler;
import com.ecallac.rentcar.domain.Dinero;
import com.ecallac.rentcar.domain.Trabajo;
import com.ecallac.rentcar.service.AlquilerService;
import com.ecallac.rentcar.service.DescripsionService;
import com.ecallac.rentcar.service.DineroService;
import com.ecallac.rentcar.service.TipoService;
import com.ecallac.rentcar.service.TrabajoService;
import com.ecallac.rentcar.service.VehiculoService;

/**
 * @author efrain
 *
 */
@Component
public class OperacionFacadeImpl implements OperacionFacade{
	@Autowired
	DineroService dineroService;
	@Autowired
	TrabajoService trabajoService;
	@Autowired
	AlquilerService alquilerService;
	
	@Override
	public void saveOperacion(Trabajo trabajo,List<Dinero> dineroList,Long vehiculoId) throws Exception {
// buscar alquiler de vehiculo para la fecha -- si no hay revolver excepsion
		Alquiler alquiler = alquilerService.findByVehiculoIdAndFecha(vehiculoId, trabajo.getFecha());
		if (alquiler==null) {
			throw new Exception("No hay aquiler definido para el dia "+trabajo.getFecha().toString());
		}
		for (Dinero dinero : dineroList) {
			dineroService.save(dinero);
		}
// guardar trabajo
		trabajo.setAlquiler(alquiler);
		trabajoService.save(trabajo);
	}

	@Override
	public List<Dinero> findList() {
		// TODO Auto-generated method stub
		return dineroService.findList();
	}

}
