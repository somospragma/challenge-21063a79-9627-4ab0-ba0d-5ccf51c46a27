# Prompt para Mejorar el Codigo Base

Copia y pega el contenido del bloque de abajo en un asistente de IA (Claude, ChatGPT)
para obtener un ZIP con el proyecto completo y arrancable.

Si preferis trabajar en tu editor con un agente local (Claude Code, Cursor, Copilot), usa `AGENTS.md` en vez de este archivo: dice lo mismo pero para que escriba los archivos en disco.

## Las dos reglas que no se negocian

1. **Completa el boilerplate.** Todo lo que el proyecto necesita para compilar y arrancar: manifiesto de dependencias, punto de entrada, configuracion, capa de interfaz, y las capas del patron arquitectonico declarado. Eso es andamiaje y es tu trabajo.
2. **NO resuelvas el reto.** Los entregables de las fases son el trabajo de la persona. El hueco pedagogico se deja como esta: el proyecto arranca, pero lo que el reto pide implementar NO esta implementado.

Dicho de otra forma: si algo impide compilar, arreglalo. Si algo es logica de negocio incompleta, validaciones ausentes, un secreto hardcodeado o un patron mejorable, dejalo exactamente como esta — es lo que la persona tiene que encontrar.

## Superficie de practica — NO resuelvas

Estos archivos SON el ejercicio de la persona. No los implementes; deja stubs.

- `Dockerfile` — El topic pide contenedores/orquestacion: este archivo es el ejercicio, no scaffolding.
- `docker-compose.yml` — El topic pide contenedores/orquestacion: este archivo es el ejercicio, no scaffolding.
- `k8s/deployment.yaml` — El topic pide contenedores/orquestacion: este archivo es el ejercicio, no scaffolding.
- `k8s/service.yaml` — El topic pide contenedores/orquestacion: este archivo es el ejercicio, no scaffolding.

## Lo que le falta a este proyecto

Esto NO lo tenes que adivinar: salio de comparar el proyecto contra la arquitectura declarada del reto y de un analisis estatico del codigo. Completalo TODO.

### Referencias colgando en el codigo que si esta

Cada una rompe la compilacion:

- `src/main/java/com/example/demo/DemoApplication.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/example/demo/domain/PaymentRepository.java` — `reactor.core.publisher`: El import reactor.core.publisher.Flux pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/example/demo/infrastructure/JpaPaymentRepository.java` — `reactor.core.publisher`: El import reactor.core.publisher.Flux pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/example/demo/infrastructure/JpaPaymentRepository.java` — `reactor.core.scheduler`: El import reactor.core.scheduler.Schedulers pertenece a reactor.core.scheduler, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/example/demo/application/PaymentService.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/example/demo/application/PaymentService.java` — `reactor.core.publisher`: El import reactor.core.publisher.Flux pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/example/demo/infrastructure/PaymentController.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/example/demo/infrastructure/PaymentController.java` — `reactor.core.publisher`: El import reactor.core.publisher.Flux pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/example/demo/infrastructure/JpaPaymentRepository.java` — `PaymentStatus.equals`: Se invoca `equals` sobre `PaymentStatus`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/example/demo/application/PaymentService.java` — `Payment.status`: Se invoca `status` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/example/demo/infrastructure/PaymentController.java` — `Payment.id`: Se invoca `id` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/example/demo/infrastructure/PaymentController.java` — `Payment.userId`: Se invoca `userId` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/example/demo/infrastructure/PaymentController.java` — `Payment.amount`: Se invoca `amount` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/example/demo/infrastructure/PaymentController.java` — `Payment.currency`: Se invoca `currency` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/example/demo/infrastructure/PaymentController.java` — `Payment.description`: Se invoca `description` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/example/demo/infrastructure/PaymentController.java` — `Payment.status`: Se invoca `status` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/example/demo/infrastructure/PaymentController.java` — `Payment.createdAt`: Se invoca `createdAt` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/example/demo/infrastructure/PaymentController.java` — `Payment.updatedAt`: Se invoca `updatedAt` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/example/demo/infrastructure/PaymentController.java` — `UpdateStatusRequest.userId`: Se invoca `userId` sobre `UpdateStatusRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/example/demo/infrastructure/PaymentController.java` — `UpdateStatusRequest.amount`: Se invoca `amount` sobre `UpdateStatusRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/example/demo/infrastructure/PaymentController.java` — `UpdateStatusRequest.currency`: Se invoca `currency` sobre `UpdateStatusRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/example/demo/infrastructure/PaymentController.java` — `UpdateStatusRequest.description`: Se invoca `description` sobre `UpdateStatusRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/example/demo/infrastructure/PaymentController.java` — `UpdateStatusRequest.status`: Se invoca `status` sobre `UpdateStatusRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

## Como saber que terminaste

```bash
mvn clean compile
```

Ese comando corriendo sin errores es la definicion de "listo".

---

