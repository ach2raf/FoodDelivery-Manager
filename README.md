# FoodDelivery Manager 🛵💨
### Proyecto Final - Sistema de Gestión de Reparto

---

## 📝 Descripción
**FoodDelivery Manager** es una aplicación web desarrollada con Spring Boot y Thymeleaf diseñada para coordinar y optimizar la logística de reparto de comida a domicilio. El sistema permite administrar los pedidos pendientes, registrar repartidores en sus zonas correspondientes y planificar rutas de entrega eficientes controlando los tiempos de servicio.

Datos para demo:   
Usuario  -> Username: user Password: user  
Admin  -> Username: admin Password: admin

---

## 🛠️ Características Principales
* **Seguridad por Roles:** Control de accesos restrictivo (ADMIN y OPERADOR) mediante Spring Security.
* **Validación de Datos:** Restricciones en formularios tanto en el navegador (JavaScript) como en el servidor (`@Valid`).
* **Lógica de Negocio Automatizada:** Cálculo automático de costes de envío según la zona del repartidor y control estricto para evitar solapamientos de horarios.
* **Base de Datos en Memoria:** Uso de H2 Database para un arranque rápido en entornos de desarrollo sin necesidad de configuraciones externas.

---

## 📦 Funcionalidades del Sistema (CRUDs)

### 1. Gestión de Pedidos (`PedidoController`)
* **Crear:** Permite dar de alta nuevos pedidos con código único, cliente, contenido y precio (obligatoriamente positivo).
* **Ver:** Pantalla con la ficha exclusiva y desglose de los datos de un pedido.
* **Editar:** Modificación de los datos del pedido antes de ser asignado.
* **Borrar:** Eliminación física del pedido del sistema.

### 2. Gestión de Repartidores (`RepartidorController`)
* **Crear:** Registro de nuevos empleados de reparto. El sistema les asigna automáticamente el rol `OPERADOR` y encripta su contraseña de acceso.
* **Ver:** Ficha detallada de la información del repartidor y su zona asignada.
* **Editar:** Modificación del nombre o traslado del repartidor a una zona operativa diferente (Centro, Norte, Sur, Este, Oeste).
* **Borrar:** Baja del repartidor en el sistema.

### 3. Gestión de Entregas y Logística (`EntregaController`)
* **Crear Ruta:** Inicializa una entrega asignándole una dirección de destino, fecha/hora, estimación de tiempo y un repartidor.
* **Asignar Pedidos:** Pantalla interactiva que muestra solo los pedidos pendientes y permite añadirlos a la ruta con un orden de prioridad.
* **Cálculo de Coste:** Multiplica el precio base del pedido por el factor de la zona del repartidor (Centro x1.0, Norte/Sur x1.2, Este/Oeste x1.5).
* **Marcar como Entregado:** Cambia el estado del pedido en ruta a `ENTREGADO`.
* **Editar y Borrar:** Gestión de los datos base de la ruta o eliminación completa de la misma (borrando sus dependencias en cascada).

---

