FROM mcr.microsoft.com/java/jre-headless:8-zulu-centos

EXPOSE 5555

COPY docker-entrypoint.sh docker-entrypoint.sh
COPY /app.jar /app/app.jar
RUN chmod a+x docker-entrypoint.sh
ENTRYPOINT ["/docker-entrypoint.sh"]
