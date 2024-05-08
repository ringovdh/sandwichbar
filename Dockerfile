FROM openjdk:21
COPY /target/sandwichbar-0.0.1-SNAPSHOT.jar /home/sandwichbar-0.0.1-SNAPSHOT.jar
CMD ["java","-jar","/home/sandwichbar-0.0.1-SNAPSHOT.jar"]