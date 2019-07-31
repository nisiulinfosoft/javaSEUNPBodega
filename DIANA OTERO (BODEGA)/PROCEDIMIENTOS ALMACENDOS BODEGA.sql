USE bdBodega

GO

---PROCEDIMIENTOS ALMACENADOS PARA CARGO

CREATE PROCEDURE pa_cargo_registrar
@codigoCar varchar(20),
@nombre varchar(150),
@descripcion text
AS
BEGIN

	IF @nombre IS NULL
		BEGIN
			PRINT 'Nombre de cargo es nulo'
			RETURN
		END
	
	IF (NOT EXISTS (SELECT codigo_car FROM tbCargo WHERE codigo_car=@codigoCar))
		BEGIN
			IF (NOT EXISTS (SELECT nombre_car FROM tbCargo WHERE RTRIM(nombre_car)=RTRIM(@nombre)))
				BEGIN
					INSERT INTO tbCargo(codigo_car,nombre_car,descripcion_car)
					VALUES(@codigoCar,@nombre,@descripcion)
				END
			ELSE
				BEGIN
					PRINT 'Nombre de cargo ya existe'
					RETURN
				END
		END
	ELSE
		BEGIN
			PRINT 'Código de cargo ya existe'
			RETURN
		END
END

GO

CREATE PROCEDURE pa_cargo_editar
@codigoCar varchar(20),
@nombre varchar(150),
@descripcion text
AS
BEGIN

	IF @nombre IS NULL
		BEGIN
			PRINT 'Nombre de cargo es nulo'
			RETURN
		END
	
	IF (EXISTS (SELECT codigo_car FROM tbCargo WHERE codigo_car=@codigoCar))
		BEGIN
			
			UPDATE tbCargo SET nombre_car=@nombre, descripcion_car=@descripcion
			WHERE codigo_car=@codigoCar
				
		END
	ELSE
		BEGIN
			PRINT 'Código de cargo no existe'
			RETURN
		END
END

GO

CREATE PROCEDURE pa_cargo_eliminar
@codigoCar varchar(20)
AS
BEGIN
	IF (EXISTS (SELECT codigo_car FROM tbCargo WHERE codigo_car=@codigoCar))
		BEGIN
			DELETE FROM tbCargo WHERE codigo_car=@codigoCar
		END
	ELSE
		BEGIN
			PRINT 'Código de cargo no existe'
			RETURN
		END
END

GO

CREATE PROCEDURE pa_cargo_listar
AS
BEGIN
	SELECT * FROM tbCargo
	ORDER BY nombre_car ASC
END

GO

CREATE PROCEDURE pa_cargo_listar_por_codigo
@codigoCar varchar(20)
AS
BEGIN
	SELECT * FROM tbCargo 
	WHERE codigo_car LIKE '%'+@codigoCar+'%'
	ORDER BY codigo_car ASC
END

GO

CREATE PROCEDURE pa_cargo_listar_por_nombre
@nombre varchar(150)
AS
BEGIN
	SELECT * FROM tbCargo 
	WHERE nombre_car LIKE '%'+@nombre+'%'
	ORDER BY nombre_car ASC
END

GO

CREATE PROCEDURE pa_cargo_obtener_por_codigo
@codigoCar varchar(20)
AS
BEGIN
	IF (EXISTS (SELECT codigo_car FROM tbCargo WHERE codigo_car=@codigoCar))
		BEGIN
			SELECT * FROM tbCargo 
			WHERE codigo_car=@codigoCar
		END
	ELSE
		BEGIN
			PRINT 'Código de cargo no existe'
			RETURN
		END
END

GO

CREATE PROCEDURE pa_cargo_generar_codigo
AS
BEGIN
	DECLARE @codigo AS CHAR(10)
	DECLARE @valor AS INT
	SET @codigo=(SELECT MAX(codigo_car) FROM tbCargo)
	
	IF @codigo IS NULL
		BEGIN
			SET @valor=1
		END
	ELSE
		BEGIN
			SET @valor=CONVERT(INT,SUBSTRING(@codigo,7,10))+1
		END
		
	IF @valor<10000000
		BEGIN
			SET @codigo='CAR'+(CASE
								WHEN @valor<10 THEN '000000'+CONVERT(CHAR(1),@valor) ---9
								WHEN @valor<100 THEN '00000'+CONVERT(CHAR(2),@valor) ---99
								WHEN @valor<1000 THEN '0000'+CONVERT(CHAR(3),@valor) ---999
								WHEN @valor<10000 THEN '000'+CONVERT(CHAR(4),@valor) ---9999
								WHEN @valor<100000 THEN '00'+CONVERT(CHAR(5),@valor) ---99999
								WHEN @valor<1000000 THEN '0'+CONVERT(CHAR(6),@valor) ---999999
								ELSE CONVERT(CHAR(7),@valor)						 ---9999999
								END)
			SELECT @codigo AS codigo
		END
	ELSE
		BEGIN
			PRINT 'Código de cargo desbordo'
			RETURN
		END
END

EXECUTE pa_cargo_generar_codigo

GO

---PROCEDIMIENTOS ALMACENADOS PARA CATEGORIA


CREATE PROCEDURE pa_categoria_registrar
@codigoCat varchar(20),
@nombre varchar(150),
@descripcion text
AS
BEGIN

	IF @nombre IS NULL
		BEGIN
			PRINT 'Nombre de categoria es nulo'
			RETURN
		END
	
	IF (NOT EXISTS (SELECT codigo_cat FROM tbCategoria WHERE codigo_cat=@codigoCat))
		BEGIN
			IF (NOT EXISTS (SELECT nombre_cat  FROM tbCategoria WHERE RTRIM(nombre_cat)=RTRIM(@nombre)))
				BEGIN
					INSERT INTO tbCategoria(codigo_cat,nombre_cat,descripcion_cat)
					VALUES(@codigoCat,@nombre,@descripcion)
				END
			ELSE
				BEGIN
					PRINT 'Nombre de categoria ya existe'
					RETURN
				END
		END
	ELSE
		BEGIN
			PRINT 'Código de categoria ya existe'
			RETURN
		END
END

GO

CREATE PROCEDURE pa_categoria_editar
@codigoCat varchar(20),
@nombre varchar(150),
@descripcion text
AS
BEGIN

	IF @nombre IS NULL
		BEGIN
			PRINT 'Nombre de categoria es nulo'
			RETURN
		END
	
	IF (EXISTS (SELECT codigo_cat FROM tbCategoria WHERE codigo_cat=@codigoCat))
		BEGIN
			
			UPDATE tbCategoria SET nombre_cat=@nombre, descripcion_cat=@descripcion
			WHERE codigo_cat=@codigoCat
				
		END
	ELSE
		BEGIN
			PRINT 'Código de categoria no existe'
			RETURN
		END
END

