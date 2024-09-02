# ECommerceApplication

## DOCKER COMPOSE SETUP FOR POSTGRESQL

1) Create docker-compose file, run "docker-compose up"
2) Image will be created.
3) docker container ls
4) docker inspect <postgrest-id>
5) Copy the IP address
6) Go to localhost:/5050 (PgAdmin)
7) Add new server


This command will reapply the line-ending normalization rules to all files in your repository, 
adjusting line endings to match the rules specified in your .gitattributes file or by your 
core.autocrlf setting.

-> git add --renormalize .
-> git commit -m "Apply LF line endings to Java files"


## SECURITY:
### KeyCloak
We use KeyCloak to secure API Gateway

1) Create a docker-compose file in api-gatewayy service for keycloak
2) Go to localhost://8181
3) Create a Realm
Realm: Ream is a container which contains

## DOCUMENTING REST API - OPEN API
### Swagger
It is a tool which uses open api specification and provides a library to document rest api