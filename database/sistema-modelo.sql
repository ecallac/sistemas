-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema sistema
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema sistema
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `sistema` DEFAULT CHARACTER SET utf8 ;
-- -----------------------------------------------------
-- Schema security
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema security
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `security` DEFAULT CHARACTER SET latin1 ;
USE `sistema` ;

-- -----------------------------------------------------
-- Table `area`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `area` ;

CREATE TABLE IF NOT EXISTS `area` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(100) NULL,
  `activo` VARCHAR(45) NULL,
  `area_padre_id` INT NOT NULL,
  `datecreated` DATETIME NULL,
  `dateupdated` DATETIME NULL,
  `createdby` VARCHAR(45) NULL,
  `updatedby` VARCHAR(45) NULL,
  `version` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_area_area1_idx` (`area_padre_id` ASC),
  CONSTRAINT `fk_area_area1`
    FOREIGN KEY (`area_padre_id`)
    REFERENCES `area` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cargo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cargo` ;

CREATE TABLE IF NOT EXISTS `cargo` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(50) NULL,
  `activo` VARCHAR(45) NULL,
  `salariomin` DECIMAL(3,2) NULL,
  `salariomax` DECIMAL(3,2) NULL,
  `datecreated` DATETIME NULL,
  `dateupdated` DATETIME NULL,
  `createdby` VARCHAR(45) NULL,
  `updatedby` VARCHAR(45) NULL,
  `version` INT NULL,
  `cargo_parent_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_cargo_cargo1_idx` (`cargo_parent_id` ASC),
  CONSTRAINT `fk_cargo_cargo1`
    FOREIGN KEY (`cargo_parent_id`)
    REFERENCES `cargo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tipobase`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tipobase` ;

CREATE TABLE IF NOT EXISTS `tipobase` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `categoria` VARCHAR(45) NULL,
  `codigo` VARCHAR(45) NULL,
  `descripcion` VARCHAR(255) NULL,
  `activo` VARCHAR(45) NULL,
  `datecreated` DATETIME NULL,
  `dateupdated` DATETIME NULL,
  `createdby` VARCHAR(45) NULL,
  `updatedby` VARCHAR(45) NULL,
  `version` INT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `entidad`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `entidad` ;

CREATE TABLE IF NOT EXISTS `entidad` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `tipo_entidad` VARCHAR(45) NULL,
  `datecreated` DATETIME NULL,
  `dateupdated` DATETIME NULL,
  `createdby` VARCHAR(45) NULL,
  `updatedby` VARCHAR(45) NULL,
  `version` INT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `persona`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `persona` ;

CREATE TABLE IF NOT EXISTS `persona` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `tipo_documento_identificaion` VARCHAR(45) NULL,
  `numeroidentificacion` VARCHAR(45) NULL,
  `nombres` VARCHAR(45) NULL,
  `apellidos` VARCHAR(45) NULL,
  `tipo_estado_civil` VARCHAR(45) NULL,
  `sexo` VARCHAR(45) NULL,
  `fechanacimiento` DATETIME NULL,
  `email` VARCHAR(45) NULL,
  `entidad_id` INT NOT NULL,
  `datecreated` DATETIME NULL,
  `dateupdated` DATETIME NULL,
  `createdby` VARCHAR(45) NULL,
  `updatedby` VARCHAR(45) NULL,
  `version` INT NULL,
  `foto` VARCHAR(45) NULL,
  `tipo_profesion` VARCHAR(45) NULL,
  `status` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_persona_entidad1_idx` (`entidad_id` ASC),
  CONSTRAINT `fk_persona_entidad1`
    FOREIGN KEY (`entidad_id`)
    REFERENCES `entidad` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `empleado`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `empleado` ;

CREATE TABLE IF NOT EXISTS `empleado` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `persona_id` INT NOT NULL,
  `codigo` VARCHAR(45) NULL,
  `fechaingreso` DATETIME NULL,
  `tipo_titulo` VARCHAR(45) NULL,
  `apreciacion` INT NULL,
  `cargofamiliar` VARCHAR(45) NULL COMMENT 'si tiene a su cargo una familia',
  `datecreated` DATETIME NULL,
  `dateupdated` DATETIME NULL,
  `createdby` VARCHAR(45) NULL,
  `updatedby` VARCHAR(45) NULL,
  `version` INT NULL,
  `estado` VARCHAR(45) NULL,
  `reporta_id` INT NULL,
  `supervisorausencias_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_empleado_persona1_idx` (`persona_id` ASC),
  INDEX `fk_empleado_empleado1_idx` (`reporta_id` ASC),
  INDEX `fk_empleado_empleado2_idx` (`supervisorausencias_id` ASC),
  CONSTRAINT `fk_empleado_persona1`
    FOREIGN KEY (`persona_id`)
    REFERENCES `persona` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_empleado_empleado1`
    FOREIGN KEY (`reporta_id`)
    REFERENCES `empleado` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_empleado_empleado2`
    FOREIGN KEY (`supervisorausencias_id`)
    REFERENCES `empleado` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `organizacion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `organizacion` ;

CREATE TABLE IF NOT EXISTS `organizacion` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `tipo_organizacion` VARCHAR(45) NULL COMMENT 'dueño,laboratorio,proveedor',
  `numeroidentificacion` VARCHAR(45) NULL,
  `razonsocial` VARCHAR(225) NULL,
  `entidad_id` INT NOT NULL,
  `datecreated` DATETIME NULL,
  `dateupdated` DATETIME NULL,
  `createdby` VARCHAR(45) NULL,
  `updatedby` VARCHAR(45) NULL,
  `version` INT NULL,
  `logo` VARCHAR(45) NULL,
  `url` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_organizacion_entidad1_idx` (`entidad_id` ASC),
  CONSTRAINT `fk_organizacion_entidad1`
    FOREIGN KEY (`entidad_id`)
    REFERENCES `entidad` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ubigeo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ubigeo` ;

CREATE TABLE IF NOT EXISTS `ubigeo` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `datecreated` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dateupdated` DATETIME NULL,
  `createdby` VARCHAR(45) NULL,
  `updatedby` VARCHAR(45) NULL,
  `version` INT NOT NULL DEFAULT 0,
  `status` VARCHAR(45) NULL,
  `codigo` VARCHAR(45) NULL,
  `descripcion` VARCHAR(100) NULL,
  `abreviatura` VARCHAR(45) NULL,
  `tipo_ubigeo` VARCHAR(45) NULL,
  `parent_ubigeo_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_ubigeo_ubigeo2_idx` (`parent_ubigeo_id` ASC),
  CONSTRAINT `fk_ubigeo_ubigeo2`
    FOREIGN KEY (`parent_ubigeo_id`)
    REFERENCES `ubigeo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `direccion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `direccion` ;

CREATE TABLE IF NOT EXISTS `direccion` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `direccionexacta` VARCHAR(225) NULL,
  `codigopostal` VARCHAR(45) NULL,
  `entidad_id` INT NOT NULL,
  `ubigeo_id` INT NOT NULL,
  `esprincipal` VARCHAR(45) NULL,
  `estado` VARCHAR(45) NULL,
  `datecreated` DATETIME NULL,
  `dateupdated` DATETIME NULL,
  `createdby` VARCHAR(45) NULL,
  `updatedby` VARCHAR(45) NULL,
  `version` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_direccion_entidad_idx` (`entidad_id` ASC),
  INDEX `fk_direccion_ubigeo_idx` (`ubigeo_id` ASC),
  CONSTRAINT `fk_direccion_entidad`
    FOREIGN KEY (`entidad_id`)
    REFERENCES `entidad` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_direccion_ubigeo`
    FOREIGN KEY (`ubigeo_id`)
    REFERENCES `ubigeo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sucursal`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sucursal` ;

CREATE TABLE IF NOT EXISTS `sucursal` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(100) NULL,
  `estado` VARCHAR(45) NULL,
  `organizacion_id` INT NOT NULL,
  `tipo_sucursal` VARCHAR(45) NULL COMMENT 'almacen,tienda',
  `nombrecorto` VARCHAR(45) NULL,
  `direccion_id` INT NOT NULL,
  `datecreated` DATETIME NULL,
  `dateupdated` DATETIME NULL,
  `createdby` VARCHAR(45) NULL,
  `updatedby` VARCHAR(45) NULL,
  `version` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_sucursal_organizacion1_idx` (`organizacion_id` ASC),
  INDEX `fk_sucursal_direccion1_idx` (`direccion_id` ASC),
  CONSTRAINT `fk_sucursal_organizacion1`
    FOREIGN KEY (`organizacion_id`)
    REFERENCES `organizacion` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_sucursal_direccion1`
    FOREIGN KEY (`direccion_id`)
    REFERENCES `direccion` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `areasucursal`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `areasucursal` ;

CREATE TABLE IF NOT EXISTS `areasucursal` (
  `sucursal_id` INT NOT NULL,
  `area_id` INT NOT NULL,
  `responsable_id` INT NOT NULL,
  `estado` VARCHAR(45) NULL,
  `datecreated` DATETIME NULL,
  `dateupdated` DATETIME NULL,
  `createdby` VARCHAR(45) NULL,
  `updatedby` VARCHAR(45) NULL,
  `version` INT NULL,
  PRIMARY KEY (`sucursal_id`, `area_id`),
  INDEX `fk_sucursal_has_area_area1_idx` (`area_id` ASC),
  INDEX `fk_sucursal_has_area_sucursal1_idx` (`sucursal_id` ASC),
  INDEX `fk_areasucursal_empleado1_idx` (`responsable_id` ASC),
  CONSTRAINT `fk_sucursal_has_area_sucursal1`
    FOREIGN KEY (`sucursal_id`)
    REFERENCES `sucursal` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_sucursal_has_area_area1`
    FOREIGN KEY (`area_id`)
    REFERENCES `area` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_areasucursal_empleado1`
    FOREIGN KEY (`responsable_id`)
    REFERENCES `empleado` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `puestotrabajo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `puestotrabajo` ;