GO

CREATE PROCEDURE pa_categoria_eliminar
@codigoCat varchar(20)
AS
BEGIN
	IF (EXISTS (SELECT codigo_cat FROM tbCategoria WHERE codigo_cat=@codigoCat))
		BEGIN
			DELETE FROM tbCategoria WHERE codigo_cat=@codigoCat
		END
	ELSE
		BEGIN
			PRINT 'Código de categoria no existe'
			RETURN
		END
END

GO

CREATE PROCEDURE pa_categoria_listar
AS
BEGIN
	SELECT * FROM tbCategoria
	ORDER BY nombre_cat ASC
END

GO

CREATE PROCEDURE pa_categoria_listar_por_codigo
@codigoCat varchar(20)
AS
BEGIN
	SELECT * FROM  tbCategoria
	WHERE codigo_cat LIKE '%'+@codigoCat+'%'
	ORDER BY codigo_cat ASC
END

GO

CREATE PROCEDURE pa_categoria_listar_por_nombre
@nombre varchar(150)
AS
BEGIN
	SELECT * FROM tbCategoria
	WHERE nombre_cat LIKE '%'+@nombre+'%'
	ORDER BY nombre_cat ASC
END

GO

CREATE PROCEDURE pa_categoria_obtener_por_codigo
@codigoCat varchar(20)
AS
BEGIN
	IF (EXISTS (SELECT codigo_cat FROM tbCategoria WHERE codigo_cat=@codigoCat))
		BEGIN
			SELECT * FROM tbCategoria 
			WHERE codigo_cat=@codigoCat
		END
	ELSE
		BEGIN
			PRINT 'Código de categoria no existe'
			RETURN
		END
END

GO

CREATE PROCEDURE pa_categoria_generar_codigo
AS
BEGIN
	DECLARE @codigo AS CHAR(10)
	DECLARE @valor AS INT
	SET @codigo=(SELECT MAX(codigo_cat) FROM tbCategoria)
	
	IF @codigo IS NULL
		BEGIN
			SET @valor=1
		END
	ELSE
		BEGIN
			SET @valor=CONVERT(INT,SUBSTRING(@codigo,7,10))+1
		END
		
	IF @valor<10000000
		BEGIN
			SET @codigo='CAT'+(CASE
								WHEN @valor<10 THEN '000000'+CONVERT(CHAR(1),@valor) ---9
								WHEN @valor<100 THEN '00000'+CONVERT(CHAR(2),@valor) ---99
								WHEN @valor<1000 THEN '0000'+CONVERT(CHAR(3),@valor) ---999
								WHEN @valor<10000 THEN '000'+CONVERT(CHAR(4),@valor) ---9999
								WHEN @valor<100000 THEN '00'+CONVERT(CHAR(5),@valor) ---99999
								WHEN @valor<1000000 THEN '0'+CONVERT(CHAR(6),@valor) ---999999
								ELSE CONVERT(CHAR(7),@valor)						 ---9999999
								END)
			SELECT @codigo AS codigo
		END
	ELSE
		BEGIN
			PRINT 'Código de categoria desbordo'
			RETURN
		END
END

EXECUTE pa_categoria_generar_codigo

GO


---PROCEDIMIENTOS ALMACENADOS PARA MARCA


CREATE PROCEDURE pa_marca_registrar
@codigoMar varchar(20),
@nombre varchar(150),
@descripcion text
AS
BEGIN

	IF @nombre IS NULL
		BEGIN
			PRINT 'Nombre de marca es nulo'
			RETURN
		END
	
	IF (NOT EXISTS (SELECT codigo_mar FROM tbMarca WHERE codigo_mar=@codigoMar))
		BEGIN
			IF (NOT EXISTS (SELECT nombre_mar FROM tbMarca WHERE RTRIM(nombre_mar)=RTRIM(@nombre)))
				BEGIN
					INSERT INTO tbMarca(codigo_mar,nombre_mar,descripcion_mar)
					VALUES(@codigoMar,@nombre,@descripcion)
				END
			ELSE
				BEGIN
					PRINT 'Nombre de marca ya existe'
					RETURN
				END
		END
	ELSE
		BEGIN
			PRINT 'Código de marca ya existe'
			RETURN
		END
END

GO

CREATE PROCEDURE pa_marca_editar
@codigoMar varchar(20),
@nombre varchar(150),
@descripcion text
AS
BEGIN

	IF @nombre IS NULL
		BEGIN
			PRINT 'Nombre de marca es nulo'
			RETURN
		END
	
	IF (EXISTS (SELECT codigo_mar FROM tbMarca WHERE codigo_mar=@codigoMar))
		BEGIN
			
			UPDATE tbMarca SET nombre_mar=@nombre, descripcion_mar=@descripcion
			WHERE codigo_mar=@codigoMar
				
		END
	ELSE
		BEGIN
			PRINT 'Código de marca no existe'
			RETURN
		END
END

GO

CREATE PROCEDURE pa_marca_eliminar
@codigoMar varchar(20)
AS
BEGIN
	IF (EXISTS (SELECT codigo_mar FROM tbMarca WHERE codigo_mar=@codigoMar))
		BEGIN
			DELETE FROM tbMarca WHERE codigo_mar=@codigoMar
		END
	ELSE
		BEGIN
			PRINT 'Código de marca no existe'
			RETURN
		END
END

GO

CREATE PROCEDURE pa_marca_listar
AS
BEGIN
	SELECT * FROM tbMarca
	ORDER BY nombre_mar ASC
END

GO

CREATE PROCEDURE pa_marca_listar_por_codigo
@codigoMar varchar(20)
AS
BEGIN
	SELECT * FROM tbMarca
	WHERE codigo_mar LIKE '%'+@codigoMar+'%'
	ORDER BY codigo_mar ASC
END

GO

CREATE PROCEDURE pa_marca_listar_por_nombre
@nombre varchar(150)
AS
BEGIN
	SELECT * FROM tbMarca 
	WHERE nombre_mar LIKE '%'+@nombre+'%'
	ORDER BY nombre_mar ASC
END

GO

CREATE PROCEDURE pa_marca_obtener_por_codigo
@codigoMar varchar(20)
AS
BEGIN
	IF (EXISTS (SELECT codigo_mar FROM tbMarca WHERE codigo_mar=@codigoMar))
		BEGIN
			SELECT * FROM tbMarca 
			WHERE codigo_mar=@codigoMar
		END
	ELSE
		BEGIN
			PRINT 'Código de marca no existe'
			RETURN
		END
END

GO