```
## Briefing del reto (autoridad)
Este bloque manda sobre los archivos adjuntos. El stack y el rol salen de AQUÍ, no de un topic genérico ni de markdown placeholder.

### Perfil
Chapter Backend, Especialidad Desarrollador, Tecnología Java, Senior

### Brecha de conocimiento
Implementa la contenerización y orquestación de contenedores en proyectos, teniendo en cuenta las diferencias, ventajas y desventajas de cada enfoque. Utiliza al menos dos herramientas de contenerización/orquestación, como Docker, Docker Compose, Docker Swarm, K8s (Kubernetes) u OpenShift.

### Misión / candidato
Candidato con experiencia en desarrollo backend con Java, trabajando en arquitecturas distribuidas.

### Reto
- Tema: Implementa Plataformas de Contenedores enfocados en Docker y Kubernetes
- Seniority: senior-l2
- Tipo: practical
- Título: Implementación de Contenedores y Orquestación en Proyectos Backend
- Tiempo estimado: 40 horas

### Fases (trabajo del HUMANO — PROHIBIDO completarlas)
No implementes estos entregables. Dejalos como hueco pedagógico. El asistente solo materializa el proyecto arrancable para que el participante pueda trabajar.
- Fase 1: Contenedores con Docker — objetivo: Implementar y contenerizar una aplicación backend utilizando Docker. — entregable (NO resolver): Imagen Docker y contenedor ejecutándose con la aplicación Java.
- Fase 2: Orquestación con Docker Compose — objetivo: Configurar y orquestar múltiples contenedores utilizando Docker Compose. — entregable (NO resolver): Configuración de Docker Compose funcionando con múltiples contenedores.
- Fase 3: Orquestación con Kubernetes — objetivo: Implementar y orquestar contenedores utilizando Kubernetes. — entregable (NO resolver): Cluster de Kubernetes con aplicaciones desplegadas y funcionando.
- Fase 4: Comparación y Decisiones — objetivo: Comparar las herramientas de contenerización y orquestación y tomar decisiones basadas en ventajas y desventajas. — entregable (NO resolver): Documento que compara las herramientas y recomienda su uso en diferentes escenarios.

Eres un asistente experto en análisis, corrección y generación de archivos de cualquier tipo:
código fuente, documentación, hojas de cálculo, documentos Word, configuraciones, entre otros.
Voy a enviarte una cadena de texto que contiene uno o más archivos. Cada archivo está delimitado por un marcador con el siguiente formato:
// === ARCHIVO: ruta/del/archivo.extension ===
o también puede aparecer como:
## === ARCHIVO: ruta/del/archivo.extension ===
Lo que sigue al marcador puede ser:

El contenido real del archivo (código, texto, YAML, etc.)
Una descripción en lenguaje natural de lo que debe contener el archivo


TU TAREA
PASO 0 — ¿Esto es un proyecto o una carcasa?
Antes de extraer archivos, leé el Briefing (si está) y diagnosticá el adjunto.

Es CARCASA si ocurre CUALQUIERA de estas:
- No hay manifiesto de dependencias del stack del briefing (manifest.json de VTEX IO / package.json / pom.xml / build.gradle / requirements.txt / go.mod / *.tf / *.csproj, según corresponda)
- Hay un "binario" que en realidad es un comentario ("no puede ser mostrado como texto plano", placeholder .fig/.docx vacío)
- Los markdowns ya completan entregables de fases posteriores ("se implementó fade-in", lista de áreas ya resuelta)

Si es CARCASA:
- MATERIALIZÁ un proyecto que arranca en el stack del briefing (VTEX IO Store Framework, Angular, Terraform, pytest, Nest, etc.). Incluí manifiesto, punto de entrada y capa de interfaz reales.
- NO copies los markdowns de "solución" como si fueran el producto. Son ruido de generación.
- NO resuelvas las fases del briefing (están marcadas PROHIBIDO). Dejá el hueco pedagógico: el flujo existe, las microinteracciones/calidad/infra que el reto pide NO están hechas.
- Después seguí al PASO 5 (ZIP).

Si es un proyecto REAL (manifiesto + código que compila o arranca):
- Seguí PASO 1 en adelante. 🔴 compilación sí. 🟡 pedagógico no.

PASO 1 — Detección y extracción
Identifica todos los archivos presentes en la cadena. Para cada archivo extrae:

Su ruta completa (ej: src/main/java/com/pragma/Service.java)
Su contenido o descripción

PASO 2 — Clasificación por tipo
Clasifica cada archivo en una de estas categorías:
A) Código fuente (Java, Python, TypeScript, JavaScript, Kotlin, etc.)
B) Configuración / documentación (YAML, properties, Markdown, JSON, txt, etc.)
C) Excel (.xlsx, .xls, .csv)
D) Word (.docx, .doc)
E) Otro tipo de archivo binario o especial
PASO 3 — Clasificación de errores en código fuente

Objetivo prioritario: que el proyecto compile. No corrijas flujo de negocio ni lógica funcional.

Antes de modificar cualquier archivo de código fuente, clasifica cada problema encontrado en una de estas dos categorías:
🔴 ERROR DE COMPILACIÓN — corregir siempre
Son errores que impiden que el proyecto arranque, sin valor pedagógico:

Import faltante o incorrecto
Clase, método o variable referenciada que no existe en ningún archivo del proyecto
Error de sintaxis
Anotación con atributos inválidos
Dependencia ausente en pom.xml, package.json, etc.
Archivo referenciado que no existe y debe ser creado con implementación mínima

→ CORREGIR estos errores.
🟡 PROBLEMA FUNCIONAL O DE CALIDAD — preservar siempre
Son problemas que no impiden compilar. Pueden ser intencionales para el aprendizaje:

Clave secreta hardcodeada ("secret", "password123")
API deprecada que funciona pero tiene reemplazo moderno
Lógica de negocio incorrecta o incompleta
Código redundante o de baja legibilidad
Falta de validaciones en flujo de negocio
Patrones de diseño incorrectos pero funcionales
Concurrencia no segura
Configuración funcional pero no óptima

→ PRESERVAR tal cual. No corregir, no mejorar, no comentar.
PASO 4 — Procesamiento según tipo de archivo
Tipo A — Código fuente
Aplica únicamente las correcciones clasificadas como 🔴 ERROR DE COMPILACIÓN.
No alteres ningún elemento clasificado como 🟡 PROBLEMA FUNCIONAL O DE CALIDAD.
Si falta un archivo referenciado, créalo con la implementación mínima necesaria para compilar.
Tipo B — Configuración / documentación
Extrae el contenido tal cual, sin modificaciones salvo errores evidentes de sintaxis
(ej: YAML mal indentado).
Tipo C — Excel (.xlsx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un archivo Excel funcional con:

Fila de encabezados en negrita con color de fondo distintivo
Columnas con ancho ajustado al contenido
Tipos de dato correctos por columna
Validaciones si la descripción lo indica
Hojas nombradas descriptivamente si hay más de una
Filas de ejemplo si no hay datos reales

Tipo D — Word (.docx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un documento Word funcional con:

Estilos de título (Título 1, Título 2) para jerarquía de secciones
Fuente legible (Calibri o equivalente), tamaño 11-12pt para cuerpo
Márgenes estándar
Tabla de contenido si tiene múltiples secciones
Tablas con encabezados en negrita si aplica

Tipo E — Otro
Genera el archivo con el contenido o estructura más apropiada según la descripción.
PASO 5 — Exportación en ZIP
Empaqueta todos los archivos en un único archivo ZIP descargable respetando exactamente
la estructura de rutas indicada por los marcadores.
El ZIP debe incluir:

Archivos de código con únicamente los errores de compilación corregidos
Archivos de configuración y documentación sin cambios
Archivos nuevos creados para resolver dependencias de compilación faltantes
Archivos Excel y Word generados desde descripción

IMPORTANTE: El ZIP debe estar listo para descargar al finalizar. No preguntes si el usuario
quiere generarlo. Simplemente genera el archivo y proporciona el enlace de descarga; No debes desplegar en el chat el resumen de lo que arreglaste al Zip, solo entregalo.

REGLAS IMPORTANTES

No omitas ningún archivo aunque no tenga errores ni modificaciones
Respeta los nombres y rutas exactas indicadas por los marcadores
Si un archivo no tiene marcador claro, infiere el nombre desde su contenido
Si la cadena contiene solo documentación, placeholders o binarios fake, NO la reproduzcas:
aplicá PASO 0 (materializar el proyecto del briefing). Reproducir la carcasa es un fallo.
No agregues texto después del enlace de descarga del ZIP
No preguntes si el usuario quiere el ZIP: simplemente generalo siempre
Si detectas que falta un archivo de configuración necesario para compilar
(pom.xml, package.json, requirements.txt, build.gradle, etc.), créalo e inclúyelo
inferiendo su contenido desde los imports y frameworks detectados en el código
Nunca corrijas problemas 🟡 aunque parezcan obvios o fáciles de mejorar.
El participante que recibirá este proyecto los debe encontrar y resolver él mismo.


INPUT
Aquí está la cadena con los archivos:

// === ARCHIVO: pom.xml ===
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.5.6</version>
        <relativePath/>
    </parent>

    <groupId>com.example</groupId>
    <artifactId>demo</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>demo</name>
    <description>Demo project for Spring Boot with Docker and Kubernetes</description>

    <properties>
        <java.version>21</java.version>
        <docker.image.prefix>docker.io</docker.image.prefix>
        <docker.image.name>${project.artifactId}</docker.image.name>
    </properties>

    <dependencies>
        <!-- Spring Boot Starters -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-webflux</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-docker-compose</artifactId>
            <scope>runtime</scope>
        </dependency>

        <!-- Metrics -->
        <dependency>
            <groupId>io.micrometer</groupId>
            <artifactId>micrometer-registry-prometheus</artifactId>
        </dependency>

        <!-- Database -->
        <dependency>
            <groupId>org.postgresql</groupId>
            <artifactId>postgresql</artifactId>
            <scope>runtime</scope>
        </dependency>

        <!-- Lombok -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <scope>provided</scope>
        </dependency>

        <!-- Test -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.testcontainers</groupId>
            <artifactId>junit-jupiter</artifactId>
            <version>1.20.1</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.testcontainers</groupId>
            <artifactId>postgresql</artifactId>
            <version>1.20.1</version>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <configuration>
                    <source>${java.version}</source>
                    <target>${java.version}</target>
                </configuration>
            </plugin>
            <plugin>
                <groupId>com.google.cloud.tools</groupId>
                <artifactId>jib-maven-plugin</artifactId>
                <version>3.4.3</version>
                <configuration>
                    <to>
                        <image>${docker.image.prefix}/${docker.image.name}:latest</image>
                    </to>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>

// === ARCHIVO: src/main/java/com/example/demo/DemoApplication.java ===
package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public RouterFunction<ServerResponse> healthCheckRoute() {
        return route()
                .GET("/health", request -> ok().body(Mono.just("OK"), String.class))
                .build();
    }
}

// === ARCHIVO: src/main/resources/application.yml ===
server:
  port: 8080
  shutdown: graceful

spring:
  application:
    name: demo
  datasource:
    url: jdbc:postgresql://localhost:5432/demo
    username: demo
    password: demo
    hikari:
      connection-timeout: 20000
      maximum-pool-size: 5
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        format_sql: true
        dialect: org.hibernate.dialect.PostgreSQLDialect
  docker:
    compose:
      lifecycle-management: start-only
      start:
        command: up
      stop:
        command: down
        timeout: 1m

management:
  endpoints:
    web:
      exposure:
        include: health,info,metrics,prometheus
  endpoint:
    health:
      probes:
        enabled: true
      show-details: always
  metrics:
    export:
      prometheus:
        enabled: true
    tags:
      application: ${spring.application.name}

logging:
  level:
    root: INFO
    org.springframework.web: DEBUG
    org.hibernate: ERROR
    com.example.demo: DEBUG

// === ARCHIVO: src/main/java/com/example/demo/domain/Payment.java ===
package com.example.demo.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Entidad de dominio que representa un pago realizado en el sistema.
 * Esta clase encapsula toda la lógica de negocio relacionada con pagos
 * y es utilizada como modelo principal en las operaciones del dominio.
 */
public record Payment(
    @NotNull
    UUID id,

    @NotNull
    @Size(min = 5, max = 100)
    String description,

    @NotNull
    @DecimalMin(value = "0.01")
    BigDecimal amount,

    @NotNull
    LocalDateTime createdAt,

    @NotNull
    PaymentStatus status,

    @NotNull
    UUID userId,

    @NotNull
    String currency
) {
    /**
     * Enumeración que representa los posibles estados de un pago.
     */
    public enum PaymentStatus {
        PENDING,
        COMPLETED,
        FAILED,
        REFUNDED
    }

    /**
     * Constructor alternativo que genera un ID y timestamp automáticamente.
     *
     * @param description Descripción del pago
     * @param amount Cantidad del pago
     * @param userId ID del usuario que realizó el pago
     * @param currency Moneda del pago
     */
    public Payment {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be empty");
        }
        if (userId == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }
        if (currency == null || currency.length() != 3) {
            throw new IllegalArgumentException("Currency must be a 3-letter code");
        }

        // Validación de campos obligatorios
        id = id != null ? id : UUID.randomUUID();
        createdAt = createdAt != null ? createdAt : LocalDateTime.now();
        status = status != null ? status : PaymentStatus.PENDING;
    }

    /**
     * Crea una nueva instancia de Payment con estado COMPLETED.
     *
     * @return Nueva instancia de Payment con estado COMPLETED
     */
    public Payment complete() {
        return new Payment(id, description, amount, createdAt, PaymentStatus.COMPLETED, userId, currency);
    }

    /**
     * Crea una nueva instancia de Payment con estado FAILED.
     *
     * @return Nueva instancia de Payment con estado FAILED
     */
    public Payment fail() {
        return new Payment(id, description, amount, createdAt, PaymentStatus.FAILED, userId, currency);
    }

    /**
     * Valida que el pago cumpla con todas las reglas de negocio.
     *
     * @throws IllegalStateException si el pago no es válido
     */
    public void validate() {
        if (status == PaymentStatus.COMPLETED && amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalStateException("Completed payment must have positive amount");
        }
        if (createdAt.isAfter(LocalDateTime.now())) {
            throw new IllegalStateException("Payment date cannot be in the future");
        }
    }
}

// === ARCHIVO: src/main/java/com/example/demo/domain/PaymentRepository.java ===
package com.example.demo.domain;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.UUID;

/**
 * Interfaz del puerto de dominio para operaciones de persistencia de pagos.
 * Define los contratos que la capa de infraestructura debe implementar
 * para interactuar con el almacén de datos.
 */
public interface PaymentRepository {
    /**
     * Guarda un pago en el almacén de datos.
     *
     * @param payment El pago a guardar
     * @return Mono con el pago guardado
     */
    Mono<Payment> save(Payment payment);

    /**
     * Obtiene un pago por su ID.
     *
     * @param id ID del pago
     * @return Mono con el pago encontrado o vacío si no existe
     */
    Mono<Payment> findById(UUID id);

    /**
     * Obtiene todos los pagos realizados por un usuario.
     *
     * @param userId ID del usuario
     * @return Flux con los pagos del usuario
     */
    Flux<Payment> findByUserId(UUID userId);

    /**
     * Obtiene todos los pagos con un estado específico.
     *
     * @param status Estado de los pagos a buscar
     * @return Flux con los pagos que coinciden con el estado
     */
    Flux<Payment> findByStatus(Payment.PaymentStatus status);

    /**
     * Elimina un pago por su ID.
     *
     * @param id ID del pago a eliminar
     * @return Mono con el resultado de la operación
     */
    Mono<Void> deleteById(UUID id);

    /**
     * Actualiza el estado de un pago.
     *
     * @param id ID del pago a actualizar
     * @param status Nuevo estado del pago
     * @return Mono con el pago actualizado
     */
    Mono<Payment> updateStatus(UUID id, Payment.PaymentStatus status);
}

// === ARCHIVO: src/main/java/com/example/demo/infrastructure/JpaPaymentRepository.java ===
package com.example.demo.infrastructure;


import com.example.demo.domain.PaymentStatus;
import com.example.demo.domain.Payment;
import com.example.demo.domain.PaymentRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import java.util.UUID;

/**
 * Adaptador de infraestructura que implementa el puerto PaymentRepository
 * utilizando Spring Data JPA para persistencia reactiva.
 */
@Repository
public interface JpaPaymentRepository extends PaymentRepository, JpaRepository<PaymentEntity, UUID> {

    /**
     * Implementación del método save del puerto PaymentRepository.
     * Realiza la conversión entre el dominio y la entidad JPA.
     *
     * @param payment Pago de dominio a guardar
     * @return Mono con el pago guardado
     */
    @Override
    default Mono<Payment> save(Payment payment) {
        return Mono.fromCallable(() -> {
            PaymentEntity entity = PaymentEntity.fromDomain(payment);
            PaymentEntity savedEntity = save(entity);
            return savedEntity.toDomain();
        }).subscribeOn(Schedulers.boundedElastic());
    }

    /**
     * Implementación del método findById del puerto PaymentRepository.
     *
     * @param id ID del pago a buscar
     * @return Mono con el pago encontrado o vacío
     */
    @Override
    default Mono<Payment> findById(UUID id) {
        return Mono.fromCallable(() -> findById(id).orElse(null))
                .subscribeOn(Schedulers.boundedElastic())
                .filter(entity -> entity != null)
                .map(PaymentEntity::toDomain);
    }

    /**
     * Implementación del método findByUserId del puerto PaymentRepository.
     *
     * @param userId ID del usuario
     * @return Flux con los pagos del usuario
     */
    @Override
    default Flux<Payment> findByUserId(UUID userId) {
        return Flux.defer(() -> Flux.fromIterable(findByUserIdInternal(userId)))
                .subscribeOn(Schedulers.boundedElastic())
                .map(PaymentEntity::toDomain);
    }

    /**
     * Implementación del método findByStatus del puerto PaymentRepository.
     *
     * @param status Estado de los pagos a buscar
     * @return Flux con los pagos que coinciden con el estado
     */
    @Override
    default Flux<Payment> findByStatus(Payment.PaymentStatus status) {
        return Flux.defer(() -> Flux.fromIterable(findByStatusInternal(status)))
                .subscribeOn(Schedulers.boundedElastic())
                .map(PaymentEntity::toDomain);
    }

    /**
     * Implementación del método deleteById del puerto PaymentRepository.
     *
     * @param id ID del pago a eliminar
     * @return Mono con el resultado de la operación
     */
    @Override
    default Mono<Void> deleteById(UUID id) {
        return Mono.fromRunnable(() -> deleteById(id))
                .subscribeOn(Schedulers.boundedElastic())
                .then();
    }

    /**
     * Implementación del método updateStatus del puerto PaymentRepository.
     *
     * @param id ID del pago a actualizar
     * @param status Nuevo estado del pago
     * @return Mono con el pago actualizado
     */
    @Override
    default Mono<Payment> updateStatus(UUID id, Payment.PaymentStatus status) {
        return Mono.fromCallable(() -> {
            PaymentEntity entity = findById(id).orElseThrow(() ->
                new IllegalArgumentException("Payment not found with id: " + id));
            entity.setStatus(status);
            PaymentEntity updatedEntity = save(entity);
            return updatedEntity.toDomain();
        }).subscribeOn(Schedulers.boundedElastic());
    }

    /**
     * Método interno para buscar pagos por usuario (no reactivo).
     *
     * @param userId ID del usuario
     * @return Lista de entidades JPA
     */
    default java.util.List<PaymentEntity> findByUserIdInternal(UUID userId) {
        return findAll().stream()
                .filter(entity -> userId.equals(entity.getUserId()))
                .toList();
    }

    /**
     * Método interno para buscar pagos por estado (no reactivo).
     *
     * @param status Estado de los pagos
     * @return Lista de entidades JPA
     */
    default java.util.List<PaymentEntity> findByStatusInternal(Payment.PaymentStatus status) {
        return findAll().stream()
                .filter(entity -> status.equals(entity.getStatus()))
                .toList();
    }
}

// === ARCHIVO: src/main/java/com/example/demo/application/PaymentService.java ===
package com.example.demo.application;

import com.example.demo.domain.Payment;
import com.example.demo.domain.PaymentRepository;
import com.example.demo.domain.Payment.PaymentStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class PaymentService {

    private static final Logger log = LoggerFactory.getLogger(PaymentService.class);
    private static final BigDecimal MINIMUM_AMOUNT = new BigDecimal("0.01");
    private static final BigDecimal MAXIMUM_AMOUNT = new BigDecimal("999999.99");

    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Mono<Payment> createPayment(UUID userId, BigDecimal amount, String currency, String description) {
        log.info("Creating payment for user {} with amount {} {}", userId, amount, currency);
        
        if (amount == null || amount.compareTo(MINIMUM_AMOUNT) < 0) {
            log.warn("Invalid payment amount: {}", amount);
            return Mono.error(new IllegalArgumentException("Amount must be greater than " + MINIMUM_AMOUNT));
        }
        
        if (amount.compareTo(MAXIMUM_AMOUNT) > 0) {
            log.warn("Payment amount exceeds maximum: {} > {}", amount, MAXIMUM_AMOUNT);
            return Mono.error(new IllegalArgumentException("Amount must not exceed " + MAXIMUM_AMOUNT));
        }
        
        if (currency == null || currency.isBlank()) {
            return Mono.error(new IllegalArgumentException("Currency is required"));
        }
        
        Payment payment = new Payment(
            UUID.randomUUID(),
            userId,
            amount,
            currency,
            description,
            PaymentStatus.PENDING,
            java.time.Instant.now(),
            null
        );
        
        payment.validate();
        
        return paymentRepository.save(payment)
            .doOnSuccess(saved -> log.info("Payment created successfully: {}", saved.id()))
            .doOnError(error -> log.error("Failed to create payment: {}", error.getMessage()));
    }

    public Mono<Payment> completePayment(UUID paymentId) {
        log.info("Completing payment: {}", paymentId);
        
        return paymentRepository.findById(paymentId)
            .flatMap(payment -> {
                if (payment.status() != PaymentStatus.PENDING) {
                    log.warn("Cannot complete payment {} with status: {}", paymentId, payment.status());
                    return Mono.error(new IllegalStateException("Payment is not in PENDING status"));
                }
                Payment completed = payment.complete();
                return paymentRepository.save(completed);
            })
            .doOnSuccess(saved -> log.info("Payment completed successfully: {}", saved.id()))
            .doOnError(error -> log.error("Failed to complete payment: {}", error.getMessage()));
    }

    public Mono<Payment> failPayment(UUID paymentId, String reason) {
        log.info("Failing payment: {} with reason: {}", paymentId, reason);
        
        return paymentRepository.findById(paymentId)
            .flatMap(payment -> {
                if (payment.status() != PaymentStatus.PENDING) {
                    log.warn("Cannot fail payment {} with status: {}", paymentId, payment.status());
                    return Mono.error(new IllegalStateException("Payment is not in PENDING status"));
                }
                Payment failed = payment.fail();
                return paymentRepository.save(failed);
            })
            .doOnSuccess(saved -> log.info("Payment failed: {}", saved.id()))
            .doOnError(error -> log.error("Failed to fail payment: {}", error.getMessage()));
    }

    public Mono<Payment> getPaymentById(UUID paymentId) {
        log.debug("Retrieving payment: {}", paymentId);
        return paymentRepository.findById(paymentId)
            .switchIfEmpty(Mono.error(new IllegalArgumentException("Payment not found: " + paymentId)));
    }

    public Flux<Payment> getPaymentsByUser(UUID userId) {
        log.debug("Retrieving payments for user: {}", userId);
        return paymentRepository.findByUserId(userId)
            .doOnSubscribe(s -> log.debug("Started fetching payments for user: {}", userId))
            .doOnComplete(() -> log.debug("Completed fetching payments for user: {}", userId));
    }

    public Flux<Payment> getPaymentsByStatus(PaymentStatus status) {
        log.debug("Retrieving payments with status: {}", status);
        return paymentRepository.findByStatus(status)
            .doOnSubscribe(s -> log.debug("Started fetching payments with status: {}", status))
            .doOnComplete(() -> log.debug("Completed fetching payments with status: {}", status));
    }

    public Mono<Void> deletePayment(UUID paymentId) {
        log.info("Deleting payment: {}", paymentId);
        return paymentRepository.findById(paymentId)
            .flatMap(payment -> {
                if (payment.status() == PaymentStatus.COMPLETED) {
                    log.warn("Cannot delete completed payment: {}", paymentId);
                    return Mono.error(new IllegalStateException("Cannot delete a completed payment"));
                }
                return paymentRepository.deleteById(paymentId);
            })
            .doOnSuccess(v -> log.info("Payment deleted: {}", paymentId))
            .doOnError(error -> log.error("Failed to delete payment: {}", error.getMessage()));
    }

    public Mono<Payment> updatePaymentStatus(UUID paymentId, PaymentStatus newStatus) {
        log.info("Updating payment {} to status: {}", paymentId, newStatus);
        return paymentRepository.updateStatus(paymentId, newStatus)
            .doOnSuccess(saved -> log.info("Payment status updated: {} -> {}", saved.id(), saved.status()))
            .doOnError(error -> log.error("Failed to update payment status: {}", error.getMessage()));
    }
}

// === ARCHIVO: src/main/java/com/example/demo/infrastructure/PaymentController.java ===
package com.example.demo.infrastructure;

import com.example.demo.application.PaymentService;
import com.example.demo.domain.Payment;
import com.example.demo.domain.Payment.PaymentStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private static final Logger log = LoggerFactory.getLogger(PaymentController.class);

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public record CreatePaymentRequest(
        @NotNull(message = "User ID is required") UUID userId,
        @NotNull(message = "Amount is required") @DecimalMin(value = "0.01", message = "Amount must be at least 0.01") BigDecimal amount,
        @NotBlank(message = "Currency is required") @Size(min = 3, max = 3, message = "Currency must be 3 characters") String currency,
        @Size(max = 500, message = "Description cannot exceed 500 characters") String description
    ) {}

    public record UpdateStatusRequest(
        @NotNull(message = "Status is required") PaymentStatus status
    ) {}

    public record PaymentResponse(
        UUID id,
        UUID userId,
        BigDecimal amount,
        String currency,
        String description,
        PaymentStatus status,
        Instant createdAt,
        Instant updatedAt
    ) {
        public static PaymentResponse fromDomain(Payment payment) {
            return new PaymentResponse(
                payment.id(),
                payment.userId(),
                payment.amount(),
                payment.currency(),
                payment.description(),
                payment.status(),
                payment.createdAt(),
                payment.updatedAt()
            );
        }
    }

    public record ErrorResponse(
        String error,
        String message,
        Instant timestamp,
        Map<String, String> details
    ) {
        public static ErrorResponse of(String error, String message) {
            return new ErrorResponse(error, message, Instant.now(), null);
        }

        public static ErrorResponse of(String error, String message, Map<String, String> details) {
            return new ErrorResponse(error, message, Instant.now(), details);
        }
    }

    @PostMapping
    public Mono<ResponseEntity<PaymentResponse>> createPayment(@Valid @RequestBody CreatePaymentRequest request) {
        log.info("POST /api/v1/payments - Creating payment for user: {}", request.userId());
        
        return paymentService.createPayment(
            request.userId(),
            request.amount(),
            request.currency(),
            request.description()
        )
        .map(payment -> ResponseEntity
            .status(HttpStatus.CREATED)
            .contentType(MediaType.APPLICATION_JSON)
            .body(PaymentResponse.fromDomain(payment)))
        .doOnSuccess(response -> log.info("Payment created with ID: {}", response.body().id()))
        .doOnError(error -> log.error("Error creating payment: {}", error.getMessage()));
    }

    @GetMapping("/{paymentId}")
    public Mono<ResponseEntity<PaymentResponse>> getPayment(@PathVariable UUID paymentId) {
        log.info("GET /api/v1/payments/{}", paymentId);
        
        return paymentService.getPaymentById(paymentId)
            .map(payment -> ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(PaymentResponse.fromDomain(payment)))
            .doOnSuccess(response -> log.debug("Payment retrieved: {}", paymentId))
            .doOnError(error -> log.error("Error retrieving payment: {}", error.getMessage()));
    }

    @GetMapping
    public Mono<ResponseEntity<Flux<PaymentResponse>>> getPaymentsByUser(
            @RequestParam(required = false) UUID userId,
            @RequestParam(required = false) PaymentStatus status) {
        
        log.info("GET /api/v1/payments - userId: {}, status: {}", userId, status);
        
        Flux<PaymentResponse> paymentsFlux;
        
        if (userId != null) {
            paymentsFlux = paymentService.getPaymentsByUser(userId)
                .map(PaymentResponse::fromDomain);
        } else if (status != null) {
            paymentsFlux = paymentService.getPaymentsByStatus(status)
                .map(PaymentResponse::fromDomain);
        } else {
            return Mono.just(ResponseEntity
                .badRequest()
                .body(Flux.just(PaymentResponse.fromDomain(
                    new Payment(null, null, null, null, null, null, null, null)
                ))));
        }
        
        return Mono.just(ResponseEntity
            .ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(paymentsFlux))
            .doOnSuccess(response -> log.debug("Payments retrieved successfully"))
            .doOnError(error -> log.error("Error retrieving payments: {}", error.getMessage()));
    }

    @PostMapping("/{paymentId}/complete")
    public Mono<ResponseEntity<PaymentResponse>> completePayment(@PathVariable UUID paymentId) {
        log.info("POST /api/v1/payments/{}/complete", paymentId);
        
        return paymentService.completePayment(paymentId)
            .map(payment -> ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(PaymentResponse.fromDomain(payment)))
            .doOnSuccess(response -> log.info("Payment completed: {}", paymentId))
            .doOnError(error -> log.error("Error completing payment: {}", error.getMessage()));
    }

    @PostMapping("/{paymentId}/fail")
    public Mono<ResponseEntity<PaymentResponse>> failPayment(
            @PathVariable UUID paymentId,
            @RequestBody(required = false) Map<String, String> body) {
        
        String reason = body != null ? body.get("reason") : "Unknown error";
        log.info("POST /api/v1/payments/{}/fail - reason: {}", paymentId, reason);
        
        return paymentService.failPayment(paymentId, reason)
            .map(payment -> ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(PaymentResponse.fromDomain(payment)))
            .doOnSuccess(response -> log.info("Payment failed: {}", paymentId))
            .doOnError(error -> log.error("Error failing payment: {}", error.getMessage()));
    }

    @PatchMapping("/{paymentId}/status")
    public Mono<ResponseEntity<PaymentResponse>> updatePaymentStatus(
            @PathVariable UUID paymentId,
            @Valid @RequestBody UpdateStatusRequest request) {
        
        log.info("PATCH /api/v1/payments/{}/status - new status: {}", paymentId, request.status());
        
        return paymentService.updatePaymentStatus(paymentId, request.status())
            .map(payment -> ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(PaymentResponse.fromDomain(payment)))
            .doOnSuccess(response -> log.info("Payment status updated: {}", paymentId))
            .doOnError(error -> log.error("Error updating payment status: {}", error.getMessage()));
    }

    @DeleteMapping("/{paymentId}")
    public Mono<ResponseEntity<Void>> deletePayment(@PathVariable UUID paymentId) {
        log.info("DELETE /api/v1/payments/{}", paymentId);
        
        return paymentService.deletePayment(paymentId)
            .thenReturn(ResponseEntity.noContent().build())
            .doOnSuccess(response -> log.info("Payment deleted: {}", paymentId))
            .doOnError(error -> log.error("Error deleting payment: {}", error.getMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public Mono<ResponseEntity<ErrorResponse>> handleIllegalArgument(IllegalArgumentException ex) {
        log.warn("Bad request: {}", ex.getMessage());
        return Mono.just(ResponseEntity
            .badRequest()
            .body(ErrorResponse.of("BAD_REQUEST", ex.getMessage())));
    }

    @ExceptionHandler(IllegalStateException.class)
    public Mono<ResponseEntity<ErrorResponse>> handleIllegalState(IllegalStateException ex) {
        log.warn("Invalid state: {}", ex.getMessage());
        return Mono.just(ResponseEntity
            .status(HttpStatus.CONFLICT)
            .body(ErrorResponse.of("CONFLICT", ex.getMessage())));
    }

    @ExceptionHandler(Exception.class)
    public Mono<ResponseEntity<ErrorResponse>> handleGenericException(Exception ex) {
        log.error("Unexpected error: {}", ex.getMessage(), ex);
        return Mono.just(ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(ErrorResponse.of("INTERNAL_ERROR", "An unexpected error occurred")));
    }
}

// === ARCHIVO: Dockerfile ===
# Stage 1: Build stage
FROM eclipse-temurin:21-jdk-alpine AS build
WORKDIR /app

# Copy pom文件和源代码
COPY pom.xml .
COPY src ./src

# Download dependencies and build the application
RUN apk add --no-cache maven && \
    mvn clean package -DskipTests

# Stage 2: Runtime stage
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Create non-root user for security
RUN addgroup -S appgroup && adduser -S appuser -G appgroup

# Copy the built artifact from build stage
COPY --from=build /app/target/*.jar app.jar

# Set ownership
RUN chown -R appuser:appgroup /app

# Switch to non-root user
USER appuser

# Expose port
EXPOSE 8080

# Health check
HEALTHCHECK --interval=30s --timeout=3s --start-period=60s --retries=3 \
    CMD wget --no-verbose --tries=1 --spider http://localhost:8080/actuator/health || exit 1

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]

// === ARCHIVO: .dockerignore ===
# Maven
target/
pom.xml.tag
pom.xml.releaseBackup
pom.xml.versionsBackup
pom.xml.next
release.properties
dependency-reduced-pom.xml
buildNumber.properties
.mvn/timing.properties
.mvn/wrapper/maven-wrapper.jar

# IDE
.idea/
*.iml
*.ipr
*.iws
.project
.classpath
.settings/
.vscode/
*.swp
*.swo
*~

# OS
.DS_Store
Thumbs.db

# Logs
*.log
logs/

# Test
src/test/
*Test.java
*Tests.java

# Kubernetes (se incluyen en otros archivos)
k8s/
scripts/
docs/

# Documentation
README.md
docs/

// === ARCHIVO: docker-compose.yml ===
version: '3.8'

services:
  app:
    build:
      context: .
      dockerfile: Dockerfile
    image: demo-app:latest
    container_name: demo-app
    ports:
      - "8080:8080"
    environment:
      - SPRING_PROFILES_ACTIVE=prod
      - SPRING_DATASOURCE_URL=jdbc:postgresql://postgres:5432/demo
      - SPRING_DATASOURCE_USERNAME=demo
      - SPRING_DATASOURCE_PASSWORD=demo_password
      - SPRING_JPA_HIBERNATE_DDL_AUTO=update
      - SPRING_JPA_SHOW_SQL=false
      - JAVA_OPTS=-Xmx512m -Xms256m
    depends_on:
      postgres:
        condition: service_healthy
    networks:
      - demo-network
    healthcheck:
      test: ["CMD", "wget", "--no-verbose", "--tries=1", "--spider", "http://localhost:8080/actuator/health"]
      interval: 30s
      timeout: 10s
      retries: 5
      start_period: 60s
    restart: unless-stopped

  postgres:
    image: postgres:17-alpine
    container_name: demo-postgres
    environment:
      - POSTGRES_DB=demo
      - POSTGRES_USER=demo
      - POSTGRES_PASSWORD=demo_password
    ports:
      - "5432:5432"
    volumes:
      - postgres-data:/var/lib/postgresql/data
    networks:
      - demo-network
    healthcheck:
      test: ["CMD-SHELL", "pg_isready -U demo -d demo"]
      interval: 10s
      timeout: 5s
      retries: 5
      start_period: 30s
    restart: unless-stopped

networks:
  demo-network:
    driver: bridge

volumes:
  postgres-data:
    driver: local

// === ARCHIVO: README.md ===
# Demo Spring Boot - Docker y Kubernetes

Proyecto base para demostrar contenerización y orquestación de aplicaciones Java con Spring Boot.

## Requisitos Previos

- Java 21
- Maven 3.9+
- Docker 24+
- Docker Compose 2.24+
- kubectl (para Kubernetes)
- Minikube o Kind (para entorno local de Kubernetes)

## Construcción del Proyecto

```bash
mvn clean package -DskipTests
```

## Ejecución Local (sin Docker)

```bash
mvn spring-boot:run
```

La aplicación estará disponible en `http://localhost:8080`.

