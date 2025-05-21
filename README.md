# Bazar todoCode examen final

## 📌 Descripción del proyecto
Esta es mi resolución al ejercicio final del curso de spring boot de la TodoCode Academy
donde se plantea que: Un bazar ha incrementado considerablemente sus ventas dado este caso
quiere tener una nueva forma donde registrar y manejar su stock para el cual se ha creado
una API Rest mediante spring boot, en la API se gestiona productos, cleintes y ventas, este
sistema permite realizar operaciones CRUD completas y consultas específicas para apoyar
el manejo del inventario y las transacciones comerciales.

## 🎯 Objetivo
Validar los conocimientos prácticos y técnicos en el desarrollo de APIs con java y 
Spring Boot adquiridos en el curso de TodoCode Academy

## 🌟 Características principales
- CRUD completo para Productos, Clientes y Ventas.

## 📋 Modelo de Datos
### Producto
- codigoProducto
- nombre
- marca
- costo
- cantidadDisponible

### Cliente
- idCliente
- nombre
- apellido
- dni

### Venta
- codigoVenta
- fechaVenta
- total
- listaProductos
- cliente

## 🔍 Endpoints Dispoibles
### Producto
- `GET /productos` obtiene todos los productos
- `GET /productos/{id}` obtiene un producto especifico
- `GET /productos/falta-stock` obtiene el producto con menos de 5 en stock
- `POST /productos/crear` crear un producto
- `PUT /productos/editar/{id}` actualizar un producto
- `DELETE /productos/eliminar/{id}` eliminar un producto

### Cliente
- `GET /clientes` obtiene todos los clientes
- `GET /clientes/{id}` obtiene un cliente especifico
- `POST /clientes/crear` crear un cliente
- `PUT /clientes/editar/{id}` actualizar un cliente
- `DELETE /clientes/eliminar/{id}` eliminar un cliente

### Venta
- `GET /ventas` obtiene todos las ventas
- `GET /ventas/{id}` obtiene una venta en especifico
- `GET /ventas/productos/{id}` obtiene las ventas mediante el codigoVenta
- `GET /ventas/venta-del-dia/{fecha}` obtiene la mejor venta del dia
- `GET /ventas/mayor-venta` obtiene la mayor venta realizada
- `POST /ventas/crear` crear una venta
- `PUT /ventas/editar/{id}` actualizar una venta
- `DELETE /ventas/eliminar/{id}` elimina una venta

## ©️ Créditos
Desarrollado por **Mateo Congo**