CREATE PROCEDURE pa_marca_generar_codigo
AS
BEGIN
	DECLARE @codigo AS CHAR(10)
	DECLARE @valor AS INT
	SET @codigo=(SELECT MAX(codigo_mar) FROM tbMarca)
	
	IF @codigo IS NULL
		BEGIN
			SET @valor=1
		END
	ELSE
		BEGIN
			SET @valor=CONVERT(INT,SUBSTRING(@codigo,7,10))+1
		END
		
	IF @valor<10000000
		BEGIN
			SET @codigo='MAR'+(CASE
								WHEN @valor<10 THEN '000000'+CONVERT(CHAR(1),@valor) ---9
								WHEN @valor<100 THEN '00000'+CONVERT(CHAR(2),@valor) ---99
								WHEN @valor<1000 THEN '0000'+CONVERT(CHAR(3),@valor) ---999
								WHEN @valor<10000 THEN '000'+CONVERT(CHAR(4),@valor) ---9999
								WHEN @valor<100000 THEN '00'+CONVERT(CHAR(5),@valor) ---99999
								WHEN @valor<1000000 THEN '0'+CONVERT(CHAR(6),@valor) ---999999
								ELSE CONVERT(CHAR(7),@valor)						 ---9999999
								END)
			SELECT @codigo AS codigo
		END
	ELSE
		BEGIN
			PRINT 'Código de marca desbordo'
			RETURN
		END
END

EXECUTE pa_marca_generar_codigo

GO


---PROCEDIMIENTOS ALMACENADOS PARA UNIDAD MEDIDA


CREATE PROCEDURE pa_unidadMedida_registrar
@codigoUniMed varchar(20),
@nombre varchar(150),
@descripcion text
AS
BEGIN

	IF @nombre IS NULL
		BEGIN
			PRINT 'Nombre de unidad medida es nulo'
			RETURN
		END
	
	IF (NOT EXISTS (SELECT codigo_uni FROM tbUnidadMedida WHERE codigo_uni=@codigoUniMed))
		BEGIN
			IF (NOT EXISTS (SELECT nombre_uni FROM tbUnidadMedida WHERE RTRIM(nombre_uni)=RTRIM(@nombre)))
				BEGIN
					INSERT INTO tbUnidadMedida(codigo_uni,nombre_uni,descripcion_uni)
					VALUES(@codigoUniMed,@nombre,@descripcion)
				END
			ELSE
				BEGIN
					PRINT 'Nombre de unidad medida ya existe'
					RETURN
				END
		END
	ELSE
		BEGIN
			PRINT 'Código de unidad medida ya existe'
			RETURN
		END
END

GO

CREATE PROCEDURE pa_unidadMedida_editar
@codigoUniMed varchar(20),
@nombre varchar(150),
@descripcion text
AS
BEGIN

	IF @nombre IS NULL
		BEGIN
			PRINT 'Nombre de unidad medida es nulo'
			RETURN
		END
	
	IF (EXISTS (SELECT codigo_uni FROM tbUnidadMedida WHERE codigo_uni=@codigoUniMed))
		BEGIN
			
			UPDATE tbUnidadMedida SET nombre_uni=@nombre, descripcion_uni=@descripcion
			WHERE codigo_uni=@codigoUniMed
				
		END
	ELSE
		BEGIN
			PRINT 'Código de unidad medida no existe'
			RETURN
		END
END

GO

CREATE PROCEDURE pa_unidadMedida_eliminar
@codigoUniMed varchar(20)
AS
BEGIN
	IF (EXISTS (SELECT codigo_uni FROM tbUnidadMedida WHERE codigo_uni=@codigoUniMed))
		BEGIN
			DELETE FROM tbUnidadMedida WHERE codigo_uni=@codigoUniMed
		END
	ELSE
		BEGIN
			PRINT 'Código de unidad medida no existe'
			RETURN
		END
END

GO

CREATE PROCEDURE pa_unidadMedida_listar
AS
BEGIN
	SELECT * FROM tbUnidadMedida
	ORDER BY nombre_uni ASC
END

GO

CREATE PROCEDURE pa_unidadMedida_listar_por_codigo
@codigoUniMed varchar(20)
AS
BEGIN
	SELECT * FROM tbUnidadMedida
	WHERE codigo_uni LIKE '%'+@codigoUniMed+'%'
	ORDER BY codigo_uni ASC
END

GO

CREATE PROCEDURE pa_unidadMedida_listar_por_nombre
@nombre varchar(150)
AS
BEGIN
	SELECT * FROM tbUnidadMedida
	WHERE nombre_uni LIKE '%'+@nombre+'%'
	ORDER BY nombre_uni ASC
END

GO

CREATE PROCEDURE pa_unidadMedida_obtener_por_codigo
@codigoUniMed varchar(20)
AS
BEGIN
	IF (EXISTS (SELECT codigo_uni FROM tbUnidadMedida WHERE codigo_uni=@codigoUniMed))
		BEGIN
			SELECT * FROM tbUnidadMedida 
			WHERE codigo_uni=@codigoUniMed
		END
	ELSE
		BEGIN
			PRINT 'Código de unidad medida no existe'
			RETURN
		END
END

GO

CREATE PROCEDURE pa_unidadMedida_generar_codigo
AS
BEGIN
	DECLARE @codigo AS CHAR(10)
	DECLARE @valor AS INT
	SET @codigo=(SELECT MAX(codigo_uni) FROM tbUnidadMedida)
	
	IF @codigo IS NULL
		BEGIN
			SET @valor=1
		END
	ELSE
		BEGIN
			SET @valor=CONVERT(INT,SUBSTRING(@codigo,7,10))+1
		END
		
	IF @valor<10000000
		BEGIN
			SET @codigo='UNI'+(CASE
								WHEN @valor<10 THEN '000000'+CONVERT(CHAR(1),@valor) ---9
								WHEN @valor<100 THEN '00000'+CONVERT(CHAR(2),@valor) ---99
								WHEN @valor<1000 THEN '0000'+CONVERT(CHAR(3),@valor) ---999
								WHEN @valor<10000 THEN '000'+CONVERT(CHAR(4),@valor) ---9999
								WHEN @valor<100000 THEN '00'+CONVERT(CHAR(5),@valor) ---99999
								WHEN @valor<1000000 THEN '0'+CONVERT(CHAR(6),@valor) ---999999
								ELSE CONVERT(CHAR(7),@valor)						 ---9999999
								END)
			SELECT @codigo AS codigo
		END
	ELSE
		BEGIN
			PRINT 'Código de unidad medida desbordo'
			RETURN
		END
END

EXECUTE pa_unidadMedida_generar_codigo

GO


---PROCEDIMIENTOS ALMACENADOS PARA CLIENTE


