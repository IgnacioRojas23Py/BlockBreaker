# BlockBreaker

Videojuego de romper bloques desarrollado en Java utilizando el framework **libGDX**.

## Requisitos

- Java JDK 8 o Java JDK 11 (probado principalmente con JDK 8).
- Eclipse IDE.
- Gradle (se utiliza el Gradle Wrapper incluido en el proyecto).

## Ejecución

### 1. Preparación del proyecto

1. Descargar el archivo ZIP del proyecto desde el repositorio de GitHub.
2. Extraer el contenido del archivo ZIP en una nueva carpeta.
3. Abrir Eclipse IDE.

### 2. Importación en Eclipse

1. En Eclipse, seleccionar **File → Import**.
2. Buscar la categoría **Gradle** y seleccionar **Existing Gradle Project**.
3. Presionar **Next**.
4. En el campo **Project root directory**, seleccionar **Browse**.
5. Buscar la carpeta donde se extrajo el proyecto.
6. Seleccionar la carpeta raíz **BlockBreakerEntrega2**.
7. Finalizar la importación presionando **Finish**.

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

Para solucionar este problema, se debe ajustar el nivel de compatibilidad del compilador:

1. En Eclipse, hacer clic derecho sobre el proyecto `BlockBreaker-core`.
2. Seleccionar **Properties**.
3. Entrar en la sección **Java Compiler**.
4. Cambiar **Compiler compliance level** a `1.8` o `11`, según la versión del JDK instalado.
5. Presionar **Apply and Close**.

Si el problema persiste, comprobar que Eclipse esté utilizando una instalación de JDK compatible con la versión seleccionada.

## Tecnologías utilizadas

- Java JDK.
- libGDX.
- Gradle.
- Eclipse IDE.
