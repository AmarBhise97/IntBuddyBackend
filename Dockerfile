<<<<<<< HEAD
FROM eclipse-temurin:21-jdk AS builder

WORKDIR /app

COPY . .

RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests
=======
FROM eclipse-temurin:21-jre

WORKDIR /app

COPY target/IntBuddy-0.0.1-SNAPSHOT.jar app.jar
>>>>>>> 822ccfa649d886091e1ebc9e723833c0bd3c4886

FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=builder /app/target/IntBuddy-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

<<<<<<< HEAD
ENTRYPOINT ["java","-jar","app.jar"]
=======
ENTRYPOINT ["java","-jar","app.jar"]
>>>>>>> 822ccfa649d886091e1ebc9e723833c0bd3c4886