## Contenedores con Docker

### Construir Imagen

```bash
docker build -t demo:latest .
```

O usando Jib (sin Docker daemon):

```bash
mvn jib:build
```

### Ejecutar Contenedor

```bash
docker run -p 8080:8080 demo:latest
```

## Orquestación con Docker Compose

### Iniciar Servicios

```bash
docker-compose up --build
```

### Verificar Estado

```bash
docker-compose ps
```

### Detener Servicios

```bash
docker-compose down
```

La aplicación estará disponible en `http://localhost:8080` y PostgreSQL en `localhost:5432`.

## Orquestación con Kubernetes

### Requisitos

- Kubernetes cluster instalado (Minikube, Kind, o cluster remoto)
- kubectl configurado

### Desplegar en Kubernetes

```bash
kubectl apply -f k8s/
```

### Verificar Despliegue

```bash
kubectl get pods
kubectl get services
```

### Ver Logs

```bash
kubectl logs -l app=demo
```

### Escalar Aplicación

```bash
kubectl scale deployment demo --replicas=3
```

### Eliminar Despliegue

```bash
kubectl delete -f k8s/
```

## Endpoints de Salud

- Health: `GET /actuator/health`
- Prometheus: `GET /actuator/prometheus`

## Estructura del Proyecto

```
demo/
├── src/main/java/com/example/demo/
│   ├── domain/          # Entidades y puertos
│   ├── application/     # Casos de uso
│   └── infrastructure/  # Adaptadores y controladores
├── src/main/resources/
│   └── application.yml
├── k8s/
│   ├── deployment.yaml
│   └── service.yaml
├── Dockerfile
├── docker-compose.yml
└── pom.xml
```

