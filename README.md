# Geomax Dating API

Учебный сервис знакомств на **Spring Boot 3** с **JWT‑аутентификацией**, **PostgreSQL** и **Swagger/OpenAPI**.

## Стек
Java 21 · Spring Boot 3 · Spring Security (JWT) · Spring Data JPA · PostgreSQL · Flyway · Docker/Compose · Swagger (springdoc) · JUnit 5

## Быстрый старт (prod, Docker Compose)

### 1. Сборка jar
```bash
./gradlew clean bootJar
```

### 2. Запуск через Docker Compose (production)
```bash
docker compose -f docker-compose.prod.yml up -d --build
```

### 3. Сервисы 
- Swagger UI: http://localhost:8080/swagger-ui/index.html  
- OpenAPI JSON: http://localhost:8080/v3/api-docs  
- Postgres: `localhost:5432` (db: `datingapps`, user: `postgres`, pass: `postgres`)

### 4. Переменные окружения
Задаются в `docker-compose.prod.yml`:
- `SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/datingapps`
- `SPRING_DATASOURCE_USERNAME=postgres`
- `SPRING_DATASOURCE_PASSWORD=postgres`
- `APP_JWT_SECRET=change-me-please-change-me-please-32b`  
  > Используйте секрет длиной ≥ 32 байт (лучше base64).

### 5. Остановка
```bash
docker compose -f docker-compose.prod.yml down
```

---

## Локальный запуск (без Docker)

1) Поднять PostgreSQL:
```bash
docker run --name dating-db   -e POSTGRES_DB=datingapps   -e POSTGRES_USER=postgres   -e POSTGRES_PASSWORD=postgres   -p 5432:5432 -d postgres:15-alpine
```

2) Запустить приложение:
```bash
./gradlew bootRun
```

---

## Быстрая проверка API

### Регистрация
```bash
curl -X POST http://localhost:8080/api/auth/register   -H "Content-Type: application/json"   -d '{"email":"alice@example.com","password":"secret123","name":"Alice"}'
```

### Логин (получение JWT)
```bash
curl -X POST http://localhost:8080/api/auth/login   -H "Content-Type: application/json"   -d '{"email":"alice@example.com","password":"secret123"}'
```

### Пример авторизованного запроса
```bash
TOKEN=<подставьте_JWT_из_логина>
curl -H "Authorization: Bearer $TOKEN" http://localhost:8080/matches
```

---

## Тесты
```bash
./gradlew test
```

---

## Health‑check
```
GET http://localhost:8080/health
```