CREATE TABLE IF NOT EXISTS `puestotrabajo` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `area_id` INT NOT NULL,
  `sucursal_id` INT NOT NULL,
  `cargo_id` INT NOT NULL,
  `cantidadpuestos` INT NULL,
  `descripcion` VARCHAR(225) NULL,
  `estado` VARCHAR(45) NULL,
  `responsable_seleccion_id` INT NOT NULL,
  `fechafin` DATE NULL,
  `trabajoremoto` VARCHAR(45) NULL,
  `nombre` VARCHAR(100) NULL,
  `tipo_contrato` VARCHAR(45) NULL,
  `tipo_jornada` VARCHAR(45) NULL COMMENT 'tiempo completo, medio tiempo',
  `salario` DECIMAL(3,2) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_plaza_areasucursal1_idx` (`sucursal_id` ASC, `area_id` ASC),
  INDEX `fk_plaza_cargo1_idx` (`cargo_id` ASC),
  INDEX `fk_plaza_empleado1_idx` (`responsable_seleccion_id` ASC),
  CONSTRAINT `fk_plaza_areasucursal1`
    FOREIGN KEY (`sucursal_id` , `area_id`)
    REFERENCES `areasucursal` (`sucursal_id` , `area_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_plaza_cargo1`
    FOREIGN KEY (`cargo_id`)
    REFERENCES `cargo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_plaza_empleado1`
    FOREIGN KEY (`responsable_seleccion_id`)
    REFERENCES `empleado` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `contratoempleado`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `contratoempleado` ;

CREATE TABLE IF NOT EXISTS `contratoempleado` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `organizacion_id` INT NOT NULL,
  `representante_id` INT NOT NULL,
  `empleado_id` INT NOT NULL,
  `plaza_id` INT NOT NULL,
  `cargo_id` INT NOT NULL,
  `fechacommienzo` DATE NULL,
  `fechatermino` DATE NULL,
  `salario` DECIMAL(7,2) NULL,
  `horaentrada` TIME NULL,
  `horasalida` TIME NULL,
  `tipo_contrato` VARCHAR(45) NULL,
  `tipo_pago` VARCHAR(45) NULL COMMENT 'banco,efectivo',
  `estado` VARCHAR(45) NULL,
  `datecreated` DATETIME NULL,
  `dateupdated` DATETIME NULL,
  `createdby` VARCHAR(45) NULL,
  `updatedby` VARCHAR(45) NULL,
  `version` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_contratoempleado_organizacion1_idx` (`organizacion_id` ASC),
  INDEX `fk_contratoempleado_empleado1_idx` (`empleado_id` ASC),
  INDEX `fk_contratoempleado_empleado2_idx` (`representante_id` ASC),
  INDEX `fk_contratoempleado_plaza1_idx` (`plaza_id` ASC),
  INDEX `fk_contratoempleado_cargo1_idx` (`cargo_id` ASC),
  CONSTRAINT `fk_contratoempleado_organizacion1`
    FOREIGN KEY (`organizacion_id`)
    REFERENCES `organizacion` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_contratoempleado_empleado1`
    FOREIGN KEY (`empleado_id`)
    REFERENCES `empleado` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_contratoempleado_empleado2`
    FOREIGN KEY (`representante_id`)
    REFERENCES `empleado` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_contratoempleado_plaza1`
    FOREIGN KEY (`plaza_id`)
    REFERENCES `puestotrabajo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_contratoempleado_cargo1`
    FOREIGN KEY (`cargo_id`)
    REFERENCES `cargo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `telefono`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `telefono` ;

CREATE TABLE IF NOT EXISTS `telefono` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `tipo` VARCHAR(45) NULL,
  `numero` VARCHAR(45) NULL,
  `codigoarea` VARCHAR(45) NULL,
  `entidad_id` INT NOT NULL,
  `datecreated` DATETIME NULL,
  `dateupdated` DATETIME NULL,
  `createdby` VARCHAR(45) NULL,
  `updatedby` VARCHAR(45) NULL,
  `version` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_telefono_entidad1_idx` (`entidad_id` ASC),
  CONSTRAINT `fk_telefono_entidad1`
    FOREIGN KEY (`entidad_id`)
    REFERENCES `entidad` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `regla`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `regla` ;

CREATE TABLE IF NOT EXISTS `regla` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `categoria` VARCHAR(45) NULL,
  `nombre` VARCHAR(45) NULL,
  `codigo` VARCHAR(45) NULL,
  `descripcion` VARCHAR(255) NULL,
  `tipo_valor` VARCHAR(45) NULL,
  `activo` VARCHAR(45) NULL,
  `datecreated` DATETIME NULL,
  `dateupdated` DATETIME NULL,
  `createdby` VARCHAR(45) NULL,
  `updatedby` VARCHAR(45) NULL,
  `version` INT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `regladetalle`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `regladetalle` ;

CREATE TABLE IF NOT EXISTS `regladetalle` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `condicion` VARCHAR(45) NULL,
  `valornumero` DECIMAL(3,2) NULL,
  `valorcadena` VARCHAR(45) NULL,
  `valorfecha` DATETIME NULL,
  `activo` VARCHAR(45) NULL,
  `regla_id` INT NOT NULL,
  `datecreated` DATETIME NULL,
  `dateupdated` DATETIME NULL,
  `createdby` VARCHAR(45) NULL,
  `updatedby` VARCHAR(45) NULL,
  `version` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_regladetalle_regla1_idx` (`regla_id` ASC),
  CONSTRAINT `fk_regladetalle_regla1`
    FOREIGN KEY (`regla_id`)
    REFERENCES `regla` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `categoria`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `categoria` ;

CREATE TABLE IF NOT EXISTS `categoria` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(100) NULL,
  `tipo_categoria` VARCHAR(45) NULL,
  `tipo_estrategia_retiro` VARCHAR(45) NULL COMMENT 'fifo,lifo',
  `categoria_padre_id` INT NULL,
  `datecreated` DATETIME NULL,
  `dateupdated` DATETIME NULL,
  `createdby` VARCHAR(45) NULL,
  `updatedby` VARCHAR(45) NULL,
  `version` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_categoria_categoria1_idx` (`categoria_padre_id` ASC),
  CONSTRAINT `fk_categoria_categoria1`
    FOREIGN KEY (`categoria_padre_id`)
    REFERENCES `categoria` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ubicacion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ubicacion` ;

CREATE TABLE IF NOT EXISTS `ubicacion` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL,
  `ubicacion_padre_id` INT NOT NULL,
  `tipo_ubicacion` VARCHAR(45) NULL,
  `pasillo` VARCHAR(45) NULL,
  `estanteria` VARCHAR(45) NULL,
  `altura` VARCHAR(45) NULL,
  `sucursal_id` INT NOT NULL,
  `datecreated` DATETIME NULL,
  `dateupdated` DATETIME NULL,
  `createdby` VARCHAR(45) NULL,
  `updatedby` VARCHAR(45) NULL,
  `version` INT NULL,
  `categoria_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_ubicacion_ubicacion1_idx` (`ubicacion_padre_id` ASC),
  INDEX `fk_ubicacion_sucursal1_idx` (`sucursal_id` ASC),
  INDEX `fk_ubicacion_categoria1_idx` (`categoria_id` ASC),
  CONSTRAINT `fk_ubicacion_ubicacion1`
    FOREIGN KEY (`ubicacion_padre_id`)
    REFERENCES `ubicacion` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ubicacion_sucursal1`
    FOREIGN KEY (`sucursal_id`)
    REFERENCES `sucursal` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ubicacion_categoria1`
    FOREIGN KEY (`categoria_id`)
    REFERENCES `categoria` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `marca`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `marca` ;

CREATE TABLE IF NOT EXISTS `marca` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `descripcion` VARCHAR(100) NULL,
  `datecreated` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dateupdated` DATETIME NULL,
  `createdby` VARCHAR(45) NULL,
  `updatedby` VARCHAR(45) NULL,
  `version` INT NOT NULL DEFAULT 0,
  `status` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));


-- -----------------------------------------------------
-- Table `producto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `producto` ;

