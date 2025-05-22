# Imagen base con Java
FROM eclipse-temurin:17-jdk-alpine

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el jar generado (ajusta el nombre si es distinto)
COPY target/*.jar app.jar

# Expone el puerto esperado por Render
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]