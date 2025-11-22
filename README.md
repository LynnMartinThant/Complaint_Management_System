The software Architecture for Complaint Management System 

How to run:
1. Install Java 17+ and Maven.
2. From the project root run: mvn spring-boot:run
3. Open http://localhost:8080/complaint-form to submit a complaint.
4. Admin console:
   - Login page: /admin/login
   - Admin user (in-memory): admin / admin123
5. User login (in-memory demo): user@example.com / user123
Notes:
- This starter uses H2 in-memory DB. To persist data, configure datasource in application.properties.
- Passwords are stored in-memory/plaintext for demo only; please add password encoding for production.