## Variables de Entorno

| Variable | Descripción | Valor por defecto |
|----------|-------------|-------------------|
| SPRING_DATASOURCE_URL | URL de PostgreSQL | jdbc:postgresql://localhost:5432/demo |
| SPRING_DATASOURCE_USERNAME | Usuario de BD | demo |
| SPRING_DATASOURCE_PASSWORD | Contraseña de BD | demo |
| SERVER_PORT | Puerto HTTP | 8080 |

// === ARCHIVO: k8s/deployment.yaml ===
apiVersion: apps/v1
kind: Deployment
metadata:
  name: demo
  labels:
    app: demo
spec:
  replicas: 1
  selector:
    matchLabels:
      app: demo
  template:
    metadata:
      labels:
        app: demo
    spec:
      containers:
        - name: demo
          image: demo:latest
          imagePullPolicy: IfNotPresent
          ports:
            - containerPort: 8080
          env:
            - name: SPRING_DATASOURCE_URL
              value: "jdbc:postgresql://postgres:5432/demo"
            - name: SPRING_DATASOURCE_USERNAME
              value: "demo"
            - name: SPRING_DATASOURCE_PASSWORD
              value: "demo"
          livenessProbe:
            httpGet:
              path: /actuator/health/liveness
              port: 8080
            initialDelaySeconds: 60
            periodSeconds: 10
          readinessProbe:
            httpGet:
              path: /actuator/health/readiness
              port: 8080
            initialDelaySeconds: 30
            periodSeconds: 5

