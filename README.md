# 🚀 Innovatech Solutions - Microservicio de Recursos Humanos (RRHH)

Este microservicio constituye el motor crítico de gestión de talento y recursos de Innovatech. 

Su propósito fundamental es administrar la información de los empleados y calcular dinámicamente su disponibilidad de carga horaria semanal. Esto permite realizar asignaciones de proyectos balanceadas basándose en las horas contratadas y reglas de negocio específicas de acuerdo con el perfil profesional de cada recurso.

---

## 🏗️ Arquitectura de Software

El sistema implementa patrones avanzados de diseño y control de consistencia:

*   **Strategy Pattern (Patrón Estrategia):** La lógica de cálculo de capacidad laboral está encapsulada en clases de estrategia independientes (`DeveloperCapacityStrategy`, `UXDesignerCapacityStrategy` y `UXCapacityStrategy`). Esto permite definir límites semanales o fórmulas de deducción particulares por cargo (ej. 40 horas base para perfiles técnicos, 35 horas para diseño) sin alterar el código central.
*   **Lookup Dinámico de Estrategias:** El servicio inyecta de forma reflexiva todas las implementaciones de capacidad de carga y selecciona la adecuada en tiempo de ejecución utilizando comparación semántica (`contains`) sobre el cargo registrado del empleado. Esto permite flexibilidad ante variaciones textuales del cargo (ej. "Lead Developer" o "Backend Developer").
*   **Gestión de Errores Semántica:** Mapeo de excepciones de negocio directamente a códigos de estado HTTP para consumo seguro desde el BFF:
    *   `EmpleadoNotFoundException` ➔ **`404 Not Found`**
    *   `StrategyNotFoundException` ➔ **`422 Unprocessable Entity`**
*   **Inicialización y Semilla:** Al iniciar, se cargan de forma automática **120 empleados** de prueba mediante scripts SQL (Flyway) con cargos normalizados (como `DEVELOPER_LEAD`, `UX_DESIGNER_SENIOR`, etc.).

---

## 🛠️ Stack Tecnológico

*   **Lenguaje:** Java 21 (Eclipse Temurin JRE)
*   **Framework:** Spring Boot 3.x (JPA / Hibernate)
*   **Base de Datos:** PostgreSQL 18.3-alpine (Flyway para base de datos y semillas)
*   **Orquestación:** Docker Compose

---

## 🚀 Guía de Despliegue y Ejecución

### 📋 Prerrequisitos

*   Docker Desktop / Docker Engine (con soporte WSL2 en Windows)
*   Para ejecutar localmente (fuera de Docker):
    *   PostgreSQL corriendo en el puerto **5432** con base de datos `rrhh_db`, usuario `user_rrhh` y contraseña `admin123`.

### 🐳 Ejecución con Docker

El microservicio se ejecuta en conjunto con su base de datos PostgreSQL dedicada mediante Docker Compose.

1. En la raíz del directorio `fs3_ms_recursos_humanos`, ejecute:
   ```bash
   # Limpiar volúmenes y reiniciar
   docker compose down -v
   
   # Levantar la base de datos y la API de RRHH
   docker compose up -d --build
   ```
2. **Puertos expuestos:**
   *   **API de RRHH:** Puerto **`8081`** en el host.
   *   **Base de Datos (PostgreSQL):** Puerto **`5432`** en el host.

### 💻 Ejecución Local (Desarrollo)

Para ejecutar el servicio localmente sin Docker:
```bash
# Windows
.\mvnw.cmd spring-boot:run

# Linux / macOS
./mvnw spring-boot:run
```
*Nota: Si ejecuta localmente fuera de Docker, asegúrese de ajustar el hostname en `src/main/resources/application.properties` de `innovatech-db-rrhh` a `localhost` en el datasource URL.*

---

## 🧪 Ejecución de Pruebas Unitarias

El proyecto cuenta con pruebas unitarias que evalúan las estrategias de cálculo de capacidad y las operaciones del controlador y servicio de empleados. La cobertura se sitúa dentro de las metas de **60% y 75%**.

Para ejecutar las pruebas usando la base de datos **H2** en memoria integrada:

```bash
# En Windows (CMD o PowerShell)
.\mvnw.cmd test

# En Linux o macOS
./mvnw test
```

Para generar el reporte de cobertura de Jacoco:
```bash
# Windows
.\mvnw.cmd clean verify

# Linux / macOS
./mvnw clean verify
```
El reporte se generará en: `target/site/jacoco/index.html`.

---

## 🔌 Documentación del API (Puerto 8081)

Expone los siguientes endpoints bajo el prefijo `/api/rrhh/empleados`:

| Método | Endpoint | Payload (Request Body) | Descripción | Código de Éxito | Códigos de Error |
| :--- | :--- | :--- | :--- | :--- | :--- |
| `GET` | `/api/rrhh/empleados` | Ninguno | Obtiene el listado completo de empleados. | `200 OK` | - |
| `GET` | `/api/rrhh/empleados/{id}` | Ninguno | Obtiene la información detallada de un empleado. | `200 OK` | `404 Not Found` |
| `GET` | `/api/rrhh/empleados/{id}/capacity` | Ninguno | Calcula dinámicamente y retorna la capacidad horaria disponible. | `200 OK` | `404 Not Found`, `422 Unprocessable` |
| `POST` | `/api/rrhh/empleados` | `Empleado` (JSON) | Registra un nuevo empleado en la base de datos. | `201 Created` | - |
| `PUT` | `/api/rrhh/empleados/{id}` | `Empleado` (JSON) | Actualiza los datos de un empleado existente. | `200 OK` | `404 Not Found` |
| `DELETE` | `/api/rrhh/empleados/{id}` | Ninguno | Elimina un empleado de la base de datos. | `204 No Content` | `404 Not Found` |

---

© 2026 Innovatech Solutions - Ingeniería Civil Informática - Documentación Técnica (EV2)