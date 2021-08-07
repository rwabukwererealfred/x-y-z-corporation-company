FROM openjdk:8
COPY ./target/notification.jar notification.jar
EXPOSE 9090
CMD ["java","-jar","notification.jar"]