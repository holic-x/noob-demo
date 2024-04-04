# Docker 镜像构建
FROM maven:3.8.1-jdk-8-slim as builder

COPY *.jar /app.jar

CMD ["--server.port=8085"]

EXPOSE 8085

ENTRYPOINT ["java","-jar","/app.jar"]