CREATE PROCEDURE pa_cliente_registrar
@codigoCli varchar(20),
@ruc varchar(15),
@razonSocial varchar(150),
@direccion varchar(150)
AS
BEGIN

	IF @razonSocial IS NULL
		BEGIN
			PRINT 'Razon social de cliente es nulo'
			RETURN
		END
	
	IF (NOT EXISTS (SELECT codigo_cli FROM tbCliente WHERE codigo_cli=@codigoCli))
		BEGIN
			IF (NOT EXISTS (SELECT ruc_dni_cli FROM tbCliente WHERE ruc_dni_cli=@ruc))
				BEGIN
					IF (NOT EXISTS (SELECT razon_social_cli FROM tbCliente WHERE RTRIM(razon_social_cli)=RTRIM(@razonSocial)))
						BEGIN
							INSERT INTO tbCliente(codigo_cli,ruc_dni_cli,razon_social_cli,direccion_cli)
							VALUES(@codigoCli,@ruc,@razonSocial,@direccion)
						END
					ELSE
						BEGIN
							PRINT 'Razon social de cliente ya existe'
							RETURN
						END
				END
			ELSE
				BEGIN
					PRINT 'RUC de cliente ya existe'
					RETURN
				END
		END
	ELSE
		BEGIN
			PRINT 'Código de cliente ya existe'
			RETURN
		END
END

GO

CREATE PROCEDURE pa_cliente_editar
@codigoCli varchar(20),
@ruc varchar(15),
@razonSocial varchar(150),
@direccion varchar(150)
AS
BEGIN

	IF @razonSocial IS NULL
		BEGIN
			PRINT 'Razon social de cliente es nulo'
			RETURN
		END
	
	IF (EXISTS (SELECT codigo_cli FROM tbCliente WHERE codigo_cli=@codigoCli))
		BEGIN
			
			UPDATE tbCliente SET razon_social_cli=@razonSocial, direccion_cli=@direccion
			WHERE codigo_cli=@codigoCli
				
		END
	ELSE
		BEGIN
			PRINT 'Código de cliente no existe'
			RETURN
		END
END

GO

CREATE PROCEDURE pa_cliente_eliminar
@codigoCli varchar(20)
AS
BEGIN
	IF (EXISTS (SELECT codigo_cli FROM tbCliente WHERE codigo_cli=@codigoCli))
		BEGIN
			DELETE FROM tbCliente WHERE codigo_cli=@codigoCli
		END
	ELSE
		BEGIN
			PRINT 'Código de cliente no existe'
			RETURN
		END
END

GO

CREATE PROCEDURE pa_cliente_listar
AS
BEGIN
	SELECT * FROM tbCliente
	ORDER BY razon_social_cli ASC
END

GO

CREATE PROCEDURE pa_cliente_listar_por_codigo
@codigoCli varchar(20)
AS
BEGIN
	SELECT * FROM tbCliente
	WHERE codigo_cli  LIKE '%'+@codigoCli+'%'
	ORDER BY codigo_cli ASC
END

GO

CREATE PROCEDURE pa_cliente_listar_por_nombre
@razonSocial varchar(150)
AS
BEGIN
	SELECT * FROM tbCliente 
	WHERE razon_social_cli LIKE '%'+@razonSocial+'%'
	ORDER BY razon_social_cli ASC
END

GO

CREATE PROCEDURE pa_cliente_obtener_por_codigo
@codigoCli varchar(20)
AS
BEGIN
	IF (EXISTS (SELECT codigo_cli FROM tbCliente WHERE codigo_cli=@codigoCli))
		BEGIN
			SELECT * FROM tbCliente
			WHERE codigo_cli=@codigoCli
		END
	ELSE
		BEGIN
			PRINT 'Código de cliente no existe'
			RETURN
		END
END

GO

CREATE PROCEDURE pa_cliente_generar_codigo
AS
BEGIN
	DECLARE @codigo AS CHAR(10)
	DECLARE @valor AS INT
	SET @codigo=(SELECT MAX(codigo_cli) FROM tbCliente)
	
	IF @codigo IS NULL
		BEGIN
			SET @valor=1
		END
	ELSE
		BEGIN
			SET @valor=CONVERT(INT,SUBSTRING(@codigo,7,10))+1
		END
		
	IF @valor<10000000
		BEGIN
			SET @codigo='CLI'+(CASE
								WHEN @valor<10 THEN '000000'+CONVERT(CHAR(1),@valor) ---9
								WHEN @valor<100 THEN '00000'+CONVERT(CHAR(2),@valor) ---99
								WHEN @valor<1000 THEN '0000'+CONVERT(CHAR(3),@valor) ---999
								WHEN @valor<10000 THEN '000'+CONVERT(CHAR(4),@valor) ---9999
								WHEN @valor<100000 THEN '00'+CONVERT(CHAR(5),@valor) ---99999
								WHEN @valor<1000000 THEN '0'+CONVERT(CHAR(6),@valor) ---999999
								ELSE CONVERT(CHAR(7),@valor)						 ---9999999
								END)
			SELECT @codigo AS codigo
		END
	ELSE
		BEGIN
			PRINT 'Código de cliente desbordo'
			RETURN
		END
END

EXECUTE pa_cliente_generar_codigo

GO

---PROCEDIMIENTOS ALMACENADOS PARA PROVEEDOR


CREATE PROCEDURE pa_proveedor_registrar
@codigoProv varchar(20),
@ruc varchar(15),
@razonSocial varchar(150),
@direccion varchar(150),
@telefono varchar(15),
@email varchar(100),
@observacion text
AS
BEGIN

	IF @razonSocial IS NULL
		BEGIN
			PRINT 'Razon social de proveedor es nulo'
			RETURN
		END
	
	IF (NOT EXISTS (SELECT codigo_prov FROM tbProveedor WHERE codigo_prov=@codigoProv))
		BEGIN
			IF (NOT EXISTS (SELECT ruc_prov FROM tbProveedor WHERE ruc_prov=@ruc))
				BEGIN
					IF (NOT EXISTS (SELECT razon_social_prov FROM tbProveedor WHERE RTRIM(razon_social_prov)=RTRIM(@razonSocial)))
						BEGIN
							INSERT INTO tbProveedor(codigo_prov,ruc_prov,razon_social_prov,direccion_prov,telefono_prov,email_prov,observacion_prov)
							VALUES(@codigoProv,@ruc,@razonSocial,@direccion,@telefono,@email,@observacion)
						END
					ELSE
						BEGIN
							PRINT 'Razon social de proveedor ya existe'
							RETURN
						END
				END
			ELSE
				BEGIN
					PRINT 'RUC de proveedor ya existe'
					RETURN
				END
		END
	ELSE
		BEGIN
			PRINT 'Código de proveedor ya existe'
			RETURN
		END
END

GO

