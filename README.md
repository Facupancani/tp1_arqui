# TPE ARQUITECTURAS WEB 2024

## Integrantes
- Dante Tomas Tourn
- Santiago Bugnón
- Facundo Pancani

## MonopatinService

| Metodo | Endpoint | Descripcion |
|-|-|-|
|**PUT**| /monopatines/{id_monopatin}/mantenimiento | Pone un monopatin en mantenimiento
|**PUT**| /monopatines/{id_monopatin}/finalizar-mantenimiento | Termina el mantenimiento de un monopatin
|**PUT**| /monopatines/{id_monopatin}/parada/{id_parada} | Ubica un monopatin en una parada
|**POST**| /monopatines | [Agrega un monopatin](#agregar-un-monopatin)
|**DELETE**| /monopatines/{id_monopatin} | Elimina un monopatin
|**GET**| /monopatines | [Lista los monopatines](#listar-monopatines)

| Metodo | Endpoint | Descripcion |
|-|-|-|
|**POST**| /paradas |  [Agrega una parada](#agregar-una-parada)
|**DELETE**| /paradas/{id_parada} | Elimina una parada

#### [Ejemplos]
##### Agregar un monopatin
```json
{
  "modelo": "Samsung X3200",
  "kilometraje": 7250,
  "tiempoDeUso": 12375,
  "tiempoEnPausa": 525,
  "estado": "en_uso",
  "enMantenimiento": false,
  "ubicacion": {
    "latitud": -39.32345,
    "longitud": -58.42345
  }
}
```
##### Agregar una parada
```json
{
  "nombre":"Parque Central 2",
  "ubicacion":{
    "latitud": -39.42358,
    "longitud": -58.63456
  }
}
```

##### Listar monopatines
```json
[
  {
    "idMonopatin": 1,
    "modelo": "Xiami M365",
    "kilometraje": 5600.0,
    "tiempoDeUso": 8670,
    "tiempoEnPausa": 215,
    "estado": "disponible",
    "enMantenimiento": false,
    "ubicacion": {
      "idUbicacion": 3,
      "latitud": -39.24589,
      "longitud": -58.52349
    },
    "paradaActual": {
      "idParada": 1,
      "nombre": "Plaza Manuel Belgrano",
      "ubicacion": {
        "idUbicacion": 3,
        "latitud": -39.24589,
        "longitud": -58.52349
      }
    }
  },
  {
    "idMonopatin": 2,
    "modelo": "Samsung X3200",
    "kilometraje": 7250.0,
    "tiempoDeUso": 12375,
    "tiempoEnPausa": 525,
    "estado": "en_uso",
    "enMantenimiento": false,
    "ubicacion": {
      "idUbicacion": 4,
      "latitud": -39.42358,
      "longitud": -58.63456
    },
    "paradaActual": {
      "idParada": 2,
      "nombre": "Parque Central",
      "ubicacion": {
        "idUbicacion": 4,
        "latitud": -39.42358,
        "longitud": -58.63456
      }
    }
  }
]
```

## ReporteService

| Metodo | Endpoint | Descripcion |
|-|-|-|
|**GET**| /reportes/kilometros |  Obtiene reporte de kilometraje de los monopatines
|**GET**| /reportes/tiempo-con-pausas |  Obtiene reporte de tiempo de uso CON pausas de los monopatines
|**GET**| /reportes/tiempo-sin-pausas |  Obtiene reporte de tiempo de uso SIN pausas de los monopatines