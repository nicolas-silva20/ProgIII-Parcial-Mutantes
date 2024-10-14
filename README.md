# Mutant Detector API

Esta API permite detectar secuencias de ADN mutante a través de un conjunto de secuencias de ADN ingresadas. Utiliza una lógica de búsqueda de patrones en las secuencias para determinar si el ADN pertenece a un mutante.

## Instrucciones para Ejecutar el Programa

### Requisitos Previos

- **Java 17**
- **Gradle**
- **Docker** (opcional para la ejecución en contenedores)

### Clonar el Repositorio

```bash
git clone https://github.com/nicolas-silva20/ProgIII-Parcial-Mutantes
cd ProgIII-Parcial-Mutantes
```

### Ejecución Local

Para compilar y ejecutar el proyecto, utiliza el siguiente comando:

```bash
./gradlew bootRun
```

#### Acceso a la API:

- Accede a la API en: [http://localhost:8080](http://localhost:8080)
- Consola H2: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
  - **JDBC URL**: `jdbc:h2:mem:testdb`
  - **Usuario**: `sa`
  - **Contraseña**: *(dejar vacío)*

### Ejecución en Docker

Para ejecutar el proyecto en un contenedor Docker:

1. Construir la imagen de Docker:
   ```bash
   docker build -t mutant .
   ```

2. Ejecutar el contenedor:
   ```bash
   docker run -p 8080:8080 mutant
   ```

#### Acceso a la API:

Accede a la API en: [http://localhost:8080](http://localhost:8080)

### Despliegue en la Nube (Render)

La API está desplegada en la plataforma Render y disponible en la siguiente URL:

- **URL de la API en producción**: [https://progiii-parcial-mutantes.onrender.com](https://progiii-parcial-mutantes.onrender.com)

## Endpoints

### 1. Detectar Mutante

- **URL**: `/mutant`
- **Método**: `POST`
- **Descripción**: Este endpoint recibe una matriz de secuencias de ADN y determina si el ADN es mutante.

#### Ejemplo de Solicitud `POST`:

```json
{
    "dna": [
        "ATGCGA",
        "CAGTGC",
        "TTATGT",
        "AGAAGG",
        "CCCCTA",
        "TCACTG"
    ]
}
```

#### Respuestas

- **Respuesta exitosa (Mutante)**:
  - **Código**: `200 OK`
  - **Cuerpo**:
    ```json
    {
        "isMutant": true,
        "message": "Mutant detected"
    }
    ```

- **Respuesta fallida (No mutante)**:
  - **Código**: `403 Forbidden`
  - **Cuerpo**:
    ```json
    {
        "isMutant": false,
        "message": "Not a mutant"
    }
    ```

### 2. Obtener Estadísticas

- **URL**: `/stats/`
- **Método**: `GET`
- **Descripción**: Este endpoint devuelve estadísticas sobre las secuencias de ADN procesadas.

#### Ejemplo de Solicitud `GET`:

```bash
GET https://progiii-parcial-mutantes.onrender.com/stats/
```

#### Respuesta exitosa

- **Código**: `200 OK`
- **Cuerpo**:
  ```json
  {
      "count_mutant_dna": 10,
      "count_human_dna": 20,
      "ratio": 0.5
  }
  ```
