CREATE DATABASE bdBodega

GO

USE bdBodega

GO

CREATE TABLE tbUnidadMedida (
  codigo_uni VARCHAR(20) NOT NULL,
  nombre_uni VARCHAR(150) NULL,
  descripcion_uni TEXT NULL,
  PRIMARY KEY(codigo_uni)
)

GO

CREATE TABLE tbProveedor (
  codigo_prov VARCHAR(20) NOT NULL,
  ruc_prov VARCHAR(15) NULL,
  razon_social_prov VARCHAR(150) NULL,
  direccion_prov VARCHAR(150) NULL,
  telefono_prov VARCHAR(15) NULL,
  email_prov VARCHAR(100) NULL,
  observacion_prov TEXT NULL,
  PRIMARY KEY(codigo_prov)
)

GO

CREATE TABLE tbMarca (
  codigo_mar VARCHAR(20) NOT NULL,
  nombre_mar VARCHAR(150) NULL,
  descripcion_mar TEXT NULL,
  PRIMARY KEY(codigo_mar)
)

GO

CREATE TABLE tbCliente (
  codigo_cli VARCHAR(20) NOT NULL,
  ruc_dni_cli VARCHAR(15) NULL,
  razon_social_cli VARCHAR(150) NULL,
  direccion_cli VARCHAR(150) NULL,
  PRIMARY KEY(codigo_cli)
)

GO

CREATE TABLE tbCargo (
  codigo_car VARCHAR(20) NOT NULL,
  nombre_car VARCHAR(150) NULL,
  descripcion_car TEXT NULL,
  PRIMARY KEY(codigo_car)
)

GO

CREATE TABLE tbCategoria (
  codigo_cat VARCHAR(20) NOT NULL,
  nombre_cat VARCHAR(150) NULL,
  descripcion_cat TEXT NULL,
  PRIMARY KEY(codigo_cat)
)

GO

CREATE TABLE tbEmpleado (
  codigo_emp VARCHAR(20) NOT NULL,
  codigo_car VARCHAR(20) NOT NULL,
  dni_emp VARCHAR(15) NULL,
  nombre_emp VARCHAR(50) NULL,
  apellidos_emp VARCHAR(100) NULL,
  sexo_emp VARCHAR(20) NULL,
  fecha_nacimiento_emp DATE NULL,
  direccion_emp VARCHAR(150) NULL,
  telefono_emp VARCHAR(15) NULL,
  celular_emp VARCHAR(15) NULL,
  email_emp VARCHAR(100) NULL,
  foto_emp IMAGE NULL,
  observacion_emp TEXT NULL,
  PRIMARY KEY(codigo_emp),
  FOREIGN KEY(codigo_car)
    REFERENCES tbCargo(codigo_car)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
)

GO

CREATE TABLE tbBoleta (
  codigo_bol VARCHAR(20) NOT NULL,
  codigo_emp VARCHAR(20) NOT NULL,
  codigo_cli VARCHAR(20) NOT NULL,
  fecha_bol DATE NULL,
  PRIMARY KEY(codigo_bol),
  FOREIGN KEY(codigo_cli)
    REFERENCES tbCliente(codigo_cli)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(codigo_emp)
    REFERENCES tbEmpleado(codigo_emp)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
)

GO

CREATE TABLE tbProducto (
  codigo_pro VARCHAR(20) NOT NULL,
  codigo_prov VARCHAR(20) NOT NULL,
  codigo_cat VARCHAR(20) NOT NULL,
  codigo_mar VARCHAR(20) NOT NULL,
  codigo_uni VARCHAR(20) NOT NULL,
  nombre_pro VARCHAR(150) NULL,
  stock_max_pro INT NULL,
  stock_min_pro INT NULL,
  precio_compra_pro DECIMAL(18,2) NULL,
  precio_venta_pro DECIMAL(18,2) NULL,
  foto_pro IMAGE NULL,
  observacion_pro TEXT NULL,
  PRIMARY KEY(codigo_pro),
  FOREIGN KEY(codigo_prov)
    REFERENCES tbProveedor(codigo_prov)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(codigo_mar)
    REFERENCES tbMarca(codigo_mar)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(codigo_uni)
    REFERENCES tbUnidadMedida(codigo_uni)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(codigo_cat)
    REFERENCES tbCategoria(codigo_cat)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
)

GO

CREATE TABLE tbUsuario (
  codigo_usu VARCHAR(20) NOT NULL,
  codigo_emp VARCHAR(20) NOT NULL,
  login_usu VARCHAR(50) NULL,
  pass_usu VARCHAR(50) NULL,
  administracion_usu VARCHAR(10) NULL,
  ventas_usu VARCHAR(10) NULL,
  consultas_usu VARCHAR(10) NULL,
  reportes_usu VARCHAR(10) NULL,
  PRIMARY KEY(codigo_usu),
  FOREIGN KEY(codigo_emp)
    REFERENCES tbEmpleado(codigo_emp)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
)

GO

CREATE TABLE tbDetalleBoleta (
  codigo_bol VARCHAR(20) NOT NULL,
  codigo_pro VARCHAR(20) NOT NULL,
  precio_bol DECIMAL(18,2) NULL,
  cantidad_bol INT NULL,
  PRIMARY KEY(codigo_bol, codigo_pro),
  FOREIGN KEY(codigo_bol)
    REFERENCES tbBoleta(codigo_bol)
      ON DELETE CASCADE
      ON UPDATE NO ACTION,
  FOREIGN KEY(codigo_pro)
    REFERENCES tbProducto(codigo_pro)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
)