CREATE PROCEDURE pa_proveedor_editar
@codigoProv varchar(20),
@ruc varchar(15),
@razonSocial varchar(150),
@direccion varchar(150),
@telefono varchar(15),
@email varchar(100),
@observacion text
AS
BEGIN

	IF @razonSocial IS NULL
		BEGIN
			PRINT 'Razon social de proveedor es nulo'
			RETURN
		END
	
	IF (EXISTS (SELECT codigo_prov FROM tbProveedor WHERE codigo_prov=@codigoProv))
		BEGIN
			
			UPDATE tbProveedor SET razon_social_prov=@razonSocial, direccion_prov=@direccion, telefono_prov=@telefono, email_prov=@email, observacion_prov=@observacion
			WHERE codigo_prov=@codigoProv
				
		END
	ELSE
		BEGIN
			PRINT 'Código de proveedor no existe'
			RETURN
		END
END

GO

CREATE PROCEDURE pa_proveedor_eliminar
@codigoProv varchar(20)
AS
BEGIN
	IF (EXISTS (SELECT codigo_prov FROM tbProveedor WHERE codigo_prov=@codigoProv))
		BEGIN
			DELETE FROM tbProveedor WHERE codigo_prov=@codigoProv
		END
	ELSE
		BEGIN
			PRINT 'Código de proveedor no existe'
			RETURN
		END
END

GO

CREATE PROCEDURE pa_proveedor_listar
AS
BEGIN
	SELECT * FROM tbProveedor
	ORDER BY razon_social_prov ASC
END

GO

CREATE PROCEDURE pa_proveedor_listar_por_codigo
@codigoProv varchar(20)
AS
BEGIN
	SELECT * FROM tbProveedor
	WHERE codigo_prov LIKE '%'+@codigoProv+'%'
	ORDER BY codigo_prov ASC
END

GO

CREATE PROCEDURE pa_proveedor_listar_por_nombre
@razonSocial varchar(150)
AS
BEGIN
	SELECT * FROM tbProveedor 
	WHERE razon_social_prov LIKE '%'+@razonSocial+'%'
	ORDER BY razon_social_prov ASC
END

GO

CREATE PROCEDURE pa_proveedor_obtener_por_codigo
@codigoProv varchar(20)
AS
BEGIN
	IF (EXISTS (SELECT codigo_prov FROM tbProveedor WHERE codigo_prov=@codigoProv))
		BEGIN
			SELECT * FROM tbProveedor 
			WHERE codigo_prov=@codigoProv
		END
	ELSE
		BEGIN
			PRINT 'Código de proveedor no existe'
			RETURN
		END
END

GO

CREATE PROCEDURE pa_proveedor_generar_codigo
AS
BEGIN
	DECLARE @codigo AS CHAR(11)
	DECLARE @valor AS INT
	SET @codigo=(SELECT MAX(codigo_prov) FROM tbProveedor)
	
	IF @codigo IS NULL
		BEGIN
			SET @valor=1
		END
	ELSE
		BEGIN
			SET @valor=CONVERT(INT,SUBSTRING(@codigo,7,11))+1
		END
		
	IF @valor<10000000
		BEGIN
			SET @codigo='PROV'+(CASE
								WHEN @valor<10 THEN '000000'+CONVERT(CHAR(1),@valor) ---9
								WHEN @valor<100 THEN '00000'+CONVERT(CHAR(2),@valor) ---99
								WHEN @valor<1000 THEN '0000'+CONVERT(CHAR(3),@valor) ---999
								WHEN @valor<10000 THEN '000'+CONVERT(CHAR(4),@valor) ---9999
								WHEN @valor<100000 THEN '00'+CONVERT(CHAR(5),@valor) ---99999
								WHEN @valor<1000000 THEN '0'+CONVERT(CHAR(6),@valor) ---999999
								ELSE CONVERT(CHAR(7),@valor)						 ---9999999
								END)
			SELECT @codigo AS codigo
		END
	ELSE
		BEGIN
			PRINT 'Código de proveedor desbordo'
			RETURN
		END
END

EXECUTE pa_proveedor_generar_codigo

GO

---PROCEDIMIENTOS ALMACENADOS PARA PRODUCTO



CREATE PROCEDURE pa_producto_registrar
@codigoPro varchar(20),
@codigoProv varchar(20),
@codigoCat varchar(20),
@codigoMar varchar(20),
@codigoUni varchar(20),
@nombre varchar(150),
@stockMax int,
@stockMin int,
@precioCom decimal(18,2),
@precioVen decimal(18,2),
@foto image,
@observacion text
AS
BEGIN

	IF @nombre IS NULL
		BEGIN
			PRINT 'Nombre de producto es nulo'
			RETURN
		END
	
	IF (NOT EXISTS (SELECT codigo_pro FROM tbProducto WHERE codigo_pro=@codigoPro))
		BEGIN
			IF (NOT EXISTS (SELECT nombre_pro FROM tbProducto WHERE RTRIM(nombre_pro)=RTRIM(@nombre)))
				BEGIN
					INSERT INTO tbProducto(codigo_pro,codigo_prov,codigo_cat,codigo_mar,codigo_uni,nombre_pro,stock_max_pro,stock_min_pro,precio_compra_pro,precio_venta_pro,foto_pro,observacion_pro)
					VALUES(@codigoPro,@codigoProv,@codigoCat,@codigoMar,@codigoUni,@nombre,@stockMax,@stockMin,@precioCom,@precioVen,@foto,@observacion)
				END
			ELSE
				BEGIN
					PRINT 'Nombre de producto ya existe'
					RETURN
				END
		END
	ELSE
		BEGIN
			PRINT 'Código de producto ya existe'
			RETURN
		END
END

GO

CREATE PROCEDURE pa_producto_editar
@codigoPro varchar(20),
@codigoProv varchar(20),
@codigoCat varchar(20),
@codigoMar varchar(20),
@codigoUni varchar(20),
@nombre varchar(150),
@stockMax int,
@stockMin int,
@precioCom decimal(18,2),
@precioVen decimal(18,2),
@foto image,
@observacion text
AS
BEGIN

	IF @nombre IS NULL
		BEGIN
			PRINT 'Nombre de producto es nulo'
			RETURN
		END
	
	IF (EXISTS (SELECT codigo_pro FROM tbProducto WHERE codigo_pro=@codigoPro))
		BEGIN
			
			UPDATE tbProducto SET codigo_prov=@codigoProv,codigo_cat=@codigoCat,codigo_mar=@codigoMar,codigo_uni=@codigoUni,nombre_pro=@nombre,stock_max_pro=@stockMax,stock_min_pro=@stockMin,precio_compra_pro=@precioCom,precio_venta_pro=@precioVen,foto_pro=@foto,observacion_pro=@observacion
			WHERE codigo_pro=@codigoPro
				
		END
	ELSE
		BEGIN
			PRINT 'Código de producto no existe'
			RETURN
		END
END

GO

CREATE PROCEDURE pa_producto_eliminar
@codigoPro varchar(20)
AS
BEGIN
	IF (EXISTS (SELECT codigo_pro FROM tbProducto WHERE codigo_pro=@codigoPro))
		BEGIN
			DELETE FROM tbProducto WHERE codigo_pro=@codigoPro
		END
	ELSE
		BEGIN
			PRINT 'Código de producto no existe'
			RETURN
		END
