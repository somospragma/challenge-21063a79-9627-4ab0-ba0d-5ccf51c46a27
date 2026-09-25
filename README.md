# Implementación de Contenedores y Orquestación en Proyectos Backend

Como desarrollador backend con experiencia en Java, debes implementar la contenerización y orquestación de contenedores en proyectos de arquitecturas distribuidas. Debes considerar las diferencias, ventajas y desventajas de usar Docker, Docker Compose, Docker Swarm y Kubernetes. El objetivo es tener un sistema que contenga y orqueste contenedores de manera eficiente y escalable.

## Informacion General

| Campo | Valor |
|-------|-------|
| **Tema** | Implementa Plataformas de Contenedores enfocados en Docker y Kubernetes |
| **Nivel** | senior-l2 |
| **Tipo** | practical |
| **Tiempo estimado** | 40 horas |

## Fases del Reto

### Fase 0: Configuración del Proyecto

**Objetivo:** Obtener el proyecto base funcional enviando el Código Base a un asistente de IA, que lo analizará, corregirá errores y generará un ZIP listo para usar.

**Tiempo estimado:** 15-30 minutos

**Instrucciones:**

- Asegúrate de tener instalado para ejecutar el proyecto: JDK 17+, Maven 3.9+, IDE con soporte Java.
- Copia todo el contenido del campo **Código Base** de este reto — incluyendo el texto de instrucciones que aparece al inicio.
- Abre un asistente de IA (Claude en claude.ai, ChatGPT o Gemini — se recomienda Claude), pega el contenido copiado en el chat y envíalo.
- El asistente analizará los archivos, corregirá errores y generará un archivo ZIP descargable. Descárgalo y extráelo en la carpeta donde quieras trabajar.
- Ejecuta `mvn compile` en la raíz. Si no hay errores, estás listo.

**Entregable:** El proyecto compila/arranca sin errores.

<details>
<summary>Pistas de conocimiento</summary>

- Copia el Código Base completo incluyendo el texto de instrucciones al inicio — esas instrucciones le indican al asistente exactamente qué hacer con los archivos.
- Si el asistente no genera el ZIP automáticamente al terminar el análisis, escríbele: "genera el ZIP ahora".
- Si el proyecto tiene errores al arrancar, comparte el mensaje de error con el mismo asistente para que lo corrija.

</details>

### Fase 1: Contenedores con Docker

**Objetivo:** Implementar y contenerizar una aplicación backend utilizando Docker.

**Tiempo estimado:** 10 horas

**Instrucciones:**

- Crear un Dockerfile para una aplicación Java.
- Construir una imagen Docker y ejecutar un contenedor.
- Verificar que la aplicación se ejecuta correctamente dentro del contenedor.

**Entregable:** Imagen Docker y contenedor ejecutándose con la aplicación Java.

<details>
<summary>Pistas de conocimiento</summary>

- Estructura de un Dockerfile.
- Comandos básicos de Docker.

</details>

### Fase 2: Orquestación con Docker Compose

**Objetivo:** Configurar y orquestar múltiples contenedores utilizando Docker Compose.

**Tiempo estimado:** 10 horas

**Instrucciones:**

- Crear un archivo docker-compose.yml para definir servicios.
- Ejecutar y verificar la comunicación entre contenedores.
- Asegurar que los servicios se levantan y caen juntos.

**Entregable:** Configuración de Docker Compose funcionando con múltiples contenedores.

<details>
<summary>Pistas de conocimiento</summary>

- Estructura de un archivo docker-compose.yml.
- Dependencias entre servicios.

</details>

### Fase 3: Orquestación con Kubernetes

**Objetivo:** Implementar y orquestar contenedores utilizando Kubernetes.

**Tiempo estimado:** 10 horas

**Instrucciones:**

- Configurar un cluster de Kubernetes.
- Crear y aplicar manifests de Kubernetes para desplegar aplicaciones.
- Verificar que las aplicaciones se ejecutan y escalan correctamente en el cluster.

**Entregable:** Cluster de Kubernetes con aplicaciones desplegadas y funcionando.

<details>
<summary>Pistas de conocimiento</summary>

- Estructura de un manifest de Kubernetes.
- Comandos básicos de kubectl.

</details>

### Fase 4: Comparación y Decisiones

**Objetivo:** Comparar las herramientas de contenerización y orquestación y tomar decisiones basadas en ventajas y desventajas.

**Tiempo estimado:** 10 horas

**Instrucciones:**

- Comparar Docker, Docker Compose, Docker Swarm y Kubernetes en términos de complejidad, escalabilidad y facilidad de uso.
- Tomar decisiones sobre cuándo usar cada herramienta en diferentes escenarios.
- Documentar tus hallazgos y recomendaciones.

**Entregable:** Documento que compara las herramientas y recomienda su uso en diferentes escenarios.

<details>
<summary>Pistas de conocimiento</summary>

- Ventajas y desventajas de cada herramienta.
- Escenarios donde cada herramienta es más adecuada.

</details>

## Dimensiones Evaluadas

- **queEs**: ¿Qué es Docker y cómo se usa para contenerizar aplicaciones?
- **paraQueSirve**: ¿Para qué sirve Docker Compose en la orquestación de contenedores?
- **comoSeUsa**: ¿Cómo se usa Kubernetes para desplegar y escalar aplicaciones en un cluster?
- **erroresComunes**: ¿Cuáles son los errores comunes al configurar Docker y Kubernetes?
- **queDecisionesImplica**: ¿Qué decisiones debes tomar al elegir entre Docker, Docker Compose, Docker Swarm y Kubernetes para un proyecto?

## Criterios de Evaluacion

- Implementar un Dockerfile para una aplicación Java y ejecutar un contenedor.
- Configurar Docker Compose para orquestar múltiples contenedores y verificar la comunicación entre ellos.
- Configurar un cluster de Kubernetes y desplegar aplicaciones utilizando manifests.
- Comparar Docker, Docker Compose, Docker Swarm y Kubernetes y recomendar su uso en diferentes escenarios.

## Como trabajar con un asistente de IA

Hay dos caminos, elegi uno:

- **AGENTS.md** (recomendado) — instrucciones nativas del repo. Abri esta carpeta con tu agente local (Claude Code, Cursor, Codex, Copilot, Gemini) y las carga solo. Sabe que archivos faltan y con que comando se verifica, y completa el scaffold escribiendo en disco.
- **PROMPT_MEJORA.md** — para copiar y pegar en un chat (claude.ai, ChatGPT). Devuelve un ZIP con el proyecto. Sirve si no tenes un agente en el IDE.

Ninguno de los dos resuelve las fases del reto: eso es tu trabajo.

## Verificacion

El proyecto esta listo para trabajar cuando este comando corre sin errores:

```bash
mvn clean compile
```

---

*Reto generado automaticamente por Challenge Generator - Pragma*
