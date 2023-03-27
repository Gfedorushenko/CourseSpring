FROM openjdk:17-alpine
EXPOSE 8080
ADD build/libs/springBack-0.0.1-SNAPSHOT.jar myapp.jar
ENTRYPOINT ["java","-jar","/myapp.jar"]