END

GO

CREATE PROCEDURE pa_producto_actulizar_stock
@codigoPro varchar(20),
@cantidad int
AS
BEGIN
	
	IF (EXISTS (SELECT codigo_pro FROM tbProducto WHERE codigo_pro=@codigoPro))
		BEGIN
			
			UPDATE tbProducto SET stock_max_pro=stock_max_pro-@cantidad
			WHERE codigo_pro=@codigoPro
				
		END
	ELSE
		BEGIN
			PRINT 'Código de producto no existe'
			RETURN
		END
END

GO

CREATE PROCEDURE pa_producto_listar
AS
BEGIN
	SELECT * FROM tbProducto
	ORDER BY nombre_pro ASC
END

GO

CREATE PROCEDURE pa_producto_listar_por_codigo
@codigoPro varchar(20)
AS
BEGIN
	SELECT * FROM tbProducto 
	WHERE codigo_pro LIKE '%'+@codigoPro+'%'
	ORDER BY codigo_pro ASC
END

GO

CREATE PROCEDURE pa_producto_listar_por_nombre
@nombre varchar(150)
AS
BEGIN
	SELECT * FROM tbProducto 
	WHERE nombre_pro LIKE '%'+@nombre+'%'
	ORDER BY nombre_pro ASC
END

GO

CREATE PROCEDURE pa_producto_obtener_por_codigo
@codigoPro varchar(20)
AS
BEGIN
	IF (EXISTS (SELECT codigo_pro FROM tbProducto WHERE codigo_pro=@codigoPro))
		BEGIN
			SELECT * FROM tbProducto 
			WHERE codigo_pro=@codigoPro
		END
	ELSE
		BEGIN
			PRINT 'Código de producto no existe'
			RETURN
		END
END

GO

CREATE PROCEDURE pa_producto_generar_codigo
AS
BEGIN
	DECLARE @codigo AS CHAR(10)
	DECLARE @valor AS INT
	SET @codigo=(SELECT MAX(codigo_pro) FROM tbProducto)
	
	IF @codigo IS NULL
		BEGIN
			SET @valor=1
		END
	ELSE
		BEGIN
			SET @valor=CONVERT(INT,SUBSTRING(@codigo,7,10))+1
		END
		
	IF @valor<10000000
		BEGIN
			SET @codigo='PRO'+(CASE
								WHEN @valor<10 THEN '000000'+CONVERT(CHAR(1),@valor) ---9
								WHEN @valor<100 THEN '00000'+CONVERT(CHAR(2),@valor) ---99
								WHEN @valor<1000 THEN '0000'+CONVERT(CHAR(3),@valor) ---999
								WHEN @valor<10000 THEN '000'+CONVERT(CHAR(4),@valor) ---9999
								WHEN @valor<100000 THEN '00'+CONVERT(CHAR(5),@valor) ---99999
								WHEN @valor<1000000 THEN '0'+CONVERT(CHAR(6),@valor) ---999999
								ELSE CONVERT(CHAR(7),@valor)						 ---9999999
								END)
			SELECT @codigo AS codigo
		END
	ELSE
		BEGIN
			PRINT 'Código de producto desbordo'
			RETURN
		END

END               

EXECUTE pa_producto_generar_codigo

GO

---PROCEDIMIENTOS ALMACENADOS PARA EMPLEADO


CREATE PROCEDURE pa_empleado_registrar
@codigoEmp varchar(20),
@codigoCar varchar(20),
@dni varchar(15),
@nombre varchar(50),
@apellidos varchar(50),
@sexo varchar(20),
@fechaNacimiento date,
@direccion varchar(150),
@telefono varchar(15),
@celular varchar(15),
@email varchar(100),
@foto image,
@observacion text
AS
BEGIN
	IF (NOT EXISTS (SELECT codigo_emp FROM tbEmpleado WHERE codigo_emp=@codigoEmp))
		BEGIN
			IF (NOT EXISTS (SELECT dni_emp FROM tbEmpleado WHERE dni_emp=@dni))
				BEGIN
					IF (NOT EXISTS (SELECT nombre_emp FROM tbEmpleado WHERE RTRIM(nombre_emp)+' '+RTRIM(apellidos_emp)=RTRIM(@nombre)+' '+RTRIM(@apellidos)))
						BEGIN
							INSERT INTO tbEmpleado(codigo_emp,codigo_car,dni_emp,nombre_emp,apellidos_emp,sexo_emp,fecha_nacimiento_emp,direccion_emp,telefono_emp,celular_emp,email_emp,foto_emp,observacion_emp)
							VALUES(@codigoEmp,@codigoCar,@dni,@nombre,@apellidos,@sexo,@fechaNacimiento,@direccion,@telefono,@celular,@email,@foto,@observacion)
						END
					ELSE
						BEGIN
							PRINT 'Nombre y apellido de empleado ya existe'
							RETURN
						END
				END
			ELSE
				BEGIN
					PRINT 'DNI de empleado ya existe'
					RETURN
				END
		END
	ELSE
		BEGIN
			PRINT 'Código de empleado ya existe'
			RETURN
		END
END

GO

CREATE PROCEDURE pa_empleado_editar
@codigoEmp varchar(20),
@codigoCar varchar(20),
@dni varchar(15),
@nombre varchar(50),
@apellidos varchar(50),
@sexo varchar(20),
@fechaNacimiento date,
@direccion varchar(150),
@telefono varchar(15),
@celular varchar(15),
@email varchar(100),
@foto image,
@observacion text
AS
BEGIN
	IF (EXISTS (SELECT codigo_emp FROM tbEmpleado WHERE codigo_emp=@codigoEmp))
		BEGIN
			
			UPDATE tbEmpleado SET codigo_car=@codigoCar,nombre_emp=@nombre,apellidos_emp=@apellidos,sexo_emp=@sexo,fecha_nacimiento_emp=@fechaNacimiento,direccion_emp=@direccion,telefono_emp=@telefono,celular_emp=@celular,email_emp=@email,foto_emp=@foto,observacion_emp=@observacion
			WHERE codigo_emp=@codigoEmp
				
		END
	ELSE
		BEGIN
			PRINT 'Código de empleado no existe'
			RETURN
		END
END

GO

CREATE PROCEDURE pa_empleado_eliminar
@codigoEmp varchar(20)
AS
BEGIN
	IF (EXISTS (SELECT codigo_emp FROM tbEmpleado WHERE codigo_emp=@codigoEmp))
		BEGIN
			DELETE FROM tbEmpleado WHERE codigo_emp=@codigoEmp
		END
	ELSE
		BEGIN
			PRINT 'Código de empleado no existe'
			RETURN
		END
END

