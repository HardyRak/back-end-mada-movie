# Utiliser une image JDK de base avec la version 19.0.2
FROM openjdk:19-jdk

# Installer Maven
RUN apt-get update && apt-get install -y maven

# Définir le répertoire de travail dans l'image Docker
WORKDIR /app

# Copier le fichier de configuration Maven dans le répertoire de travail
COPY pom.xml .

# Télécharger les dépendances Maven sans construire le projet
RUN mvn dependency:go-offline

# Copier le reste du code source de l'application
COPY src ./src

# Construire l'application
RUN mvn clean package -DskipTests

# Exposer le port sur lequel l'application écoute
EXPOSE 8080

# Définir la commande de démarrage de l'application
ENTRYPOINT ["java", "-jar", "target/hard-0.0.1-SNAPSHOT.jar"]
