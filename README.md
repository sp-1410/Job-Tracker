# JobTrack

JobTrack is a full-stack job application and interview tracking platform.

## Stack

- Angular
- Java 17
- Spring Boot
- Maven
- Spring Data JPA / Hibernate
- PostgreSQL (Supabase)

## Architecture

Angular :4200 -> Spring Boot REST API :8080 -> Supabase PostgreSQL

## Prerequisites

- JDK 17+
- Maven 3.9+ (or IntelliJ Maven)
- Node.js 18+
- Angular CLI

## 1. Configure Supabase

Create a Supabase project and obtain the PostgreSQL connection information.

Set these environment variables before starting the backend:

DB_URL
DB_USERNAME
DB_PASSWORD

Example Windows PowerShell:

$env:DB_URL="jdbc:postgresql://HOST:5432/postgres?sslmode=require"
$env:DB_USERNAME="postgres"
$env:DB_PASSWORD="YOUR_PASSWORD"

Do not commit real credentials.

## 2. Start backend

cd backend
mvn spring-boot:run

Backend: http://localhost:8080

Hibernate will create/update the development tables automatically.

## 3. Start frontend

cd frontend
npm install
ng serve

Frontend: http://localhost:4200

## Main APIs

GET/POST /api/jobs
GET/PUT/DELETE /api/jobs/{id}

GET/POST /api/companies
GET/PUT/DELETE /api/companies/{id}

GET/POST /api/applications
GET/PUT/DELETE /api/applications/{id}
PATCH /api/applications/{id}/status?value=INTERVIEW
GET /api/applications/{id}/history

GET/POST /api/interviews
GET /api/interviews/upcoming

GET/POST /api/resumes

GET /api/dashboard/summary

## DBeaver

Use DBeaver to connect to the same Supabase PostgreSQL database and inspect the tables/data.

## Bruno

Create requests against http://localhost:8080 using the endpoints above.
