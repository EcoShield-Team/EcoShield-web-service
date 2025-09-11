## 👥 Team & Feature Responsibilities

A continuación se listan los módulos (`feature/*`) asignados a cada integrante del equipo:

### 🔹 Gerardo Chavez
- `feature/authentication` → login, registro y recuperación de cuenta.
- `feature/user-management` → gestión de perfil de usuario y roles.

### 🔹 Integrante 2
- `feature/almanaque` → gestión de enfermedades y plagas (listado y detalle).
- `feature/crop-detection` → simulación degit add . detección de cultivos a partir de fotos.

### 🔹 Integrante 3
- `feature/community-forum` → foro de comunidad (posts y comentarios).

### 🔹 Integrante 4
- `feature/recommendations` → módulo de recomendaciones: blogs, noticias y tips diarios.

### 🔹 Integrante 5
- `feature/feedback` → formulario de feedback con puntuación y comentarios.
- `feature/photos-history` → historial de fotos y resultados de detecciones.  

---

## 🔀 Workflow: Cómo trabajar en una rama `feature/*`

Cada integrante debe seguir este flujo de trabajo:

1. **Actualizar `develop`**  
   Siempre empezar desde la última versión de `develop`:
   ```bash
   git checkout develop
   git pull origin develop
   
2. **Crear tu rama de feature**
   Crear una rama nueva a partir de `develop`:
3. ```bash
   git checkout -b feature/nombre-del-modulo
   git push -u origin feature/nombre-del-modulo
   
---

**(solo si quieres)**
Para saber que cambiaste puedes seguir los conventional commits,
Le puedes pedir a Chatgpt que te lo genere.

✅ Convenciones de commits

Usamos el estándar Conventional Commits:

- feat: → nueva funcionalidad
- fix: → corrección de bug
- docs: → cambios en documentación
- style: → cambios de formato (espacios, imports, etc.)
- refactor: → cambios internos sin alterar funcionalidad
- test: → agregar o corregir tests