Zidio Connect

Zidio Connect is a full-stack platform built with Spring Boot MicroServices (backend) and React (frontend).
It allows students and recruiters to connect, manage profiles, post opportunities, apply for jobs/internships ,
 subscription plans , buying subscription, notifications and interact in real time.


---

📂 Project Structure

ZidioProject/
│── zidio-backend/     # Spring Boot backend
│── zidio-frontend/    # React frontend
│── README.md          # Project documentation


---

⚙️ Technologies Used

Backend

Java 17

Spring Boot

Spring Security (JWT Authentication)

MySQL

JPA/Hibernate

Cloudinary (for file uploads)

ActiveMQ (for Notifications)


Frontend

React (Vite)

Axios (API calls)

CSS (styling)

React Router (navigation)


---
🔧 Installation & Setup

1️⃣ Clone the Repository

git clone https://github.com/maheshtelugu01/ZidioProject.git
cd ZidioProject


---

2️⃣ Backend Setup (Spring Boot)

cd zidio-backend
mvn clean install
mvn spring-boot:run

The backend will run on 👉 http://localhost:9090

 How to Run the Project

🖥 Backend (Spring Boot Microservices)

1. Go to zidio-backend/


2. Open the services in Eclipse/IntelliJ


3. Start the servers in this order:

Database & Cloudinary configurations are already available in application.properties.

1.First Run ActiveMQ in your System

2.Config Server 

3.Eureka Server 

4.API Gateway 

---

📌 Other Microservices (start after the above 3 are running)

AuthService04 

NotificationService11 

StudentService05 

RecruiterService06 

JobService07 

InternshipService08 

ApplicationService09

FileUploadService12 

AnalyticsService13 

PaymentService14 

SubscriptionService15 

UserSubscriptionStatusService16 

AdminService10 



---

🎨 Frontend (React App)

1. Go to zidio-frontend/


2. Install dependencies:

npm install


3. Start the app:

npm run dev (Vite)


4. Runs on 👉 http://localhost:5173

🔑 Features

✅ User Authentication (JWT-based)
✅ Role-based Access: Student / Recruiter / Admin
✅ Student & Recruiter Profile Management
✅ Admin Subscription Creation
✅ Post & Apply for Opportunities
✅ Recruiter Accept,Reject Applications
✅ Based on Subscription You can Post/Apply no.of Jobs
✅ Notifications & Messaging System
✅ File Uploads with Cloudinary


