package com.internal.web.view;

import com.BaseEntity;
import com.common.Regla;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReglaDetalleView extends BaseEntity {
    @NotEmpty
    private String condicion;
    private Double valornumero;
    private String valorcadena;
    @NotEmpty
    private String activo;
    private String activoDescripcion;
    private String activoType;
    private String ids[];
    private ReglaView regla;
    private String reglaId;
}
