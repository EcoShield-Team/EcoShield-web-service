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

- 🔐 **Autenticación JWT** — registro, inicio de sesión, recuperación de cuenta y control de roles (`USER`, `ADMIN`).
- 👤 **Gestión de usuarios** — actualización de perfil y foto, asignación de roles y bloqueo de cuentas.
- 🌾 **Almanaque agrícola** — catálogo de plagas y enfermedades con filtros y búsquedas.
- 🧠 **Detección IA (Gemini)** — análisis de imágenes de cultivos mediante IA para identificar plagas o enfermedades.
- 📝 **Comunidad** — publicación de posts, comentarios y participación en foros.
- 📰 **Recomendaciones y blogs** — contenido informativo, noticias y tips agrícolas.
- 💬 **Feedback** — módulo de sugerencias y calificación de experiencia del usuario.
- ☁️ **Integración con Cloudinary** — almacenamiento seguro y eficiente de imágenes.

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
     ├─ import.sql   
     ├─ static/
     └─ templates/
```

---

## 🧠 Endpoints destacados

| Módulo | Endpoint | Descripción |
|--------|-----------|-------------|
| Auth | `/auth/login`, `/auth/register` | Inicio de sesión y registro |
| Usuario | `/users/{id}` | Gestión de perfil, foto y roles |
| Blog | `/blogs` | Publicación de noticias y tips |
| Post | `/posts` | Creación de publicaciones comunitarias |
| Comentario | `/comentarios` | Comentarios sobre publicaciones |
| Detección | `/deteccion` | Análisis IA de fotos agrícolas |
| Almanaque | `/plagas`, `/enfermedades` | Consulta de plagas y enfermedades |
| Feedback | `/feedback` | Opiniones y calificaciones del sistema |

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
CLOUDINARY_URL=cloudinary://<api_key>:<api_secret>@<cloud_name>
GEMINI_API_KEY=<tu_api_key_de_gemini>
JWT_SECRET=<tu_secreto_jwt>
SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/ecoshield
SPRING_DATASOURCE_USERNAME=postgres
SPRING_DATASOURCE_PASSWORD=<tu_password>
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
**v1.0.0** → Primera versión estable.  
Incluye autenticación JWT, detección IA (Gemini), almacenamiento en Cloudinary,  
módulos de usuario, blog, comunidad y feedback integrados.

---

## 🧾 Licencia
Este proyecto se distribuye bajo la licencia **MIT**.  
© 2025 – Equipo **EcoShield** 🌱

> “Protegiendo los cultivos, impulsando el futuro verde.”
