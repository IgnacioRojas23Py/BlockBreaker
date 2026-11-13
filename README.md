# BlockBreaker

Videojuego de romper bloques desarrollado en Java utilizando el framework **libGDX**.

## Requisitos

- Java JDK 8 o Java JDK 11 (probado principalmente con JDK 8).
- Eclipse IDE.
- Gradle (se utiliza el Gradle Wrapper incluido en el proyecto).

## Ejecución

### 1. Obtener el proyecto

El proyecto puede obtenerse de dos maneras:

**Opción A: Archivo ZIP de la entrega**
1. Descargar el archivo ZIP proporcionado para la entrega.
2. Extraer su contenido en una nueva carpeta.

**Opción B: Descargar desde GitHub**
1. Ingresar al repositorio de BlockBreaker.
2. Presionar el botón **Code** y seleccionar **Download ZIP**.
3. Extraer el archivo descargado en una nueva carpeta. La carpeta extraída puede tener el nombre `BlockBreaker-main`.

### 2. Importación en Eclipse

1. Abrir Eclipse IDE.
2. Seleccionar **File → Import**.
3. Buscar la categoría **Gradle** y seleccionar **Existing Gradle Project**.
4. Presionar **Next**.
5. En el campo **Project root directory**, seleccionar **Browse**.
6. Buscar la carpeta donde se extrajo el proyecto.
7. Seleccionar la carpeta raíz del proyecto:
   - Si se descargó el ZIP de la entrega, seleccionar `BlockBreakerEntrega2`.
   - Si se descargó desde GitHub, seleccionar `BlockBreaker-main`.
8. Presionar **Finish** para completar la importación.

### 3. Ejecución del videojuego

1. Una vez importado el proyecto, localizar el módulo `desktop`.
2. Dentro de `desktop/src/com/mygdx/game`, buscar la clase `DesktopLauncher.java`.
3. Hacer clic derecho sobre `DesktopLauncher.java`.
4. Seleccionar **Run As → Java Application**.

El videojuego debería iniciarse en una nueva ventana.

## Solución de problemas

### Error de compatibilidad de Java

Durante la importación del proyecto en Eclipse, puede aparecer el siguiente mensaje:

`Compiling for Java version '1.7' is no longer supported`

Para solucionar este problema:

1. Hacer clic derecho sobre el proyecto `BlockBreaker-core`.
2. Seleccionar **Properties**.
3. Entrar en **Java Compiler**.
4. Cambiar **Compiler compliance level** a `1.8` o `11`, según la versión del JDK instalado.
5. Presionar **Apply and Close**.

Si el problema persiste, comprobar que Eclipse esté utilizando una instalación de JDK compatible con la versión seleccionada.

## Tecnologías utilizadas

- Java JDK.
- libGDX.
- Gradle.
- Eclipse IDE.
