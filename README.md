# 🚀 Innovatech Solutions - Microservicio de Recursos Humanos (RRHH)

Este componente es una pieza core de la plataforma inteligente de **Innovatech Solutions**, diseñada para gestionar la disponibilidad y los perfiles profesionales de los empleados de la organización. El microservicio utiliza una arquitectura robusta, desacoplada y completamente contenida para garantizar la consistencia entre entornos.

## 🏗️ Arquitectura y Tecnologías

El sistema sigue un modelo de **Arquitectura Multicapa** (Controller, Service, Repository, Model) con un enfoque en **seguridad operativa y eficiencia de recursos**.

* **Lenguaje:** Java 21 (Runtime optimizado: Eclipse Temurin JRE Alpine)
* **Framework:** Spring Boot 3.x
* **Persistencia:** Spring Data JPA / Hibernate
* **Base de Datos:** PostgreSQL 18.3-alpine
* **Orquestación:** Docker Compose (Infrastructure as Code)

---

## 🛠️ Infraestructura y Despliegue (Full-Stack Docker)

Para garantizar la **portabilidad absoluta**, tanto la aplicación como la base de datos están integradas en un flujo de orquestación único. Esto elimina la necesidad de pre-instalar JDK o Maven en el host; el proceso de construcción se realiza mediante un **Multi-stage Build** interno en Docker.

### 📋 Prerrequisitos

* **Docker Desktop:** Instalado y en ejecución (WSL2 recomendado en Windows).
* **Puertos:** Los puertos `8081` (API) y `5432` (DB) deben estar disponibles.

### 🚀 Instrucciones de Ejecución

1.  **Construcción y Arranque:**
    Desde la raíz del proyecto (donde se encuentra el archivo `docker-compose.yml`), ejecute:
    ```bash
    docker compose up -d --build
    ```

2.  **Verificación de Servicios:**
    El microservicio implementa un **Healthcheck** para la base de datos. La API solo iniciará su contexto de Spring una vez que el motor de PostgreSQL esté totalmente listo. Puede monitorear el estado con:
    ```bash
    docker compose ps
    ```

3.  **Acceso al Servicio:**
    La API estará disponible para recibir peticiones en: `http://localhost:8081`

---

## 🔑 Parámetros de Conexión y Red

El sistema utiliza una red aislada (`rrhh-network`) para la comunicación inter-contenedor. La configuración de conexión se inyecta dinámicamente mediante variables de entorno:

| Parámetro | Valor Interno (Red Docker) | Valor Externo (Host) |
| :--- | :--- | :--- |
| **Host DB** | `innovatech-db-rrhh` | `localhost` |
| **Puerto DB** | `5432` | `5432` |
| **Database** | `rrhh_db` | `rrhh_db` |
| **Usuario** | `user_rrhh` | `user_rrhh` |

---

## 🛡️ Estándares de Seguridad y Diseño Senior

* **Runtime Minimalista:** Se utiliza una imagen JRE basada en **Alpine Linux**, lo que reduce drásticamente el tamaño del artefacto y la superficie de ataque.
* **Principio de Privilegio Mínimo:** El contenedor de la aplicación se ejecuta bajo un usuario de sistema dedicado (`spring`), evitando el uso de privilegios de `root`.
* **Estrategia de Persistencia:** Automatización de esquema mediante `ddl-auto=update`, asegurando que el modelo de datos sea siempre consistente con las entidades JPA.
* **Resiliencia de Arranque:** Uso de directivas `depends_on` con condiciones de salud para evitar fallos de conexión en el arranque inicial.

---

## 🧪 Calidad y Escalabilidad

La lógica de negocio está centralizada en la capa de servicios, facilitando la implementación de pruebas unitarias y la futura integración de algoritmos complejos para el cálculo de capacidad de recursos (Dev, UX, QA).

---
© 2026 Innovatech Solutions - Documentación Técnica de Evaluación (EV2)