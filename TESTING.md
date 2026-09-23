# Quick test

Register:
POST /auth/register?username=test01&password=123456&role=USER

Login:
POST /auth/login?username=test01&password=wrong

Repeat the wrong password 3 times.
Expected: Account locked for 15 minutes.

Admin unlock:
POST /admin/users/{id}/unlock

The /admin/** route is restricted to ROLE_ADMIN by SecurityConfig.

Important:
The simple /auth/login endpoint demonstrates the lockout logic but does not create a Spring Security authenticated session. The next upgrade should integrate AuthenticationManager/UserDetailsService (or JWT) so login and role authorization use the same authentication system.
