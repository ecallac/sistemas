/**
 *
 */
package com.common.service;

import com.DataTablesInput;
import com.DataTablesOutput;
import com.common.domain.Categoria;

import java.util.List;

/**
 * @author efrain.calla
 *
 */
public interface CategoriaService extends BaseService<Categoria> {

    List<Categoria> findByParentCategoriaId(Long parentCategoriaId);
    List<Categoria> findByStatus(String status);
    Categoria findByNombre(String nombre);
    DataTablesOutput<Categoria> findDataTablesList(DataTablesInput<Categoria> dataTablesInput);
}
