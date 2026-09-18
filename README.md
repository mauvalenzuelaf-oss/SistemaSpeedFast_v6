![Duoc UC](https://www.duoc.cl/wp-content/uploads/2022/09/logo-0.png)

# 🧠 Semana 6 - Actividad Formativa 4 - Desarrollo Orientado a Objetos II

## 👤 Autor del proyecto

* **Nombre completo:** Mauricio Francisco Valenzuela Fuentes
* **Carrera:** Analista Programador Computacional
* **Sede:** Online

---

## 📘 Descripción general del sistema

Este proyecto corresponde a la **Actividad Formativa 4** de la asignatura **Desarrollo Orientado a Objetos II**.

Se trata de la sexta etapa de **SistemaSpeedFast**, una aplicación desarrollada en Java para representar la gestión de pedidos de la empresa de reparto a domicilio **Speed Fast**.

En esta versión se incorpora una **interfaz gráfica de escritorio utilizando Java Swing**, permitiendo al usuario registrar pedidos, visualizar los pedidos existentes y asignar repartidores para iniciar las entregas.

Los datos se almacenan temporalmente en memoria mediante una lista compartida de objetos `Pedido`.

El sistema permite:

* Registrar nuevos pedidos.
* Ingresar ID y dirección de entrega.
* Seleccionar el tipo de pedido.
* Validar los datos antes de guardar.
* Evitar IDs duplicados.
* Visualizar los pedidos registrados en una tabla.
* Actualizar la información mostrada.
* Asignar repartidores a los pedidos pendientes.
* Cambiar el estado de los pedidos de `PENDIENTE` a `EN_REPARTO`.

---

## 🧱 Estructura general del proyecto

```text
📁 SistemaSpeedFast_v6/
│
├── 📁 src/
│   ├── 📁 main/
│   │   └── Main.java
│   │
│   ├── 📁 modelo/
│   │   ├── EstadoPedido.java
│   │   └── Pedido.java
│   │
│   └── 📁 vista/
│       ├── VentanaPrincipal.java
│       ├── VentanaRegistroPedido.java
│       └── VentanaListaPedidos.java
│
├── 📄 .gitignore
├── 📄 SistemaSpeedFast_v6.iml
└── 📄 README.md
```

---

## 🧩 Organización por paquetes

El proyecto se encuentra organizado en tres paquetes principales:

### 1. `main`

Contiene la clase encargada de iniciar la aplicación.

#### `Main.java`

Es el punto de entrada del sistema.

Crea una instancia de:

```java
VentanaPrincipal
```

y muestra la interfaz principal al usuario.

---

### 2. `modelo`

Contiene las clases relacionadas con los datos del sistema.

#### `EstadoPedido.java`

Enum que representa los estados utilizados por los pedidos:

```text
PENDIENTE
EN_REPARTO
ENTREGADO
```

Los pedidos se registran inicialmente como:

```text
PENDIENTE
```

Cuando se les asigna un repartidor y comienza la entrega, cambian a:

```text
EN_REPARTO
```

---

#### `Pedido.java`

Representa un pedido registrado en el sistema SpeedFast.

Contiene los atributos:

* `id`
* `direccionEntrega`
* `tipo`
* `estado`
* `repartidor`

Cada pedido comienza con:

```text
Estado: PENDIENTE
Repartidor: Sin asignar
```

La clase también incorpora:

* Constructor.
* Getters.
* Setters.
* Método `toString()`.

---

### 3. `vista`

Contiene las ventanas gráficas de la aplicación.

#### `VentanaPrincipal.java`

Es la ventana principal del sistema.

Contiene tres botones:

```text
Registrar pedido
Listar pedidos
Asignar repartidor / Iniciar entrega
```

También contiene la lista compartida:

```java
ArrayList<Pedido>
```

Esta lista se envía a las demás ventanas para que todas trabajen con los mismos pedidos.

La asignación de repartidores sigue el orden:

```text
Juan → Camila → Pedro → Juan → ...
```

Los pedidos que ya se encuentran en estado `EN_REPARTO` no vuelven a ser asignados.

---

#### `VentanaRegistroPedido.java`

Permite registrar nuevos pedidos mediante un formulario.

El formulario contiene:

```text
ID
Dirección
Tipo
```

El tipo de pedido se selecciona mediante un `JComboBox` con las opciones:

```text
Comida
Encomienda
Express
```

Antes de guardar un pedido se realizan las siguientes validaciones:

* Los campos obligatorios no pueden estar vacíos.
* El ID debe ser numérico.
* El ID debe ser mayor que cero.
* No puede existir otro pedido con el mismo ID.

Si los datos son correctos, el pedido se agrega a la lista y se muestra una confirmación mediante `JOptionPane`.

---

#### `VentanaListaPedidos.java`

Permite visualizar los pedidos almacenados en memoria.

Utiliza:

```java
JTable
```

junto con:

```java
DefaultTableModel
```

La tabla contiene las columnas:

```text
ID
Dirección
Tipo
Estado
Repartidor
```

También contiene el botón:

```text
Actualizar
```

que vuelve a leer la lista compartida y actualiza la información mostrada.

Esto permite visualizar cambios realizados después de abrir la ventana, como nuevos pedidos o asignaciones de repartidores.

---

## 🪟 Navegación entre ventanas

La navegación comienza desde:

```text
VentanaPrincipal
```

El flujo general es:

```text
                    VentanaPrincipal
                           │
             ┌─────────────┼─────────────┐
             │             │             │
             ↓             ↓             ↓
     Registrar pedido  Listar pedidos  Iniciar entrega
             │             │
             ↓             ↓
VentanaRegistroPedido  VentanaListaPedidos
```

Todas las ventanas trabajan con la misma lista de pedidos en memoria.

---

## 📝 Registro y validación de pedidos

Para registrar un pedido, el usuario debe ingresar:

```text
ID
Dirección
Tipo
```

Ejemplo:

```text
ID: 101
Dirección: Providencia
Tipo: Comida
```

Si los datos son válidos, se crea un objeto:

```java
Pedido
```

y se agrega a la lista compartida.

El pedido queda inicialmente como:

```text
Estado: PENDIENTE
Repartidor: Sin asignar
```

---

## 🚚 Asignación de repartidores

Al presionar:

```text
Asignar repartidor / Iniciar entrega
```

el sistema recorre los pedidos que se encuentran en estado:

```text
PENDIENTE
```

y les asigna un repartidor.

La secuencia utilizada es:

```text
Juan
Camila
Pedro
Juan
Camila
Pedro
...
```

Después de asignar el repartidor, el estado cambia de:

```text
PENDIENTE
```

a:

```text
EN_REPARTO
```

Los pedidos que ya se encuentran en reparto no son modificados nuevamente.

---

## 📊 Actualización de la tabla

La ventana de listado permite mantener visible la información actualizada.

Por ejemplo, inicialmente puede mostrarse:

```text
ID    Dirección      Tipo         Estado       Repartidor
101   Providencia    Comida       PENDIENTE    Sin asignar
102   La Florida     Encomienda   PENDIENTE    Sin asignar
103   Ñuñoa          Express      PENDIENTE    Sin asignar
```

Después de iniciar las entregas y presionar `Actualizar`:

```text
ID    Dirección      Tipo         Estado       Repartidor
101   Providencia    Comida       EN_REPARTO   Juan
102   La Florida     Encomienda   EN_REPARTO   Camila
103   Ñuñoa          Express      EN_REPARTO   Pedro
```

---

## 💾 Almacenamiento en memoria

En esta versión no se utiliza una base de datos.

Los pedidos se almacenan temporalmente utilizando:

```java
ArrayList<Pedido>
```

Esto significa que la información se mantiene mientras la aplicación se encuentra en ejecución.

Cuando el programa se cierra, los datos registrados se pierden.

La persistencia mediante base de datos será incorporada posteriormente en la asignatura.

---

## 🎨 Diseño de la interfaz

Para organizar los componentes gráficos se utilizan distribuciones básicas de Java Swing:

```java
BorderLayout
```

y:

```java
GridLayout
```

La interfaz busca mantener:

* Componentes alineados.
* Textos legibles.
* Botones claramente identificados.
* Formularios simples.
* Navegación directa entre las distintas ventanas.

---

## ⚙️ Instrucciones para ejecutar el proyecto

1. Clona el repositorio desde GitHub:

```bash
git clone https://github.com/mauvalenzuelaf-oss/SistemaSpeedFast_v6.git
```

2. Abre **IntelliJ IDEA**.

3. Selecciona la opción:

```text
Open
```

4. Abre la carpeta:

```text
SistemaSpeedFast_v6
```

5. Verifica que dentro de `src` se encuentren los paquetes:

```text
main
modelo
vista
```

6. Abre:

```text
src/main/Main.java
```

7. Ejecuta el método:

```java
main()
```

8. Se abrirá la ventana principal de SpeedFast.

---

## 🖥️ Flujo de funcionamiento

```text
Inicio de aplicación
        ↓
VentanaPrincipal
        ↓
Registrar pedido
        ↓
Validar datos
        ↓
Guardar en ArrayList
        ↓
Listar pedidos
        ↓
Visualizar JTable
        ↓
Asignar repartidor
        ↓
PENDIENTE → EN_REPARTO
        ↓
Actualizar tabla
```

---

**Repositorio GitHub:** https://github.com/mauvalenzuelaf-oss/SistemaSpeedFast_v6

**Fecha de Entrega:** 21/09/2026
