# 🚀 Innovatech Solutions - Microservicio de Recursos Humanos (RRHH)

Este microservicio constituye el motor crítico de gestión de talento de Innovatech Solutions. Su propósito principal es el cálculo dinámico y preciso de la disponibilidad de carga horaria de los empleados, permitiendo una asignación de proyectos basada en datos reales y reglas de negocio específicas por perfil profesional.

---

## 🏗️ Arquitectura de Software

El sistema implementa patrones de diseño de para garantizar un desacoplamiento total entre las reglas de negocio y la capa de infraestructura:

### Strategy Pattern (Patrón Estrategia)
La lógica de cálculo de capacidad se encuentra encapsulada en estrategias independientes (`DeveloperCapacityStrategy`, `UXCapacityStrategy`). Esto permite que el negocio defina reglas distintas por cargo (ej: 40h base para perfiles técnicos vs 35h para diseño) sin modificar el servicio central.

### Lookup Dinámico por Contrato
El servicio inyecta automáticamente todas las estrategias disponibles y selecciona la correcta mediante semántica de texto (`contains`) sobre el cargo del empleado, garantizando tolerancia a variaciones en los datos (ej: "Lead Developer" o "Backend Developer").

### Gestión de Errores Semántica
Se han definido excepciones personalizadas vinculadas a códigos de estado HTTP específicos para una integración limpia:

- `EmpleadoNotFoundException` → **404 Not Found**
- `StrategyNotFoundException` → **422 Unprocessable Entity**

---

## 🛠️ Stack Tecnológico

- **Backend:** Java 21 (Eclipse Temurin JRE optimizado para Alpine Linux)
- **Framework:** Spring Boot 3.x con inyección dinámica de estrategias
- **Persistencia:** Spring Data JPA / Hibernate en modo `validate`
- **Motor de Base de Datos:** PostgreSQL 18.3-alpine
- **Orquestación:** Docker Compose (Infraestructura como Código)

---

## 🚀 Guía de Despliegue y Ejecución

### 📋 Prerrequisitos

- Docker Desktop / Docker Engine (WSL2 recomendado en Windows)
- Puertos **8081** (API) y **5432** (DB) disponibles en el host

---

### ⚡ Arranque del Entorno

El proyecto utiliza un **Multi-stage Build** para garantizar portabilidad absoluta.  
Para limpiar volúmenes previos y reconstruir el stack, ejecute:

```bash
docker compose down -v
docker compose up -d --build
```

## 🗄️ Inicialización de Datos

Al iniciar, el sistema carga automáticamente un dataset inicial de **120 empleados** de Innovatech.  
Los cargos han sido normalizados bajo los grupos `DEVELOPER_` y `UX_DESIGNER_` para asegurar la compatibilidad con el motor de estrategias dinámicas.

---

## 🧪 Documentación del API

### Consultar Detalle de Empleado (Integración BFF)

Recupera el objeto completo del empleado para la resolución de identidades en la capa de orquestación.

- **Endpoint:** `GET /api/rrhh/empleados/{id}`
- **Puerto:** `8081`
- **Formato de Respuesta:** `JSON` (Empleado Object)

### Uso Crítico

Este endpoint es consumido por el **BFF** para mapear los campos `nombre` y `cargo` en el resumen del dashboard.

### Consultar Capacidad Disponible

Calcula las horas semanales restantes de un empleado según su contrato y carga de trabajo actual.

- **Endpoint:** `GET /api/rrhh/empleados/{id}/capacity`
- **Puerto:** `8081`
- **Formato de Respuesta:** `Double` (Precisión decimal para gestión de horas/hombre)

### Mapeo de Errores

- **200 OK:** Cálculo exitoso  
- **404 NOT FOUND:** El ID proporcionado no existe en la base de datos  
- **422 UNPROCESSABLE ENTITY:** El empleado tiene un cargo que no cuenta con una lógica de capacidad definida  

---

## 🛡️ Estándares de Seguridad y Resiliencia

- **Privilegio Mínimo:** El contenedor de la API se ejecuta bajo el usuario de sistema `spring`, nunca como `root`  
- **Validación de Integridad:** Se utiliza `SPRING_JPA_HIBERNATE_DDL_AUTO=validate` para forzar que el modelo Java y el esquema físico sean idénticos  
- **Aislamiento de Red:** Comunicación aislada en `rrhh-network`, con exposición externa controlada únicamente al puerto `8081`  

---

© 2026 Innovatech Solutions - Ingeniería Civil Informática - Documentación Técnica (EV2)