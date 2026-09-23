# Job Portal Security Module

Basic Spring Boot security module for a Job Portal.

Included:
- Register
- BCrypt password hashing
- Login
- 3 failed login attempts -> 15 minute lock
- Automatic unlock after 15 minutes
- Admin unlock
- ADMIN role protection for /admin/**
- Candidate/Recruiter/Admin roles can be stored in User

This is a starter module to copy into an existing Spring Boot project.
Adjust package names, table names, and your existing User model as needed.

Important: the simple /auth/login endpoint demonstrates the lockout logic. It does not itself create a Spring Security authenticated session. For a production implementation, integrate authentication with AuthenticationManager/UserDetailsService (or JWT) rather than maintaining a separate custom login system.
