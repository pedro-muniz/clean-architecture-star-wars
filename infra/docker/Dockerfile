FROM openjdk:8-alpine

# Required for starting application up.
RUN apk update && apk add bash

RUN mkdir -p /opt/app
ENV PROJECT_HOME /opt/app

COPY starwars-0.0.1-SNAPSHOT.jar $PROJECT_HOME/starwars-0.0.1-SNAPSHOT.jar

WORKDIR $PROJECT_HOME

CMD ["java","-Dspring.profiles.active=docker","-Djava.security.egd=file:/dev/./urandom","-jar","./starwars-0.0.1-SNAPSHOT.jar"]
