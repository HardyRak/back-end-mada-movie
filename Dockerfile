# Utiliser une image de base officielle de Java
FROM openjdk:19-jdk

# Ajouter un argument pour définir le nom du fichier jar
ARG JAR_FILE=target/*.jar

# Ajouter l'application Jar au conteneur
COPY ${JAR_FILE} app.jar

# Exposer le port de l'application
EXPOSE 8080

# Exécuter l'application Jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
