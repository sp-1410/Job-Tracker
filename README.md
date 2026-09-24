JobTrack – Job Application Tracker

A full-stack recruitment tracking application for organizing and monitoring the complete job application lifecycle.

JobTrack centralizes jobs, companies, applications, online assessments, interviews, resumes, deadlines, and application history into one platform.

**Features**

**Job & Company Management**

Create and manage job opportunities and companies

Track location, work mode, job type, source, salary range, and deadlines

**Application Tracking**

Track applications across recruitment stages

Maintain application dates and notes

Record complete application status history

**Online Assessment Tracking**

Track assessment platform, date, duration, and score

Record results, topics tested, and notes

Support PENDING, PASSED, FAILED, and NOT_KNOWN outcomes

**Interview Tracking**

Manage multiple interview rounds for each application

Record round type, date/time, topics, questions, and outcome

Store interview feedback and preparation notes

**Resume Version Management**

Maintain multiple resume versions

Track target roles and resume-specific notes

Associate the resume used with an application

**Dashboard & Deadline Tracking**

Monitor recruitment progress from a centralized dashboard

Track upcoming application deadlines

View application, assessment, and interview summaries

**Tech Stack**

Layer

Technologies

Backend

Java 21, Spring Boot, Spring Data JPA, Hibernate, Maven

Frontend

Angular, TypeScript, HTML, CSS

Database

PostgreSQL, Supabase

API Testing

Bruno

Architecture

Layered REST API

**Architecture**

The backend follows a layered architecture:

Controller → Service → Repository → JPA/Hibernate → PostgreSQL/Supabase

The Angular frontend communicates with the Spring Boot backend through REST APIs.

Core Domain

Job
 ├── Company
 └── Application
      ├── Status History
      ├── Online Assessments
      ├── Interviews
      └── Resume

**Project Structure**

Job-Tracker/
│
├── backend/
│   ├── src/main/java/com/jobtrack/
│   │   ├── controller/
│   │   ├── service/
│   │   ├── repository/
│   │   └── entity/
│   ├── src/main/resources/
│   └── pom.xml
│
├── frontend/
│   ├── src/app/
│   │   ├── services/
│   │   ├── models/
│   │   ├── app.component.html
│   │   ├── app.component.ts
│   │   └── app.component.css
│   ├── package.json
│   └── angular.json
│
├── bruno/
├── .env.example
├── .gitignore
├── package-lock.json
└── README.md

**Getting Started**

Prerequisites:

Java 21

Maven

Node.js & npm

PostgreSQL or a Supabase project

1. Clone the Repository

git clone https://github.com/sp-1410/Job-Tracker.git
cd Job-Tracker

2. Configure the Database

Set the following environment variables:

DB_URL=your_database_url
DB_USERNAME=your_database_username
DB_PASSWORD=your_database_password

Note: Never commit real database credentials to the repository.

3. Start the Backend

cd backend
mvn spring-boot:run

Backend: http://localhost:8080

4. Start the Frontend

Open a new terminal:

cd frontend
npm install
npm start

Frontend: http://localhost:4200

**API Modules**

The backend exposes REST APIs for:

/api/jobs

/api/companies

/api/applications

/api/interviews

/api/online-assessments

/api/resumes

/api/dashboard

Application status history is available through the application API.

**Current Development**

JobTrack is an ongoing project. Current development focuses on improving dashboard functionality and deadline tracking, with planned extensions for notifications and external job-site integration.

**Future Enhancements**

Job-site/API integration for importing opportunities

Automated deadline and interview notifications

Advanced recruitment analytics

Improved search and filtering

Authentication and user-specific job tracking

