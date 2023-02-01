FROM amazoncorretto:11-alpine-jdk
MAINTAINER gl
COPY target/glap-0.0.1-SNAPSHOT.jar  gl-app.jar
ENTRYPOINT ["java","-jar","/gl-app.jar"]
