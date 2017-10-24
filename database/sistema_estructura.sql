SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';


-- -----------------------------------------------------
-- Table `area`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `area` ;

CREATE  TABLE IF NOT EXISTS `area` (
  `id` INT NOT NULL ,
  `nombre` VARCHAR(45) NULL ,
  `activo` VARCHAR(45) NULL ,
  `area_padre_id` INT NOT NULL ,
  `datecreated` DATETIME NULL ,
  `dateupdated` DATETIME NULL ,
  `createdby` VARCHAR(45) NULL ,
  `updatedby` VARCHAR(45) NULL ,
  `version` INT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_area_area1_idx` (`area_padre_id` ASC) ,
  CONSTRAINT `fk_area_area1`
    FOREIGN KEY (`area_padre_id` )
    REFERENCES `area` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cargo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cargo` ;

CREATE  TABLE IF NOT EXISTS `cargo` (
  `id` INT NOT NULL ,
  `nombre` VARCHAR(45) NULL ,
  `activo` VARCHAR(45) NULL ,
  `salariomin` DECIMAL(3,2) NULL ,
  `salariomax` DECIMAL(3,2) NULL ,
  `cargo_padre_id` INT NOT NULL ,
  `datecreated` DATETIME NULL ,
  `dateupdated` DATETIME NULL ,
  `createdby` VARCHAR(45) NULL ,
  `updatedby` VARCHAR(45) NULL ,
  `version` INT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_cargo_cargo1_idx` (`cargo_padre_id` ASC) ,
  CONSTRAINT `fk_cargo_cargo1`
    FOREIGN KEY (`cargo_padre_id` )
    REFERENCES `cargo` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tipobase`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tipobase` ;

CREATE  TABLE IF NOT EXISTS `tipobase` (
  `id` INT NOT NULL ,
  `categoria` VARCHAR(45) NULL ,
  `codigo` VARCHAR(45) NULL ,
  `descripcion` VARCHAR(255) NULL ,
  `activo` VARCHAR(45) NULL ,
  `datecreated` DATETIME NULL ,
  `dateupdated` DATETIME NULL ,
  `createdby` VARCHAR(45) NULL ,
  `updatedby` VARCHAR(45) NULL ,
  `version` INT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ubigeo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ubigeo` ;

CREATE  TABLE IF NOT EXISTS `ubigeo` (
  `id` INT NOT NULL ,
  `descripcion` VARCHAR(45) NULL ,
  `idubigeo` VARCHAR(45) NULL ,
  `pais_id` INT NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ciudad`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ciudad` ;

CREATE  TABLE IF NOT EXISTS `ciudad` (
  `id` INT NOT NULL ,
  `pais_id` INT NOT NULL ,
  `descripcion` VARCHAR(45) NULL ,
  `ubigeo_id` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_ciudad_ubigeo1_idx` (`ubigeo_id` ASC) ,
  CONSTRAINT `fk_ciudad_ubigeo1`
    FOREIGN KEY (`ubigeo_id` )
    REFERENCES `ubigeo` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `entidad`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `entidad` ;

CREATE  TABLE IF NOT EXISTS `entidad` (
  `id` INT NOT NULL ,
  `tipo_entidad` VARCHAR(45) NULL ,
  `datecreated` DATETIME NULL ,
  `dateupdated` DATETIME NULL ,
  `createdby` VARCHAR(45) NULL ,
  `updatedby` VARCHAR(45) NULL ,
  `version` INT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `persona`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `persona` ;

CREATE  TABLE IF NOT EXISTS `persona` (
  `id` INT NOT NULL ,
  `tipo_documento_identificaion` VARCHAR(45) NULL ,
  `numeroidentificacion` VARCHAR(45) NULL ,
  `nombres` VARCHAR(45) NULL ,
  `apellidos` VARCHAR(45) NULL ,
  `tipo_estado_civil` VARCHAR(45) NULL ,
  `sexo` VARCHAR(45) NULL ,
  `fechanacimiento` DATETIME NULL ,
  `email` VARCHAR(45) NULL ,
  `entidad_id` INT NOT NULL ,
  `datecreated` DATETIME NULL ,
  `dateupdated` DATETIME NULL ,
  `createdby` VARCHAR(45) NULL ,
  `updatedby` VARCHAR(45) NULL ,
  `version` INT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_persona_entidad1_idx` (`entidad_id` ASC) ,
  CONSTRAINT `fk_persona_entidad1`
    FOREIGN KEY (`entidad_id` )
    REFERENCES `entidad` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `empleado`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `empleado` ;

CREATE  TABLE IF NOT EXISTS `empleado` (
  `id` INT NOT NULL ,
  `persona_id` INT NOT NULL ,
  `codigo` VARCHAR(45) NULL ,
  `email` VARCHAR(45) NULL ,
  `telefono` VARCHAR(45) NULL ,
  `fechaingreso` DATETIME NULL ,
  `tipo_titulo` VARCHAR(45) NULL ,
  `apreciacion` INT NULL ,
  `datecreated` DATETIME NULL ,
  `dateupdated` DATETIME NULL ,
  `createdby` VARCHAR(45) NULL ,
  `updatedby` VARCHAR(45) NULL ,
  `version` INT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_empleado_persona1_idx` (`persona_id` ASC) ,
  CONSTRAINT `fk_empleado_persona1`
    FOREIGN KEY (`persona_id` )
    REFERENCES `persona` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `organizacion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `organizacion` ;

CREATE  TABLE IF NOT EXISTS `organizacion` (
  `id` INT NOT NULL ,
  `tipo_organizacion` VARCHAR(45) NULL ,
  `numeroidentificacion` VARCHAR(45) NULL ,
  `razonsocial` VARCHAR(225) NULL ,
  `entidad_id` INT NOT NULL ,
  `datecreated` DATETIME NULL ,
  `dateupdated` DATETIME NULL ,
  `createdby` VARCHAR(45) NULL ,
  `updatedby` VARCHAR(45) NULL ,
  `version` INT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_organizacion_entidad1_idx` (`entidad_id` ASC) ,
  CONSTRAINT `fk_organizacion_entidad1`
    FOREIGN KEY (`entidad_id` )
    REFERENCES `entidad` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `direccion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `direccion` ;

CREATE  TABLE IF NOT EXISTS `direccion` (
  `id` INT NOT NULL ,
  `direccionexacta` VARCHAR(45) NULL ,
  `codigopostal` VARCHAR(45) NULL ,
  `entidad_id` INT NOT NULL ,
  `ciudad_id` INT NOT NULL ,
  `ubigeo_id` INT NOT NULL ,
  `esprincipal` VARCHAR(45) NULL ,
  `estado` VARCHAR(45) NULL ,
  `datecreated` DATETIME NULL ,
  `dateupdated` DATETIME NULL ,
  `createdby` VARCHAR(45) NULL ,
  `updatedby` VARCHAR(45) NULL ,
  `version` INT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_direccion_entidad1_idx` (`entidad_id` ASC) ,
  INDEX `fk_direccion_ciudad1_idx` (`ciudad_id` ASC) ,
  INDEX `fk_direccion_ubigeo1_idx` (`ubigeo_id` ASC) ,
  CONSTRAINT `fk_direccion_entidad1`
    FOREIGN KEY (`entidad_id` )
    REFERENCES `entidad` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_direccion_ciudad1`
    FOREIGN KEY (`ciudad_id` )
    REFERENCES `ciudad` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_direccion_ubigeo1`
    FOREIGN KEY (`ubigeo_id` )
    REFERENCES `ubigeo` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sucursal`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sucursal` ;

CREATE  TABLE IF NOT EXISTS `sucursal` (
  `id` INT NOT NULL ,
  `nombre` VARCHAR(45) NULL ,
  `estado` VARCHAR(45) NULL ,
  `organizacion_id` INT NOT NULL ,
  `tipo_sucursal` VARCHAR(45) NULL ,
  `nombrecorto` VARCHAR(45) NULL ,
  `direccion_id` INT NOT NULL ,
  `datecreated` DATETIME NULL ,
  `dateupdated` DATETIME NULL ,
  `createdby` VARCHAR(45) NULL ,
  `updatedby` VARCHAR(45) NULL ,
  `version` INT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_sucursal_organizacion1_idx` (`organizacion_id` ASC) ,
  INDEX `fk_sucursal_direccion1_idx` (`direccion_id` ASC) ,
  CONSTRAINT `fk_sucursal_organizacion1`
    FOREIGN KEY (`organizacion_id` )
    REFERENCES `organizacion` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_sucursal_direccion1`
    FOREIGN KEY (`direccion_id` )
    REFERENCES `direccion` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `areasucursal`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `areasucursal` ;

CREATE  TABLE IF NOT EXISTS `areasucursal` (
  `sucursal_id` INT NOT NULL ,
  `area_id` INT NOT NULL ,
  `responsable_id` INT NOT NULL ,
  `estado` VARCHAR(45) NULL ,
  `datecreated` DATETIME NULL ,
  `dateupdated` DATETIME NULL ,
  `createdby` VARCHAR(45) NULL ,
  `updatedby` VARCHAR(45) NULL ,
  `version` INT NULL ,
  PRIMARY KEY (`sucursal_id`, `area_id`) ,
  INDEX `fk_sucursal_has_area_area1_idx` (`area_id` ASC) ,
  INDEX `fk_sucursal_has_area_sucursal1_idx` (`sucursal_id` ASC) ,
  INDEX `fk_areasucursal_empleado1_idx` (`responsable_id` ASC) ,
  CONSTRAINT `fk_sucursal_has_area_sucursal1`
    FOREIGN KEY (`sucursal_id` )
    REFERENCES `sucursal` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_sucursal_has_area_area1`
    FOREIGN KEY (`area_id` )
    REFERENCES `area` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_areasucursal_empleado1`
    FOREIGN KEY (`responsable_id` )
    REFERENCES `empleado` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `puestotrabajo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `puestotrabajo` ;

CREATE  TABLE IF NOT EXISTS `puestotrabajo` (
  `plaza_id` INT NOT NULL ,
  `area_id` INT NOT NULL ,
  `sucursal_id` INT NOT NULL ,
  `cargo_id` INT NOT NULL ,
  `cantidadpuestos` INT NULL ,
  `descripcion` VARCHAR(225) NULL ,
  `estado` VARCHAR(45) NULL ,
  `responsable_seleccion_id` INT NOT NULL ,
  PRIMARY KEY (`plaza_id`) ,
  INDEX `fk_plaza_areasucursal1_idx` (`sucursal_id` ASC, `area_id` ASC) ,
  INDEX `fk_plaza_cargo1_idx` (`cargo_id` ASC) ,
  INDEX `fk_plaza_empleado1_idx` (`responsable_seleccion_id` ASC) ,
  CONSTRAINT `fk_plaza_areasucursal1`
    FOREIGN KEY (`sucursal_id` , `area_id` )
    REFERENCES `areasucursal` (`sucursal_id` , `area_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_plaza_cargo1`
    FOREIGN KEY (`cargo_id` )
    REFERENCES `cargo` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_plaza_empleado1`
    FOREIGN KEY (`responsable_seleccion_id` )
    REFERENCES `empleado` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `contratoempleado`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `contratoempleado` ;

CREATE  TABLE IF NOT EXISTS `contratoempleado` (
  `id` INT NOT NULL ,
  `organizacion_id` INT NOT NULL ,
  `representante_id` INT NOT NULL ,
  `empleado_id` INT NOT NULL ,
  `plaza_id` INT NOT NULL ,
  `cargo_id` INT NOT NULL ,
  `fechacommienzo` DATE NULL ,
  `fechatermino` DATE NULL ,
  `salario` DECIMAL(7,2) NULL ,
  `horaentrada` TIME NULL ,
  `horasalida` TIME NULL ,
  `tipo_contrato` VARCHAR(45) NULL ,
  `estado` VARCHAR(45) NULL ,
  `datecreated` DATETIME NULL ,
  `dateupdated` DATETIME NULL ,
  `createdby` VARCHAR(45) NULL ,
  `updatedby` VARCHAR(45) NULL ,
  `version` INT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_contratoempleado_organizacion1_idx` (`organizacion_id` ASC) ,
  INDEX `fk_contratoempleado_empleado1_idx` (`empleado_id` ASC) ,
  INDEX `fk_contratoempleado_empleado2_idx` (`representante_id` ASC) ,
  INDEX `fk_contratoempleado_plaza1_idx` (`plaza_id` ASC) ,
  INDEX `fk_contratoempleado_cargo1_idx` (`cargo_id` ASC) ,
  CONSTRAINT `fk_contratoempleado_organizacion1`
    FOREIGN KEY (`organizacion_id` )
    REFERENCES `organizacion` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_contratoempleado_empleado1`
    FOREIGN KEY (`empleado_id` )
    REFERENCES `empleado` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_contratoempleado_empleado2`
    FOREIGN KEY (`representante_id` )
    REFERENCES `empleado` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_contratoempleado_plaza1`
    FOREIGN KEY (`plaza_id` )
    REFERENCES `puestotrabajo` (`plaza_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_contratoempleado_cargo1`
    FOREIGN KEY (`cargo_id` )
    REFERENCES `cargo` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `telefono`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `telefono` ;

CREATE  TABLE IF NOT EXISTS `telefono` (
  `id` INT NOT NULL ,
  `tipo` VARCHAR(45) NULL ,
  `numero` VARCHAR(45) NULL ,
  `codigoarea` VARCHAR(45) NULL ,
  `entidad_id` INT NOT NULL ,
  `datecreated` DATETIME NULL ,
  `dateupdated` DATETIME NULL ,
  `createdby` VARCHAR(45) NULL ,
  `updatedby` VARCHAR(45) NULL ,
  `version` INT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_telefono_entidad1_idx` (`entidad_id` ASC) ,
  CONSTRAINT `fk_telefono_entidad1`
    FOREIGN KEY (`entidad_id` )
    REFERENCES `entidad` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `regla`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `regla` ;

CREATE  TABLE IF NOT EXISTS `regla` (
  `id` INT NOT NULL ,
  `categoria` VARCHAR(45) NULL ,
  `nombre` VARCHAR(45) NULL ,
  `codigo` VARCHAR(45) NULL ,
  `descripcion` VARCHAR(255) NULL ,
  `tipo_valor` VARCHAR(45) NULL ,
  `activo` VARCHAR(45) NULL ,
  `datecreated` DATETIME NULL ,
  `dateupdated` DATETIME NULL ,
  `createdby` VARCHAR(45) NULL ,
  `updatedby` VARCHAR(45) NULL ,
  `version` INT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `regladetalle`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `regladetalle` ;

CREATE  TABLE IF NOT EXISTS `regladetalle` (
  `id` INT NOT NULL ,
  `condicion` VARCHAR(45) NULL ,
  `valornumero` DECIMAL(3,2) NULL ,
  `valorcadena` VARCHAR(45) NULL ,
  `valorfecha` DATETIME NULL ,
  `activo` VARCHAR(45) NULL ,
  `regla_id` INT NOT NULL ,
  `datecreated` DATETIME NULL ,
  `dateupdated` DATETIME NULL ,
  `createdby` VARCHAR(45) NULL ,
  `updatedby` VARCHAR(45) NULL ,
  `version` INT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_regladetalle_regla1_idx` (`regla_id` ASC) ,
  CONSTRAINT `fk_regladetalle_regla1`
    FOREIGN KEY (`regla_id` )
    REFERENCES `regla` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `categoria`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `categoria` ;

CREATE  TABLE IF NOT EXISTS `categoria` (
  `id` INT NOT NULL ,
  `nombre` VARCHAR(45) NULL ,
  `tipo_categoria` VARCHAR(45) NULL ,
  `tipo_estrategia_retiro` VARCHAR(45) NULL ,
  `categoria_padre_id` INT NOT NULL ,
  `datecreated` DATETIME NULL ,
  `dateupdated` DATETIME NULL ,
  `createdby` VARCHAR(45) NULL ,
  `updatedby` VARCHAR(45) NULL ,
  `version` INT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_categoria_categoria1_idx` (`categoria_padre_id` ASC) ,
  CONSTRAINT `fk_categoria_categoria1`
    FOREIGN KEY (`categoria_padre_id` )
    REFERENCES `categoria` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ubicacion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ubicacion` ;

CREATE  TABLE IF NOT EXISTS `ubicacion` (
  `id` INT NOT NULL ,
  `nombre` VARCHAR(45) NULL ,
  `ubicacion_padre_id` INT NOT NULL ,
  `tipo_ubicacion` VARCHAR(45) NULL ,
  `pasillo` VARCHAR(45) NULL ,
  `estanteria` VARCHAR(45) NULL ,
  `altura` VARCHAR(45) NULL ,
  `sucursal_id` INT NOT NULL ,
  `datecreated` DATETIME NULL ,
  `dateupdated` DATETIME NULL ,
  `createdby` VARCHAR(45) NULL ,
  `updatedby` VARCHAR(45) NULL ,
  `version` INT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_ubicacion_ubicacion1_idx` (`ubicacion_padre_id` ASC) ,
  INDEX `fk_ubicacion_sucursal1_idx` (`sucursal_id` ASC) ,
  CONSTRAINT `fk_ubicacion_ubicacion1`
    FOREIGN KEY (`ubicacion_padre_id` )
    REFERENCES `ubicacion` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ubicacion_sucursal1`
    FOREIGN KEY (`sucursal_id` )
    REFERENCES `sucursal` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `producto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `producto` ;

CREATE  TABLE IF NOT EXISTS `producto` (
  `id` INT NOT NULL ,
  `nombre` VARCHAR(45) NULL ,
  `puedevenderse` VARCHAR(45) NULL ,
  `puedecomprarse` VARCHAR(45) NULL ,
  `tipo_producto` VARCHAR(45) NULL ,
  `referenciainterna` VARCHAR(45) NULL ,
  `codigobarras` VARCHAR(45) NULL ,
  `precioventa` VARCHAR(45) NULL ,
  `coste` VARCHAR(45) NULL ,
  `categoria_id` INT NOT NULL ,
  `peso` VARCHAR(45) NULL ,
  `volumen` VARCHAR(45) NULL ,
  `descripcion` VARCHAR(225) NULL ,
  `marca` VARCHAR(45) NULL ,
  `datecreated` DATETIME NULL ,
  `dateupdated` DATETIME NULL ,
  `createdby` VARCHAR(45) NULL ,
  `updatedby` VARCHAR(45) NULL ,
  `version` INT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_producto_categoria1_idx` (`categoria_id` ASC) ,
  CONSTRAINT `fk_producto_categoria1`
    FOREIGN KEY (`categoria_id` )
    REFERENCES `categoria` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `albaran`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `albaran` ;

CREATE  TABLE IF NOT EXISTS `albaran` (
  `id` INT NOT NULL ,
  `nombre` VARCHAR(45) NULL ,
  `tipo_operacion` VARCHAR(45) NULL ,
  `albaran_devoluciones_id` INT NOT NULL ,
  `sucursal_id` INT NOT NULL ,
  `ubicacion_origen_id` INT NOT NULL ,
  `ubicacion_destino_id` INT NOT NULL ,
  `datecreated` DATETIME NULL ,
  `dateupdated` DATETIME NULL ,
  `createdby` VARCHAR(45) NULL ,
  `updatedby` VARCHAR(45) NULL ,
  `version` INT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_albaran_albaran1_idx` (`albaran_devoluciones_id` ASC) ,
  INDEX `fk_albaran_sucursal1_idx` (`sucursal_id` ASC) ,
  INDEX `fk_albaran_ubicacion1_idx` (`ubicacion_origen_id` ASC) ,
  INDEX `fk_albaran_ubicacion2_idx` (`ubicacion_destino_id` ASC) ,
  CONSTRAINT `fk_albaran_albaran1`
    FOREIGN KEY (`albaran_devoluciones_id` )
    REFERENCES `albaran` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_albaran_sucursal1`
    FOREIGN KEY (`sucursal_id` )
    REFERENCES `sucursal` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_albaran_ubicacion1`
    FOREIGN KEY (`ubicacion_origen_id` )
    REFERENCES `ubicacion` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_albaran_ubicacion2`
    FOREIGN KEY (`ubicacion_destino_id` )
    REFERENCES `ubicacion` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `transferencia`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `transferencia` ;

CREATE  TABLE IF NOT EXISTS `transferencia` (
  `id` INT NOT NULL ,
  `fechaprevista` VARCHAR(45) NULL ,
  `estado` VARCHAR(45) NULL ,
  `descripcion` VARCHAR(45) NULL ,
  `referencia` VARCHAR(45) NULL ,
  `documento` VARCHAR(45) NULL ,
  `organizacion_id` INT NOT NULL ,
  `tipo_entrega` VARCHAR(45) NULL ,
  `tipo_prioridad` VARCHAR(45) NULL ,
  `albaran_id` INT NOT NULL ,
  `datecreated` DATETIME NULL ,
  `dateupdated` DATETIME NULL ,
  `createdby` VARCHAR(45) NULL ,
  `updatedby` VARCHAR(45) NULL ,
  `version` INT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_transferencia_organizacion1_idx` (`organizacion_id` ASC) ,
  INDEX `fk_transferencia_albaran1_idx` (`albaran_id` ASC) ,
  CONSTRAINT `fk_transferencia_organizacion1`
    FOREIGN KEY (`organizacion_id` )
    REFERENCES `organizacion` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_transferencia_albaran1`
    FOREIGN KEY (`albaran_id` )
    REFERENCES `albaran` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `transferenciaproducto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `transferenciaproducto` ;

CREATE  TABLE IF NOT EXISTS `transferenciaproducto` (
  `producto_id` INT NOT NULL ,
  `transferencia_id` INT NOT NULL ,
  `cantidad` VARCHAR(45) NULL ,
  `estado` VARCHAR(45) NULL ,
  PRIMARY KEY (`producto_id`, `transferencia_id`) ,
  INDEX `fk_producto_has_transferencia_transferencia1_idx` (`transferencia_id` ASC) ,
  INDEX `fk_producto_has_transferencia_producto1_idx` (`producto_id` ASC) ,
  CONSTRAINT `fk_producto_has_transferencia_producto1`
    FOREIGN KEY (`producto_id` )
    REFERENCES `producto` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_producto_has_transferencia_transferencia1`
    FOREIGN KEY (`transferencia_id` )
    REFERENCES `transferencia` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `inventario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `inventario` ;

CREATE  TABLE IF NOT EXISTS `inventario` (
  `id` INT NOT NULL ,
  `nombre` VARCHAR(45) NULL ,
  `fecha` VARCHAR(45) NULL ,
  `tipo_inventario` VARCHAR(45) NULL ,
  `incluirproductosagotados` VARCHAR(45) NULL ,
  `categoria_id` INT NOT NULL ,
  `estado` VARCHAR(45) NULL ,
  `sucursal_id` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_inventario_categoria1_idx` (`categoria_id` ASC) ,
  INDEX `fk_inventario_sucursal1_idx` (`sucursal_id` ASC) ,
  CONSTRAINT `fk_inventario_categoria1`
    FOREIGN KEY (`categoria_id` )
    REFERENCES `categoria` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_inventario_sucursal1`
    FOREIGN KEY (`sucursal_id` )
    REFERENCES `sucursal` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `inventarioproducto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `inventarioproducto` ;

CREATE  TABLE IF NOT EXISTS `inventarioproducto` (
  `inventario_id` INT NOT NULL ,
  `producto_id` INT NOT NULL ,
  `cantidadteorica` VARCHAR(45) NULL ,
  `cantidadreal` VARCHAR(45) NULL ,
  PRIMARY KEY (`inventario_id`, `producto_id`) ,
  INDEX `fk_inventario_has_producto_producto1_idx` (`producto_id` ASC) ,
  INDEX `fk_inventario_has_producto_inventario1_idx` (`inventario_id` ASC) ,
  CONSTRAINT `fk_inventario_has_producto_inventario1`
    FOREIGN KEY (`inventario_id` )
    REFERENCES `inventario` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_inventario_has_producto_producto1`
    FOREIGN KEY (`producto_id` )
    REFERENCES `producto` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `reglaabastecimiento`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `reglaabastecimiento` ;

CREATE  TABLE IF NOT EXISTS `reglaabastecimiento` (
  `id` INT NOT NULL ,
  `nombre` VARCHAR(45) NULL ,
  `tipo_accion` VARCHAR(45) NULL ,
  `ubicacion_origen_id` INT NOT NULL ,
  `ubicacion_destino_id` INT NOT NULL ,
  `albaran_id` INT NOT NULL ,
  `datecreated` DATETIME NULL ,
  `dateupdated` DATETIME NULL ,
  `createdby` VARCHAR(45) NULL ,
  `updatedby` VARCHAR(45) NULL ,
  `version` INT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_regla_ubicacion1_idx` (`ubicacion_origen_id` ASC) ,
  INDEX `fk_regla_ubicacion2_idx` (`ubicacion_destino_id` ASC) ,
  INDEX `fk_regla_albaran1_idx` (`albaran_id` ASC) ,
  CONSTRAINT `fk_regla_ubicacion1`
    FOREIGN KEY (`ubicacion_origen_id` )
    REFERENCES `ubicacion` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_regla_ubicacion2`
    FOREIGN KEY (`ubicacion_destino_id` )
    REFERENCES `ubicacion` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_regla_albaran1`
    FOREIGN KEY (`albaran_id` )
    REFERENCES `albaran` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `abastecimiento`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `abastecimiento` ;

CREATE  TABLE IF NOT EXISTS `abastecimiento` (
  `id` INT NOT NULL ,
  `cantidad` VARCHAR(45) NULL ,
  `fechaprevista` VARCHAR(45) NULL ,
  `tipo_prioridad` VARCHAR(45) NULL ,
  `producto_id` INT NOT NULL ,
  `sucursal_id` INT NOT NULL ,
  `ubicacion_id` INT NOT NULL ,
  `documento` VARCHAR(45) NULL ,
  `cliente_id` VARCHAR(45) NULL ,
  `regla_id` INT NOT NULL ,
  `estado` VARCHAR(45) NULL ,
  `descripsion` VARCHAR(225) NULL ,
  `datecreated` DATETIME NULL ,
  `dateupdated` DATETIME NULL ,
  `createdby` VARCHAR(45) NULL ,
  `updatedby` VARCHAR(45) NULL ,
  `version` INT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_abastecimiento_producto1_idx` (`producto_id` ASC) ,
  INDEX `fk_abastecimiento_sucursal1_idx` (`sucursal_id` ASC) ,
  INDEX `fk_abastecimiento_ubicacion1_idx` (`ubicacion_id` ASC) ,
  INDEX `fk_abastecimiento_regla1_idx` (`regla_id` ASC) ,
  CONSTRAINT `fk_abastecimiento_producto1`
    FOREIGN KEY (`producto_id` )
    REFERENCES `producto` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_abastecimiento_sucursal1`
    FOREIGN KEY (`sucursal_id` )
    REFERENCES `sucursal` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_abastecimiento_ubicacion1`
    FOREIGN KEY (`ubicacion_id` )
    REFERENCES `ubicacion` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_abastecimiento_regla1`
    FOREIGN KEY (`regla_id` )
    REFERENCES `reglaabastecimiento` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `reabastecimiento`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `reabastecimiento` ;

CREATE  TABLE IF NOT EXISTS `reabastecimiento` (
  `id` INT NOT NULL ,
  `nombre` VARCHAR(45) NULL ,
  `cantidadminima` VARCHAR(45) NULL ,
  `cantidadmaxima` VARCHAR(45) NULL ,
  `plazoentrega` INT NULL ,
  `tipo_plazoentrega` VARCHAR(45) NULL ,
  `producto_id` INT NOT NULL ,
  `datecreated` DATETIME NULL ,
  `dateupdated` DATETIME NULL ,
  `createdby` VARCHAR(45) NULL ,
  `updaetdby` VARCHAR(45) NULL ,
  `version` INT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_reabastecimiento_producto1_idx` (`producto_id` ASC) ,
  CONSTRAINT `fk_reabastecimiento_producto1`
    FOREIGN KEY (`producto_id` )
    REFERENCES `producto` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `entidadrol`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `entidadrol` ;

CREATE  TABLE IF NOT EXISTS `entidadrol` (
  `id` INT NOT NULL ,
  `estado` VARCHAR(45) NULL ,
  `tipo_entidadrol` VARCHAR(45) NULL ,
  `entidad_id` INT NOT NULL ,
  `datecreated` DATETIME NULL ,
  `dateupdated` DATETIME NULL ,
  `createdby` VARCHAR(45) NULL ,
  `updatedby` VARCHAR(45) NULL ,
  `version` INT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_entidadrol_entidad1_idx` (`entidad_id` ASC) ,
  CONSTRAINT `fk_entidadrol_entidad1`
    FOREIGN KEY (`entidad_id` )
    REFERENCES `entidad` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cotizacion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cotizacion` ;

CREATE  TABLE IF NOT EXISTS `cotizacion` (
  `id` INT NOT NULL ,
  `entidadrol_id` INT NOT NULL ,
  `empleado_id` INT NOT NULL ,
  `fecha` DATETIME NULL ,
  `tipo_moneda` VARCHAR(45) NULL ,
  `impuesto` VARCHAR(45) NULL ,
  `subtotal` VARCHAR(45) NULL ,
  `igv` VARCHAR(45) NULL ,
  `total` VARCHAR(45) NULL ,
  `tipo_pago` VARCHAR(45) NULL ,
  `estado` VARCHAR(45) NULL ,
  `datecreated` DATETIME NULL ,
  `dateupdated` DATETIME NULL ,
  `createdby` VARCHAR(45) NULL ,
  `updatedby` VARCHAR(45) NULL ,
  `version` INT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_cotizacion_entidadrol1_idx` (`entidadrol_id` ASC) ,
  INDEX `fk_cotizacion_empleado1_idx` (`empleado_id` ASC) ,
  CONSTRAINT `fk_cotizacion_entidadrol1`
    FOREIGN KEY (`entidadrol_id` )
    REFERENCES `entidadrol` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_cotizacion_empleado1`
    FOREIGN KEY (`empleado_id` )
    REFERENCES `empleado` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `venta`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `venta` ;

CREATE  TABLE IF NOT EXISTS `venta` (
  `id` INT NOT NULL ,
  `codigo` VARCHAR(45) NULL ,
  `entidadrol_id` INT NOT NULL ,
  `tipo_comprobante` VARCHAR(45) NULL ,
  `empleado_id` INT NOT NULL ,
  `fecha` DATETIME NULL ,
  `estado` VARCHAR(45) NULL ,
  `cotizacion_id` INT NULL ,
  `tipo_moneda` VARCHAR(45) NULL ,
  `impuesto` VARCHAR(45) NULL ,
  `subtotal` VARCHAR(45) NULL ,
  `igv` VARCHAR(45) NULL ,
  `total` VARCHAR(45) NULL ,
  `tipo_pago` VARCHAR(45) NULL ,
  `datecreated` DATETIME NULL ,
  `dateupdated` DATETIME NULL ,
  `createdby` VARCHAR(45) NULL ,
  `updatedby` VARCHAR(45) NULL ,
  `version` INT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_comprovante_entidadrol1_idx` (`entidadrol_id` ASC) ,
  INDEX `fk_venta_empleado1_idx` (`empleado_id` ASC) ,
  INDEX `fk_venta_cotizacion1_idx` (`cotizacion_id` ASC) ,
  CONSTRAINT `fk_comprovante_entidadrol1`
    FOREIGN KEY (`entidadrol_id` )
    REFERENCES `entidadrol` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_venta_empleado1`
    FOREIGN KEY (`empleado_id` )
    REFERENCES `empleado` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_venta_cotizacion1`
    FOREIGN KEY (`cotizacion_id` )
    REFERENCES `cotizacion` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ventaproducto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ventaproducto` ;

CREATE  TABLE IF NOT EXISTS `ventaproducto` (
  `producto_id` INT NOT NULL ,
  `venta_id` INT NOT NULL ,
  `sucursal_id` INT NOT NULL ,
  `cantidad` VARCHAR(45) NULL ,
  `impuesto` VARCHAR(45) NULL ,
  `importe` VARCHAR(45) NULL ,
  `preciounitario` VARCHAR(45) NULL ,
  `descuento` VARCHAR(45) NULL ,
  PRIMARY KEY (`producto_id`, `venta_id`) ,
  INDEX `fk_venta_has_producto_producto1_idx` (`producto_id` ASC) ,
  INDEX `fk_venta_has_producto_venta1_idx` (`venta_id` ASC) ,
  INDEX `fk_venta_has_producto_sucursal1_idx` (`sucursal_id` ASC) ,
  CONSTRAINT `fk_venta_has_producto_producto1`
    FOREIGN KEY (`producto_id` )
    REFERENCES `producto` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_venta_has_producto_venta1`
    FOREIGN KEY (`venta_id` )
    REFERENCES `venta` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_venta_has_producto_sucursal1`
    FOREIGN KEY (`sucursal_id` )
    REFERENCES `sucursal` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cotizacionproducto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cotizacionproducto` ;

CREATE  TABLE IF NOT EXISTS `cotizacionproducto` (
  `cotizacion_id` INT NOT NULL ,
  `producto_id` INT NOT NULL ,
  `cantidad` VARCHAR(45) NULL ,
  `impuesto` VARCHAR(45) NULL ,
  `importe` VARCHAR(45) NULL ,
  `sucursal_id` INT NOT NULL ,
  `preciounitario` VARCHAR(45) NULL ,
  PRIMARY KEY (`cotizacion_id`, `producto_id`) ,
  INDEX `fk_cotizacion_has_producto_producto1_idx` (`producto_id` ASC) ,
  INDEX `fk_cotizacion_has_producto_cotizacion1_idx` (`cotizacion_id` ASC) ,
  INDEX `fk_cotizacionproducto_sucursal1_idx` (`sucursal_id` ASC) ,
  CONSTRAINT `fk_cotizacion_has_producto_cotizacion1`
    FOREIGN KEY (`cotizacion_id` )
    REFERENCES `cotizacion` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_cotizacion_has_producto_producto1`
    FOREIGN KEY (`producto_id` )
    REFERENCES `producto` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_cotizacionproducto_sucursal1`
    FOREIGN KEY (`sucursal_id` )
    REFERENCES `sucursal` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `compra`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `compra` ;

CREATE  TABLE IF NOT EXISTS `compra` (
  `id` INT NOT NULL ,
  `entidadrol_id` INT NOT NULL ,
  `empleado_id` INT NOT NULL ,
  `fechafacturado` DATE NULL ,
  `fechavencimiento` DATE NULL ,
  `datecreated` DATETIME NULL ,
  `dateupdated` DATETIME NULL ,
  `createdby` VARCHAR(45) NULL ,
  `updatedby` VARCHAR(45) NULL ,
  `version` INT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_compra_entidadrol1_idx` (`entidadrol_id` ASC) ,
  INDEX `fk_compra_empleado1_idx` (`empleado_id` ASC) ,
  CONSTRAINT `fk_compra_entidadrol1`
    FOREIGN KEY (`entidadrol_id` )
    REFERENCES `entidadrol` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_compra_empleado1`
    FOREIGN KEY (`empleado_id` )
    REFERENCES `empleado` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `compraproducto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `compraproducto` ;

CREATE  TABLE IF NOT EXISTS `compraproducto` (
  `compra_id` INT NOT NULL ,
  `producto_id` INT NOT NULL ,
  `cantidad` INT NULL ,
  `preciounitario` VARCHAR(45) NULL ,
  `impuestos` VARCHAR(45) NULL ,
  `importe` VARCHAR(45) NULL ,
  PRIMARY KEY (`compra_id`, `producto_id`) ,
  INDEX `fk_compra_has_producto_producto1_idx` (`producto_id` ASC) ,
  INDEX `fk_compra_has_producto_compra1_idx` (`compra_id` ASC) ,
  CONSTRAINT `fk_compra_has_producto_compra1`
    FOREIGN KEY (`compra_id` )
    REFERENCES `compra` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_compra_has_producto_producto1`
    FOREIGN KEY (`producto_id` )
    REFERENCES `producto` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `codigobarranomenclatura`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `codigobarranomenclatura` ;

CREATE  TABLE IF NOT EXISTS `codigobarranomenclatura` (
  `id` INT NOT NULL ,
  `nombre` VARCHAR(45) NULL ,
  `tipo_conversion` VARCHAR(45) NULL ,
  `datecreated` DATETIME NULL ,
  `dateupdated` DATETIME NULL ,
  `createdby` VARCHAR(45) NULL ,
  `updatedby` VARCHAR(45) NULL ,
  `version` INT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `puntoventa`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `puntoventa` ;

CREATE  TABLE IF NOT EXISTS `puntoventa` (
  `id` INT NOT NULL ,
  `nombre` VARCHAR(45) NULL ,
  `tipo_diatioventas` VARCHAR(45) NULL ,
  `tipo_diariofactura` VARCHAR(45) NULL ,
  `activo` VARCHAR(45) NULL ,
  `estado` VARCHAR(45) NULL ,
  `codigobarranomenclatura_id` INT NOT NULL ,
  `sucursal_id` INT NOT NULL ,
  `datecreated` DATETIME NULL ,
  `dateupdated` DATETIME NULL ,
  `createdby` VARCHAR(45) NULL ,
  `updatedby` VARCHAR(45) NULL ,
  `version` INT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_puntoventa_codigobarra1_idx` (`codigobarranomenclatura_id` ASC) ,
  INDEX `fk_puntoventa_sucursal1_idx` (`sucursal_id` ASC) ,
  CONSTRAINT `fk_puntoventa_codigobarra1`
    FOREIGN KEY (`codigobarranomenclatura_id` )
    REFERENCES `codigobarranomenclatura` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_puntoventa_sucursal1`
    FOREIGN KEY (`sucursal_id` )
    REFERENCES `sucursal` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `codigobarraregla`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `codigobarraregla` ;

CREATE  TABLE IF NOT EXISTS `codigobarraregla` (
  `id` INT NOT NULL ,
  `nombreregla` VARCHAR(45) NULL ,
  `tipo` VARCHAR(45) NULL ,
  `patron` VARCHAR(45) NULL ,
  `secuencia` INT NULL ,
  `tipo_codificacion` VARCHAR(45) NULL ,
  `codigobarra_id` INT NOT NULL ,
  `datecreated` DATETIME NULL ,
  `dateupdated` DATETIME NULL ,
  `createdby` VARCHAR(45) NULL ,
  `updatedby` VARCHAR(45) NULL ,
  `version` INT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_codigobarraregla_codigobarra1_idx` (`codigobarra_id` ASC) ,
  CONSTRAINT `fk_codigobarraregla_codigobarra1`
    FOREIGN KEY (`codigobarra_id` )
    REFERENCES `codigobarranomenclatura` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `puntoventaatributo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `puntoventaatributo` ;

CREATE  TABLE IF NOT EXISTS `puntoventaatributo` (
  `id` INT NOT NULL ,
  `tipo_atributo` VARCHAR(45) NULL ,
  `valor` VARCHAR(45) NULL ,
  `puntoventa_id` INT NOT NULL ,
  `datecreated` DATETIME NULL ,
  `dateupdated` DATETIME NULL ,
  `createdby` VARCHAR(45) NULL ,
  `updatedby` VARCHAR(45) NULL ,
  `version` INT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_puntoventaatributo_puntoventa1_idx` (`puntoventa_id` ASC) ,
  CONSTRAINT `fk_puntoventaatributo_puntoventa1`
    FOREIGN KEY (`puntoventa_id` )
    REFERENCES `puntoventa` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `metodopago`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `metodopago` ;

CREATE  TABLE IF NOT EXISTS `metodopago` (
  `id` INT NOT NULL ,
  `nombrediario` VARCHAR(45) NULL ,
  `tipo_metodo` VARCHAR(45) NULL ,
  `codigocorto` VARCHAR(45) NULL ,
  `datecreated` DATETIME NULL ,
  `dateupdated` DATETIME NULL ,
  `createdby` VARCHAR(45) NULL ,
  `updatedby` VARCHAR(45) NULL ,
  `version` INT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `metodopagopuntoventa`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `metodopagopuntoventa` ;

CREATE  TABLE IF NOT EXISTS `metodopagopuntoventa` (
  `metodopago_id` INT NOT NULL ,
  `puntoventa_id` INT NOT NULL ,
  PRIMARY KEY (`metodopago_id`, `puntoventa_id`) ,
  INDEX `fk_metodopago_has_puntoventa_puntoventa1_idx` (`puntoventa_id` ASC) ,
  INDEX `fk_metodopago_has_puntoventa_metodopago1_idx` (`metodopago_id` ASC) ,
  CONSTRAINT `fk_metodopago_has_puntoventa_metodopago1`
    FOREIGN KEY (`metodopago_id` )
    REFERENCES `metodopago` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_metodopago_has_puntoventa_puntoventa1`
    FOREIGN KEY (`puntoventa_id` )
    REFERENCES `puntoventa` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sesion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sesion` ;

CREATE  TABLE IF NOT EXISTS `sesion` (
  `id` INT NOT NULL ,
  `codigo` VARCHAR(45) NULL ,
  `fechaapertura` DATETIME NULL ,
  `fechacierre` DATETIME NULL ,
  `estado` VARCHAR(45) NULL ,
  `empleado_id` INT NOT NULL ,
  `puntoventa_id` INT NOT NULL ,
  `datecreated` DATETIME NULL ,
  `dateupdated` DATETIME NULL ,
  `createdby` VARCHAR(45) NULL ,
  `updatedby` VARCHAR(45) NULL ,
  `version` INT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_sesion_empleado1_idx` (`empleado_id` ASC) ,
  INDEX `fk_sesion_puntoventa1_idx` (`puntoventa_id` ASC) ,
  CONSTRAINT `fk_sesion_empleado1`
    FOREIGN KEY (`empleado_id` )
    REFERENCES `empleado` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_sesion_puntoventa1`
    FOREIGN KEY (`puntoventa_id` )
    REFERENCES `puntoventa` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ticket`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ticket` ;

CREATE  TABLE IF NOT EXISTS `ticket` (
  `id` INT NOT NULL ,
  `sesion_id` INT NOT NULL ,
  `entidadrol_id` INT NOT NULL ,
  `subtotal` VARCHAR(45) NULL ,
  `descuento` VARCHAR(45) NULL ,
  `total` VARCHAR(45) NULL ,
  `efectivo` VARCHAR(45) NULL ,
  `cambio` VARCHAR(45) NULL ,
  `metodopago` VARCHAR(45) NULL ,
  `factura` TINYINT(1) NULL ,
  `codigo` VARCHAR(45) NULL ,
  `numeroorden` VARCHAR(45) NULL ,
  `inicio` DATETIME NULL ,
  `fin` DATETIME NULL ,
  `estado` VARCHAR(45) NULL ,
  `datecreated` DATETIME NULL ,
  `dateupdated` DATETIME NULL ,
  `createdby` VARCHAR(45) NULL ,
  `updatedby` VARCHAR(45) NULL ,
  `version` INT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_ticket_sesion1_idx` (`sesion_id` ASC) ,
  INDEX `fk_ticket_entidadrol1_idx` (`entidadrol_id` ASC) ,
  CONSTRAINT `fk_ticket_sesion1`
    FOREIGN KEY (`sesion_id` )
    REFERENCES `sesion` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ticket_entidadrol1`
    FOREIGN KEY (`entidadrol_id` )
    REFERENCES `entidadrol` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ticketproducto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ticketproducto` ;

CREATE  TABLE IF NOT EXISTS `ticketproducto` (
  `ticket_id` INT NOT NULL ,
  `producto_id` INT NOT NULL ,
  `cantidad` VARCHAR(45) NULL ,
  `ajusteprecio` VARCHAR(45) NULL ,
  `descuento` VARCHAR(45) NULL ,
  PRIMARY KEY (`ticket_id`, `producto_id`) ,
  INDEX `fk_ticket_has_producto_producto1_idx` (`producto_id` ASC) ,
  INDEX `fk_ticket_has_producto_ticket1_idx` (`ticket_id` ASC) ,
  CONSTRAINT `fk_ticket_has_producto_ticket1`
    FOREIGN KEY (`ticket_id` )
    REFERENCES `ticket` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ticket_has_producto_producto1`
    FOREIGN KEY (`producto_id` )
    REFERENCES `producto` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `personaorganizacion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `personaorganizacion` ;

CREATE  TABLE IF NOT EXISTS `personaorganizacion` (
  `persona_id` INT NOT NULL ,
  `organizacion_id` INT NOT NULL ,
  PRIMARY KEY (`persona_id`, `organizacion_id`) ,
  INDEX `fk_persona_has_organizacion_organizacion1_idx` (`organizacion_id` ASC) ,
  INDEX `fk_persona_has_organizacion_persona1_idx` (`persona_id` ASC) ,
  CONSTRAINT `fk_persona_has_organizacion_persona1`
    FOREIGN KEY (`persona_id` )
    REFERENCES `persona` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_persona_has_organizacion_organizacion1`
    FOREIGN KEY (`organizacion_id` )
    REFERENCES `organizacion` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
