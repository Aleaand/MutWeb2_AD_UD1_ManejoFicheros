# Sistema de Gestión de Jugadores

###### _Este proyecto es parte de una práctica educativa que tiene como objetivo aplicar los conocimientos adquiridos en el manejo de archivos en Java._

Este programa es un sistema de gestión de jugadores desarrollado en Java, utilizando el patrón de diseño MVC (Modelo-Vista-Controlador). El sistema permite realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre los datos de los jugadores, utilizando diferentes métodos de almacenamiento.

## Descripción del Proyecto

El sistema permite a los usuarios gestionar información de jugadores mediante un menú interactivo que ofrece opciones para:

1. Alta de jugadores
2. Baja de jugadores
3. Modificación de jugadores
4. Listado por ID
5. Listado general de jugadores
6. Salir

Los datos de los jugadores se gestionan utilizando diferentes tipos de almacenamiento:

1. Fichero secuencial de texto
2. Fichero secuencial binario
3. Fichero de objetos binario
4. Fichero de acceso aleatorio binario
5. Fichero de texto XML

### Tipos de Almacenamiento

- **Fichero secuencial de texto**: Almacena los datos en un formato legible para humanos, fácil de editar.
- **Fichero secuencial binario**: Ofrece un método eficiente para almacenar datos, pero no es legible por humanos.
- **Fichero de objetos binario**: Permite serializar y deserializar objetos Java de manera eficiente.
- **Fichero de acceso aleatorio binario**: Permite acceder a cualquier parte del archivo sin necesidad de leerlo secuencialmente.
- **Fichero de texto XML**: Proporciona una estructura jerárquica que es útil para la interoperabilidad y la validación de datos.

## Requisitos

Para ejecutar este proyecto, necesitarás:

- **Java JDK** (versión 8 o superior)
- **Maven** (para la gestión de dependencias)

## Ejecución

Para ejecutar el proyecto, sigue estos pasos:

1. **Clona el repositorio**:

   - Abre tu terminal o consola de comandos.
   - Clona el repositorio con el siguiente comando:
     ```bash
     git clone https://github.com/Aleaand/MutWeb2_AD_UD1_ManejoFicheros.git
     ```
   - Navega al directorio del proyecto:
     ```bash
     cd MutWeb2_AD_UD1_ManejoFicheros
     ```

