# f1it2-Team-10-Backend
Repositorio Backend Team-10 Agenda de Citas

![image](https://github.com/Trycatch-tv/f1it2-Team-10-Backend/assets/122529721/0f8d0215-04fc-49f1-907b-e3bec9cd1508)


# **Documentación del Backend - MyApp**

## **Tabla de contenidos**

- [Autenticación](https://www.phind.com/search?cache=5e81e66d-6d2a-4220-91e2-e91a8db81224#autenticaci%C3%B3n)
    - [Registro de usuario](https://www.phind.com/search?cache=5e81e66d-6d2a-4220-91e2-e91a8db81224#registro-de-usuario)
    - [Inicio de sesión](https://www.phind.com/search?cache=5e81e66d-6d2a-4220-91e2-e91a8db81224#inicio-de-sesi%C3%B3n)
    - [Cerrar sesión](https://www.phind.com/search?cache=5e81e66d-6d2a-4220-91e2-e91a8db81224#cerrar-sesi%C3%B3n)
- [Gestión de citas](https://www.phind.com/search?cache=5e81e66d-6d2a-4220-91e2-e91a8db81224#gesti%C3%B3n-de-citas)
    - [Obtener citas](https://www.phind.com/search?cache=5e81e66d-6d2a-4220-91e2-e91a8db81224#obtener-citas)
    - [Crear cita](https://www.phind.com/search?cache=5e81e66d-6d2a-4220-91e2-e91a8db81224#crear-cita)
    - [Actualizar cita](https://www.phind.com/search?cache=5e81e66d-6d2a-4220-91e2-e91a8db81224#actualizar-cita)
    - [Eliminar cita](https://www.phind.com/search?cache=5e81e66d-6d2a-4220-91e2-e91a8db81224#eliminar-cita)
- [Notificaciones](https://www.phind.com/search?cache=5e81e66d-6d2a-4220-91e2-e91a8db81224#notificaciones)
    - [Enviar notificación](https://www.phind.com/search?cache=5e81e66d-6d2a-4220-91e2-e91a8db81224#enviar-notificaci%C3%B3n)

## **Autenticación**

****Registro de usuario****

- **Endpoint**: `POST /api/users/register`
- **Parámetros**:
    - `email`: Dirección de correo electrónico del usuario (requerido)
    - `password`: Contraseña del usuario (requerido)
- **Respuesta exitosa**: `201 Created`
    - `user_id`: ID del usuario creado
- **Respuesta de error**: `400 Bad Request`
    - Mensaje de error específico

****Inicio de sesión****

- **Endpoint**: `POST /api/users/login`
- **Parámetros**:
    - `email`: Dirección de correo electrónico del usuario (requerido)
    - `password`: Contraseña del usuario (requerido)
- **Respuesta exitosa**: `200 OK`
    - `access_token`: Token de acceso para autenticar las solicitudes de la API
- **Respuesta de error**: `401 Unauthorized`
    - Mensaje de error específico

****Cerrar sesión****

- **Endpoint**: `POST /api/users/logout`
- **Encabezados**:
    - `Authorization`: Token de acceso del usuario (requerido)
- **Respuesta exitosa**: `204 No Content`
- **Respuesta de error**: `401 Unauthorized`
    - Mensaje de error específico

## **Gestión de citas**

****Obtener citas****

- **Endpoint**: `GET /api/appointments`
- **Encabezados**:
    - `Authorization`: Token de acceso del usuario (requerido)
- **Respuesta exitosa**: `200 OK`
    - Lista de citas con los siguientes campos:
        - `id`: ID de la cita
        - `title`: Título de la cita
        - `date`: Fecha y hora de la cita
        - `location`: Ubicación de la cita
- **Respuesta de error**: `401 Unauthorized`
    - Mensaje de error específico

****Crear cita****

- **Endpoint**: `POST /api/appointments`
- **Encabezados**:
    - `Authorization`: Token de acceso del usuario (requerido)
- **Parámetros**:
    - `title`: Título de la cita (requerido)
    - `date`: Fecha y hora de la cita (requerido)
    - `location`: Ubicación de la cita (opcional)
- **Respuesta exitosa**: `201 Created`
    - `id`: ID de la cita creada
- **Respuesta de error**: `400 Bad Request` o `401 Unauthorized`
    - Mensaje de error específico

****Actualizar cita****

- **Endpoint**: `PUT /api/appointments/:id`
- **Encabezados**:
    - `Authorization`: Token de acceso del usuario (requerido)
- **Parámetros**:
    - `title`: Título de la cita (opcional)
    - `date`: Fecha y hora de la cita (opcional)
    - `location`: Ubicación de la cita (opcional)
- **Respuesta exitosa**: `200 OK`
    - `id`: ID de la cita actualizada
- **Respuesta de error**: `400 Bad Request`, `401 Unauthorized` o `404 Not Found`
    - Mensaje de error específico

****Eliminar cita****

- **Endpoint**: `DELETE /api/appointments/:id`
- **Encabezados**:
    - `Authorization`: Token de acceso del usuario (requerido)
- **Respuesta exitosa**: `204 No Content`
- **Respuesta de error**: `401 Unauthorized` o `404 Not Found`
    - Mensaje de error específico

## **Notificaciones**

****Enviar notificación****

- **Endpoint**: `POST /api/notifications`
- **Encabezados**:
    - `Authorization`: Token de acceso del usuario (requerido)
- **Parámetros**:
    - `user_id`: ID del usuario destinatario (requerido)
