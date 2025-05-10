# DocNET - Healthcare Management System

## Overview
DocNET is a comprehensive healthcare management system designed to streamline interactions between patients, doctors, and administrators. The platform provides a centralized solution for appointment scheduling, prescription management, doctor ratings, and office administration.

## Features

### User Management
- **Multi-user Support**: System supports three user types:
    - **Patients**: Can book appointments, view prescriptions, and rate doctors
    - **Doctors**: Can manage their appointments, issue prescriptions, and maintain office information
    - **Administrators**: Can approve doctor registrations and manage system operations

### Appointment Scheduling
- Book, view, and manage appointments
- Appointment status tracking (pending, approved, declined)
- Appointment history and details

### Prescription Management
- Doctors can issue prescriptions to patients
- Patients can view their prescription history
- Complete prescription tracking with issue dates and instructions

### Doctor Rating System
- Patients can rate doctors after appointments
- Rating includes score and written reviews
- Average rating calculation for doctors

### Office Management
- Each doctor has an associated office
- Office details include name, location, operating hours, and account balance
- Office search functionality

## Technical Architecture

# Database Schema
The system uses a relational database with the following main tables:
- user
- doctor
- patient
- admin
- appointment
- prescription
- rating
- office


## Getting Started

### Environment Setup
1. Clone the repository
2. Create a `.env` file in the project root with the following parameters:
   ```
   DB_URL=jdbc:mysql://localhost:3306/docnet
   DB_USER=your_username
   DB_PASSWORD=your_password
   ```

### Database Setup
1. Create a database named `docnet`
2. The system will automatically create the necessary tables on first run

### Registration
1. Select option 2 from the main menu
2. Enter your personal details
3. Select user type (DOCTOR, PATIENT, or ADMIN)
4. Complete registration with role-specific information

### Login
1. Select option 1 from the main menu
2. Enter your email and password

### Patient Features
- Make appointments with doctors
- View all appointments and their status
- View prescriptions issued by doctors
- Rate doctors after appointments
- Search for doctors by specialization

### Doctor Features
- Manage pending appointments
- Issue prescriptions to patients
- View patient information
- Update office details

### Admin Features
- Approve or decline doctor registrations
- Monitor system operations

