FROM openjdk:8-jdk-alpine
LABEL maintainer="boehlecj@austin.rr.com"
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=build/libs/cmdb-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} cmdb.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/cmdb.jar"]