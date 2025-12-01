# 🌿 EcoShield Web Service API

[![Java](https://img.shields.io/badge/Java-17-blue.svg?logo=openjdk)](https://openjdk.org/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3-green.svg?logo=springboot)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-blue.svg?logo=postgresql)](https://www.postgresql.org/)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)
[![Status](https://img.shields.io/badge/Status-Stable-success.svg)]()
[![Swagger](https://img.shields.io/badge/API-Swagger%20UI-orange.svg?logo=swagger)]()

### Plataforma inteligente para la detección de plagas y enfermedades agrícolas

El **EcoShield Web Service** es una API RESTful desarrollada en **Spring Boot** que integra inteligencia artificial para el análisis de imágenes de cultivos, combinada con módulos sociales e informativos para agricultores, investigadores y entusiastas de la agricultura sostenible.

---

## 🚀 Características principales

### 🔐 Autenticación y Seguridad
- Registro e inicio de sesión con **JWT**
- Cambio de contraseña autenticado
- Recuperación de contraseña vía email (código de verificación)
- Rate limiting para evitar abuso en *forgot password*
- Heartbeat para detectar actividad del usuario
- Roles: `USER` y `ADMIN`

### 👤 Gestión de usuarios
- Ver perfil
- Actualizar perfil + foto
- Eliminar usuario (ADMIN)
- Asignar roles
- Obtener mis posts
- Última actividad del usuario

### 🌾 Almanaque agrícola
Incluye:

- Listar plagas y enfermedades
- Ordenamiento: severidad, nombre asc/desc
- Filtros: tipo, temporada, severidad
- Búsqueda por nombre
- Detalles completos
- Enfermedades/plagas relacionadas

### 🧠 Detección IA (Gemini)
- Subida de imagen
- Análisis automático
- Diagnóstico de plaga o enfermedad
- Recomendaciones
- Historial por usuario

### 📝 Comunidad (Posts, Comentarios, Likes)
- Crear/editar/eliminar posts con imagen
- Crear/editar/eliminar comentarios
- Likes para posts y comentarios
- Búsqueda global con tabs:  
  `destacado | recientes | personas`
- Listado por usuario

### 📰 Blogs agrícolas
- CRUD completo para ADMIN
- Tip del día
- Noticias y tips separados
- Soporte de imagen Cloudinary

### 💬 Feedback
- Envío de opiniones (USER)
- Gestión por parte de ADMIN
- Filtros por usuario o tipo

### 🌦️ Clima
- Búsqueda por ciudad
- Búsqueda por coordenadas
- Ciudad por defecto (Lima)

---

## ⚙️ Tecnologías utilizadas

| Tecnología | Descripción |
|-------------|--------------|
| **Java 17** | Lenguaje base del proyecto |
| **Spring Boot 3.3+** | Framework principal para desarrollo del servicio |
| **Spring Security / JWT** | Autenticación y autorización basada en tokens |
| **Hibernate + JPA** | ORM para persistencia en base de datos |
| **PostgreSQL** | Base de datos relacional |
| **Cloudinary API** | Gestión de imágenes |
| **Weather API** | Clima |
| **Gmail SMTP** | Reset password |
| **Gemini AI API** | Análisis automatizado de cultivos mediante IA |
| **Swagger / OpenAPI** | Documentación interactiva de endpoints |

---

## 🧩 Estructura del proyecto

```bash
src/
 ├─ main/java/com/api/ecoshieldwebservice
 │   ├─ config/              → configuración general (seguridad, Swagger, etc.)
 │   ├─ controllers/         → controladores REST
 │   ├─ dtos/                → Data Transfer Objects (request/response)
 │   ├─ entities/            → entidades JPA del modelo de datos
 │   ├─ enums/               → enumeraciones usadas en los modelos
 │   ├─ interfaces/          → interfaces de servicios y repositorios
 │   ├─ jobs/                → necesario para cambiar password
 │   ├─ repositories/        → acceso a la base de datos
 │   ├─ security/            → identificacion dentro de la API
 │   ├─ services/            → lógica de negocio
 │   └─ EcoShieldWebServiceApplication.java
 └─ resources/
     ├─ application.properties
     ├─ data.sql   
     ├─ static/
     └─ templates/
```

---

## 🧠 Endpoints destacados

| Módulo | Endpoint                                                                                                                                | Descripción                            |
|--------|-----------------------------------------------------------------------------------------------------------------------------------------|----------------------------------------|
| Auth | `/auth/login`, `/auth/register`, `/auth/password/change`, `/auth/password/reset`, `/auth/password/forgot`, `/auth/password/verify-code` | Inicio de sesión y registro            |
| Usuario | `/users`, `/users/{id}`, `/users/{id}/rol` , `/users/heartbeat`                                                                         | Gestión de perfil, foto y roles        |
| Blog | `/blogs`, `/blogs/{id}`, `/blogs/tip` , `/blogs/news`                                                                                   | Publicación de noticias y tips         |
| Post | `/posts`, `/posts/{id}`, `/posts/{postId}/like`                                                                                         | Creación de publicaciones comunitarias |
| Comentario | `/posts/{postId}/comentarios`, `/posts/{postId}/comentarios/{comentarioId}`                                                             | Comentarios sobre publicaciones        |
| Detección | `/deteccion`, `/deteccion/{id}`, `/deteccion/historial `                                                                                | Análisis IA de fotos agrícolas         |
| Almanaque | `/almanaque/enfermedades`, `/almanaque/plagas`                                                                                          | Consulta de plagas y enfermedades      |
| Feedback | `/feedback`, `/feedback/{id}`, `/feedback/usuario/{usuarioId}`, `/feedback/tipo/{tipo}`                                                 | Opiniones y calificaciones del sistema |
| Weather | `/weather?city=`,`/weather?lat=&lon=`, `/weather`                                                                                       | Obtención de ubicación y clima actual  |
---

## 🧰 Configuración e instalación

---

### 1️⃣ Clonar el repositorio
```bash
git clone https://github.com/<ORG>/<REPO>.git
cd EcoShield-web-service
```

---

### 2️⃣ Configurar variables de entorno
Crea un archivo `.env` en la raíz del proyecto con lo siguiente:

```bash
CLOUDINARY_URL=cloudinary://<api_key>:<secret>@<name>
JWT_SECRET=<secreto>
GEMINI_API_KEY=<api_key>
SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/ecoshield
SPRING_DATASOURCE_USERNAME=postgres
SPRING_DATASOURCE_PASSWORD=****
WEATHER_API_KEY=<api_key_clima>
SPRING_MAIL_USERNAME=<mail>
SPRING_MAIL_PASSWORD=<pass>
```

> ⚠️ **Importante:** el archivo `.env` está incluido en el `.gitignore`.  
> No debe subirse al repositorio ya que contiene credenciales sensibles.

---

### 3️⃣ Ejecutar el proyecto
```bash
mvn clean package -DskipTests
java -jar target/ecoshieldService.jar
```

---

### 4️⃣ Documentación Swagger
Una vez en ejecución, abre en tu navegador:

```bash
http://localhost:8080/swagger-ui/index.html
```

---

## 📦 Versionado
**v2.0.0** → Release Final.  
Incluye:
- Comunidad completa (posts, comentarios, likes)
- Buscador avanzado
- IA con historial
- Almanaque mejorado con filtros avanzados
- Blogs con tip del día
- Clima con integración externa
- Recuperación de contraseña con Gmail
- Rate limiting por IP
- Heartbeat
- Refactor general + DTOs mejorados

---

## 🧾 Licencia
Este proyecto se distribuye bajo la licencia **MIT**.  
© 2025 – Equipo **EcoShield** 🌱

> “Protegiendo los cultivos, impulsando el futuro verde.”
