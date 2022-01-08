FROM openjdk:14-alpine
COPY build/libs/testutils-*-all.jar testutils.jar
EXPOSE 8080
CMD ["java", "-Dcom.sun.management.jmxremote", "-Xmx128m", "-jar", "testutils.jar"]