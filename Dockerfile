FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/trek-0.0.1-SNAPSHOT.jar trek.jar

# Copy keystore.p12 into the image
# COPY keystore.p12 /etc/ssl/certs/keystore.p12

EXPOSE 8080

ENTRYPOINT ["java","-jar","trek.jar"]

# Adjust the ENTRYPOINT to include SSL configuration
# ENTRYPOINT ["java","-Dserver.ssl.key-store=/etc/ssl/certs/keystore.p12","-Dserver.ssl.key-store-password=jakarta","-Dserver.ssl.keyStoreType=PKCS12","-Dserver.ssl.keyAlias=ale6andria.com","-jar","trek.jar"]
