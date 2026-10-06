# API REST Supermercado — Inventarios y Productos

[![CI](https://github.com/destebNC/proyectoPrueba/actions/workflows/ci.yml/badge.svg)](https://github.com/destebNC/proyectoPrueba/actions/workflows/ci.yml)

API REST hecha con **Java 21 + Spring Boot 3** para gestionar los **inventarios** de un supermercado
(por ejemplo "Almacén Madrid") y los **productos** que contienen. Está protegida con **JWT y roles**,
documentada con **OpenAPI/Swagger** y, a partir del contrato OpenAPI, genera **SDKs en Java, TypeScript, C# y PHP**.

Proyecto de prácticas realizado en equipo.

---

## Puesta en marcha rápida

Elige **una** de estas opciones.

### Opción A — Solo Java (sin instalar base de datos)

Requisitos: **JDK 21 o superior**. Maven no hace falta (se usa el wrapper `mvnw`).

```bash
git clone https://github.com/destebNC/proyectoPrueba.git
cd proyectoPrueba
./mvnw spring-boot:run          # en Windows (cmd/PowerShell): mvnw.cmd spring-boot:run
```

Usa una base de datos **H2 en memoria** que se rellena con datos de ejemplo en cada arranque
(los cambios se pierden al parar la aplicación).

### Opción B — Docker (API + MySQL)

Requisitos: **Docker Desktop**.

```bash
git clone https://github.com/destebNC/proyectoPrueba.git
cd proyectoPrueba
docker compose up --build
```

Levanta MySQL y la API. Los datos **se conservan** entre reinicios (volumen `db_data`).
Para borrarlo todo: `docker compose down -v`.

### Opción C — IntelliJ IDEA

Abre la carpeta del proyecto y ejecuta la clase `SupermercadoApplication`.

---

Con cualquiera de las tres, abre **http://localhost:8080/swagger-ui.html**.

## Usuarios de prueba

| Usuario | Contraseña | Rol   | Puede usar                                   |
|---------|------------|-------|----------------------------------------------|
| `admin` | `admin123` | ADMIN | Todo                                         |
| `user`  | `user123`  | USER  | Productos (no puede crear, renombrar ni borrar inventarios) |

Cualquiera puede registrarse con `POST /auth/register`; las cuentas nuevas siempre tienen el rol **USER**.

**En Swagger UI:** ejecuta `POST /auth/login`, copia el `token` de la respuesta, pulsa **Authorize** y pégalo.

## Cómo funciona

```
Cliente ──HTTP + JWT──▶ Controller ──▶ Service ──▶ Repository (JPA) ──▶ H2 / MySQL
                            │              │
                       DTO + @Valid     reglas de negocio,
                                        errores 404/409...
```

| Paquete      | Qué contiene                                                                     |
|--------------|----------------------------------------------------------------------------------|
| `controller` | Endpoints REST. Reciben y devuelven DTOs, nunca entidades.                       |
| `service`    | Lógica de negocio y conversión entidad ↔ DTO (`Mapper`).                         |
| `repository` | Acceso a datos con Spring Data JPA.                                              |
| `model`      | Entidades JPA: `Inventory` 1 ── N `Product`, y `User`.                           |
| `security`   | Generación/validación de JWT, filtro de autenticación y reglas de acceso por rol. |
| `exception`  | Errores en formato estándar RFC 7807 (`application/problem+json`).               |
| `config`     | `DataInitializer`: crea los usuarios demo y datos de ejemplo al arrancar.        |

### Endpoints

Los listados están paginados con `?page=0&size=10` (máximo 100 por página).

| Método | Ruta                                                 | Rol         | Descripción                              |
|--------|------------------------------------------------------|-------------|------------------------------------------|
| POST   | `/auth/register`                                     | público     | Crea un usuario USER y devuelve un token |
| POST   | `/auth/login`                                        | público     | Devuelve un token JWT (1 hora)           |
| GET    | `/api/inventories`                                   | ADMIN       | Lista los inventarios                    |
| POST   | `/api/inventories`                                   | ADMIN       | Crea un inventario (con o sin productos) |
| GET    | `/api/inventories/{id}`                              | ADMIN       | Obtiene un inventario con sus productos  |
| PUT    | `/api/inventories/{id}`                              | ADMIN       | Renombra un inventario                   |
| DELETE | `/api/inventories/{id}`                              | ADMIN       | Borra un inventario y sus productos      |
| GET    | `/api/inventories/{inventoryId}/products`            | ADMIN, USER | Productos de un inventario               |
| POST   | `/api/inventories/{inventoryId}/products`            | ADMIN, USER | Añade un producto a un inventario        |
| PUT    | `/api/inventories/{inventoryId}/products/{productId}`| ADMIN, USER | Actualiza un producto del inventario     |
| DELETE | `/api/inventories/{inventoryId}/products/{productId}`| ADMIN, USER | Borra un producto del inventario         |
| GET    | `/api/products`                                      | ADMIN, USER | Lista todos los productos                |
| POST   | `/api/products`                                      | ADMIN, USER | Crea un producto sin inventario          |
| GET    | `/api/products/{id}`                                 | ADMIN, USER | Obtiene un producto                      |

Códigos de respuesta: `200` OK · `201` creado · `204` borrado · `400` datos no válidos ·
`401` sin token, token no válido o credenciales incorrectas · `403` rol insuficiente · `404` no existe · `409` usuario ya existe.

### Ejemplo con curl

```bash
# 1. Login
TOKEN=$(curl -s -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"admin","password":"admin123"}' | sed 's/.*"token":"\([^"]*\)".*/\1/')

# 2. Crear un inventario con un producto
curl -X POST http://localhost:8080/api/inventories \
  -H "Authorization: Bearer $TOKEN" -H "Content-Type: application/json" \
  -d '{"name":"Almacen Sevilla","products":[{"name":"Gazpacho 1L","price":2.5,"weight":1}]}'

# 3. Ver sus productos paginados
curl "http://localhost:8080/api/inventories/3/products?page=0&size=5" -H "Authorization: Bearer $TOKEN"
```

## Configuración

Todo tiene valores por defecto pensados para desarrollo. En un entorno real cámbialos con variables de entorno
(o con un fichero `.env` si usas Docker Compose):

| Variable                 | Por defecto           | Uso                                         |
|--------------------------|-----------------------|---------------------------------------------|
| `SPRING_PROFILES_ACTIVE` | `dev`                 | `dev` = H2 en memoria, `mysql` = MySQL      |
| `DB_URL`                 | `jdbc:mysql://localhost:3306/supermercado…` | Conexión MySQL (perfil `mysql`) |
| `DB_USERNAME` / `DB_PASSWORD` | `supermercado` / `supermercado` | Credenciales MySQL               |
| `JWT_SECRET`             | secreto de desarrollo | Clave para firmar los tokens (≥ 32 caracteres) |
| `JWT_EXPIRATION_MINUTES` | `60`                  | Validez de los tokens                       |
| `ADMIN_PASSWORD`         | `admin123`            | Contraseña del usuario `admin`              |
| `DEMO_USER_PASSWORD`     | `user123`             | Contraseña del usuario `user`               |
| `SEED_ENABLED`           | `true`                | Crear usuarios demo y datos de ejemplo      |

## Tests

```bash
./mvnw verify
```

Ejecuta, sobre H2 y sin servicios externos:

- **Tests de integración** (`ApiIntegrationTest`): login/registro, permisos por rol, CRUD de inventarios y productos,
  paginación y códigos de error.
- **Tests de contrato** (`ContractIntegrityTest`): comprueban que las peticiones y respuestas reales cumplen
  `src/main/resources/static/openapi.yaml`.
- Validación de la sintaxis del contrato OpenAPI.

GitHub Actions ejecuta todo esto en cada push y pull request a `master`.

## Contrato OpenAPI y SDKs

El contrato está escrito a mano (*contract-first*) en `src/main/resources/static/openapi.yaml`, y es lo que muestra Swagger UI.
`openapi/releases/` guarda las versiones publicadas.

```bash
# Genera en generated/ los SDKs (Java, TypeScript, C#, PHP) y la colección Postman
./mvnw -Psdk generate-resources

# Arranca la app, exporta el OpenAPI generado por springdoc a target/openapi/ y pasa Spectral (requiere Node.js)
./mvnw -Pcontract verify
```

Para importar la colección en Postman: **Import** → `generated/postman/postman.json` y define la variable `baseUrl` como `http://localhost:8080`.

## CI/CD

- **GitHub Actions** (`.github/workflows/ci.yml`): build, tests y construcción de la imagen Docker.
- **Jenkins** (`Jenkinsfile`, opcional): genera los SDKs, publica la imagen en Docker Hub con Jib, publica el SDK
  TypeScript en npm y archiva los artefactos de la release. Necesita las credenciales `docker-hub-credentials` y `npm-token`.

## Tecnologías

Java 21 · Spring Boot 3.2 (Web, Data JPA, Security, Validation) · JWT (jjwt) · H2 / MySQL 8 ·
springdoc-openapi · OpenAPI Generator · Atlassian swagger-request-validator · Docker · GitHub Actions · Jenkins

## Qué hemos aprendido

- Diseñar una API REST *contract-first* con OpenAPI y validar que la implementación lo cumple.
- Generar SDKs para varios lenguajes a partir del contrato.
- Separar DTOs y entidades de dominio, y validar la entrada con Bean Validation.
- Seguridad sin estado con JWT y autorización por roles en Spring Security.
- Manejo de errores homogéneo con Problem Details (RFC 7807).
- Configuración por perfiles y variables de entorno, contenedores con Docker y pipelines de CI/CD.