// === ARCHIVO: k8s/service.yaml ===
apiVersion: v1
kind: Service
metadata:
  name: demo
  labels:
    app: demo
spec:
  type: ClusterIP
  selector:
    app: demo
  ports:
    - protocol: TCP
      port: 80
      targetPort: 8080
      name: http

// === ARCHIVO: scripts/healthcheck.sh ===
#!/bin/bash
set -e

HEALTH_ENDPOINT="${HEALTH_ENDPOINT:-http://localhost:8080/actuator/health}"
METRICS_ENDPOINT="${METRICS_ENDPOINT:-http://localhost:8080/actuator/prometheus}"
MAX_RETRIES="${MAX_RETRIES:-30}"
RETRY_INTERVAL="${RETRY_INTERVAL:-2}"

echo "=== Health Check Script ==="
echo "Target: $HEALTH_ENDPOINT"
echo "Max retries: $MAX_RETRIES"
echo "Retry interval: $RETRY_INTERVAL seconds"
echo ""

check_health() {
    local response
    response=$(curl -sf "$HEALTH_ENDPOINT" 2>/dev/null || echo "")
    
    if [ -z "$response" ]; then
        return 1
    fi
    
    local status
    status=$(echo "$response" | grep -o '"status":"[^"]*"' | cut -d'"' -f4 || echo "UNKNOWN")
    
    if [ "$status" = "UP" ]; then
        echo "✓ Application is UP"
        echo "Response: $response"
        return 0
    else
        echo "✗ Application status: $status"
        echo "Response: $response"
        return 1
    fi
}