CREATE TABLE IF NOT EXISTS `producto` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `categoria_id` INT NOT NULL,
  `marca_id` INT NOT NULL,
  `laboratorio_id` INT NOT NULL,
  `tipo_producto` VARCHAR(45) NULL,
  `tipo_presentacion` VARCHAR(45) NULL,
  `tipo_unidadmedida` VARCHAR(45) NULL,
  `nombre` VARCHAR(100) NULL,
  `descripcion` VARCHAR(225) NULL,
  `uso` VARCHAR(225) NULL,
  `generico` VARCHAR(45) NULL,
  `puedevenderse` VARCHAR(45) NULL,
  `puedecomprarse` VARCHAR(45) NULL,
  `puedetransferirse` VARCHAR(45) NULL,
  `referenciainterna` VARCHAR(45) NULL,
  `codigobarras` VARCHAR(45) NULL,
  `peso` DECIMAL(3,2) NULL,
  `volumen` VARCHAR(45) NULL,
  `stockinicial` INT NULL,
  `fraccioninicial` INT NULL,
  `datecreated` DATETIME NULL,
  `dateupdated` DATETIME NULL,
  `createdby` VARCHAR(45) NULL,
  `updatedby` VARCHAR(45) NULL,
  `version` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_producto_categoria1_idx` (`categoria_id` ASC),
  INDEX `fk_producto_marca1_idx` (`marca_id` ASC),
  INDEX `fk_producto_organizacion1_idx` (`laboratorio_id` ASC),
  CONSTRAINT `fk_producto_categoria1`
    FOREIGN KEY (`categoria_id`)
    REFERENCES `categoria` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_producto_marca1`
    FOREIGN KEY (`marca_id`)
    REFERENCES `marca` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_producto_organizacion1`
    FOREIGN KEY (`laboratorio_id`)
    REFERENCES `organizacion` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `albaran`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `albaran` ;

CREATE TABLE IF NOT EXISTS `albaran` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL,
  `tipo_operacion` VARCHAR(45) NULL,
  `albaran_devoluciones_id` INT NOT NULL,
  `sucursal_id` INT NOT NULL,
  `ubicacion_origen_id` INT NOT NULL,
  `ubicacion_destino_id` INT NOT NULL,
  `datecreated` DATETIME NULL,
  `dateupdated` DATETIME NULL,
  `createdby` VARCHAR(45) NULL,
  `updatedby` VARCHAR(45) NULL,
  `version` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_albaran_albaran1_idx` (`albaran_devoluciones_id` ASC),
  INDEX `fk_albaran_sucursal1_idx` (`sucursal_id` ASC),
  INDEX `fk_albaran_ubicacion1_idx` (`ubicacion_origen_id` ASC),
  INDEX `fk_albaran_ubicacion2_idx` (`ubicacion_destino_id` ASC),
  CONSTRAINT `fk_albaran_albaran1`
    FOREIGN KEY (`albaran_devoluciones_id`)
    REFERENCES `albaran` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_albaran_sucursal1`
    FOREIGN KEY (`sucursal_id`)
    REFERENCES `sucursal` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_albaran_ubicacion1`
    FOREIGN KEY (`ubicacion_origen_id`)
    REFERENCES `ubicacion` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_albaran_ubicacion2`
    FOREIGN KEY (`ubicacion_destino_id`)
    REFERENCES `ubicacion` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `entidadrol`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `entidadrol` ;

CREATE TABLE IF NOT EXISTS `entidadrol` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `estado` VARCHAR(45) NULL,
  `tipo_entidadrol` VARCHAR(45) NULL,
  `entidad_id` INT NOT NULL,
  `datecreated` DATETIME NULL,
  `dateupdated` DATETIME NULL,
  `createdby` VARCHAR(45) NULL,
  `updatedby` VARCHAR(45) NULL,
  `version` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_entidadrol_entidad1_idx` (`entidad_id` ASC),
  CONSTRAINT `fk_entidadrol_entidad1`
    FOREIGN KEY (`entidad_id`)
    REFERENCES `entidad` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `transferencia`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `transferencia` ;

CREATE TABLE IF NOT EXISTS `transferencia` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `fechaprevista` DATETIME NULL,
  `estado` VARCHAR(45) NULL COMMENT 'borrador,en espera,preparado,completado',
  `descripcion` VARCHAR(100) NULL,
  `referencia` VARCHAR(45) NULL,
  `tipo_documento` VARCHAR(45) NULL,
  `organizacion_id` INT NOT NULL,
  `tipo_entrega` VARCHAR(45) NULL,
  `tipo_prioridad` VARCHAR(45) NULL COMMENT 'lo antes posible,cuando esten listos',
  `albaran_id` INT NOT NULL,
  `datecreated` DATETIME NULL,
  `dateupdated` DATETIME NULL,
  `createdby` VARCHAR(45) NULL,
  `updatedby` VARCHAR(45) NULL,
  `version` INT NULL,
  `documento` VARCHAR(45) NULL,
  `entidadrol_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_transferencia_organizacion1_idx` (`organizacion_id` ASC),
  INDEX `fk_transferencia_albaran1_idx` (`albaran_id` ASC),
  INDEX `fk_transferencia_entidadrol1_idx` (`entidadrol_id` ASC),
  CONSTRAINT `fk_transferencia_organizacion1`
    FOREIGN KEY (`organizacion_id`)
    REFERENCES `organizacion` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_transferencia_albaran1`
    FOREIGN KEY (`albaran_id`)
    REFERENCES `albaran` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_transferencia_entidadrol1`
    FOREIGN KEY (`entidadrol_id`)
    REFERENCES `entidadrol` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `transferenciaproducto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `transferenciaproducto` ;

CREATE TABLE IF NOT EXISTS `transferenciaproducto` (
  `producto_id` INT NOT NULL,
  `transferencia_id` INT NOT NULL,
  `cantidad` INT NULL,
  `fraccion` INT NULL,
  `estado` VARCHAR(45) NULL,
  PRIMARY KEY (`producto_id`, `transferencia_id`),
  INDEX `fk_producto_has_transferencia_transferencia1_idx` (`transferencia_id` ASC),
  INDEX `fk_producto_has_transferencia_producto1_idx` (`producto_id` ASC),
  CONSTRAINT `fk_producto_has_transferencia_producto1`
    FOREIGN KEY (`producto_id`)
    REFERENCES `producto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_producto_has_transferencia_transferencia1`
    FOREIGN KEY (`transferencia_id`)
    REFERENCES `transferencia` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `inventario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `inventario` ;

CREATE TABLE IF NOT EXISTS `inventario` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL,
  `fecha` DATE NULL,
  `tipo_inventario` VARCHAR(45) NULL,
  `incluirproductosagotados` VARCHAR(45) NULL,
  `categoria_id` INT NOT NULL,
  `estado` VARCHAR(45) NULL,
  `sucursal_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_inventario_categoria1_idx` (`categoria_id` ASC),
  INDEX `fk_inventario_sucursal1_idx` (`sucursal_id` ASC),
  CONSTRAINT `fk_inventario_categoria1`
    FOREIGN KEY (`categoria_id`)
    REFERENCES `categoria` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_inventario_sucursal1`
    FOREIGN KEY (`sucursal_id`)
    REFERENCES `sucursal` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `inventarioproducto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `inventarioproducto` ;

