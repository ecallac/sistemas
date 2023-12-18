package com.internal.web.view;

import com.BaseEntity;
import com.common.Entidad;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DireccionView extends BaseEntity {
    @NotEmpty
    private String direccionexacta;
    private String codigopostal;
    private Entidad entidad;
    @NotEmpty
    private String entidadId;
    private UbigeoView ubigeo;
    @NotEmpty
    private String ubigeoId;
    @NotEmpty
    private String esprincipal;
    private String referencia;
    @NotEmpty
    private String estado;
    private String estadoDescripcion;
    private String estadoType;
    private String ids[];

    private String esprincipalStyle;
    private String ubicaionTotal;

}
