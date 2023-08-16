package com.internal.web.view;

import com.BaseEntity;
import com.common.ReglaDetalle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReglaView extends BaseEntity {
    @NotEmpty
    private String categoria;
    @NotEmpty
    private String nombre;
    @NotEmpty
    private String codigo;
    private String descripcion;
    private String tipoValor;
    @NotEmpty
    private String activo;
    private String activoDescripcion;
    private String activoType;
    private String ids[];
    private List<ReglaDetalleView> reglaDetalles;
}