check_readiness() {
    local response
    response=$(curl -sf "${HEALTH_ENDPOINT}/ready" 2>/dev/null || echo "")
    
    if [ -z "$response" ]; then
        return 1
    fi
    
    local status
    status=$(echo "$response" | grep -o '"status":"[^"]*"' | cut -d'"' -f4 || echo "UNKNOWN")
    
    if [ "$status" = "UP" ]; then
        echo "✓ Application is READY"
        return 0
    else
        echo "✗ Application not ready: $status"
        return 1
    fi
}

check_liveness() {
    local response
    response=$(curl -sf "${HEALTH_ENDPOINT}/live" 2>/dev/null || echo "")
    
    if [ -z "$response" ]; then
        return 1
    fi
    
    local status
    status=$(echo "$response" | grep -o '"status":"[^"]*"' | cut -d'"' -f4 || echo "UNKNOWN")
    
    if [ "$status" = "UP" ]; then
        echo "✓ Application is ALIVE"
        return 0
    else
        echo "✗ Application not alive: $status"
        return 1
    fi
}

echo "Starting health check..."
echo ""

retry_count=0
while [ $retry_count -lt $MAX_RETRIES ]; do
    if check_health; then
        echo ""
        echo "=== Additional Checks ==="
        check_liveness || true
        check_readiness || true
        
        if curl -sf "$METRICS_ENDPOINT" >/dev/null 2>&1; then
            echo "✓ Metrics endpoint available"
        else
            echo "⚠ Metrics endpoint not available"
        fi
        
        echo ""
        echo "=== Health Check Passed ==="
        exit 0
    fi
    
    retry_count=$((retry_count + 1))
    echo "Attempt $retry_count/$MAX_RETRIES - Waiting ${RETRY_INTERVAL}s..."
    sleep $RETRY_INTERVAL
