FROM java:8

COPY /target/*.jar /target/app.jar

CMD ["server port : 8080"]

EXPOSE 8080

ENTRYPOINT ["java","-jar","/target/app.jar"]