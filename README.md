# Kabtest

## Prerequisitos

- Gradle 6.8.1
- Java 1.8

## Construir ejecutable

```
git clone https://github.com/xjeojx/kabtest
```
```
cd kabtest/kabeliaccount/
```
```
gradle build
```
```
gradle bootjar
```

Una vez realizado esto el ejecutable se encontrara en 'kabtest/kabeliaccount/build/libs' con el nombre 'Kabeli Test-0.0.1.jar'

## Ejecutar paquete

```
cd build/libs
```
```
java -jar Kabeli\ Test-0.0.1.jar 
```

Esto ejecutara el programa abriendo el puerto 9000 para su interaccion

## Pruebas con POSTMAN

Abrir postman, y seleccionar la opcion "Importar", elegir el archivo de nombre 'Kabeli.postman_collection.json' en el repositorio.

Esto creara la coleccion 'Kabeli' en el programa.

