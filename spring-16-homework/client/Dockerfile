FROM openjdk:8

EXPOSE 9080

MAINTAINER Zemtsov Alexey Vladimirovich <zemtsov1997@yandex.ru>

WORKDIR /opt/auth-service

ADD target/client-*.jar client.jar

ENTRYPOINT java -jar client.jar