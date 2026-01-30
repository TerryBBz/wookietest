# WookieTest - Test DokPloy avec Spring Boot + PostgreSQL

## Description

Projet de test pour déployer une application Spring Boot complète avec base de données PostgreSQL sur DokPloy.

Application basée sur WookieeAirlines - un planificateur de missions pour pilotes de chasseurs.

## Technologies

- **Backend**: Spring Boot 3.5.7 + Java 17
- **Frontend**: Thymeleaf templates
- **Database**: PostgreSQL (prod) / H2 (dev)
- **Security**: Spring Security
- **Build**: Maven
- **Deploy**: Docker + DokPloy

## Profils de configuration

### Développement (dev)
- Base de données H2 en mémoire
- Console H2 activée sur `/h2-console`
- Configuration locale simple

### Production (prod)
- Base de données PostgreSQL
- Variables d'environnement pour la DB
- Configuration optimisée

## Variables d'environnement (Production)

```bash
SPRING_PROFILES_ACTIVE=prod
DATABASE_URL=jdbc:postgresql://postgres-host:5432/wookieairlines
DATABASE_USERNAME=wookieuser
DATABASE_PASSWORD=your-secure-password
```

## Déploiement avec DokPloy

1. **Application Spring Boot**
   - Port: 8080
   - Profil: prod
   - Dockerfile multi-stage inclus

2. **Service PostgreSQL**
   - Service DokPloy intégré
   - Base de données: `wookieairlines`
   - Utilisateur: `wookieuser`

3. **Domaine**
   - `wookietest.manabu.fr`
   - SSL automatique avec Let's Encrypt

## Endpoints

- `/` - Page d'accueil
- `/pilots` - Gestion des pilotes
- `/fighters` - Gestion des chasseurs
- `/missions` - Planification des missions
- `/actuator/health` - Health check pour Docker

## Build local

```bash
# Développement avec H2
mvn spring-boot:run

# Production avec PostgreSQL
SPRING_PROFILES_ACTIVE=prod mvn spring-boot:run
```

## Build Docker

```bash
docker build -t wookietest .
docker run -p 8080:8080 -e SPRING_PROFILES_ACTIVE=prod wookietest
```