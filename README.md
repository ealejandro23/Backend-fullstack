# Proyecto EFA — Backend

API REST desarrollada en **Spring Boot 3.5** (Java 17) para una tienda de ropa online. Gestiona productos, ventas, usuarios, categorías, colores, tallas, materiales, marcas, métodos de pago/envío e imágenes (con subida a Cloudinary).

## Tecnologías

- **Java 17** + **Spring Boot 3.5.8**
- **Spring Data JPA** (Hibernate) para persistencia
- **Spring Security** para autenticación/autorización
- **Spring Validation** para validación de datos
- **PostgreSQL** en producción / **H2** en memoria para desarrollo local
- **Springdoc OpenAPI (Swagger UI)** para documentación de la API
- **Cloudinary** para almacenamiento y gestión de imágenes
- **Lombok** para reducir boilerplate
- **Maven** como gestor de dependencias y build
- **Docker** (multi-stage build) y **Docker Compose** para contenedores
- **GitHub Actions** para CI/CD hacia AWS (ECR + EC2 self-hosted runner)

## Estructura del proyecto

```
demo/src/main/java/Proyecto_EFA/
├── ProductosApplication.java     # Clase principal Spring Boot
├── demo/
│   ├── config/          # CloudinaryConfig, CorsConfig, DataLoader, SecurityConfig, SwaggerConfig
│   ├── controller/      # Endpoints REST (Producto, Venta, Usuario, Categoria, Color, Marca,
│   │                    #   Material, Talla, Estado, MetodoPago, MetodoEnvio, Imagen, Rol,
│   │                    #   ProductoVenta)
│   ├── dto/              # ItemVentaRequest, VentaRequest
│   ├── model/             # Entidades JPA (Producto, Venta, Usuario, Categoria, Color, Marca,
│   │                       #   Material, Talla, Imagen, Direccion, Comuna, Region, etc.)
│   ├── repository/         # Repositorios Spring Data JPA
│   └── service/              # Lógica de negocio por entidad
└── resources/
    └── application.properties
```

## Módulos / entidades principales

- **Productos**: catálogo con categoría, color, marca, material, talla e imágenes asociadas.
- **Ventas**: registro de ventas y sus ítems (`VentaController`, `ProductoVentaController`).
- **Usuarios y roles**: gestión de usuarios y roles (`UsuarioController`, `RolController`).
- **Ubicación**: modelos de Región, Comuna y Dirección para envíos.
- **Métodos de pago y envío**: catálogos configurables (`MetodoPagoController`, `MetodoEnvioController`).
- **Imágenes**: subida y gestión vía Cloudinary (`ImagenController`, `CloudinaryService`).

## Configuración

La configuración vive en `application.properties` y usa variables de entorno con valores por defecto para desarrollo local:

| Variable | Descripción | Por defecto (local) |
|---|---|---|
| `PORT` | Puerto del servidor | `8080` |
| `SPRING_DATASOURCE_URL` | URL de la base de datos | H2 en memoria |
| `SPRING_DATASOURCE_USERNAME` / `PASSWORD` | Credenciales BD | `sa` / vacío |
| `CLOUDINARY_URL` | Credenciales de Cloudinary | vacío |

- Documentación interactiva disponible en `/doc/swagger-ui.html` (Swagger UI habilitado).
- Consola H2 deshabilitada por defecto (`spring.h2.console.enabled=false`).
- Seguridad básica configurada con usuario `admin` (ver `application.properties`).

## Puesta en marcha

### Requisitos
- Java 17
- Maven (o usar el wrapper `mvnw` incluido)

### Ejecución local (con H2 en memoria)

```bash
cd demo
./mvnw spring-boot:run
```

La API quedará disponible en `http://localhost:8080`, y Swagger en `http://localhost:8080/doc/swagger-ui.html`.

### Con Docker Compose (PostgreSQL + backend)

Desde la raíz del proyecto, definiendo las variables de entorno necesarias (`POSTGRES_DB`, `POSTGRES_USER`, `POSTGRES_PASSWORD`, `CLOUDINARY_URL`, y las de la imagen `ECR_REGISTRY`/`ECR_REPOSITORY`/`IMAGE_TAG` si se usa la imagen ya publicada):

```bash
docker compose up -d
```

### Build manual de la imagen Docker

```bash
docker build -t proyecto-efa-backend .
docker run -p 8080:8080 proyecto-efa-backend
```

## CI/CD

El workflow `.github/workflows/deploy.yml` automatiza:

