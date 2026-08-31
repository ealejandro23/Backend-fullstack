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