2. **Construye el proyecto con Maven**:

   - Asegúrate de tener Maven instalado. Si no lo tienes, puedes descargarlo desde [aquí](https://maven.apache.org/download.cgi).
   - En la terminal, ejecuta el siguiente comando para compilar el proyecto:
     ```bash
     mvn clean install
     ```

3. **Ejecuta la aplicación**:

   - Navega al paquete `mvc.run` dentro de tu IDE (como IntelliJ, Eclipse, NetBeans, etc.).
   - Busca la clase `run`.
   - Ejecuta la clase `run.java` como una aplicación Java.

4. **Interactúa con el menú**:
   - Una vez que la aplicación esté en ejecución, seguirás el menú interactivo que aparecerá en la consola para gestionar los jugadores.

## Estructura del Proyecto

La estructura del proyecto sigue el patrón MVC, con las siguientes carpetas:

- `mvc.run`: Contiene la clase principal para iniciar la aplicación.
- `mvc.controlador`: Contiene la lógica de control para interactuar entre la vista y el modelo.
- `mvc.modelo`: Contiene las clases relacionadas con los datos, incluyendo el DAO y la clase Jugador.
- `mvc.vista`: Contiene las clases responsables de la interfaz de usuario y la presentación de datos.

### Clases Principales

- **Run**: Clase principal que inicia la aplicación.
- **Controlador**: Controla el flujo de la aplicación y las interacciones entre la vista y el modelo.
- **GestorAlmacenamiento**: Clase encargada de gestionar el almacenamiento de objetos Jugador.
- **IDAO<T>**: Interfaz que define las operaciones CRUD genéricas para la gestión de datos.
- **Jugador**: Esta clase representa un jugador con atributos como ID, nombre, experiencia, nivel de vida y monedas.
- **JugadorDAO**: Clase que implementa la interfaz IDAO para realizar operaciones CRUD sobre la entidad Jugador.

## Flujo de Trabajo

Al iniciar el programa, se presentará un menú para seleccionar el tipo de almacenamiento. Se controla mediante los numeros **1: Texto, 2: Binario, 3: Objetos binarios, 4: Acceso aleatorio, 5: XML**

```
==============================
    CONFIGURACIÓN DE
         ALMACENAMIENTO
==============================
1. Fichero secuencial de texto
2. Fichero secuencial binario
3. Fichero de objetos binario
4. Fichero de acceso aleatorio binario
5. Fichero de texto XML
Seleccione el tipo de almacenamiento:
```

Una vez seleccionado el tipo de almacenamiento, y de recibir la confirmación de **FLUJO INICIADO** el menú principal se mostrará automáticamente de la siguiente manera:

```
========================
     MENÚ PRINCIPAL
========================
1. Alta de jugadores
2. Baja de jugadores
3. Modificación de jugadores
4. Listado por ID
5. Listado general
6. Salir
Elija una opción:
```

Al igual que el menú anterior, este tambien se controla mediante números.

## Ejemplo de Uso

1. Al seleccionar **"Alta de jugadores"**, el programa solicitará la información del jugador:

```
========================
     ALTA DE JUGADOR
========================
Ingrese el nombre del jugador: Pablo
Ingrese experiencia del jugador: 26
Ingrese nivel de vida del jugador: 100
Ingrese monedas del jugador: 111

```

2. Al seleccionar **"Baja de jugadores"**, el programa solicitará el ID del jugador:

```
========================
      BAJA DE JUGADOR
========================
Ingrese ID del jugador: 0
```

3. Al seleccionar **"Modificación de jugadores"**, el programa nos preguntará el ID del jugador para despues mediantes de s/n cambiaremos lo que desamos:

```
========================
   MODIFICACIÓN DE JUGADOR
========================
Ingrese ID del jugador: 1
Los datos a modificar son los del jugador: Jacinto
Desea modificar el nombre? (s/n): s
Ingrese el nuevo nombre: Nuevo Jacinto
Desea modificar el experiencia? (s/n): n
Desea modificar el nivel de vida? (s/n): n
Desea modificar el monedas? (s/n): s
Ingrese la nueva cantidad de monedas: 1000
Datos actualizados exitosamente
```

4. Al seleccionar **"Listado por ID"**, el programa solicitará el ID del jugador:

```
========================
   CONSULTA POR ID
========================
Ingrese ID del jugador: 1
[USER_ID = 1, NICK_NAME = Nuevo Jacinto, EXPERIENCE = 545, LIFE_LEVEL = 5, COINS = 1000]
```

5. Al seleccionar **"Listado general"**, el programa nos mostrará todos los jugadores:

```
========================
   LISTADO DE JUGADORES
========================
[USER_ID = 1, NICK_NAME = Nuevo Jacinto, EXPERIENCE = 545, LIFE_LEVEL = 5, COINS = 1000]
[USER_ID = 2, NICK_NAME = Ana, EXPERIENCE = 4, LIFE_LEVEL = 56, COINS = 5]
[USER_ID = 3, NICK_NAME = Pepita, EXPERIENCE = 45, LIFE_LEVEL = 14, COINS = 1]
[USER_ID = 4, NICK_NAME = Noa, EXPERIENCE = 45, LIFE_LEVEL = 3, COINS = 45]
[USER_ID = 5, NICK_NAME = Luara, EXPERIENCE = 6, LIFE_LEVEL = 4, COINS = 6]

```

6. Al seleccionar **"Salir"**, el programa se finalizará y se guardarán todos los datos en el documento elejido.

```
========================
     MENÚ PRINCIPAL
========================
1. Alta de jugadores
2. Baja de jugadores
3. Modificación de jugadores
4. Listado por ID
5. Listado general
6. Salir
Elija una opción: 6
Saliendo del programa. ¡Hasta luego!
Jugadores guardados en texto correctamente.
```

