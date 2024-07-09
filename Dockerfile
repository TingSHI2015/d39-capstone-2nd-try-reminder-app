FROM --platform=linux/amd64 openjdk:22
LABEL authors="ting"
EXPOSE 8080
ADD backend/target/reminderapp.jar reminderapp.jar
ENTRYPOINT ["java", "-jar", "reminderapp.jar"]

