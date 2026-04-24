# 🚀 Innovatech Solutions - Microservicio de Recursos Humanos (RRHH)

Este componente es una pieza core de la plataforma inteligente de **Innovatech Solutions**, diseñada para gestionar la disponibilidad y los perfiles profesionales de los **120 empleados** de la organización. Su objetivo principal es resolver la falta de visibilidad en la asignación de recursos mediante una arquitectura de microservicios robusta y desacoplada.

## 🏗️ Arquitectura y Tecnologías

El microservicio ha sido desarrollado bajo un modelo de **Arquitectura Multicapa** (Controller, Service, Repository, Model) garantizando la separación de responsabilidades y la mantenibilidad del código.

* **Lenguaje:** Java 17+
* **Framework:** Spring Boot 3.x
* **Persistencia:** Spring Data JPA con Hibernate.
* **Base de Datos:** PostgreSQL 18.3-alpine (Containerized).
* **Gestión de Dependencias:** Maven (Basado en Arquetipos Personalizados).

---

## 🛠️ Infraestructura y Despliegue (Docker)

Para asegurar la **eficiencia técnica** y que el sistema funcione de forma idéntica en cualquier entorno, la base de datos se gestiona mediante **Docker Compose**.

### 📋 Prerrequisitos

* **Docker Desktop:** Instalado y en ejecución.
* **Java JDK 17** y **Maven 3.8+** instalados localmente.
* **Puerto 5432:** Debe estar libre para la instancia de PostgreSQL.

### 🚀 Instrucciones de Ejecución

1.  **Levantar la Base de Datos:**
    Desde la raíz del proyecto, ejecute el siguiente comando para iniciar el contenedor de PostgreSQL 18:
    ```bash
    docker-compose up -d
    ```

2.  **Configuración de Persistencia:**
    El sistema utiliza la propiedad `hibernate.ddl-auto=update`, por lo que las tablas y restricciones de integridad se generarán automáticamente al iniciar la aplicación.

3.  **Ejecutar la Aplicación:**
    Utilice su IDE (IntelliJ IDEA recomendado) o ejecute mediante Maven:
    ```bash
    mvn spring-boot:run
    ```

---

## 🔑 Credenciales de Conexión

La configuración en `application.properties` está predefinida para una integración "llave en mano" con el contenedor Docker:

| Parámetro | Valor |
| :--- | :--- |
| **Host** | `localhost` |
| **Puerto** | `5432` |
| **Base de Datos** | `rrhh_db` |
| **Usuario** | `user_rrhh` |
| **Contraseña** | `admin123` |

---

## 🛡️ Patrones de Diseño Implementados

Siguiendo los requerimientos técnicos de la organización, este microservicio implementa:

* **Repository Pattern:** Desacopla la lógica de negocio del acceso a datos, facilitando el mantenimiento y la creación de Mocks para pruebas unitarias.
* **Strategy Pattern:** Implementado para gestionar los algoritmos de cálculo de **Capacity** de forma dinámica según el perfil del empleado (Dev, UX, QA).
* **Clean Code (JPA Entities):** Uso de anotaciones de validación para garantizar que solo datos íntegros persistan en la base de datos, cumpliendo con los estándares de calidad del proyecto.

---

## 🧪 Calidad y Pruebas

El microservicio está estructurado para facilitar el alcance de una cobertura mínima del **60% en pruebas unitarias**. Las validaciones de negocio están centralizadas en la capa de servicios para asegurar resultados consistentes en la gestión de recursos humanos.

---
© 2026 Innovatech Solutions - Documentación Técnica de Evaluación (EV2)
