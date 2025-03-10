# URL Shortener

A simple URL shortener application built with a Vite-based frontend and a Spring Boot backend. The project uses Docker to manage the database.

![image](https://github.com/user-attachments/assets/21052569-9b05-4fb7-95e3-6a1f9c08673e)
![image](https://github.com/user-attachments/assets/20c6a4ac-a237-47c5-a4b3-526c66a8a70e)

## Getting Started

Follow the steps below to set up and run the project.

### Frontend

1. Navigate to the frontend directory (if applicable).
2. Run the following command:
   ```sh
   cd frontend
   npm run dev
   ```

### Backend

1. Ensure you have Docker installed and running.
2. Start the database by running:
   ```sh
   cd backend
   cd docker
   docker compose up
   ```
3. Run the Spring Boot application through your preferred IDE.

## Dependencies

- **Frontend:** Vite, React and Typescript.
- **Backend:** Spring Boot and Java.
- **Database:** MongoDB managed via Docker.
