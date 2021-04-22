FROM mcr.microsoft.com/java/jre-headless:8-zulu-centos

EXPOSE 5555
COPY /app.jar /app.jar
ENTRYPOINT ["java","-jar","app.jar"]