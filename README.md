# Sabor Digital

![Java](https://img.shields.io/badge/Java-21-blue)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2.2-green)
![License](https://img.shields.io/badge/License-Apache2-yellow)
![Build](https://img.shields.io/badge/Build-Maven-red)
![OAuth2](https://img.shields.io/badge/Auth-OAuth2-blueviolet)
![Keycloak](https://img.shields.io/badge/SSO-Keycloak-orange)

This project is a **microservices-based system** for managing a restaurant. It provides services for handling **orders, menus, menu items, tables, and waiters**.

## Prerequisites
Ensure you have the following installed:
- *Java 21*
- *Maven 3.x*

---

## Microservices Architecture
| Service         | Description                          | Port  |
|---------------|----------------------------------|------|
| **Order Service** | Handles customer orders | `8081` |
| **Restaurant Service**  | Manages restaurant, menu & menu items | `8082` |
| **Table Service** | Manages table reservations | `8083` |
| **Waiter Service** | Handles waiter assignments | `8084` |

---

## License

This project is licensed under the  Apache 2.0 license - see the LICENSE file for detail
