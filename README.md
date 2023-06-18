# f1it2-Team-10-Backend
Repositorio Backend Team-10 Agenda de Citas

[![Shared-Screenshot.jpg](https://i.postimg.cc/sgjhf4zS/Shared-Screenshot.jpg)](https://postimg.cc/JGFnPJG4)


# **Documentación del Backend - MyApp**

## **Tabla de contenidos**

- [Gestión de citas]
    - [Obtener citas]
    - [Actualizar cita]
    - [Crear cita]
    - [Obtener una cita]
    - [Eliminar cita]

## ** Gestión de citas **

***Obtener citas***

- **Endpoint**: `Endpoint: GET /api/citas`
- **Respuesta exitosa**: `200 "Cita encontrada".`
    -La lista citas con los siguientes campos:
        -`id`: ID de la cita
        -`nombre`: Nombre de la persona en la cita.
        -`dateTime`: Fecha y hora de la cita.
        -`duracion`: Duración de la cita en minutos.
        -`ubicacion`: Ubicación de la cita.
        -`detalles`: Detalles de la cita.
        -`estado`: Estado de la cita.
- **Respuesta de error**: `404 "Cita no encontrada".`
    -Mensaje de error específico.

***Actualizar cita***
- **Endpoint**: `PUT /api/citas`
- ***Parámetros:***
    -`id`: ID de la cita
    -`nombre`: Nombre de la persona en la cita (requerido).
    -`dateTime`: Fecha y hora de la cita (requerido).
    -`duracion`: Duración de la cita en minutos (requerido).
    -`ubicacion`: Ubicación de la cita (requerido).
    -`detalles`: Detalles de la cita (requerido).
    -`estado`: Estado de la cita (requerido).
- **Respuesta exitosa**: ` 201 Cita actualizada`
    -Retorna la cita actualizada.
- **Respuesta de error**: `404 Cita no encontrada`
    -Mensaje de error específico.

***Crear cita***
- **Endpoint**: `POST /api/citas`
- ***Parámetros:***
    -`nombre`: Nombre de la persona en la cita (requerido).
    -`dateTime`: Fecha y hora de la cita (requerido).
    -`duracion`: Duración de la cita en minutos (requerido).
    -`ubicacion`: Ubicación de la cita (requerido).
    -`detalles`: Detalles de la cita (requerido).
- **Respuesta exitosa**: `201 Cita creada.`
    -`id`: ID de la cita actualizada
- **Respuesta de error**: `400 Cita no creada.`
    -Mensaje de error específico.
- **Respuesta de validación**: `409 Cita ya existe.`
    -Mensaje de validación específico.

***Obtener una cita***
- **Endpoint**: `GET /api/citas/{id}`
- **Respuesta exitosa**: `200 "Cita encontrada".`
    -La cita con los siguientes campos:
        -`id`: ID de la cita
        -`nombre`: Nombre de la persona en la cita.
        -`dateTime`: Fecha y hora de la cita.
        -`duracion`: Duración de la cita en minutos.
        -`ubicacion`: Ubicación de la cita.
        -`detalles`: Detalles de la cita.
        -`estado`: Estado de la cita.
- **Respuesta de error**: ` 404 "Cita no encontrada".`
    -Mensaje de error específico.

***Eliminar cita***
- **Endpoint**: `DELETE /api/citas/{id}`
- **Respuesta exitosa**: `201 "Cita cancelada".`
- **Respuesta de error**: ` 404 "Cita no encontrada".`
    -Mensaje de error específico.
