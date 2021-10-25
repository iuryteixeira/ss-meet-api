# FROM amazon/aws-cli AS aws

# RUN 

FROM openjdk:17-alpine

ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME

COPY ./build/libs/*.jar ./app.jar

EXPOSE 8080
CMD ["java","-jar","app.jar"]