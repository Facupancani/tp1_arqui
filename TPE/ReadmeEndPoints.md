
ENDPOINTS CONSIGNAS:

a) Como encargado de mantenimiento, quiero poder generar un reporte de uso de monopatines por
   kilómetros para establecer si un monopatín requiere mantenimiento. Este reporte debe permitir
   configurar si se incluyen (o no) los tiempos de pausa.
    Endpoint: GET /monopatines/reporte-uso

    Parámetros:
     incluirTiempoPausa (booleano, opcional, valor por defecto: false):
        Indica si el reporte debe incluir el tiempo de pausa acumulado de cada monopatín
        en el cálculo de tiempo de uso.

    Postman:
     ej: http://localhost:8003/monopatines/reporte-uso?incluirTiempoPausa=false
    
    Funcionalidad: Este endpoint permite obtener un reporte detallado sobre el uso de cada
    monopatín en función de los kilómetros recorridos y el tiempo total de uso, con la opción
    de incluir o excluir el tiempo en pausa. La información devuelta es útil para determinar qué 
    monopatines podrían necesitar mantenimiento, basándose en su kilometraje y en el tiempo que han 
    estado en uso (y en pausa, si se incluye).



b) Como administrador, quiero poder anular cuentas para inhabilitar el uso momentáneo de la misma.
    Endpoint: PATCH /cuentas/{idCuenta}/desactivar

    Parámetros:
    -idCuenta: ID de la cuenta que se desea desactivar.

    Postman
     ej: http://localhost:8003/cuentas/2/desactivar

    Funcionalidad: Este endpoint permite a los administradores desactivar una cuenta,
       lo cual inhabilita temporalmente el uso de la misma. Llama al método
       cuentaService.desactivarCuenta(idCuenta), que actualiza el estado de la cuenta en la base de
       datos para reflejar que está desactivada.


c)Como administrador quiero consultar los monopatines con más de X viajes en un cierto año.
    Endpoint: GET /viajes/monopatines/mas-viajes

    Parámetros:
    -anio: Año en el que se quieren contar los viajes.
    -numViajes: Número mínimo de viajes que un monopatín debe tener para ser incluido en el resultado.

    Postman
    ej: http://localhost:8003/viajes/monopatines/mas-viajes?anio=2023&numViajes=3

    Funcionalidad: Este método llama a viajeService.obtenerMonopatinesConViajes(anio, numViajes),
       que debería realizar la consulta en la base de datos para obtener los monopatines que cumplen con }
       los criterios de más de numViajes en el año anio.


d) Como administrador, quiero consultar el total facturado en un rango de meses de cierto año.
    Endpoint: GET /viajes/facturado-entre

    Parámetros:
    -anio (int): Año en el que se quiere consultar el total facturado.
    -mesInicio (int): Mes inicial del rango (1 para enero, 12 para diciembre).
    -mesFin (int): Mes final del rango (1 para enero, 12 para diciembre).

    Postman
    ej: http://localhost:8003/viajes/facturado-entre?anio=2023&mesInicio=5&mesFin=10

    Funcionalidad: Este endpoint permite a los administradores consultar el total facturado
       entre un rango específico de meses dentro de un año determinado. El método llama a
       viajeService.getViajesEntre(anio, mesInicio, mesFin), que calcula la suma de los costos de
       los viajes realizados en el rango de fechas indicado.


e) Como administrador, quiero consultar la cantidad de monopatines actualmente en operación,
   versus la cantidad de monopatines actualmente en mantenimiento.
    Endpoint: GET /monopatines/estado

    Postman
    ej: http://localhost:8003/monopatines/estado

    Funcionalidad: Este endpoint permite a los administradores consultar el estado actual de los
       monopatines en el sistema, devolviendo la cantidad de monopatines que se encuentran en operación
       y la cantidad en mantenimiento.


f) Como administrador, quiero hacer un ajuste de precios, y que a partir de cierta fecha el
   sistema habilite los nuevos precios.
    Endpoint: POST /tarifa

    Parámetros:
    -tarifaPorMinuto (double): Nueva tarifa por minuto a aplicar.
    -tarifaExtra (double): Nueva tarifa extra a aplicar.
    -fechaInicioVigencia (LocalDate): Fecha a partir de la cual las nuevas tarifas serán aplicadas. Esta fecha debe ser proporcionada en formato yyyy-MM-dd.

    Postman
     ej: http://localhost:8003/tarifa
        body: {
                "tarifaPorMinuto": 1.5,
                "tarifaExtra": 2.0,
                "fechaInicioVigencia": "2024-11-20"
               }


    Funcionalidad:Este endpoint permite a los administradores actualizar las tarifas del sistema.
        A partir de la fecha de inicio de vigencia proporcionada, la nueva tarifa será habilitada.
         Si ya existe una tarifa activa (sin fecha de fin de vigencia), esta será desactivada y se le
         asignará una fecha de fin. La nueva tarifa será registrada como activa a partir de la fecha
         de vigencia especificada.
        El método llama a tarifaService.actualizarTarifa(tarifa, fechaInicioVigencia) para gestionar
         el cambio en la tarifa y la actualización de la base de datos.

g) Como usuario, quiero un listado de los monopatines cercanos a mi zona, para poder encontrar un
   monopatín cerca de mi ubicación.
    Endpoint: GET /monopatines/cercanos?latitud= (Latitud) & longitud= (long) & radio= (radio)

    Parámetros:
    -latitud (double): Latitud de la ubicación del usuario (en grados decimales).
    -longitud (double): Longitud de la ubicación del usuario (en grados decimales).
    -radio (double, opcional): Radio en grados en el cual buscar los monopatines cercanos. El valor por defecto es 0.01, lo que equivale aproximadamente a 1 km.

    Postman
    ej:http://localhost:8003/monopatines/cercanos?latitud=40&longitud=-74&radio=0.01

    Funcionalidad:
        Este endpoint permite a los usuarios consultar los monopatines disponibles cerca de
         su ubicación. La búsqueda se realiza en un radio especificado alrededor de las coordenadas
         de latitud y longitud proporcionadas. Si no se especifica un radio, el valor por defecto
         será de aproximadamente 1 km.
        El método llama a monopatinService.obtenerMonopatinesCercanos(latitud, longitud, radio),
         que realiza la consulta de monopatines cercanos en la base de datos según las coordenadas
         del usuario.