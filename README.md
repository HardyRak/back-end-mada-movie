# 🎬 MadaMovie — Back-end API

API REST pour la plateforme MadaMovie, gérant films, abonnements et authentification.

## 🚀 Stack technique
- **Framework** : Spring Boot
- **Langage** : Java
- **Auth** : JWT
- **CI/CD** : GitHub Actions
- **Conteneurisation** : Docker

## ✨ Fonctionnalités
- Authentification JWT (inscription / connexion)
- Gestion des films et catégories
- Système dabonnements et bouquets
- Messagerie interne
- Notifications
- Gestion des utilisateurs

## 📁 Structure
```
src/main/java/
├── controllers/    # Endpoints REST
├── DTO/            # Data Transfer Objects
├── models/         # Entités JPA
├── repositories/   # Spring Data JPA
└── services/       # Logique métier
```

## ▶️ Lancer le projet
```bash
./mvnw spring-boot:run
```

## 🔗 Front-end
Voir [madamovie-front-end](https://github.com/HardyRak/madamovie-front-end) — Angular + Ionic