1. **Build & push**: compila la imagen Docker y la publica en **Amazon ECR**.
2. **Deploy**: en un runner self-hosted en **EC2**, hace *pull* de la nueva imagen y levanta el stack (`backend` + `db`) con Docker Compose, limpiando imágenes antiguas.

Se dispara en cada `push` a `main` que modifique `demo/**`, `Dockerfile` o el propio workflow (también manualmente vía `workflow_dispatch`).

## Documentación adicional

El archivo `MODELS.md` contiene el detalle del modelo de datos del proyecto.

---

# Control de versiones y flujo de trabajo DevOps

Este proyecto se gestiona siguiendo una estrategia de ramificación **GitFlow**, con el fin de asegurar la trazabilidad del código, la colaboración entre integrantes y la estabilidad de la rama de producción.

## Estrategia de ramificación: GitFlow

Elegimos **GitFlow** por sobre *trunk-based development* por las siguientes razones:

- El encargo exige explícitamente las ramas `main`, `develop`, `feature/*` y `hotfix/*`, que es el modelo natural de GitFlow.
- El proyecto se desarrolla en parejas y se integrará con lanzamientos de versiones planificados (releases) a lo largo del semestre, ideal para un producto tipo e-commerce.
- Las ramas de larga duración (`main` y `develop`) separan claramente lo estable de lo que está en desarrollo.
- Las ramas efímeras (`feature/*` y `hotfix/*`) aíslan el trabajo y permiten la revisión mediante Pull Requests antes de integrar.
- La rama `hotfix/*` permite corregir problemas en producción sin esperar al siguiente release.

### Estructura de ramas

| Rama | Descripción |
|---|---|
| `main` | Rama de producción. Siempre estable. Solo recibe merges desde `develop` y `hotfix/*`. |
| `develop` | Rama de integración. Centraliza los features desarrollados. |
| `feature/<nombre>` | Nueva funcionalidad. Nace de `develop` y se integra vía Pull Request. |
| `hotfix/<nombre>` | Corrección de emergencia. Nace de `main` y se mezcla a `main` y de vuelta a `develop`. |

## Convenciones de nombrado de ramas

- Se usan minúsculas y guiones (`-`) como separadores: `feature/mejora-catalogo`, `hotfix/correccion-ventas`.
- Las `feature/*` deben salir desde `develop` y referirse a una funcionalidad concreta, en inglés o español según el caso.

## Convención de mensajes de commit

Seguimos una convención basada en **Conventional Commits**:

```
<tipo>(<ámbito>): <descripción>
```

Ejemplos:

- `feat(catalogo): agregar filtro por talla`
- `fix(ventas): corregir cálculo de totales`
- `docs(readme): documentar convenciones de ramas`
- `style(usuario): ordenar imports`
- `refactor(pago): simplificar lógica del checkout`
- `test(venta): agregar pruebas del servicio`
- `chore(deps): actualizar dependencias`

Tipos usados: `feat`, `fix`, `docs`, `style`, `refactor`, `test`, `chore`.

## Estrategia de merges

- Los cambios de funcionalidad se integran a `develop` mediante **Pull Requests**.
- Se utilizan merge de `--no-ff` (con commit de merge) para conservar la trazabilidad del historial.
- Una vez `develop` está estable, se fusiona a `main` (release).
- Las correcciones `hotfix/*` se fusionan a `main` y se propagan también a `develop`.

## Estrategia de revisión (Code Review)

- Toda integración a `develop` y a `main` requiere **al menos una aprobación** de un integrante del equipo.
- Antes de aprobar se verifica el resultado del pipeline de CI (GitHub Actions) y el *diff* de la Pull Request.
- Las discusiones y resoluciones de conflictos se realizan dentro de los comentarios del Pull Request.
- Se evita el *push* directo a `main` y `develop`; los cambios entran exclusivamente por Pull Request.

## Pipeline CI/CD (GitHub Actions)

- **`ci.yml`**: se ejecuta en cada `push` a `develop` y en cada `pull_request` hacia `main`. Realiza el *build* y las pruebas automáticas para validar la integración.
- **`deploy.yml`**: se ejecuta en cada `push` a `main` (vía `push` directo o tras aprobar el release) y despliega la imagen Docker hacia **Amazon ECR** y **EC2** (runner self-hosted), levantando el stack `backend` + `db` con Docker Compose.

Los detalles de build, push y despliegue se encuentran en las secciones anteriores de este documento.

## Uso de herramientas de IA

En este proyecto se utilizó IA como apoyo para mejorar la redacción de esta documentación y generar diagramas de apoyo. Todo el contenido técnico generado fue revisado y validado por el equipo. Las reflexiones y justificaciones técnicas son de autoría propia de los integrantes.
