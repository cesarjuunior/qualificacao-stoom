FROM openjdk

WORKDIR /app

COPY target/appendereco-0.0.1-SNAPSHOT.jar /app/appendereco.jar

ENTRYPOINT ["java", "-jar", "appendereco.jar"]