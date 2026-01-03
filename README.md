# ProjectORM

![GitHub repo size](https://img.shields.io/github/repo-size/dfleper/ProjectORM?logo=github)
![GitHub last commit](https://img.shields.io/github/last-commit/dfleper/ProjectORM?color=blue&label=last-commit&logo=github&logoColor=white)

Aplicación Java (Maven) con Hibernate/JPA para gestionar un pequeño dominio de “concesionario” (Clientes, Coches y Revisiones) usando MySQL.  
Pensado para ejecutarse en **VS Code**.

---

## Stack y versiones usadas

Estas versiones salen del `pom.xml` del proyecto:

- **Java (bytecode):** `--release 8` (se genera compatible con Java 8)
- **JDK recomendado:** **JDK 17** (para trabajar cómodo en VS Code)
- **Hibernate:** `org.hibernate:hibernate-core:5.5.3.Final`
- **JPA API:** `javax.persistence:javax.persistence-api:2.2`
- **MySQL Connector/J:** `com.mysql:mysql-connector-j:9.5.0`  
  - Compatible con **JRE 8+** y requiere **MySQL Server 8.0+**. :contentReference[oaicite:0]{index=0}
- **Maven:** 3.x (recomendado 3.8+)

**VS Code**
- VS Code estable (última versión recomendada)
- Extensión: **Extension Pack for Java** (imprescindible)

---

## Requisitos previos

1. **JDK 17 instalado** (o superior)  
   Comprueba:
   ```bash
   java -version

## Requisitos
- JDK 8+ (recomendado JDK 17)
- Maven
- MySQL en `localhost:3306`

## Base de datos
Crear la BD (una vez):

```sql
CREATE DATABASE concesionario CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

Edita `src/main/resources/META-INF/persistence.xml` si tu usuario/contraseña no es `root`/`1234`.

## Ejecutar
Desde la carpeta del proyecto (`proyectORM/`):

```bash
mvn -U clean package
mvn exec:java
```

Alternativa (después de compilar):

```bash
java -jar target/proyectORM-1.0.jar
```

## Nota sobre iconos
Los iconos ahora se cargan desde `src/main/resources/aed/ui/img/` (funciona al clonar y también dentro del JAR).