GO

CREATE PROCEDURE pa_empleado_listar
AS
BEGIN
	SELECT * FROM tbEmpleado
	ORDER BY apellidos_emp+' '+nombre_emp ASC
END

GO

CREATE PROCEDURE pa_empleado_listar_por_codigo
@codigoEmp varchar(20)
AS
BEGIN
	SELECT * FROM tbEmpleado 
	WHERE codigo_emp LIKE '%'+@codigoEmp+'%'
	ORDER BY codigo_emp ASC
END

GO

CREATE PROCEDURE pa_empleado_listar_por_nombre
@nombre varchar(100)
AS
BEGIN
	SELECT * FROM tbEmpleado 
	WHERE apellidos_emp+' '+nombre_emp LIKE '%'+@nombre+'%'
	ORDER BY apellidos_emp+' '+nombre_emp ASC
END

GO

CREATE PROCEDURE pa_empleado_obtener_por_codigo
@codigoEmp varchar(20)
AS
BEGIN
	IF (EXISTS (SELECT codigo_emp FROM tbEmpleado WHERE codigo_emp=@codigoEmp))
		BEGIN
			SELECT * FROM tbEmpleado 
			WHERE codigo_emp=@codigoEmp
		END
	ELSE
		BEGIN
			PRINT 'Código de empleado no existe'
			RETURN
		END
END

GO

CREATE PROCEDURE pa_empleado_generar_codigo
AS
BEGIN
	DECLARE @codigo AS CHAR(10)
	DECLARE @valor AS INT
	SET @codigo=(SELECT MAX(codigo_emp) FROM tbEmpleado)
	
	IF @codigo IS NULL
		BEGIN
			SET @valor=1
		END
	ELSE
		BEGIN
			SET @valor=CONVERT(INT,SUBSTRING(@codigo,7,10))+1
		END
		
	IF @valor<10000000
		BEGIN
			SET @codigo='EMP'+(CASE
								WHEN @valor<10 THEN '000000'+CONVERT(CHAR(1),@valor) ---9
								WHEN @valor<100 THEN '00000'+CONVERT(CHAR(2),@valor) ---99
								WHEN @valor<1000 THEN '0000'+CONVERT(CHAR(3),@valor) ---999
								WHEN @valor<10000 THEN '000'+CONVERT(CHAR(4),@valor) ---9999
								WHEN @valor<100000 THEN '00'+CONVERT(CHAR(5),@valor) ---99999
								WHEN @valor<1000000 THEN '0'+CONVERT(CHAR(6),@valor) ---999999
								ELSE CONVERT(CHAR(7),@valor)						 ---9999999
								END)
			SELECT @codigo AS codigo
		END
	ELSE
		BEGIN
			PRINT 'Código de empleado desbordo'
			RETURN
		END
END

EXECUTE pa_empleado_generar_codigo

GO

---PROCEDIMIENTOS ALMACENADOS PARA USUARIO


CREATE PROCEDURE pa_usuario_registrar
@codigoUsu varchar(20),
@codigoEmp varchar(20),
@login varchar(50),
@pass varchar(50),
@administracion varchar(10),
@ventas varchar(10),
@consultas varchar(10),
@reportes varchar(10)
AS
BEGIN

	IF @login IS NULL
		BEGIN
			PRINT 'Login de usuario es nulo'
			RETURN
		END
	
	IF (NOT EXISTS (SELECT codigo_usu FROM tbUsuario WHERE codigo_usu=@codigoUsu))
		BEGIN
			IF (NOT EXISTS (SELECT codigo_emp FROM tbUsuario WHERE codigo_emp=@codigoEmp))
				BEGIN
					IF (NOT EXISTS (SELECT login_usu FROM tbUsuario WHERE RTRIM(login_usu)=RTRIM(@login)))
						BEGIN
							INSERT INTO tbUsuario(codigo_usu,codigo_emp,login_usu,pass_usu,administracion_usu,ventas_usu,consultas_usu,reportes_usu)
							VALUES(@codigoUsu,@codigoEmp,@login,@pass,@administracion,@ventas,@consultas,@reportes)
						END
					ELSE
						BEGIN
							PRINT 'Login de usuario ya existe'
							RETURN
						END
				END
			ELSE
				BEGIN
					PRINT 'Empleado ya existe'
					RETURN
				END
		END
	ELSE
		BEGIN
			PRINT 'Código de usuario ya existe'
			RETURN
		END
END

GO

CREATE PROCEDURE pa_usuario_editar
@codigoUsu varchar(20),
@codigoEmp varchar(20),
@login varchar(50),
@pass varchar(50),
@administracion varchar(10),
@ventas varchar(10),
@consultas varchar(10),
@reportes varchar(10)
AS
BEGIN

	IF @login IS NULL
		BEGIN
			PRINT 'Login de usuario es nulo'
			RETURN
		END
	
	IF (EXISTS (SELECT codigo_usu FROM tbUsuario WHERE codigo_usu=@codigoUsu))
		BEGIN
			IF (EXISTS (SELECT login_usu FROM tbUsuario WHERE login_usu=@login))
				BEGIN
					UPDATE tbUsuario SET pass_usu=@pass,administracion_usu=@administracion,ventas_usu=@ventas,consultas_usu=@consultas,reportes_usu=@reportes
					WHERE codigo_usu=@codigoUsu
				END
			ELSE
				BEGIN
					UPDATE tbUsuario SET login_usu=@login,pass_usu=@pass,administracion_usu=@administracion,ventas_usu=@ventas,consultas_usu=@consultas,reportes_usu=@reportes
					WHERE codigo_usu=@codigoUsu
				END
		END
	ELSE
		BEGIN
			PRINT 'Código de usuario no existe'
			RETURN
		END
END

GO

CREATE PROCEDURE pa_usuario_eliminar
@codigoUsu varchar(20)
AS
BEGIN
	IF (EXISTS (SELECT codigo_usu FROM tbUsuario WHERE codigo_usu=@codigoUsu))
		BEGIN
			DELETE FROM tbUsuario WHERE codigo_usu=@codigoUsu
		END
	ELSE
		BEGIN
			PRINT 'Código de usuario no existe'
			RETURN
		END
END

GO

CREATE PROCEDURE pa_usuario_inicio_sesion
@login varchar(50),
@pass varchar(50)
AS
BEGIN
	SELECT * FROM tbUsuario 
	WHERE login_usu=@login AND pass_usu=@pass
END

GO

CREATE PROCEDURE pa_usuario_listar
AS
BEGIN
	SELECT U.* FROM tbUsuario AS U
	INNER JOIN tbEmpleado AS E ON U.codigo_emp=E.codigo_emp
	ORDER BY E.apellidos_emp+' '+E.nombre_emp ASC
END

GO