done

echo ""
echo "=== Health Check Failed ==="
echo "Application failed to become healthy after $MAX_RETRIES attempts"
exit 1

// === ARCHIVO: docs/comparativa-herramientas.md ===
# Comparativa de Herramientas de Contenedores y Orquestación

## 1. Docker

### Descripción
Docker es una plataforma de contenedorización que permite crear, desplegar y ejecutar aplicaciones en contenedores aislados. Un contenedor es una unidad ligera que incluye todo lo necesario para ejecutar el software: código, runtime, herramientas del sistema, librerías y configuraciones.

### Ventajas
- **Portabilidad**: Los contenedores funcionan de manera idéntica en cualquier entorno que tenga Docker instalado.
- **Ligereza**: Comparten el kernel del sistema operativo, consumiendo menos recursos que las máquinas virtuales.
- **Aislamiento**: Cada contenedor tiene su propio filesystem, procesos y red, garantizando consistencia.
- **Eficiencia**: El tiempo de inicio es de milisegundos, frente a los minutos de las VMs.
- **Reproducibilidad**: El Dockerfile garantiza que el entorno sea idéntico en todas las despliegue.

### Desventajas
- **Gestión manual**: En entornos con múltiples contenedores, la gestión manual se vuelve compleja.
- **Sin orquestación nativa**: No proporciona mecanismos nativos para balanceo de carga, escalado o recuperación ante fallos.
- **Almacenamiento efímero**: Los datos se pierden al destruir el contenedor (requiere volúmenes externos).
- **Limitaciones en producción**: Necesita herramientas adicionales para entornos de producción complejos.

### Casos de uso recomendados
- Desarrollo local y pruebas
- Microservicios simples con pocos contenedores
- CI/CD pipelines
- Prototipos y demos

---

## 2. Docker Compose

### Descripción
Docker Compose es una herramienta para definir y ejecutar aplicaciones multi-contenedor. Mediante un archivo YAML (`docker-compose.yml`), se configuran todos los servicios, redes y volúmenes de la aplicación.

