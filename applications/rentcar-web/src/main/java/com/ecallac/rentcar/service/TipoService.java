package com.ecallac.rentcar.service;

import java.util.List;

import com.ecallac.rentcar.domain.Tipo;

public interface TipoService{
	List<Tipo> findList();
	Tipo findById(Long id);
	void save(Tipo entity);
	void delete(Long id,String deletedBy);
}