CREATE PROCEDURE pa_usuario_listar_por_codigo
@codigoUsu varchar(20)
AS
BEGIN
	SELECT * FROM tbUsuario 
	WHERE codigo_usu LIKE '%'+@codigoUsu+'%'
	ORDER BY codigo_usu ASC
END

GO

CREATE PROCEDURE pa_usuario_listar_por_nombre
@nombre varchar(100)
AS
BEGIN
	SELECT U.* FROM tbUsuario AS U
	INNER JOIN tbEmpleado AS E ON U.codigo_emp=E.codigo_emp
	WHERE E.apellidos_emp+' '+E.nombre_emp LIKE '%'+@nombre+'%'
	ORDER BY E.apellidos_emp+' '+E.nombre_emp ASC
END

GO

CREATE PROCEDURE pa_usuario_obtener_por_codigo
@codigoUsu varchar(20)
AS
BEGIN
	IF (EXISTS (SELECT codigo_usu FROM tbUsuario WHERE codigo_usu=@codigoUsu))
		BEGIN
			SELECT * FROM tbUsuario 
			WHERE codigo_usu=@codigoUsu
		END
	ELSE
		BEGIN
			PRINT 'Código de usuario no existe'
			RETURN
		END
END

GO

CREATE PROCEDURE pa_usuario_generar_codigo
AS
BEGIN
	DECLARE @codigo AS CHAR(10)
	DECLARE @valor AS INT
	SET @codigo=(SELECT MAX(codigo_usu) FROM tbUsuario)
	
	IF @codigo IS NULL
		BEGIN
			SET @valor=1
		END
	ELSE
		BEGIN
			SET @valor=CONVERT(INT,SUBSTRING(@codigo,7,10))+1
		END
		
	IF @valor<10000000
		BEGIN
			SET @codigo='USU'+(CASE
								WHEN @valor<10 THEN '000000'+CONVERT(CHAR(1),@valor) ---9
								WHEN @valor<100 THEN '00000'+CONVERT(CHAR(2),@valor) ---99
								WHEN @valor<1000 THEN '0000'+CONVERT(CHAR(3),@valor) ---999
								WHEN @valor<10000 THEN '000'+CONVERT(CHAR(4),@valor) ---9999
								WHEN @valor<100000 THEN '00'+CONVERT(CHAR(5),@valor) ---99999
								WHEN @valor<1000000 THEN '0'+CONVERT(CHAR(6),@valor) ---999999
								ELSE CONVERT(CHAR(7),@valor)						 ---9999999
								END)
			SELECT @codigo AS codigo
		END
	ELSE
		BEGIN
			PRINT 'Código de usurio desbordo'
			RETURN
		END
END

EXECUTE pa_usuario_generar_codigo

GO

---PROCEDIMIENTOS ALMACENADOS PARA BOLETA


CREATE PROCEDURE pa_boleta_registrar
@codigoBol varchar(20),
@codigoEmp varchar(20),
@codigoCli varchar(20),
@fecha date
AS
BEGIN
	IF (NOT EXISTS (SELECT codigo_bol FROM tbBoleta WHERE codigo_bol=@codigoBol))
		BEGIN
			
			INSERT INTO tbBoleta(codigo_bol,codigo_emp,codigo_cli,fecha_bol)
			VALUES(@codigoBol,@codigoEmp,@codigoCli,@fecha)
				
		END
	ELSE
		BEGIN
			PRINT 'Código de boleta ya existe'
			RETURN
		END
END

GO

CREATE PROCEDURE pa_boleta_listar_por_codigo
@codigoBol varchar(20)
AS
BEGIN
	SELECT * FROM  tbBoleta
	WHERE codigo_bol LIKE '%'+@codigoBol+'%'
	ORDER BY codigo_bol ASC
END

GO

CREATE PROCEDURE pa_boleta_obtener_por_codigo
@codigoBol varchar(20)
AS
BEGIN
	IF (EXISTS (SELECT codigo_bol FROM tbBoleta WHERE codigo_bol=@codigoBol))
		BEGIN
			SELECT * FROM tbBoleta 
			WHERE codigo_bol=@codigoBol
		END
	ELSE
		BEGIN
			PRINT 'Código de boleta no existe'
			RETURN
		END
END

GO

CREATE PROCEDURE pa_boleta_generar_codigo
AS
BEGIN
	DECLARE @codigo AS CHAR(10)
	DECLARE @valor AS INT
	SET @codigo=(SELECT MAX(codigo_bol) FROM tbBoleta)
	
	IF @codigo IS NULL
		BEGIN
			SET @valor=1
		END
	ELSE
		BEGIN
			SET @valor=CONVERT(INT,SUBSTRING(@codigo,7,10))+1
		END
		
	IF @valor<10000000
		BEGIN
			SET @codigo='BOL'+(CASE
								WHEN @valor<10 THEN '000000'+CONVERT(CHAR(1),@valor) ---9
								WHEN @valor<100 THEN '00000'+CONVERT(CHAR(2),@valor) ---99
								WHEN @valor<1000 THEN '0000'+CONVERT(CHAR(3),@valor) ---999
								WHEN @valor<10000 THEN '000'+CONVERT(CHAR(4),@valor) ---9999
								WHEN @valor<100000 THEN '00'+CONVERT(CHAR(5),@valor) ---99999
								WHEN @valor<1000000 THEN '0'+CONVERT(CHAR(6),@valor) ---999999
								ELSE CONVERT(CHAR(7),@valor)						 ---9999999
								END)
			SELECT @codigo AS codigo
		END
	ELSE
		BEGIN
			PRINT 'Código de boleta desbordo'
			RETURN
		END
END

EXECUTE pa_boleta_generar_codigo

GO

---PROCEDIMIENTOS ALMACENADOS PARA DETALLE BOLETA


CREATE PROCEDURE pa_detalleBoleta_registrar
@codigoBol varchar(20),
@codigoPro varchar(20),
@precio decimal(18,2),
@cantidad int
AS
BEGIN
	
	INSERT INTO tbDetalleBoleta(codigo_bol,codigo_pro,precio_bol,cantidad_bol)
	VALUES(@codigoBol,@codigoPro,@precio,@cantidad)
				
END

GO

CREATE PROCEDURE pa_detalleBoleta_obtener_por_codigo
@codigoBol varchar(20)
AS
BEGIN
	IF (EXISTS (SELECT codigo_bol FROM tbDetalleBoleta WHERE codigo_bol=@codigoBol))
		BEGIN
			SELECT DB.codigo_bol, P.nombre_pro, DB.precio_bol, DB.cantidad_bol FROM tbDetalleBoleta AS DB
			INNER JOIN tbProducto AS P ON DB.codigo_pro=P.codigo_pro 
			WHERE codigo_bol=@codigoBol
		END
	ELSE
		BEGIN
			PRINT 'Código de boleta no existe'
			RETURN
		END
END

GO