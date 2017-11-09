# keycloak-spring-boot-rest-angular-demo
Demo for configuring Keycloak authentication for a spring-boot rest service and AngularJs web client

# Prerequisite

- Keycloack (3.3.0) server installed
- Java (1.8)
- Maven (3.2.2)
- Source code

 
# Setup

1. Clone https://github.com/hosnimed/keycloack-springboot-angular-demo

2. Download **keycloak-3.3.0.Final.zip** (or later version).

Start keycloack and import the realm provided with the source code.

3. Start the rest server:

`mvn spring-boot:run`

4. Start the angular application

`mvn spring-boot:run`

5. Go to [localhost:7005](localhost:7005 "localhost:7005") and login using 'melhosni' and 'password' user/pass.

6. Press reload to create a hello-world request to server.

 

# Configuration


As all three applications run on different domains, we have to configure CORS.

## Enable CORS in rest project

In **keycloak.json** a new line has to be added:

`"enable-cors": true`
Also, **CORSFilter** has to be added in order to provide required headers for XMLHttpRequests.

## AngularJS project configuration

The application must be public. Also, configure the web origin and the matching redirect URL.

## Spring Security Context

 
In some cases we may need to access the user in context. Keycloak configuration is configured at container level, so Spring Security filters cannot be used.

Add security context dependency in order to have **SpringContextHolder** class. Do not use spring-boot-security as  then the web application will be secure by default with ‘basic’ authentication on all HTTP endpoints.

We can add a simple **HttpFilter** which sets the securityContext:

 

# Resources


- AngularJs integration with Keycloak: https://github.com/keycloak/keycloak/tree/master/examples/demo-template/angular-product-app
- Demo for Spring Boot and Keycloak SSO integration: https://github.com/dasniko/keycloak-springboot-demo
- Configuring Keycloak authentication for a spring-boot: https://github.com/iuliazidaru/keycloak-spring-boot-rest-angular-demo