FROM eclipse-temurin:21-jdk-jammy

WORKDIR /app

# Instala Maven (requiere actualizar el sistema primero)
RUN apt-get update && apt-get install -y maven

# Copia el POM y los fuentes
COPY pom.xml .
COPY src ./src

# Construye la aplicación
RUN mvn package -DskipTests

# Copia el JAR generado
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]