CREATE TABLE IF NOT EXISTS `inventarioproducto` (
  `inventario_id` INT NOT NULL,
  `producto_id` INT NOT NULL,
  `cantidadteorica` INT NULL,
  `cantidadreal` INT NULL,
  PRIMARY KEY (`inventario_id`, `producto_id`),
  INDEX `fk_inventario_has_producto_producto1_idx` (`producto_id` ASC),
  INDEX `fk_inventario_has_producto_inventario1_idx` (`inventario_id` ASC),
  CONSTRAINT `fk_inventario_has_producto_inventario1`
    FOREIGN KEY (`inventario_id`)
    REFERENCES `inventario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_inventario_has_producto_producto1`
    FOREIGN KEY (`producto_id`)
    REFERENCES `producto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `reglaabastecimiento`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `reglaabastecimiento` ;

CREATE TABLE IF NOT EXISTS `reglaabastecimiento` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL,
  `tipo_accion` VARCHAR(45) NULL,
  `ubicacion_origen_id` INT NOT NULL,
  `ubicacion_destino_id` INT NOT NULL,
  `albaran_id` INT NOT NULL,
  `datecreated` DATETIME NULL,
  `dateupdated` DATETIME NULL,
  `createdby` VARCHAR(45) NULL,
  `updatedby` VARCHAR(45) NULL,
  `version` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_regla_ubicacion1_idx` (`ubicacion_origen_id` ASC),
  INDEX `fk_regla_ubicacion2_idx` (`ubicacion_destino_id` ASC),
  INDEX `fk_regla_albaran1_idx` (`albaran_id` ASC),
  CONSTRAINT `fk_regla_ubicacion1`
    FOREIGN KEY (`ubicacion_origen_id`)
    REFERENCES `ubicacion` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_regla_ubicacion2`
    FOREIGN KEY (`ubicacion_destino_id`)
    REFERENCES `ubicacion` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_regla_albaran1`
    FOREIGN KEY (`albaran_id`)
    REFERENCES `albaran` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `solicitudcompra`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `solicitudcompra` ;

CREATE TABLE IF NOT EXISTS `solicitudcompra` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `datecreated` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dateupdated` DATETIME NULL,
  `createdby` VARCHAR(45) NULL,
  `updatedby` VARCHAR(45) NULL,
  `version` INT NOT NULL DEFAULT 0,
  `status` VARCHAR(45) NULL COMMENT 'abierto,cerrado',
  `entidadrol_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_solicitudcompra_entidadrol1_idx` (`entidadrol_id` ASC),
  CONSTRAINT `fk_solicitudcompra_entidadrol1`
    FOREIGN KEY (`entidadrol_id`)
    REFERENCES `entidadrol` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `abastecimiento`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `abastecimiento` ;

CREATE TABLE IF NOT EXISTS `abastecimiento` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `cantidad` INT NULL,
  `fechaprevista` DATETIME NULL,
  `tipo_prioridad` VARCHAR(45) NULL,
  `producto_id` INT NOT NULL,
  `sucursal_id` INT NOT NULL,
  `ubicacion_id` INT NOT NULL,
  `tipo_documento` VARCHAR(45) NULL,
  `regla_id` INT NOT NULL,
  `estado` VARCHAR(45) NULL COMMENT 'cotizado,en peoceso,pendiente',
  `descripsion` VARCHAR(225) NULL,
  `datecreated` DATETIME NULL,
  `dateupdated` DATETIME NULL,
  `createdby` VARCHAR(45) NULL,
  `updatedby` VARCHAR(45) NULL,
  `version` INT NULL,
  `solicitudcompra_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_abastecimiento_producto1_idx` (`producto_id` ASC),
  INDEX `fk_abastecimiento_sucursal1_idx` (`sucursal_id` ASC),
  INDEX `fk_abastecimiento_ubicacion1_idx` (`ubicacion_id` ASC),
  INDEX `fk_abastecimiento_regla1_idx` (`regla_id` ASC),
  INDEX `fk_abastecimiento_solicitudcompra1_idx` (`solicitudcompra_id` ASC),
  CONSTRAINT `fk_abastecimiento_producto1`
    FOREIGN KEY (`producto_id`)
    REFERENCES `producto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_abastecimiento_sucursal1`
    FOREIGN KEY (`sucursal_id`)
    REFERENCES `sucursal` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_abastecimiento_ubicacion1`
    FOREIGN KEY (`ubicacion_id`)
    REFERENCES `ubicacion` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_abastecimiento_regla1`
    FOREIGN KEY (`regla_id`)
    REFERENCES `reglaabastecimiento` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_abastecimiento_solicitudcompra1`
    FOREIGN KEY (`solicitudcompra_id`)
    REFERENCES `solicitudcompra` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `reabastecimiento`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `reabastecimiento` ;

CREATE TABLE IF NOT EXISTS `reabastecimiento` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL,
  `cantidadminima` INT NULL,
  `cantidadmaxima` INT NULL,
  `plazoentrega` INT NULL,
  `tipo_plazoentrega` VARCHAR(45) NULL,
  `producto_id` INT NOT NULL,
  `datecreated` DATETIME NULL,
  `dateupdated` DATETIME NULL,
  `createdby` VARCHAR(45) NULL,
  `updaetdby` VARCHAR(45) NULL,
  `version` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_reabastecimiento_producto1_idx` (`producto_id` ASC),
  CONSTRAINT `fk_reabastecimiento_producto1`
    FOREIGN KEY (`producto_id`)
    REFERENCES `producto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `codigobarranomenclatura`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `codigobarranomenclatura` ;

CREATE TABLE IF NOT EXISTS `codigobarranomenclatura` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL,
  `tipo_conversion` VARCHAR(45) NULL,
  `datecreated` DATETIME NULL,
  `dateupdated` DATETIME NULL,
  `createdby` VARCHAR(45) NULL,
  `updatedby` VARCHAR(45) NULL,
  `version` INT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `punto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `punto` ;

CREATE TABLE IF NOT EXISTS `punto` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL,
  `tipo_diarioventas` VARCHAR(45) NULL,
  `tipo_diariofactura` VARCHAR(45) NULL,
  `activo` VARCHAR(45) NULL,
  `estado` VARCHAR(45) NULL,
  `codigobarranomenclatura_id` INT NOT NULL,
  `sucursal_id` INT NOT NULL,
  `datecreated` DATETIME NULL,
  `dateupdated` DATETIME NULL,
  `createdby` VARCHAR(45) NULL,
  `updatedby` VARCHAR(45) NULL,
  `version` INT NULL,
  `tipo_punto` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_puntoventa_codigobarra1_idx` (`codigobarranomenclatura_id` ASC),
  INDEX `fk_puntoventa_sucursal1_idx` (`sucursal_id` ASC),
  CONSTRAINT `fk_puntoventa_codigobarra1`
    FOREIGN KEY (`codigobarranomenclatura_id`)
    REFERENCES `codigobarranomenclatura` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_puntoventa_sucursal1`
    FOREIGN KEY (`sucursal_id`)
    REFERENCES `sucursal` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cotizacion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cotizacion` ;

CREATE TABLE IF NOT EXISTS `cotizacion` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `entidadrol_id` INT NOT NULL,
  `empleado_id` INT NOT NULL,
  `fecha` DATETIME NULL,
  `tipo_moneda` VARCHAR(45) NULL,
  `impuesto` DECIMAL(3,2) NULL,
  `subtotal` DECIMAL(3,2) NULL,
  `igv` DECIMAL(3,2) NULL,
  `total` DECIMAL(3,2) NULL,
  `tipo_pago` VARCHAR(45) NULL,
  `estado` VARCHAR(45) NULL,
  `datecreated` DATETIME NULL,
  `dateupdated` DATETIME NULL,
  `createdby` VARCHAR(45) NULL,
  `updatedby` VARCHAR(45) NULL,
  `version` INT NULL,
  `puntoventa_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_cotizacion_entidadrol1_idx` (`entidadrol_id` ASC),
  INDEX `fk_cotizacion_empleado1_idx` (`empleado_id` ASC),
  INDEX `fk_cotizacion_puntoventa1_idx` (`puntoventa_id` ASC),
  CONSTRAINT `fk_cotizacion_entidadrol1`
    FOREIGN KEY (`entidadrol_id`)
    REFERENCES `entidadrol` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_cotizacion_empleado1`
    FOREIGN KEY (`empleado_id`)
    REFERENCES `empleado` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_cotizacion_puntoventa1`
    FOREIGN KEY (`puntoventa_id`)
    REFERENCES `punto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `venta`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `venta` ;

CREATE TABLE IF NOT EXISTS `venta` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `codigo` VARCHAR(45) NULL,
  `entidadrol_id` INT NOT NULL,
  `tipo_comprobante` VARCHAR(45) NULL,
  `empleado_id` INT NOT NULL,
  `fecha` DATETIME NULL,
  `estado` VARCHAR(45) NULL,
  `cotizacion_id` INT NULL,
  `tipo_moneda` VARCHAR(45) NULL,
  `impuesto` DECIMAL(3,2) NULL,
  `subtotal` DECIMAL(3,2) NULL,
  `igv` DECIMAL(3,2) NULL,
  `total` DECIMAL(3,2) NULL,
  `tipo_pago` VARCHAR(45) NULL,
  `datecreated` DATETIME NULL,
  `dateupdated` DATETIME NULL,
  `createdby` VARCHAR(45) NULL,
  `updatedby` VARCHAR(45) NULL,
  `version` INT NULL,
  `puntoventa_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_comprovante_entidadrol1_idx` (`entidadrol_id` ASC),
  INDEX `fk_venta_empleado1_idx` (`empleado_id` ASC),
  INDEX `fk_venta_cotizacion1_idx` (`cotizacion_id` ASC),
  INDEX `fk_venta_puntoventa1_idx` (`puntoventa_id` ASC),
  CONSTRAINT `fk_comprovante_entidadrol1`
    FOREIGN KEY (`entidadrol_id`)
    REFERENCES `entidadrol` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_venta_empleado1`
    FOREIGN KEY (`empleado_id`)
    REFERENCES `empleado` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_venta_cotizacion1`
    FOREIGN KEY (`cotizacion_id`)
    REFERENCES `cotizacion` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_venta_puntoventa1`
    FOREIGN KEY (`puntoventa_id`)
    REFERENCES `punto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ventaproducto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ventaproducto` ;

CREATE TABLE IF NOT EXISTS `ventaproducto` (
  `producto_id` INT NOT NULL,
  `venta_id` INT NOT NULL,
  `cantidad` INT NULL,
  `fraccion` INT NULL,
  `impuesto` DECIMAL(3,2) NULL,
  `importe` DECIMAL(3,2) NULL,
  `preciounitario` DECIMAL(3,2) NULL,
  `descuento` DECIMAL(3,2) NULL,
  PRIMARY KEY (`producto_id`, `venta_id`),
  INDEX `fk_venta_has_producto_producto1_idx` (`producto_id` ASC),
  INDEX `fk_venta_has_producto_venta1_idx` (`venta_id` ASC),
  CONSTRAINT `fk_venta_has_producto_producto1`
    FOREIGN KEY (`producto_id`)
    REFERENCES `producto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_venta_has_producto_venta1`
    FOREIGN KEY (`venta_id`)
    REFERENCES `venta` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cotizacionproducto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cotizacionproducto` ;

CREATE TABLE IF NOT EXISTS `cotizacionproducto` (
  `cotizacion_id` INT NOT NULL,
  `producto_id` INT NOT NULL,
  `cantidad` INT NULL,
  `fraccion` INT NULL,
  `impuesto` DECIMAL(3,2) NULL,
  `importe` DECIMAL(3,2) NULL,
  `preciounitario` DECIMAL(3,2) NULL,
  PRIMARY KEY (`cotizacion_id`, `producto_id`),
  INDEX `fk_cotizacion_has_producto_producto1_idx` (`producto_id` ASC),
  INDEX `fk_cotizacion_has_producto_cotizacion1_idx` (`cotizacion_id` ASC),
  CONSTRAINT `fk_cotizacion_has_producto_cotizacion1`
    FOREIGN KEY (`cotizacion_id`)
    REFERENCES `cotizacion` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_cotizacion_has_producto_producto1`
    FOREIGN KEY (`producto_id`)
    REFERENCES `producto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ordencompra`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ordencompra` ;

CREATE TABLE IF NOT EXISTS `ordencompra` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL,
  `datecreated` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dateupdated` DATETIME NULL,
  `createdby` VARCHAR(45) NULL,
  `updatedby` VARCHAR(45) NULL,
  `version` INT NOT NULL DEFAULT 0,
  `status` VARCHAR(45) NULL COMMENT 'nuevo,pendiente,aprobado,rechazado,envisado,recibido',
  `entidadrol_id` INT NOT NULL,
  `codigo` VARCHAR(45) NULL,
  `responsable_id` INT NOT NULL,
  `comentario` VARCHAR(225) NULL,
  `approver_id` INT NOT NULL,
  `totalimpuesto` DECIMAL(3,2) NULL,
  `totalimporte` DECIMAL(3,2) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_ordencompra_entidadrol1_idx` (`entidadrol_id` ASC),
  INDEX `fk_ordencompra_entidadrol2_idx` (`responsable_id` ASC),
  INDEX `fk_ordencompra_entidadrol3_idx` (`approver_id` ASC),
  CONSTRAINT `fk_ordencompra_entidadrol1`
    FOREIGN KEY (`entidadrol_id`)
    REFERENCES `entidadrol` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ordencompra_entidadrol2`
    FOREIGN KEY (`responsable_id`)
    REFERENCES `entidadrol` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ordencompra_entidadrol3`
    FOREIGN KEY (`approver_id`)
    REFERENCES `entidadrol` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `compra`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `compra` ;

CREATE TABLE IF NOT EXISTS `compra` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `entidadrol_id` INT NOT NULL,
  `empleado_id` INT NOT NULL,
  `fechafacturado` DATE NULL,
  `fechavencimiento` DATE NULL,
  `datecreated` DATETIME NULL,
  `dateupdated` DATETIME NULL,
  `createdby` VARCHAR(45) NULL,
  `updatedby` VARCHAR(45) NULL,
  `version` INT NULL,
  `puntoventa_id` INT NOT NULL,
  `ordencompra_id` INT NOT NULL,
  `tipo_documento` VARCHAR(45) NULL,
  `documento` VARCHAR(45) NULL,
  `estado` VARCHAR(45) NULL COMMENT 'borrador,en espera,preparado,validado',
  PRIMARY KEY (`id`),
  INDEX `fk_compra_entidadrol1_idx` (`entidadrol_id` ASC),
  INDEX `fk_compra_empleado1_idx` (`empleado_id` ASC),
  INDEX `fk_compra_puntoventa1_idx` (`puntoventa_id` ASC),
  INDEX `fk_compra_ordencompra1_idx` (`ordencompra_id` ASC),
  CONSTRAINT `fk_compra_entidadrol1`
    FOREIGN KEY (`entidadrol_id`)
    REFERENCES `entidadrol` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_compra_empleado1`
    FOREIGN KEY (`empleado_id`)
    REFERENCES `empleado` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_compra_puntoventa1`
    FOREIGN KEY (`puntoventa_id`)
    REFERENCES `punto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_compra_ordencompra1`
    FOREIGN KEY (`ordencompra_id`)
    REFERENCES `ordencompra` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `compraproducto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `compraproducto` ;

CREATE TABLE IF NOT EXISTS `compraproducto` (
  `compra_id` INT NOT NULL,
  `producto_id` INT NOT NULL,
  `cantidad` INT NULL,
  `fraccion` INT NULL,
  `preciounitario` VARCHAR(45) NULL,
  `impuestos` VARCHAR(45) NULL,
  `importe` VARCHAR(45) NULL,
  PRIMARY KEY (`compra_id`, `producto_id`),
  INDEX `fk_compra_has_producto_producto1_idx` (`producto_id` ASC),
  INDEX `fk_compra_has_producto_compra1_idx` (`compra_id` ASC),
  CONSTRAINT `fk_compra_has_producto_compra1`
    FOREIGN KEY (`compra_id`)
    REFERENCES `compra` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_compra_has_producto_producto1`
    FOREIGN KEY (`producto_id`)
    REFERENCES `producto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `codigobarraregla`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `codigobarraregla` ;

CREATE TABLE IF NOT EXISTS `codigobarraregla` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombreregla` VARCHAR(45) NULL,
  `tipo` VARCHAR(45) NULL,
  `patron` VARCHAR(45) NULL,
  `secuencia` INT NULL,
  `tipo_codificacion` VARCHAR(45) NULL,
  `codigobarra_id` INT NOT NULL,
  `datecreated` DATETIME NULL,
  `dateupdated` DATETIME NULL,
  `createdby` VARCHAR(45) NULL,
  `updatedby` VARCHAR(45) NULL,
  `version` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_codigobarraregla_codigobarra1_idx` (`codigobarra_id` ASC),
  CONSTRAINT `fk_codigobarraregla_codigobarra1`
    FOREIGN KEY (`codigobarra_id`)
    REFERENCES `codigobarranomenclatura` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `puntoatributo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `puntoatributo` ;

CREATE TABLE IF NOT EXISTS `puntoatributo` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `tipo_atributo` VARCHAR(45) NULL,
  `valor` VARCHAR(45) NULL,
  `punto_id` INT NOT NULL,
  `datecreated` DATETIME NULL,
  `dateupdated` DATETIME NULL,
  `createdby` VARCHAR(45) NULL,
  `updatedby` VARCHAR(45) NULL,
  `version` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_puntoventaatributo_puntoventa1_idx` (`punto_id` ASC),
  CONSTRAINT `fk_puntoventaatributo_puntoventa1`
    FOREIGN KEY (`punto_id`)
    REFERENCES `punto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `metodopago`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `metodopago` ;

CREATE TABLE IF NOT EXISTS `metodopago` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombrediario` VARCHAR(45) NULL,
  `tipo_metodo` VARCHAR(45) NULL,
  `codigocorto` VARCHAR(45) NULL,
  `datecreated` DATETIME NULL,
  `dateupdated` DATETIME NULL,
  `createdby` VARCHAR(45) NULL,
  `updatedby` VARCHAR(45) NULL,
  `version` INT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `metodopagopuntoventa`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `metodopagopuntoventa` ;

CREATE TABLE IF NOT EXISTS `metodopagopuntoventa` (
  `metodopago_id` INT NOT NULL,
  `punto_id` INT NOT NULL,
  PRIMARY KEY (`metodopago_id`, `punto_id`),
  INDEX `fk_metodopago_has_puntoventa_puntoventa1_idx` (`punto_id` ASC),
  INDEX `fk_metodopago_has_puntoventa_metodopago1_idx` (`metodopago_id` ASC),
  CONSTRAINT `fk_metodopago_has_puntoventa_metodopago1`
    FOREIGN KEY (`metodopago_id`)
    REFERENCES `metodopago` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_metodopago_has_puntoventa_puntoventa1`
    FOREIGN KEY (`punto_id`)
    REFERENCES `punto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sesion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sesion` ;

CREATE TABLE IF NOT EXISTS `sesion` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `codigo` VARCHAR(45) NULL,
  `fechaapertura` DATETIME NULL,
  `fechacierre` DATETIME NULL,
  `estado` VARCHAR(45) NULL,
  `empleado_id` INT NOT NULL,
  `punto_id` INT NOT NULL,
  `datecreated` DATETIME NULL,
  `dateupdated` DATETIME NULL,
  `createdby` VARCHAR(45) NULL,
  `updatedby` VARCHAR(45) NULL,
  `version` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_sesion_empleado1_idx` (`empleado_id` ASC),
  INDEX `fk_sesion_puntoventa1_idx` (`punto_id` ASC),
  CONSTRAINT `fk_sesion_empleado1`
    FOREIGN KEY (`empleado_id`)
    REFERENCES `empleado` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_sesion_puntoventa1`
    FOREIGN KEY (`punto_id`)
    REFERENCES `punto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ticket`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ticket` ;

CREATE TABLE IF NOT EXISTS `ticket` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `sesion_id` INT NOT NULL,
  `entidadrol_id` INT NOT NULL,
  `subtotal` VARCHAR(45) NULL,
  `descuento` VARCHAR(45) NULL,
  `total` VARCHAR(45) NULL,
  `efectivo` VARCHAR(45) NULL,
  `cambio` VARCHAR(45) NULL,
  `metodopago` VARCHAR(45) NULL,
  `factura` TINYINT(1) NULL,
  `codigo` VARCHAR(45) NULL,
  `numeroorden` VARCHAR(45) NULL,
  `inicio` DATETIME NULL,
  `fin` DATETIME NULL,
  `estado` VARCHAR(45) NULL,
  `datecreated` DATETIME NULL,
  `dateupdated` DATETIME NULL,
  `createdby` VARCHAR(45) NULL,
  `updatedby` VARCHAR(45) NULL,
  `version` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_ticket_sesion1_idx` (`sesion_id` ASC),
  INDEX `fk_ticket_entidadrol1_idx` (`entidadrol_id` ASC),
  CONSTRAINT `fk_ticket_sesion1`
    FOREIGN KEY (`sesion_id`)
    REFERENCES `sesion` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ticket_entidadrol1`
    FOREIGN KEY (`entidadrol_id`)
    REFERENCES `entidadrol` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ticketproducto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ticketproducto` ;

CREATE TABLE IF NOT EXISTS `ticketproducto` (
  `ticket_id` INT NOT NULL,
  `producto_id` INT NOT NULL,
  `cantidad` VARCHAR(45) NULL,
  `ajusteprecio` VARCHAR(45) NULL,
  `descuento` VARCHAR(45) NULL,
  PRIMARY KEY (`ticket_id`, `producto_id`),
  INDEX `fk_ticket_has_producto_producto1_idx` (`producto_id` ASC),
  INDEX `fk_ticket_has_producto_ticket1_idx` (`ticket_id` ASC),
  CONSTRAINT `fk_ticket_has_producto_ticket1`
    FOREIGN KEY (`ticket_id`)
    REFERENCES `ticket` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ticket_has_producto_producto1`
    FOREIGN KEY (`producto_id`)
    REFERENCES `producto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `historialventas`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `historialventas` ;

CREATE TABLE IF NOT EXISTS `historialventas` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `datecreated` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dateupdated` DATETIME NULL,
  `createdby` VARCHAR(45) NULL,
  `updatedby` VARCHAR(45) NULL,
  `version` INT NOT NULL DEFAULT 0,
  `status` VARCHAR(45) NOT NULL,
  `campo` VARCHAR(45) NULL,
  `valoranterior` VARCHAR(45) NULL,
  `valornuevo` VARCHAR(45) NULL,
  `venta_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_historialventas_venta1_idx` (`venta_id` ASC),
  INDEX `index_campo` (`campo` ASC),
  CONSTRAINT `fk_historialventas_venta1`
    FOREIGN KEY (`venta_id`)
    REFERENCES `venta` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `caja`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `caja` ;

CREATE TABLE IF NOT EXISTS `caja` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `datecreated` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dateupdated` DATETIME NULL,
  `createdby` VARCHAR(45) NULL,
  `updatedby` VARCHAR(45) NULL,
  `version` INT NOT NULL DEFAULT 0,
  `status` VARCHAR(45) NOT NULL,
  `fechainicio` DATETIME NULL,
  `fechafin` DATETIME NULL,
  `saldoinicial` DOUBLE NULL,
  `punto_id` INT NOT NULL,
  `saldofinalcalculado` DOUBLE NULL,
  `saldofinalcontabilizado` DOUBLE NULL,
  `descuadre` DOUBLE NULL,
  `saldosiguientecaja` DOUBLE NULL,
  `conforme` VARCHAR(25) NULL,
  `observacion` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_caja_punto1_idx` (`punto_id` ASC),
  CONSTRAINT `fk_caja_punto1`
    FOREIGN KEY (`punto_id`)
    REFERENCES `punto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `historialcompra`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `historialcompra` ;

CREATE TABLE IF NOT EXISTS `historialcompra` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `datecreated` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dateupdated` DATETIME NULL,
  `createdby` VARCHAR(45) NULL,
  `updatedby` VARCHAR(45) NULL,
  `version` INT NOT NULL DEFAULT 0,
  `status` VARCHAR(45) NOT NULL,
  `compra_id` INT NOT NULL,
  `campo` VARCHAR(45) NULL,
  `valoranterior` VARCHAR(45) NULL,
  `valornuevo` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_tabla_compra1_idx` (`compra_id` ASC),
  CONSTRAINT `fk_tabla_compra1`
    FOREIGN KEY (`compra_id`)
    REFERENCES `compra` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `componente`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `componente` ;

CREATE TABLE IF NOT EXISTS `componente` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `descripcion` VARCHAR(100) NULL,
  `datecreated` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dateupdated` DATETIME NULL,
  `createdby` VARCHAR(45) NULL,
  `updatedby` VARCHAR(45) NULL,
  `version` INT NOT NULL DEFAULT 0,
  `status` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));


-- -----------------------------------------------------
-- Table `productocomponente`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `productocomponente` ;

CREATE TABLE IF NOT EXISTS `productocomponente` (
  `producto_id` INT NOT NULL,
  `componente_id` INT NOT NULL,
  PRIMARY KEY (`producto_id`, `componente_id`),
  INDEX `fk_productocomponente_componente1_idx` (`componente_id` ASC),
  INDEX `fk_productocomponente_producto1_idx` (`producto_id` ASC),
  CONSTRAINT `fk_producto_has_componente_producto1`
    FOREIGN KEY (`producto_id`)
    REFERENCES `producto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_producto_has_componente_componente1`
    FOREIGN KEY (`componente_id`)
    REFERENCES `componente` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cuentabanco`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cuentabanco` ;

CREATE TABLE IF NOT EXISTS `cuentabanco` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `numero` VARCHAR(45) NULL,
  `cci` VARCHAR(45) NULL,
  `tipo_banco` VARCHAR(45) NULL,
  `datecreated` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dateupdated` DATETIME NULL,
  `createdby` VARCHAR(45) NULL,
  `updatedby` VARCHAR(45) NULL,
  `version` INT NOT NULL DEFAULT 0,
  `status` VARCHAR(45) NOT NULL,
  `entidad_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_cuentabanco_entidad1_idx` (`entidad_id` ASC),
  CONSTRAINT `fk_cuentabanco_entidad1`
    FOREIGN KEY (`entidad_id`)
    REFERENCES `entidad` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `concepto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `concepto` ;

CREATE TABLE IF NOT EXISTS `concepto` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL,
  `porcentaje` INT NULL,
  `tipo_concepto` VARCHAR(45) NULL COMMENT 'fijo o variable',
  `tipo_operacion` VARCHAR(45) NULL COMMENT 'aumento, descuento',
  `datecreated` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dateupdated` DATETIME NULL,
  `createdby` VARCHAR(45) NULL,
  `updatedby` VARCHAR(45) NULL,
  `version` INT NOT NULL DEFAULT 0,
  `status` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));


-- -----------------------------------------------------
-- Table `contratoempleadoconcepto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `contratoempleadoconcepto` ;

CREATE TABLE IF NOT EXISTS `contratoempleadoconcepto` (
  `contratoempleado_id` INT NOT NULL,
  `concepto_id` INT NOT NULL,
  PRIMARY KEY (`contratoempleado_id`, `concepto_id`),
  INDEX `fk_contratoempleado_has_concepto_concepto1_idx` (`concepto_id` ASC),
  INDEX `fk_contratoempleado_has_concepto_contratoempleado1_idx` (`contratoempleado_id` ASC),
  CONSTRAINT `fk_contratoempleado_has_concepto_contratoempleado1`
    FOREIGN KEY (`contratoempleado_id`)
    REFERENCES `contratoempleado` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_contratoempleado_has_concepto_concepto1`
    FOREIGN KEY (`concepto_id`)
    REFERENCES `concepto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `planilla`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `planilla` ;

CREATE TABLE IF NOT EXISTS `planilla` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `año` VARCHAR(45) NULL,
  `mes` VARCHAR(45) NULL,
  `fechaapertura` DATETIME NULL,
  `fehaclausura` VARCHAR(45) NULL,
  `planillacol` DATETIME NULL,
  `datecreated` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dateupdated` DATETIME NULL,
  `createdby` VARCHAR(45) NULL,
  `updatedby` VARCHAR(45) NULL,
  `version` INT NOT NULL DEFAULT 0,
  `status` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));


-- -----------------------------------------------------
-- Table `planillacontratoempleado`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `planillacontratoempleado` ;

CREATE TABLE IF NOT EXISTS `planillacontratoempleado` (
  `planilla_id` INT NOT NULL,
  `contratoempleado_id` INT NOT NULL,
  `salario` DECIMAL(3,2) NULL,
  `salariobruto` DECIMAL(3,2) NULL,
  `salarioneto` DECIMAL(3,2) NULL,
  `planillacontratoempleadocol` DECIMAL(3,2) NULL,
  PRIMARY KEY (`planilla_id`, `contratoempleado_id`),
  INDEX `fk_planilla_has_contratoempleado_contratoempleado1_idx` (`contratoempleado_id` ASC),
  INDEX `fk_planilla_has_contratoempleado_planilla1_idx` (`planilla_id` ASC),
  CONSTRAINT `fk_planilla_has_contratoempleado_planilla1`
    FOREIGN KEY (`planilla_id`)
    REFERENCES `planilla` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_planilla_has_contratoempleado_contratoempleado1`
    FOREIGN KEY (`contratoempleado_id`)
    REFERENCES `contratoempleado` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `planillacontratoempleadoconcepto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `planillacontratoempleadoconcepto` ;

CREATE TABLE IF NOT EXISTS `planillacontratoempleadoconcepto` (
  `planilla_id` INT NOT NULL,
  `contratoempleado_id` INT NOT NULL,
  `concepto_id` INT NOT NULL,
  `monto` DECIMAL(3,2) NULL,
  `argumento` VARCHAR(225) NULL,
  `fecha` DATE NULL,
  PRIMARY KEY (`planilla_id`, `contratoempleado_id`, `concepto_id`),
  INDEX `fk_planillacontratoempleado_has_concepto_concepto1_idx` (`concepto_id` ASC),
  INDEX `fk_planillacontratoempleado_has_concepto_planillacontratoem_idx` (`planilla_id` ASC, `contratoempleado_id` ASC),
  CONSTRAINT `fk_planillacontratoempleado_has_concepto_planillacontratoempl1`
    FOREIGN KEY (`planilla_id` , `contratoempleado_id`)
    REFERENCES `planillacontratoempleado` (`planilla_id` , `contratoempleado_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_planillacontratoempleado_has_concepto_concepto1`
    FOREIGN KEY (`concepto_id`)
    REFERENCES `concepto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `diafestivo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `diafestivo` ;

CREATE TABLE IF NOT EXISTS `diafestivo` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(100) NULL,
  `fecha` DATE NULL,
  `datecreated` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dateupdated` DATETIME NULL,
  `createdby` VARCHAR(45) NULL,
  `updatedby` VARCHAR(45) NULL,
  `version` INT NOT NULL DEFAULT 0,
  `status` VARCHAR(45) NOT NULL,
  `sucursal_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_diafestivo_sucursal1_idx` (`sucursal_id` ASC),
  CONSTRAINT `fk_diafestivo_sucursal1`
    FOREIGN KEY (`sucursal_id`)
    REFERENCES `sucursal` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `ausencia`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ausencia` ;

CREATE TABLE IF NOT EXISTS `ausencia` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `datecreated` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dateupdated` DATETIME NULL,
  `createdby` VARCHAR(45) NULL,
  `updatedby` VARCHAR(45) NULL,
  `version` INT NOT NULL DEFAULT 0,
  `status` VARCHAR(45) NOT NULL,
  `fechainicio` DATE NULL,
  `fechafin` DATE NULL,
  `empleado_id` INT NOT NULL,
  `tipo_ausencia` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_ausencia_empleado1_idx` (`empleado_id` ASC),
  CONSTRAINT `fk_ausencia_empleado1`
    FOREIGN KEY (`empleado_id`)
    REFERENCES `empleado` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `facereclutamiento`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `facereclutamiento` ;

CREATE TABLE IF NOT EXISTS `facereclutamiento` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `datecreated` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dateupdated` DATETIME NULL,
  `createdby` VARCHAR(45) NULL,
  `updatedby` VARCHAR(45) NULL,
  `version` INT NOT NULL DEFAULT 0,
  `status` VARCHAR(45) NOT NULL,
  `nombre` VARCHAR(45) NULL,
  `descripcion` VARCHAR(225) NULL,
  `orden` INT NULL,
  PRIMARY KEY (`id`));


-- -----------------------------------------------------
-- Table `facepuestodetrabajo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `facepuestodetrabajo` ;

CREATE TABLE IF NOT EXISTS `facepuestodetrabajo` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `datecreated` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dateupdated` DATETIME NULL,
  `createdby` VARCHAR(45) NULL,
  `updatedby` VARCHAR(45) NULL,
  `version` INT NOT NULL DEFAULT 0,
  `status` VARCHAR(45) NULL,
  `facereclutamiento_id` INT NOT NULL,
  `puestotrabajo_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_facepuestodetrabajo_facereclutamiento1_idx` (`facereclutamiento_id` ASC),
  INDEX `fk_facepuestodetrabajo_puestotrabajo1_idx` (`puestotrabajo_id` ASC),
  CONSTRAINT `fk_facepuestodetrabajo_facereclutamiento1`
    FOREIGN KEY (`facereclutamiento_id`)
    REFERENCES `facereclutamiento` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_facepuestodetrabajo_puestotrabajo1`
    FOREIGN KEY (`puestotrabajo_id`)
    REFERENCES `puestotrabajo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `postulante`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `postulante` ;

CREATE TABLE IF NOT EXISTS `postulante` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `datecreated` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dateupdated` DATETIME NULL,
  `createdby` VARCHAR(45) NULL,
  `updatedby` VARCHAR(45) NULL,
  `version` INT NOT NULL DEFAULT 0,
  `status` VARCHAR(45) NOT NULL,
  `comentarios` VARCHAR(45) NULL,
  `remuneraciontentativo` DECIMAL(3,2) NULL,
  `cv` VARCHAR(100) NULL,
  `persona_id` INT NOT NULL,
  `facepuestodetrabajo_id` INT NOT NULL,
  `tipo_razonrechazo` VARCHAR(45) NULL,
  `disponibilidad` DATE NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_postulante_persona1_idx` (`persona_id` ASC),
  INDEX `fk_postulante_facepuestodetrabajo1_idx` (`facepuestodetrabajo_id` ASC),
  CONSTRAINT `fk_postulante_persona1`
    FOREIGN KEY (`persona_id`)
    REFERENCES `persona` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_postulante_facepuestodetrabajo1`
    FOREIGN KEY (`facepuestodetrabajo_id`)
    REFERENCES `facepuestodetrabajo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `managerreclutamiento`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `managerreclutamiento` ;

CREATE TABLE IF NOT EXISTS `managerreclutamiento` (
  `puestotrabajo_id` INT NOT NULL,
  `empleado_id` INT NOT NULL,
  PRIMARY KEY (`puestotrabajo_id`, `empleado_id`),
  INDEX `fk_puestotrabajo_has_empleado_empleado1_idx` (`empleado_id` ASC),
  INDEX `fk_puestotrabajo_has_empleado_puestotrabajo1_idx` (`puestotrabajo_id` ASC),
  CONSTRAINT `fk_puestotrabajo_has_empleado_puestotrabajo1`
    FOREIGN KEY (`puestotrabajo_id`)
    REFERENCES `puestotrabajo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_puestotrabajo_has_empleado_empleado1`
    FOREIGN KEY (`empleado_id`)
    REFERENCES `empleado` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lote`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lote` ;

CREATE TABLE IF NOT EXISTS `lote` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `codigo` VARCHAR(45) NULL,
  `caduca` VARCHAR(45) NULL,
  `fechavencimiento` DATE NULL,
  `datecreated` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dateupdated` DATETIME NULL,
  `createdby` VARCHAR(45) NULL,
  `updatedby` VARCHAR(45) NULL,
  `version` INT NOT NULL DEFAULT 0,
  `status` VARCHAR(45) NULL,
  `producto_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_lote_producto1_idx` (`producto_id` ASC),
  CONSTRAINT `fk_lote_producto1`
    FOREIGN KEY (`producto_id`)
    REFERENCES `producto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `precios`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `precios` ;

CREATE TABLE IF NOT EXISTS `precios` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `datecreated` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dateupdated` DATETIME NULL,
  `createdby` VARCHAR(45) NULL,
  `updatedby` VARCHAR(45) NULL,
  `version` INT NOT NULL DEFAULT 0,
  `status` VARCHAR(45) NULL,
  `preciocompra` DECIMAL(3,2) NULL,
  `precioventa` DECIMAL(3,2) NULL,
  `precioventamayor` DECIMAL(3,2) NULL,
  `producto_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_precios_producto1_idx` (`producto_id` ASC),
  CONSTRAINT `fk_precios_producto1`
    FOREIGN KEY (`producto_id`)
    REFERENCES `producto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `entidadrelacion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `entidadrelacion` ;

CREATE TABLE IF NOT EXISTS `entidadrelacion` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `datecreated` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dateupdated` DATETIME NULL,
  `createdby` VARCHAR(45) NULL,
  `updatedby` VARCHAR(45) NULL,
  `version` INT NOT NULL DEFAULT 0,
  `status` VARCHAR(45) NULL,
  `tipo_relacion` VARCHAR(45) NULL COMMENT 'familiar, representante,contacto,aprobador de compras',
  `deentidadd_id` INT NOT NULL,
  `aentidad_id` INT NOT NULL,
  PRIMARY KEY (`id`, `deentidadd_id`, `aentidad_id`),
  INDEX `fk_entidadrelacion_entidad1_idx` (`deentidadd_id` ASC),
  INDEX `fk_entidadrelacion_entidad2_idx` (`aentidad_id` ASC),
  CONSTRAINT `fk_entidadrelacion_entidad1`
    FOREIGN KEY (`deentidadd_id`)
    REFERENCES `entidad` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_entidadrelacion_entidad2`
    FOREIGN KEY (`aentidad_id`)
    REFERENCES `entidad` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `entidadrolatributo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `entidadrolatributo` ;

CREATE TABLE IF NOT EXISTS `entidadrolatributo` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `datecreated` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dateupdated` DATETIME NULL,
  `createdby` VARCHAR(45) NULL,
  `updatedby` VARCHAR(45) NULL,
  `version` INT NOT NULL DEFAULT 0,
  `status` VARCHAR(45) NULL,
  `tipo_atributo` VARCHAR(45) NULL COMMENT 'estado_proveedor,esproveedorcritico,listaemail,tipo_pago,',
  `valor` VARCHAR(225) NULL,
  `entidadrol_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_entidadrolatributo_entidadrol1_idx` (`entidadrol_id` ASC),
  CONSTRAINT `fk_entidadrolatributo_entidadrol1`
    FOREIGN KEY (`entidadrol_id`)
    REFERENCES `entidadrol` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `abastecimientoproveedor`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `abastecimientoproveedor` ;

CREATE TABLE IF NOT EXISTS `abastecimientoproveedor` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `datecreated` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dateupdated` DATETIME NULL,
  `createdby` VARCHAR(45) NULL,
  `updatedby` VARCHAR(45) NULL,
  `version` INT NOT NULL DEFAULT 0,
  `status` VARCHAR(45) NULL,
  `abastecimiento_id` INT NOT NULL,
  `unidadmedida` VARCHAR(45) NULL,
  `precio` VARCHAR(45) NULL,
  `inpuesto` VARCHAR(45) NULL,
  `importe` VARCHAR(45) NULL,
  `ordencompra_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_tabla_abastecimiento1_idx` (`abastecimiento_id` ASC),
  INDEX `fk_abastecimientoproveedor_ordencompra1_idx` (`ordencompra_id` ASC),
  CONSTRAINT `fk_tabla_abastecimiento1`
    FOREIGN KEY (`abastecimiento_id`)
    REFERENCES `abastecimiento` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_abastecimientoproveedor_ordencompra1`
    FOREIGN KEY (`ordencompra_id`)
    REFERENCES `ordencompra` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `desecho`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `desecho` ;

CREATE TABLE IF NOT EXISTS `desecho` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `datecreated` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dateupdated` DATETIME NULL,
  `createdby` VARCHAR(45) NULL,
  `updatedby` VARCHAR(45) NULL,
  `version` INT NOT NULL DEFAULT 0,
  `status` VARCHAR(45) NULL,
  `cantidad` INT NULL,
  `documento` VARCHAR(45) NULL,
  `producto_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_desecho_producto1_idx` (`producto_id` ASC),
  CONSTRAINT `fk_desecho_producto1`
    FOREIGN KEY (`producto_id`)
    REFERENCES `producto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `oferta`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `oferta` ;

CREATE TABLE IF NOT EXISTS `oferta` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `datecreated` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dateupdated` DATETIME NULL,
  `createdby` VARCHAR(45) NULL,
  `updatedby` VARCHAR(45) NULL,
  `version` INT NOT NULL DEFAULT 0,
  `status` VARCHAR(45) NULL,
  `codprodproveedor` VARCHAR(45) NULL,
  `tiempoentrega` INT NULL COMMENT 'dias',
  `cantidad` INT NULL,
  `preciounitario` DECIMAL(3,2) NULL,
  `validezinicio` DATE NULL,
  `validezfin` DATE NULL,
  `producto_id` INT NOT NULL,
  `entidadrol_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_oferta_producto1_idx` (`producto_id` ASC),
  INDEX `fk_oferta_entidadrol1_idx` (`entidadrol_id` ASC),
  CONSTRAINT `fk_oferta_producto1`
    FOREIGN KEY (`producto_id`)
    REFERENCES `producto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_oferta_entidadrol1`
    FOREIGN KEY (`entidadrol_id`)
    REFERENCES `entidadrol` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `controldinero`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `controldinero` ;

CREATE TABLE IF NOT EXISTS `controldinero` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `tipo_proceso` VARCHAR(45) NULL COMMENT 'venta de contado,pagos de deudores,venta de activos,prestamos obtenidos,rendimiento de inversiones\ncompra de materia prima, compra inventario, pago proveedores, pago a trabajadores, pago de prestamos, compra de activos fijos',
  `tipo_comprobante` VARCHAR(45) NULL COMMENT 'factura, boleta, ticket, baucher banco',
  `numerocomprobante` VARCHAR(45) NULL,
  `concepto` VARCHAR(45) NULL,
  `importeingreso` DOUBLE NULL,
  `importeegreso` DOUBLE NULL,
  `saldototal` DOUBLE NULL,
  `datecreated` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dateupdated` DATETIME NULL,
  `createdby` VARCHAR(45) NULL,
  `updatedby` VARCHAR(45) NULL,
  `version` INT NOT NULL DEFAULT 0,
  `status` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));

USE `security` ;

-- -----------------------------------------------------
-- Table `module`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `module` ;

CREATE TABLE IF NOT EXISTS `module` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `description` VARCHAR(100) NULL DEFAULT NULL,
  `enabled` VARCHAR(3) NULL DEFAULT NULL,
  `dateCreated` DATETIME NULL DEFAULT NULL,
  `dateUpdated` DATETIME NULL DEFAULT NULL,
  `createdBy` VARCHAR(45) NULL DEFAULT NULL,
  `updatedBy` VARCHAR(45) NULL DEFAULT NULL,
  `version` INT(11) NULL DEFAULT NULL,
  `author` VARCHAR(255) NULL DEFAULT NULL,
  `moduleVersion` VARCHAR(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 35
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `permission`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `permission` ;

CREATE TABLE IF NOT EXISTS `permission` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `module_id` INT(11) NOT NULL,
  `name` VARCHAR(100) NULL DEFAULT NULL,
  `description` VARCHAR(255) NULL DEFAULT NULL,
  `path` VARCHAR(100) NULL DEFAULT NULL,
  `enabled` VARCHAR(3) NULL DEFAULT NULL,
  `dateCreated` DATETIME NULL DEFAULT NULL,
  `dateUpdated` DATETIME NULL DEFAULT NULL,
  `createdBy` VARCHAR(45) NULL DEFAULT NULL,
  `updatedBy` VARCHAR(45) NULL DEFAULT NULL,
  `version` INT(11) NULL DEFAULT NULL,
  `parent_permission_id` INT(11) NULL DEFAULT NULL,
  `type` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_permission_module_idx` (`module_id` ASC),
  INDEX `fk_permission_permission1_idx` (`parent_permission_id` ASC),
  CONSTRAINT `fk_permission_module`
    FOREIGN KEY (`module_id`)
    REFERENCES `module` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_permission_permission1`
    FOREIGN KEY (`parent_permission_id`)
    REFERENCES `permission` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 12
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `role` ;

CREATE TABLE IF NOT EXISTS `role` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NULL DEFAULT NULL,
  `description` VARCHAR(255) NULL DEFAULT NULL,
  `enabled` VARCHAR(3) NULL DEFAULT NULL,
  `dateCreated` DATETIME NULL DEFAULT NULL,
  `dateUpdated` DATETIME NULL DEFAULT NULL,
  `createdBy` VARCHAR(45) NULL DEFAULT NULL,
  `updatedBy` VARCHAR(45) NULL DEFAULT NULL,
  `version` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `rolepermission`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rolepermission` ;

CREATE TABLE IF NOT EXISTS `rolepermission` (
  `role_id` INT(11) NOT NULL,
  `permission_id` INT(11) NOT NULL,
  PRIMARY KEY (`role_id`, `permission_id`),
  INDEX `fk_role_has_permission_permission1_idx` (`permission_id` ASC),
  INDEX `fk_role_has_permission_role1_idx` (`role_id` ASC),
  CONSTRAINT `fk_role_has_permission_permission1`
    FOREIGN KEY (`permission_id`)
    REFERENCES `permission` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_role_has_permission_role1`
    FOREIGN KEY (`role_id`)
    REFERENCES `role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(50) NULL DEFAULT NULL,
  `password` VARCHAR(255) NULL DEFAULT NULL,
  `status` VARCHAR(20) NULL DEFAULT NULL,
  `entidadrole_id` INT(11) NULL DEFAULT NULL,
  `question` VARCHAR(255) NULL DEFAULT NULL,
  `answer` VARCHAR(255) NULL DEFAULT NULL,
  `dateCreated` DATETIME NULL DEFAULT NULL,
  `dateUpdated` DATETIME NULL DEFAULT NULL,
  `createdBy` VARCHAR(45) NULL DEFAULT NULL,
  `updatedBy` VARCHAR(45) NULL DEFAULT NULL,
  `version` INT(11) NULL DEFAULT NULL,
  `activationDate` DATETIME NULL DEFAULT NULL,
  `inactivationDate` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `roleuser`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `roleuser` ;

CREATE TABLE IF NOT EXISTS `roleuser` (
  `role_id` INT(11) NOT NULL,
  `user_id` INT(11) NOT NULL,
  PRIMARY KEY (`role_id`, `user_id`),
  INDEX `fk_role_has_user_user1_idx` (`user_id` ASC),
  INDEX `fk_role_has_user_role1_idx` (`role_id` ASC),
  CONSTRAINT `fk_role_has_user_role1`
    FOREIGN KEY (`role_id`)
    REFERENCES `role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_role_has_user_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `session`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `session` ;

CREATE TABLE IF NOT EXISTS `session` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_id` INT(11) NOT NULL,
  `loginDate` DATETIME NULL DEFAULT NULL,
  `logoutDate` DATETIME NULL DEFAULT NULL,
  `sessionKey` VARCHAR(255) NULL DEFAULT NULL,
  `hostAddress` VARCHAR(45) NULL DEFAULT NULL,
  `module_id` INT(11) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_session_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_session_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

USE `security` ;

-- -----------------------------------------------------
-- Placeholder table for view `rolepermissionview`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rolepermissionview` (`module_id` INT, `permission_name` INT, `permission_description` INT, `path` INT, `permission_enabled` INT, `parent_permission_id` INT, `permission_id` INT, `role_id` INT, `role_name` INT, `role_description` INT, `role_enabled` INT);

-- -----------------------------------------------------
-- Placeholder table for view `roleuserview`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `roleuserview` (`name` INT, `description` INT, `role_enabled` INT, `role_id` INT, `user_id` INT, `username` INT, `password` INT, `user_status` INT, `entidadrole_id` INT, `question` INT, `answer` INT, `activationdate` INT, `inactivationdate` INT);

-- -----------------------------------------------------
-- View `rolepermissionview`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rolepermissionview`;
DROP VIEW IF EXISTS `rolepermissionview` ;
USE `security`;
CREATE  OR REPLACE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `rolepermissionview` AS select `p`.`module_id` AS `module_id`,`p`.`name` AS `permission_name`,`p`.`description` AS `permission_description`,`p`.`path` AS `path`,`p`.`enabled` AS `permission_enabled`,`p`.`parent_permission_id` AS `parent_permission_id`,`rp`.`permission_id` AS `permission_id`,`rp`.`role_id` AS `role_id`,`r`.`name` AS `role_name`,`r`.`description` AS `role_description`,`r`.`enabled` AS `role_enabled` from ((`role` `r` join `rolepermission` `rp` on((`r`.`id` = `rp`.`role_id`))) join `permission` `p` on((`rp`.`permission_id` = `p`.`id`)));

-- -----------------------------------------------------
-- View `roleuserview`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `roleuserview`;
DROP VIEW IF EXISTS `roleuserview` ;
USE `security`;
CREATE  OR REPLACE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `roleuserview` AS select `r`.`name` AS `name`,`r`.`description` AS `description`,`r`.`enabled` AS `role_enabled`,`ru`.`role_id` AS `role_id`,`ru`.`user_id` AS `user_id`,`u`.`username` AS `username`,`u`.`password` AS `password`,`u`.`status` AS `user_status`,`u`.`entidadrole_id` AS `entidadrole_id`,`u`.`question` AS `question`,`u`.`answer` AS `answer`,`u`.`activationDate` AS `activationdate`,`u`.`inactivationDate` AS `inactivationdate` from ((`role` `r` join `roleuser` `ru` on((`r`.`id` = `ru`.`role_id`))) join `user` `u` on((`ru`.`user_id` = `u`.`id`)));

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