### Ventajas
- **Definición declarativa**: Un solo archivo define toda la aplicación con sus dependencias.
- **Orquestación básica**: Gestiona múltiples contenedores, redes y volúmenes de forma coordinada.
- **Simplicidad**: Comandos intuitivos (`docker-compose up`, `down`, `logs`).
- **Dependencias gestionadas**: Asegura que los servicios arranquen en el orden correcto.
- **Variables de entorno**: Soporta configuración dinámica mediante archivos `.env`.
- **Desarrollo local**: Ideal para entornos de desarrollo con múltiples servicios.

### Desventajas
- **Sin escalado automático**: No puede escalar contenedores automáticamente según carga.
- **Sin balanceo de carga nativo**: Requiere configuraciones adicionales para distribuir tráfico.
- **Single-node**: Solo funciona en una máquina; no gestiona clusters distribuidos.
- **Limitada recuperación**: No tiene mecanismos automáticos de auto-recuperación.
- **No recomendado para producción**: Carece de características críticas para entornos de alta disponibilidad.

### Casos de uso recomendados
- Entornos de desarrollo local
- Pruebas de integración
- Staging pre-producción
- Proyectos pequeños con múltiples servicios
- Demostraciones y prototipos

---

## 3. Docker Swarm

### Descripción
Docker Swarm es la solución de orquestación nativa de Docker. Convierte un grupo de Docker Engines en un cluster unificado, proporcionando capacidades de orquestación, escalado y alta disponibilidad.

### Ventajas
- **Integración nativa**: No necesita herramientas adicionales; forma parte del ecosistema Docker.
- **Simplicidad de adopción**: Curva de aprendizaje menor que Kubernetes.
- **Orquestación automática**: Gestiona contenedores, redes y volúmenes en el cluster.
- **Escalado**: Soporta escalado horizontal de servicios.
- **Service Discovery**: DNS interno para descubrimiento de servicios.
- **Balanceo de carga**: Balanceador de carga integrado.
- **Rolling Updates**: Actualizaciones sin tiempo de inactividad.

### Desventajas
- **Ecosistema limitado**: Menos herramientas y extensiones que Kubernetes.
- **Funcionalidades reducidas**: Carece de características avanzadas como políticas de red complejas.
- **Comunidad menor**: Menor adopción y soporte comparado con Kubernetes.
- **Vendor lock-in**:绑定 específica a Docker; migración a otros orquestadores difícil.
- **Escalabilidad limitada**: No escala tan bien como Kubernetes para miles de nodos.
- **Menos personalización**: Opciones de configuración más limitadas.

### Casos de uso recomendados
- Pequeñas empresas que necesitan orquestación sin complejidad
- Equipos con experiencia Docker que buscan escalar
- Entornos donde Kubernetes es overkill
- Migración gradual desde Docker Compose

---

## 4. Kubernetes (K8s)

### Descripción
Kubernetes es un sistema de orquestación de contenedores de código abierto desarrollado por Google. Automatiza el despliegue, escalado y gestión de aplicaciones contenerizadas, soportando múltiples contenedores y nodos en clusters distribuidos.

### Ventajas
- **Ecosistema maduro**: Amplia comunidad, herramientas y extensiones disponibles.
- **Escalabilidad masiva**: Diseñado para gestionar miles de nodos y contenedores.
- **Alta disponibilidad**: Mecanismos nativos de auto-recuperación, balanceo y tolerancia a fallos.
- **Portabilidad**: Funciona en cualquier proveedor cloud (GCP, AWS, Azure) y on-premise.
- **Declarativo**: Define el estado deseado y Kubernetes lo mantiene automáticamente.
- **Servicios avanzados**: Ingress, Service Mesh, Policies de red, RBAC.
- **CI/CD integrado**: Compatible con múltiples herramientas de despliegue continuo.
- **Auto-scaling**: Horizontal Pod Autoscaler (HPA), Vertical Pod Autoscaler (VPA).
- **Gestión de secretos**: Almacenamiento seguro de credenciales y configuraciones sensibles.

### Desventajas
- **Curva de aprendizaje pronunciada**: Requiere conocimiento de múltiples conceptos (Pods, Services, Deployments, etc.).
- **Complejidad operativa**: Necesita personal capacitado y herramientas de gestión.
- **Recursos**: Mayor consumo de recursos para el cluster de control.
- **Configuración verbose**: Archivos YAML extensos para configuraciones simples.
- **Overhead para proyectos pequeños**: Puede ser overkill para aplicaciones simples.
- **Migración costosa**: Migrar desde Docker Swarm requiere reescribir configuraciones.

### Casos de uso recomendados
- Aplicaciones de producción a gran escala
- Empresas con múltiples microservicios
- Entornos multi-cloud o hybrid-cloud
- Necesidades de alta disponibilidad y fault tolerance
- Equipos con recursos para gestionar la complejidad

---

## 5. Tabla Comparativa

| Característica | Docker | Docker Compose | Docker Swarm | Kubernetes |
|----------------|--------|----------------|--------------|------------|
| **Orquestación** | No | Básica | Sí | Sí (avanzada) |
| **Escalado automático** | No | No | Limitado | Sí |
| **Alta disponibilidad** | No | No | Sí | Sí |
| **Multi-nodo** | No | No | Sí | Sí |
| **Curva de aprendizaje** | Baja | Baja | Media | Alta |
| **Complejidad** | Mínima | Baja | Media | Alta |
| **Portabilidad** | Alta | Alta | Media | Muy alta |
| **Ecosistema** | Grande | Grande | Medio | Muy grande |
| **Producción** | No solo | No recomendado | Sí (limitado) | Sí (recomendado) |

---

## 6. Recomendaciones por Escenario

### Desarrollo local个人的
- **Docker Compose**: Ideal para desarrollar con múltiples servicios (app, DB, cache, etc.).

### Proyectos pequeños o startups
- **Docker Swarm**: Si ya conoces Docker y necesitas escalar sin la complejidad de K8s.
- **Kubernetes**: Si el crecimiento esperado es grande y hay recursos para aprender.

### Empresas medianas y grandes
- **Kubernetes**: Estándar de la industria para producción. Provee las características necesarias para sistemas críticos.

### Cloud-native multi-cloud
- **Kubernetes**: La única opción que garantiza portabilidad entre proveedores cloud.

### CI/CD y pipelines
- **Docker**: Suficiente para construir y ejecutar tests en contenedores.

### Microservicios con alta disponibilidad
- **Kubernetes**: Con Service Mesh (Istio, Linkerd) para gestión avanzada de tráfico.

---

## 7. Decisiones de Implementación

La elección entre estas herramientas debe basarse en:

1. **Tamaño del equipo**: Equipos pequeños se benefician de Docker Compose o Swarm.
2. **Escala esperada**: Más de 10 microservicios -> Kubernetes.
3. **Requisitos de disponibilidad**: Mission-critical -> Kubernetes.
4. **Experiencia del equipo**: Curva de aprendizaje disponible.
5. **Presupuesto operativo**: K8s requiere más recursos y expertise.
6. **Estrategia cloud**: Multi-cloud -> Kubernetes; single-cloud -> evaluar servicios gestionados.
```
